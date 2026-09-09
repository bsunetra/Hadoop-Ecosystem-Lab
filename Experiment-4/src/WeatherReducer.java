import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

// Input:  (year, [temperature, temperature, ...])
// Output: (year, "max=.. min=.. readings=..")

public class WeatherReducer
        extends Reducer<Text, FloatWritable, Text, Text> {

    private Text result = new Text();

    @Override
    public void reduce(Text year, Iterable<FloatWritable> temps, Context context)
            throws IOException, InterruptedException {

        float max = -Float.MAX_VALUE;
        float min = Float.MAX_VALUE;
        int count = 0;

        for (FloatWritable t : temps) {
            float v = t.get();

            if (v > max)
                max = v;

            if (v < min)
                min = v;

            count++;
        }

        result.set(String.format(
                "max=%.1f°C   min=%.1f°C   readings=%d",
                max, min, count
        ));

        context.write(year, result);
    }
}
