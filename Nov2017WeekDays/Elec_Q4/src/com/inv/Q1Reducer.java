package com.inv;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class Q1Reducer extends Reducer<IntWritable, Text, IntWritable, Text>{

	int flag=0;
	IntWritable consumption=new IntWritable(0);
	String years="";
	@Override
	protected void reduce(IntWritable key, Iterable<Text> values,
			Reducer<IntWritable, Text, IntWritable, Text>.Context context) throws IOException, InterruptedException {

		years="";
		if(flag==0){
			for (Text val : values) {
				years=years+" "+val;
			}
			context.write(key, new Text(years));
			flag=1;
		}
	//0,(year1,year)
	//take the last temp
	
		//Overwrite these values 
		consumption=key;
		for (Text val : values) {
			years=years+" "+val;
		}
	
	}
	@Override
	protected void cleanup(Reducer<IntWritable, Text, IntWritable, Text>.Context context)
			throws IOException, InterruptedException {
		context.write(consumption, new Text(years));
	}
}
