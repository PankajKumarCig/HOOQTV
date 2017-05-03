package tv.hooq.web.script.sanity;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;

import tv.hooq.web.testObjects.HeaderLocators;
import tv.hooq.web.testObjects.HomePageLocators;
import tv.hooq.web.workflows.HOOQWorkflows;

public class HOOQ_Web_Sanity_Subscription_Checking_Payment_Method extends HOOQWorkflows {

	/*
	 * Verify HOOQ_Subscription_CheckingPaymentPlan functionality for Each User Type
	 */	 
 
ExcelReader xlsLogin = new ExcelReader(configProps.getProperty("WEB_TEST_DATA_SANITY"),"Subs_08");
	@Test(dataProvider = "testData",groups={"web"})
	public  void HOOQ_Web_Sanity_Subscription_Checking_Payment_Method (String email,String type,String userType,String description) throws Throwable {
		try{
			TestEngine.testDescription.put(HtmlReportSupport.tc_name, description);
			//login details
				verifyLoginWithExistingAndInvalidEmails(email,type,userType);
			//Verify Payment Options with 7 Days
				fnVerifyCarrierBilling("7 Days","69",userType);
			//Verify Payment Options with 7 Days
				fnVerifyCashCard("7 Days","69",userType);	
			//Verify Payment Options with 30 Days One Time
				fnVerifyDebitCreditCard("30 Days","199",true,userType,"credit");
			//Verify Payment Options with 30 Days Recurring
				fnVerifyDebitCreditCard("30 Days","199",false,userType,"credit");		
			//Verify Payment Options with 30 Days One Time
				fnVerifyDebitCreditCard("30 Days","199",false,userType,"");		
			//Verify Payment Options with 7 Days
				fnVerifyNetBankingSanity("7 Days","69",userType);	
			//Verify Payment Options with 30 Days Recuring
				fnVerifyPayTM("30 Days","199",true,userType);	
			//Verify Payment Options with 30 Days One Time
				fnVerifyPayTM("30 Days","199",false,userType);	
			//Logout to Hooq
				logOut();			
		}catch (Exception e) {
			e.printStackTrace();
			Reporter.failureReport("Test Subscription Page", "Failed");
		}
	}
	
	@DataProvider(name="testData")
	public Object[][] createdata1() {
	    return (Object[][]) new Object[][] { 
	    	{xlsLogin.getCellValue("IN_FREE_USER", "Value"),"valid",xlsLogin.getCellValue("USER_TYPE_FREE", "Value"),"Verifying HOOQ_Web_Sanity_Subscription_Checking_Payment_Method scenarios for IN_FREE_USER"},
    	};
	}
}
