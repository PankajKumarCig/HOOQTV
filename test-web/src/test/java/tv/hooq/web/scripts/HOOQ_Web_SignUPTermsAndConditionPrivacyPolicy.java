package tv.hooq.web.scripts;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;

import tv.hooq.web.testObjects.HeaderLocators;
import tv.hooq.web.workflows.HOOQWorkflows;

public class HOOQ_Web_SignUPTermsAndConditionPrivacyPolicy extends HOOQWorkflows {

	/*
	 *Verify sign up page by clicking on "New to HOOQ? Sign up here!"
	 */	 
 
ExcelReader xlsLogin = new ExcelReader(configProps.getProperty("WEB_TEST_DATA"),"C92");
	@Test(dataProvider = "testData",groups={"web"})
	public  void HOOQ_Web_SignUPTermsAndConditionPrivacyPolicy (String email,String type,String userType,String description) throws Throwable {
		try{
			
			TestEngine.testDescription.put(HtmlReportSupport.tc_name, description);
			//Verify Sign Up Text
				verifySignupHere(false);
				verifySignupHere(true);
				verifyPrivacyPlocy(false);	
				verifyPrivacyPlocy(true);
		}catch (Exception e) {
			e.printStackTrace();
			Reporter.failureReport("LogIn", "Failed");
		}
	}		
	
	@DataProvider(name="testData")
	public Object[][] createdata1() {
	    return (Object[][]) new Object[][] { 
	    		{xlsLogin.getCellValue("IN_EXISTING_USER", "Value"),xlsLogin.getCellValue("VALID_TYPE", "Value"),xlsLogin.getCellValue("USER_TYPE_1", "Value"),"Verifying PRIVACY POLICY and TERMS AND CONDITIONS scenarios for IN_EXISTING_USER"},
	};
	}
	
	
	
}
