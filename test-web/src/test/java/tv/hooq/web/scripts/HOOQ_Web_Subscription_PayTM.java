package tv.hooq.web.scripts;


import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import tv.hooq.web.testObjects.HomePageLocators;
import tv.hooq.web.testObjects.ContactPageLocators;
import tv.hooq.web.testObjects.HeaderLocators;
import tv.hooq.web.testObjects.MailLocators;
import tv.hooq.web.workflows.HOOQWorkflows;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;

public class HOOQ_Web_Subscription_PayTM extends HOOQWorkflows{
		/*
		 * Verify Hooq_Subscription_NetBanking functionality from My Account screen
		 */	 
	 
	ExcelReader xlsLogin = new ExcelReader(configProps.getProperty("WEB_TEST_DATA"),"Subs_04");
		@Test(dataProvider = "testData",groups={"web"})
		public  void HOOQ_Web_Subscription_PayTM (String email,String type,String userType,String description) throws Throwable {
			try{
				
				TestEngine.testDescription.put(HtmlReportSupport.tc_name, description);
				//Login to Application
					verifyLoginWithExistingAndInvalidEmails(email,type,userType);
				//Verify Payment Options with 7 Days
					fnVerifyPayTM("7 Days","69",false,userType);
				//Verify Payment Options with 30 Days Recuring
					fnVerifyPayTM("30 Days","199",true,userType);	
				//Verify Payment Options with 30 Days One Time
					fnVerifyPayTM("30 Days","199",false,userType);		
				//Verify Payment Options with 90 Days Recuring
					fnVerifyPayTM("90 Days","490",true,userType);	
				//Verify Payment Options with 90 Days One Time
					fnVerifyPayTM("90 Days","490",false,userType);	
				//Verify Payment Options with 180 Days Recuring
					fnVerifyPayTM("180 Days","900",true,userType);	
				//Verify Payment Options with 180 Days One Time
					fnVerifyPayTM("180 Days","900",false,userType);	
				//Verify Payment Options with 360 Days Recuring
					fnVerifyPayTM("360 Days","1700",true,userType);	
				//Verify Payment Options with 360 Days One Time
					fnVerifyPayTM("360 Days","1700",false,userType);		
				//Logout from HOOQ
					logOut();	
			}catch (Exception e) {
				e.printStackTrace();
				Reporter.failureReport("LogIn", "Failed");
			}
		}
				
		@DataProvider(name="testData")
		public Object[][] createdata1() {
		    return (Object[][]) new Object[][] { 
		    	{xlsLogin.getCellValue("IN_FREE_USER", "Value"),"valid",xlsLogin.getCellValue("USER_TYPE_FREE", "Value"),"Verifying PayTM scenarios for IN_FREE_USER"},
	    		{xlsLogin.getCellValue("IN_ACTIVE_USER", "Value"),"valid",xlsLogin.getCellValue("USER_TYPE_ACTIVE", "Value"),"Verifying PayTM scenarios for IN_ACTIVE_USER"},
	    		{xlsLogin.getCellValue("IN_LAPSED_USER", "Value"),"valid",xlsLogin.getCellValue("USER_TYPE_LAPSED", "Value"),"Verifying PayTM scenarios for IN_LAPSED_USER"},	    											
		};
		}
}


