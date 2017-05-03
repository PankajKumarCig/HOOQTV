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

public class HOOQ_Web_Sanity_Support extends HOOQWorkflows{
		/*
		 * Verify Login functionality from My Account screen
		 */	 
	 
	ExcelReader xlsLogin = new ExcelReader(configProps.getProperty("WEB_TEST_DATA_SANITY"),"Support_01");
		@Test(dataProvider = "testData",groups={"web"})
		public  void HOOQ_Web_Sanity_Support (String email,String type,String userType,String description) throws Throwable {
			try{
				
				TestEngine.testDescription.put(HtmlReportSupport.tc_name, description);
				//Verify the Support Link
				//Verify Hooq Footer Support Link	
				Reporter.reportStep("Verify Hooq Footer Support Link at Hooq Home Page");
				fnVerifySupportLink(lnkFooterAboutUs,"About Us",By.xpath(".//h1"),"About Us");
				fnVerifySupportLink(lnkFooterFAQ,"FAQ",By.xpath(".//h2"),"Support Center");
				fnVerifySupportLink(lnkFooterPrivacyPolicy,"Privacy Policy",By.xpath(".//h1"),"Privacy Policy");
				fnVerifySupportLink(lnkFoterTermsOfUse,"Terms of Use",By.xpath(".//h1"),"GENERAL TERMS AND CONDITIONS FOR HOOQ");		
				//Login to the Hooq 
				if(userType.equals("Anonymos"))
				{
						Reporter.reportStep("Verify Hooq Home Page from Anonymos User");
					//Navigate to Again Home Page URL
						driver.navigate().to(configProps.getProperty("URL"));
						Thread.sleep(10000);
					//Click on SignUp
						click(HeaderLocators.headerSignUp,"Sign Up");
					//Click on Skip
						click(HeaderLocators.lnkSkip,"Skip");
						Thread.sleep(5000);
						click(HeaderLocators.lnkSkip,"Skip");
						Thread.sleep(10000);
					//Verify Support Options
						Reporter.reportStep("Verify Hooq Footer Support Link for Anonymos User");
						fnVerifySupportLink(lnkFooterAboutUs,"About Us",By.xpath(".//h1"),"About Us");
						fnVerifySupportLink(lnkFooterFAQ,"FAQ",By.xpath(".//h2"),"Support Center");
						fnVerifySupportLink(lnkFooterPrivacyPolicy,"Privacy Policy",By.xpath(".//h1"),"Privacy Policy");
						fnVerifySupportLink(lnkFoterTermsOfUse,"Terms of Use",By.xpath(".//h1"),"GENERAL TERMS AND CONDITIONS FOR HOOQ");		
							
				}
				else
				{						
					//Login to Application
						verifyLoginWithExistingAndInvalidEmails(email,type,userType);
					//Verify Payment Options
						fnVerifySupport();		
					//Logout from Application
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
		    	{"Anonymos","Anonymos","Anonymos","Verifying HOOQ Support scenarios for IN_ANONYMOS_USER"},
		    	{xlsLogin.getCellValue("IN_FREE_USER", "Value"),"valid",xlsLogin.getCellValue("USER_TYPE_FREE", "Value"),"Verifying HOOQ Support for IN_FREE_USER"},
		};
		}
}


