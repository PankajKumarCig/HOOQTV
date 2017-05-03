package tv.hooq.web.scripts;


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

public class HOOQ_Web_Home_Page_VerifyBanner extends HOOQWorkflows{
		/*
		 * Verify Home Page Screen
		 */	 
	 
	ExcelReader xlsLogin = new ExcelReader(configProps.getProperty("WEB_TEST_DATA"),"Home_02");
		@Test(dataProvider = "testData",groups={"web"})
		public  void HOOQ_Web_Home_Page_VerifyBanner (String email,String type,String userType,String description) throws Throwable {
			try{
				//Define Test Description
					TestEngine.testDescription.put(HtmlReportSupport.tc_name, description);
				//Login to the Hooq 
					if(userType.equals("Anonymos"))
					{
						//Click on SignUp
							click(HeaderLocators.headerSignUp,"Sign Up");
						//Click on Skip
							click(HeaderLocators.lnkSkip,"Skip");
							click(HeaderLocators.lnkSkip,"Skip");
							Thread.sleep(10000);
					}
					else
					{
						//Login to The Hooq
							verifyLoginWithExistingAndInvalidEmails(email,type,userType);
							Thread.sleep(5000);					
					}	
				//Verify The Home Page Screen
					fnVerifyHomePageBannerAfterLogin(type);	
				//Logout
					if(type.equals("valid"))
					{
						logOut();
					}
			
			}catch (Exception e) {
				e.printStackTrace();
				Reporter.failureReport("LogIn", "Failed");
			}
		}		
		
		@DataProvider(name="testData")
		public Object[][] createdata1() {
		    return (Object[][]) new Object[][] { 
		    		{xlsLogin.getCellValue("IN_FREE_USER", "Value"),"valid",xlsLogin.getCellValue("USER_TYPE_FREE", "Value"),"Verifying login scenarios for IN_FREE_USER"},
		    		{xlsLogin.getCellValue("IN_ACTIVE_USER", "Value"),"valid",xlsLogin.getCellValue("USER_TYPE_ACTIVE", "Value"),"Verifying login scenarios for IN_ACTIVE_USER"},
		    		{xlsLogin.getCellValue("IN_LAPSED_USER", "Value"),"valid",xlsLogin.getCellValue("USER_TYPE_LAPSED", "Value"),"Verifying login scenarios for IN_LAPSED_USER"},
		    		{"Anonymos","Anonymos","Anonymos","Verifying login scenarios for IN_ANONYMOS_USER"},
		};
		}
}


