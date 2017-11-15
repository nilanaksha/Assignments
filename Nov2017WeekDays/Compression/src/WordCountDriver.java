import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.GzipCodec;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class WordCountDriver extends Configured implements Tool {
		
	@Override
	public int run(String[] arg0) throws Exception {
		//Job object
		
		/*
		 * -Dmapreduce.output.fileoutputformat.compress=true   /home/hadoop/wiki.txt.gz  /home/hadoop/OutFIles/CompOut
		 * 
		 * hadoop jar jarfile mainclass 
-Dmapreduce.output.fileoutputformat.compress=true   /home/hadoop/wiki.txt.gz  /home/hadoop/OutFIles/CompOut
		 * 
		 */
		
		for (int i = 0; i < arg0.length; i++) {
			System.out.println("Arguemnts in RUN are ="+arg0[i]);

		}
				//Configuration conf=new Configuration();
		Configuration conf=this.getConf();
		//conf.set
				Job job=Job.getInstance(conf,"Word Count");
				
				job.setJarByClass(WordCountDriver.class);
				job.setMapperClass(WordCountMapper.class);
				job.setReducerClass(WordCountReducer.class);
				job.setOutputKeyClass(Text.class);
				job.setOutputValueClass(IntWritable.class);
				
				FileInputFormat.addInputPath(job, new Path(arg0[0]));
				FileOutputFormat.setOutputPath(job, new Path(arg0[1]));
				return job.waitForCompletion(true)?0:1;
				
	}
	
	public static void main(String[] args) throws Exception {
		for (int i = 0; i < args.length; i++) {
			System.out.println("Arguemnts in MAIN are ="+args[i]);

		}
		
		int status=ToolRunner.run(new WordCountDriver(), args);
		System.exit(status);
		
	}

	
}
