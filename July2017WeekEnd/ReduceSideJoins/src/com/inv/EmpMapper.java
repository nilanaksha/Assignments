package com.inv;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class EmpMapper extends Mapper<LongWritable, Text,Text, Text>{

	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
			
		//Extract join key from emp rec  1,name,2000,001
		String deptId=value.toString().split(",")[3];
			
			//Send join key and the value you want from employee data set
			context.write(new Text(deptId.trim()+"_emp"), value);
	
	}
	
	
}
