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
		//program behavior depends on configuration
		Configuration conf=new Configuration();
		// create Job object
		Job job=Job.getInstance(conf, "Word Count");
		
		job.setMapperClass(WordCountMapper.class);
		job.setReducerClass(WordCountReducer.class);
		job.setJarByClass(WordCountDriver.class);
		//job.setCombinerClass(WordCountReducer.class);
		
		job.setNumReduceTasks(3);
		//when job input and job output types are diff
		// we have set datatypes of job output  key value pairs
		job.setOutputKeyClass(IntWritable.class);
		job.setOutputValueClass(IntWritable.class);
		
		//set the input file location
		FileInputFormat.addInputPath(job, new Path(args[0]));
		//path object converts string into URI
		//FileInputFormat.addInputPath(job, new Path(args[1]));

		FileOutputFormat.setOutputPath(job, new Path(args[1]));
			
		//submit job to cluster
		boolean result=job.waitForCompletion(true);
		int status=result?0:1;
		System.exit(status);
	}

}









