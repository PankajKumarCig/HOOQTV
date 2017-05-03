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

public class HOOQ_Web_Subscription_CreditCard extends HOOQWorkflows{
		/*
		 * Verify Hooq_Subscription_NetBanking functionality from My Account screen
		 */	 
	 
	ExcelReader xlsLogin = new ExcelReader(configProps.getProperty("WEB_TEST_DATA"),"Subs_06");
		@Test(dataProvider = "testData",groups={"web"})
		public  void HOOQ_Web_Subscription_CreditCard (String email,String type,String userType,String description) throws Throwable {
			try{
				
				TestEngine.testDescription.put(HtmlReportSupport.tc_name, description);
				//Login to Application
					verifyLoginWithExistingAndInvalidEmails(email,type,userType);
				//Verify Payment Options with 30 Days One Time
					fnVerifyDebitCreditCard("30 Days","199",true,userType,"credit");		
				//Verify Payment Options with 90 Days One Time
					fnVerifyDebitCreditCard("90 Days","490",true,userType,"credit");	
				//Verify Payment Options with 180 Days One Time
					fnVerifyDebitCreditCard("180 Days","900",true,userType,"credit");	
				//Verify Payment Options with 360 Days One Time
					fnVerifyDebitCreditCard("360 Days","1700",true,userType,"credit");		
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
		    	{xlsLogin.getCellValue("IN_FREE_USER", "Value"),"valid",xlsLogin.getCellValue("USER_TYPE_FREE", "Value"),"Verifying Credit Card scenarios for IN_FREE_USER"},
	    		{xlsLogin.getCellValue("IN_ACTIVE_USER", "Value"),"valid",xlsLogin.getCellValue("USER_TYPE_ACTIVE", "Value"),"Verifying Credit Card scenarios for IN_ACTIVE_USER"},
	    		{xlsLogin.getCellValue("IN_LAPSED_USER", "Value"),"valid",xlsLogin.getCellValue("USER_TYPE_LAPSED", "Value"),"Verifying Credit Card scenarios for IN_LAPSED_USER"},	    											
		};
		}
}


