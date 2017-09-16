package com.inv;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.BZip2Codec;
import org.apache.hadoop.io.compress.GzipCodec;
import org.apache.hadoop.io.compress.Lz4Codec;
import org.apache.hadoop.io.compress.SnappyCodec;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class WordCountDriver {
	
	//mapreduce  i/pfile oplocation
	//args[0] -> i/pfile  args[1] oplocation
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		// all the k,v pairs from default.xmls and site.xmls
		Configuration conf=new Configuration();
		
		conf.set("mapreduce.output.fileoutputformat.compress", "true");
		conf.set("mapreduce.output.fileoutputformat.compress.codec", "org.apache.hadoop.io.compress.GzipCodec");
		
		conf.set("mapreduce.map.output.compress", "true");
		//conf.set("mapreduce.map.output.compress.codec", "org.apache.hadoop.io.compress.GzipCodec");
		
		//an instance of JOb object represents MR program.
		Job job=Job.getInstance(conf, "Word Count Program");
		//Job job1=new Job(conf);
		//job.setJobName("WC Program");
		job.setJarByClass(WordCountDriver.class);
		job.setMapperClass(WordCountMapper.class);
		job.setReducerClass(WordCountReducer.class);
		
		//if job i/p types and job o/p types differ, provide below
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
	//	job.setMapOutputKeyClass(theClass);
	//	job.setMapOutputKeyClass(theClass);
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		
		/* Format formatter = new SimpleDateFormat("YYYY_MM_dd_hh_mm_ss");
		 Date date = new Date();
		 String outfolder=args[1]+"_"+formatter.format(date);
		*/
		 //System.out.println("++++++:::"+outfolder);
		 
		//FileOutputFormat.setOutputPath(job, new Path(outfolder));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		
		//FileOutputFormat.setCompressOutput(job, true);
		//FileOutputFormat.setOutputCompressorClass(job, BZip2Codec.class);
		
		boolean status=job.waitForCompletion(true);
		int result=status?0:1;
		System.exit(result);
	}
	
	
	

}






