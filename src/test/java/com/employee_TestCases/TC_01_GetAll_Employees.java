package com.employee_TestCases;

import org.testng.Assert;
import org.testng.annotations.*;

import com.employee_base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC_01_GetAll_Employees extends TestBase {

	@BeforeClass
	void getAllEmployees() throws InterruptedException
	{
		logger.info("********** Started TC_01 ***********");
		
		RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/employees");
		Thread.sleep(3000);
	}
	
	@Test
	void checkResponseBody() {
		logger.info("********* Checking Response Body ************");
		
		String responseBody = response.getBody().asString();
		logger.info("Response Body ==> "+responseBody);
		Assert.assertTrue(responseBody!=null);
	}
	
	@Test
	void checkStatusCode()
	{
		logger.info("********* Checking Status Code ************");

		int statuscode = response.getStatusCode();
		logger.info("Status Code is ==> "+statuscode);
		Assert.assertEquals(statuscode, 200);
	}
	
	@Test
	void checkResponseTime()
	{
		logger.info("********* Checking Response Time ************");

		int responseTime = (int) response.getTime();
		logger.info("Status Response Time is ==> "+responseTime);
		
		if(responseTime>2000)
		{
			logger.warn("Response Time grater than 2000");
		}
		Assert.assertTrue(responseTime<3000);		
	}
	
	@Test
	void checkStatusLine()
	{
		logger.info("********* Checking Status Line ************");

		String statusline = response.statusLine();
		logger.info("Status Line is ==> "+statusline);
		Assert.assertEquals(statusline, "HTTP/1.1 200 OK");
	}
	
	@Test
	void checkContentType()
	{
		logger.info("********* Checking Content_Type ************");

		String contentType = response.header("Content-Type");
		logger.info("Content Type is ==> "+contentType);
		Assert.assertEquals(contentType, "application/json");
	}
	
	@Test
	void checkServerType()
	{
		logger.info("********* Checking Server Type ************");

		String serverType = response.header("Server");
		logger.info("Server Type is ==> "+serverType);
		Assert.assertEquals(serverType, "nginx/1.21.6");
	}
	
	@AfterClass
	void tearDown()
	{
		logger.info("********** Completed TC_01 ************");
	}
	
}
