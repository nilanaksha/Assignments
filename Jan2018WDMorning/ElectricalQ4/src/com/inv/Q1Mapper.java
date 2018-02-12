package com.inv;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class Q1Mapper extends Mapper<LongWritable, Text, IntWritable, Text>{
	
	@Override 
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, IntWritable, Text>.Context context)
			throws IOException, InterruptedException {
	    //year,Jan,Feb,Mar,Apr,May,Jun,Jul,Aug,Sep,Oct,Nov,Dec,Avg
		//1979,23,23,2,43,24,25,26,26,26,26,25,26,25
		String vals[]=value.toString().split(",");
		String year=vals[0];
		for (int i = 1; i < vals.length-1; i++) {
			int consumption=Integer.parseInt(vals[i]);
			context.write(new IntWritable(consumption), new Text(year));
		}
		
		
	}
	
	
}








