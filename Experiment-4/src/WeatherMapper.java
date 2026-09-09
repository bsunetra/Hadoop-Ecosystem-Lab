import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

// Input line: StationID,Date(YYYYMMDD),TempC
// Output: (year, temperature)

public class WeatherMapper extends Mapper<Object, Text, Text, FloatWritable> {

    private Text year = new Text();
    private FloatWritable temp = new FloatWritable();

    @Override
    public void map(Object key, Text value, Context context)
            throws IOException, InterruptedException {

        String line = value.toString().trim();

        if (line.isEmpty())
            return;

        String[] fields = line.split(",");

        // Skip malformed rows
        if (fields.length < 3)
            return;

        try {
            String date = fields[1].trim();      // YYYYMMDD
            String yr = date.substring(0, 4);    // Extract year
            float t = Float.parseFloat(fields[2].trim());

            year.set(yr);
            temp.set(t);

            // Emit (year, temperature)
            context.write(year, temp);

        } catch (Exception e) {
            // Skip rows with invalid dates or temperatures
        }
    }
}
