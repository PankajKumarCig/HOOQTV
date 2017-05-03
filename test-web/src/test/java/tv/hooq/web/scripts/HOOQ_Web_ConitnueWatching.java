package tv.hooq.web.scripts;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;

import tv.hooq.web.testObjects.HomePageLocators;
import tv.hooq.web.workflows.HOOQWorkflows;

public class HOOQ_Web_ConitnueWatching extends HOOQWorkflows {
	/*
	 * Verify Login functionality from My Account screen
	 */	 
 
ExcelReader xlsLogin = new ExcelReader(configProps.getProperty("WEB_TEST_DATA"),"C151");
	@Test(dataProvider = "testData",groups={"web"})
	public  void HOOQ_Web_ConitnueWatching (String email,String type,String userType,String itemToPlay,String description) throws Throwable {
		try{			
				TestEngine.testDescription.put(HtmlReportSupport.tc_name, description);
			//Login to Hooq
				verifyLoginWithExistingAndInvalidEmails(email,type,userType);
			//Verify Continue Watching	
				verifyConitnueWatching(email, type,  userType, itemToPlay);
			//Logout from Hooq
				logOut();		
		}catch (Exception e) {
			e.printStackTrace();
			Reporter.failureReport("LogIn", "Failed");
		}
	}
	@DataProvider(name="testData")
	public Object[][] createData() {
	    return (Object[][]) new Object[][] { 
	    		{xlsLogin.getCellValue("IN_FREE_USER", "Value"),xlsLogin.getCellValue("EMAIL_TYPE", "Value"),xlsLogin.getCellValue("USER_TYPE_FREE", "Value"),xlsLogin.getCellValue("ITEM_TO_PLAY", "Value"),"Verifying ConitnueWatching scenario for IN_FREE_USER"},
	};
	}

}
