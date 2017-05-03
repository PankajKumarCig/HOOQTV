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

public class HOOQ_Web_Login_Email_InvalidMobile_No extends HOOQWorkflows{
		/*
		 * Verify Login functionality from My Account screen
		 */	 
	 
	ExcelReader xlsLogin = new ExcelReader(configProps.getProperty("WEB_TEST_DATA"),"C92");
		@Test(dataProvider = "testData",groups={"web"})
		public  void HOOQ_Web_Login_Email_InvalidMobile_No (String email,String type,String userType,String description) throws Throwable {
			try{			
				TestEngine.testDescription.put(HtmlReportSupport.tc_name, description);
				//Verify with Invalid Mobile Number
				verifyLoginWithInvalidMobileNo();		
			}catch (Exception e) {
				e.printStackTrace();
				Reporter.failureReport("LogIn", "Failed");
			}
		}		
		
		@DataProvider(name="testData")
		public Object[][] createdata1() {
		    return (Object[][]) new Object[][] { 
		    		{xlsLogin.getCellValue("IN_EXISTING_USER", "Value"),xlsLogin.getCellValue("VALID_TYPE", "Value"),xlsLogin.getCellValue("USER_TYPE_1", "Value"),"Verifying login Invalid Mobile No scenarios for"},
				};
		}
}


