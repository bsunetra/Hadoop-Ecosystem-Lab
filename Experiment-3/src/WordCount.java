import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordCount {

    // ---------- MAP PHASE ----------
    // Input: (offset, line of text)
    // Output: (word, 1)

    public static class TokenizerMapper
            extends Mapper<Object, Text, Text, IntWritable> {

        private final static IntWritable one = new IntWritable(1);
        private Text word = new Text();

        @Override
        public void map(Object key, Text value, Context context)
                throws IOException, InterruptedException {

            StringTokenizer itr =
                    new StringTokenizer(value.toString());

            while (itr.hasMoreTokens()) {

                // Convert to lowercase and remove punctuation
                String token = itr.nextToken()
                        .toLowerCase()
                        .replaceAll("[^a-z0-9]", "");

                if (!token.isEmpty()) {
                    word.set(token);

                    // Emit (word, 1)
                    context.write(word, one);
                }
            }
        }
    }


    // ---------- SHUFFLE & SORT ----------
    // Hadoop automatically groups values
    // belonging to the same key.
    //
    // Example:
    // word -> [1,1,1,...]


    // ---------- REDUCE PHASE ----------
    // Input:  (word, [1,1,1,...])
    // Output: (word, total_count)

    public static class IntSumReducer
            extends Reducer<Text, IntWritable, Text, IntWritable> {

        private IntWritable result = new IntWritable();

        @Override
        public void reduce(Text key,
                           Iterable<IntWritable> values,
                           Context context)
                throws IOException, InterruptedException {

            int sum = 0;

            for (IntWritable val : values) {
                sum += val.get();
            }

            result.set(sum);

            // Emit (word, sum)
            context.write(key, result);
        }
    }


    // ---------- DRIVER ----------

    public static void main(String[] args) throws Exception {

        if (args.length != 2) {
            System.err.println(
                    "Usage: WordCount <input path> <output path>"
            );
            System.exit(-1);
        }

        Configuration conf = new Configuration();

        Job job = Job.getInstance(conf, "word count");

        job.setJarByClass(WordCount.class);

        // Mapper
        job.setMapperClass(TokenizerMapper.class);

        // Combiner
        // Performs a local mini-reduce to reduce
        // network traffic.
        job.setCombinerClass(IntSumReducer.class);

        // Reducer
        job.setReducerClass(IntSumReducer.class);

        // Output types
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        // Input and output paths
        FileInputFormat.addInputPath(
                job,
                new Path(args[0])
        );

        FileOutputFormat.setOutputPath(
                job,
                new Path(args[1])
        );

        // Run the job
        System.exit(
                job.waitForCompletion(true) ? 0 : 1
        );
    }
}
