package com.employee_TestCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employee_Utilities.RestUtils;
import com.employee_base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC_03_Post_EmployeeRecord extends TestBase {
	
	String empName= RestUtils.empName();
	String empSal= RestUtils.empSal();
	String empAge= RestUtils.empAge();
	
	@SuppressWarnings("unchecked")
	@BeforeClass
	void CreateEmployee() throws InterruptedException
	{
		logger.info("********** Started TC_03_Post_EmployeeRecord ***********");
		
		RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1"; //set base URI...
		httpRequest = RestAssured.given(); //Req... object
		
		// Request payload sending along with POST Req...
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", empName);
		requestParams.put("salary", empSal);
		requestParams.put("age", empAge);
		
		httpRequest.header("Content-Type", "application/json");

		httpRequest.body(requestParams.toJSONString()); //Attaching above data to this Req...
		
		response = httpRequest.request(Method.POST,"/create");
		Thread.sleep(5000);
	}
	
	@Test
	void checkResponseBody() {
		logger.info("********* Checking Response Body ************");
		
		String responseBody = response.getBody().asString();
		logger.info("Response Body ==> "+responseBody);
		Assert.assertEquals(responseBody.contains(empName), true);
		Assert.assertEquals(responseBody.contains(empSal), true);
		Assert.assertEquals(responseBody.contains(empAge), true);
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
		Assert.assertEquals(serverType, "Apache");
	}
	
	@AfterClass
	void tearDown()
	{
		logger.info("********** Completed TC_03_Post_EmployeeRecord ************");
	}

}
