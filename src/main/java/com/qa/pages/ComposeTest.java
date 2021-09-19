package com.qa.pages;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ComposeTest {

	@Test
	public void startup() throws IOException, InterruptedException {
		
		
		File fs = new File(System.getProperty("user.dir")+"\\outputlog.txt");
		if(fs.delete()){
			
			System.out.println("Log file deleted");
		
		Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\dockerSetup.bat");
		File fs1 = new File(System.getProperty("user.dir")+"\\outputlog.txt");
		int counter = 0;
		while(!fs1.exists()) {
			System.out.println("wait"+counter++);
			Thread.sleep(5000);
		}
		
		
		boolean flag=false;
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.SECOND, 30);
		long stoptime = cal.getTimeInMillis();
		
		while(System.currentTimeMillis()<stoptime) {
			
			BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir")+"\\outputlog.txt"));
			
			String fline = br.readLine();
		while(fline!=null) {
			
			if(fline.contains("The node is registered to the hub and ready to use")){
				flag = true;
			
				break;
				
				
			}
			else {
				
				fline = br.readLine();
			}
					
		}
				
		  if(flag) 
			  break;
		  
		 
		br.close();
		}
	
		Assert.assertTrue(flag);
		
	}
	}
	
}
