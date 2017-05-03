package tv.hooq.web.scripts;

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

public class HOOQ_Web_Subscription_CheckingPaymentPlan extends HOOQWorkflows {

	/*
	 * Verify HOOQ_Subscription_CheckingPaymentPlan functionality for Each User Type
	 */	 
 
ExcelReader xlsLogin = new ExcelReader(configProps.getProperty("WEB_TEST_DATA"),"Subs_08");
	@Test(dataProvider = "testData",groups={"web"})
	public  void HOOQ_Web_Subscription_CheckingPaymentPlan (String email,String type,String userType,String details7Days,String details30Days,String details90Days,String details180Days,String details360Days, String description) throws Throwable {
		try{
			TestEngine.testDescription.put(HtmlReportSupport.tc_name, description);
			//login details
				verifyLoginWithExistingAndInvalidEmails(email,type,userType);
			//Navigate to Subscription
				navigateToSubscription();
			//Click on Manage	
				click(HeaderLocators.btnPlanPageManage,"Manage");
			//Verify Payment Methods
				verifyPaymentMethodsForAllPlans(details7Days,details30Days,details90Days,details180Days,details360Days);
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
	    		{
	    			xlsLogin.getCellValue("IN_LAPSED_USER", "Value"),xlsLogin.getCellValue("EMAIL_TYPE", "Value"),xlsLogin.getCellValue("USER_TYPE_LAPSED", "Value"),xlsLogin.getCellValue("IN_7_DAYS_PAYMENT_DETAILS", "Value"),
	    			xlsLogin.getCellValue("IN_30_DAYS_PAYMENT_DETAILS","Value"),xlsLogin.getCellValue("IN_90_DAYS_PAYMENT_DETAILS","Value"),xlsLogin.getCellValue("IN_180_DAYS_PAYMENT_DETAILS","Value"),xlsLogin.getCellValue("IN_360_DAYS_PAYMENT_DETAILS","Value"),"Checking Payment Methods for: IN LAPSED USER"
	    		},
	    		{
	    			xlsLogin.getCellValue("IN_ACTIVE_USER", "Value"),xlsLogin.getCellValue("EMAIL_TYPE", "Value"),xlsLogin.getCellValue("USER_TYPE_ACTIVE", "Value"),xlsLogin.getCellValue("IN_7_DAYS_PAYMENT_DETAILS", "Value"),
	    			xlsLogin.getCellValue("IN_30_DAYS_PAYMENT_DETAILS","Value"),xlsLogin.getCellValue("IN_90_DAYS_PAYMENT_DETAILS","Value"),xlsLogin.getCellValue("IN_180_DAYS_PAYMENT_DETAILS","Value"),xlsLogin.getCellValue("IN_360_DAYS_PAYMENT_DETAILS","Value"),"Checking Payment Methods for: IN ACTIVE USER"
	    		},
	    		{
	    			xlsLogin.getCellValue("IN_FREE_USER", "Value"),xlsLogin.getCellValue("EMAIL_TYPE", "Value"),xlsLogin.getCellValue("USER_TYPE_FREE", "Value"),xlsLogin.getCellValue("IN_7_DAYS_PAYMENT_DETAILS", "Value"),
	    			xlsLogin.getCellValue("IN_30_DAYS_PAYMENT_DETAILS","Value"),xlsLogin.getCellValue("IN_90_DAYS_PAYMENT_DETAILS","Value"),xlsLogin.getCellValue("IN_180_DAYS_PAYMENT_DETAILS","Value"),xlsLogin.getCellValue("IN_360_DAYS_PAYMENT_DETAILS","Value"),"Checking Payment Methods for: IN FREE USER"
	    		}

	    		};
	}
}
