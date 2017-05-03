package tv.hooq.web.script.sanity;


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

public class HOOQ_Web_Sanity_Search_ContentDetails extends HOOQWorkflows{
		/*
		 * Verify Login functionality from My Account screen
		 */	 
	 
	ExcelReader xlsLogin = new ExcelReader(configProps.getProperty("WEB_TEST_DATA_SANITY"),"Search_01");
		@Test(dataProvider = "testData",groups={"web"})
		public  void HOOQ_Web_Sanity_Search_ContentDetails (String email,String type,String userType,String description) throws Throwable {
			try{
				//Define Test Description
				TestEngine.testDescription.put(HtmlReportSupport.tc_name, description);
			//Login to the Hooq 
				if(userType.equals("Anonymos"))
				{
						Thread.sleep(5000);
					//Click on SignUp
						click(HeaderLocators.headerSignUp,"Sign Up");
						Thread.sleep(5000);
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
				//Search the Invalid Content
					fnSearchContent("xsdfgghfhfhf","invalid");
				//Search the Movies	
					fnSearchContent("Duplex","Movie");
				//Search the TV Series	
					fnSearchContent("Arrow","TV Series");
				//Search the Rental Content*/
					fnSearchContent("The Great Gatsby","Rent");				
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
		    	{xlsLogin.getCellValue("IN_FREE_USER", "Value"),"valid",xlsLogin.getCellValue("USER_TYPE_FREE", "Value"),"Verifying Search and Content Details scenarios for IN_FREE_USER"},
	    		{"Anonymos","Anonymos","Anonymos","Verifying Search and Content Details scenarios for IN_ANONYMOS_USER"}
		};
		}
}


