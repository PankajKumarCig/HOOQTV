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

import tv.hooq.web.testObjects.HeaderLocators;
import tv.hooq.web.testObjects.HomePageLocators;
import tv.hooq.web.testObjects.TVOD;
import tv.hooq.web.workflows.HOOQWorkflows;

public class HOOQ_Web_Filter extends HOOQWorkflows {
	/*
	 * Verify Login functionality from My Account screen
	 */	 
 
ExcelReader xlsLogin = new ExcelReader(configProps.getProperty("WEB_TEST_DATA"),"Subs_04");
	@Test(dataProvider = "testData",groups={"web"})
	public  void HOOQ_Web_Filter (String email,String type,String userType,String description) throws Throwable {
		try{
			
			TestEngine.testDescription.put(HtmlReportSupport.tc_name, description);
			//Login to the Hooq 
			if(userType.equals("Anonymos"))
			{
				//Click on SignUp
					click(HeaderLocators.headerSignUp,"Sign Up");
				//Click on Skip
					click(HeaderLocators.lnkSkip,"Skip");
					Thread.sleep(5000);
					click(HeaderLocators.lnkSkip,"Skip");
					Thread.sleep(10000);
			}
			else
			{
				//Login to The Hooq
					verifyLoginWithExistingAndInvalidEmails(email,type,userType);
					Thread.sleep(5000);					
			}
			//Verify Genre Filter
				fnVerifyBrowseFilterGenre(userType);	
				fnVerifyBrowseFilterSubTitles(userType);
				fnVerifyRentFilterLanguage(userType);
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
	public Object[][] createData() {
	    return (Object[][]) new Object[][] { 
	    	{xlsLogin.getCellValue("IN_FREE_USER", "Value"),"valid",xlsLogin.getCellValue("USER_TYPE_FREE", "Value"),"Verifying Find_Duplicate_Collection_Browse scenarios for IN_FREE_USER"},
    		{xlsLogin.getCellValue("IN_ACTIVE_USER", "Value"),"valid",xlsLogin.getCellValue("USER_TYPE_ACTIVE", "Value"),"Verifying Find_Duplicate_Collection_Browse scenarios for IN_ACTIVE_USER"},
    		{xlsLogin.getCellValue("IN_LAPSED_USER", "Value"),"valid",xlsLogin.getCellValue("USER_TYPE_LAPSED", "Value"),"Verifying Find_Duplicate_Collection_Browse scenarios for IN_LAPSED_USER"},
    		{"Anonymos","Anonymos","Anonymos","Verifying Find_Duplicate_Collection_Browse scenarios for IN_ANONYMOS_USER"},
	};
	}

}
