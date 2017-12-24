package com.inv;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordCountDriver {
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		// Jarfile entryclass inputfile outputlocation
		//args[0] input loc
		//args[1] output loc
		
		//JOB object depends configuration 
		//Get configuration object
		Configuration conf = new Configuration();
		Job job=Job.getInstance(conf, "WordCount Inventateq");
		//Job object represents MR job
		job.setJarByClass(WordCountDriver.class); //Entry point of job
		job.setMapperClass(WordCountMapper.class);
		job.setReducerClass(WordCountReducer.class);
		
		//job input types  and job output types different 
		//below lines need to be mentioned
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		boolean status=job.waitForCompletion(true);		
		int result=status?0:1;
		
		System.exit(result);
		
		
	}

}
