package com.juvenxu.mvnbook.helloworld;

import static org.junit.Assert.*;

import org.junit.Test;

public class HelloWorldTest {
	@Test
   public void testSayHello(){
	   HelloWorld helloWorld =new HelloWorld();
	   String result=helloWorld.sayHello();
	   assertEquals("hello maven",result);
   }
}
