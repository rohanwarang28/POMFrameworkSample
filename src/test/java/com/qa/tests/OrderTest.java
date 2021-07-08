package com.qa.tests;

import org.testng.annotations.Test;

public class OrderTest {

	@Test(priority = 0)
	public void AAWorld() {
		
		System.out.println("inside AA");
	}
	
	@Test(priority=-1)
	public void BBWorld() {
		
		System.out.println("inside BB");
	}
	
	
	@Test()
	public void CCWorld() {
		
		System.out.println("inside CC");
	}
	/*
	 * @Test(priority = 0) public void DDWorld() {
	 * 
	 * System.out.println("inside DD"); }
	 */
}
