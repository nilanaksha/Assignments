package com.inv;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class WordCountDriver {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		
		Integer i=100;
		System.out.println("Hash code of hi:::"+ "hi".hashCode());
		System.out.println("Hash code of 100:::"+ i.hashCode());
		System.out.println("Hash code of 100 as String:::"+ "100".hashCode());


		
		Configuration conf=new Configuration();
		Job job=Job.getInstance(conf, "Word Count Program");
		job.setJarByClass(WordCountDriver.class);
		job.setMapperClass(WordCountMapper.class);
		job.setReducerClass(WordCountReducer.class);
		job.setPartitionerClass(CustomPartitioner.class);
		job.setNumReduceTasks(27);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		boolean status=job.waitForCompletion(true);
		int result=status?0:1;
		System.exit(result);
	}

}






