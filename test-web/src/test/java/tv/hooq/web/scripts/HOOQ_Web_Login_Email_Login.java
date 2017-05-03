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

public class HOOQ_Web_Login_Email_Login extends HOOQWorkflows{
		/*
		 * Verify Login functionality from My Account screen
		 */	 
	 
	ExcelReader xlsLogin = new ExcelReader(configProps.getProperty("WEB_TEST_DATA"),"C92");
		@Test(dataProvider = "testData",groups={"web"})
		public  void HOOQ_Web_Login_Email_Login (String email,String type,String userType,String description) throws Throwable {
			try{
				
				TestEngine.testDescription.put(HtmlReportSupport.tc_name, description);				
				verifyLoginWithExistingAndInvalidEmails(email,type,userType);
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
		    		{xlsLogin.getCellValue("IN_EXISTING_USER", "Value"),xlsLogin.getCellValue("VALID_TYPE", "Value"),xlsLogin.getCellValue("USER_TYPE_1", "Value"),"Verifying login scenarios for IN_EXISTING_USER"},
					{xlsLogin.getCellValue("NON_EXISTING_USER", "Value"),xlsLogin.getCellValue("INVALID_TYPE_2", "Value"),xlsLogin.getCellValue("USER_TYPE_1", "Value"),"Verifying login scenarios for NON_EXISTING_USER"},
					{xlsLogin.getCellValue("INCOMPLETE_EMAIL", "Value"),xlsLogin.getCellValue("INVALID_TYPE_1", "Value"),xlsLogin.getCellValue("USER_TYPE_1", "Value"),"Verifying login scenarios for INCOMPLETE_EMAIL"},
					{xlsLogin.getCellValue("INCOMPLETE_EMAIL_WITH_AT", "Value"),xlsLogin.getCellValue("INVALID_TYPE_1", "Value"),xlsLogin.getCellValue("USER_TYPE_1", "Value"),"Verifying login scenarios for INCOMPLETE_EMAIL_WITH_AT"},
					{xlsLogin.getCellValue("INCOMPLETE_EMAIL_WITH_DOT", "Value"),xlsLogin.getCellValue("INVALID_TYPE_1", "Value"),xlsLogin.getCellValue("USER_TYPE_1", "Value"),"Verifying login scenarios for INCOMPLETE_EMAIL_WITH_DOT"},
					{xlsLogin.getCellValue("SPECIAL_CHARACTERS_WITH_DOT_AND_AT", "Value"),xlsLogin.getCellValue("INVALID_TYPE_1", "Value"),xlsLogin.getCellValue("USER_TYPE_1", "Value"),"Verifying login scenarios for SPECIAL_CHARACTERS_WITH_DOT_AND_AT"},
					{xlsLogin.getCellValue("SPECIAL_CHARACTERS_WITH_AT", "Value"),xlsLogin.getCellValue("INVALID_TYPE_1", "Value"),xlsLogin.getCellValue("USER_TYPE_1", "Value"),"Verifying login scenarios for SPECIAL_CHARACTERS_WITH_AT"},
					{xlsLogin.getCellValue("SPECIAL_CHARACTERS_WITH_DOT", "Value"),xlsLogin.getCellValue("INVALID_TYPE_1", "Value"),xlsLogin.getCellValue("USER_TYPE_1", "Value"),"Verifying login scenarios for SPECIAL_CHARACTERS_WITH_DOT"},
					{xlsLogin.getCellValue("SPECIAL_CHARACTERS_WITHOUT_DOT_AND_AT", "Value"),xlsLogin.getCellValue("INVALID_TYPE_1", "Value"),xlsLogin.getCellValue("USER_TYPE_1", "Value"),"Verifying login scenarios for SPECIAL_CHARACTERS_WITHOUT_DOT_AND_AT"},
					{xlsLogin.getCellValue("SPACES_WITH_DOT", "Value"),xlsLogin.getCellValue("INVALID_TYPE_1", "Value"),xlsLogin.getCellValue("USER_TYPE_1", "Value"),"Verifying login scenarios for SPACES_WITH_DOT"},
					{xlsLogin.getCellValue("SPACES_WITH_AT", "Value"),xlsLogin.getCellValue("INVALID_TYPE_1", "Value"),xlsLogin.getCellValue("USER_TYPE_1", "Value"),"Verifying login scenarios for SPACES_WITH_AT"},
					{xlsLogin.getCellValue("SPACES_WITH_DOT_AND_AT", "Value"),xlsLogin.getCellValue("INVALID_TYPE_1", "Value"),xlsLogin.getCellValue("USER_TYPE_1", "Value"),"Verifying login scenarios for SPACES_WITH_DOT_AND_AT"},
					{xlsLogin.getCellValue("SPACES_WITHOUT_DOT_AND_AT", "Value"),xlsLogin.getCellValue("INVALID_TYPE_1", "Value"),xlsLogin.getCellValue("USER_TYPE_1", "Value"),"Verifying login scenarios for SPACES_WITHOUT_DOT_AND_AT"}
					
		};
		}
}


