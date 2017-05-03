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
import tv.hooq.web.workflows.HOOQWorkflows;

public class HOOQ_Web_PickYourPlan_ALL_USER extends HOOQWorkflows {
	/*
	 * Verify Login functionality from My Account screen
	 */	 
 
ExcelReader xlsLogin = new ExcelReader(configProps.getProperty("WEB_TEST_DATA"),"PLRST_02");
	@Test(dataProvider = "testData",groups={"web"})
	public  void HOOQ_Web_PickYourPlan_ALL_USER  (String email,String type,String userType,String description) throws Throwable {
		try{
			
			TestEngine.testDescription.put(HtmlReportSupport.tc_name, description);			
			if(userType.equals("LAPSED"))
			{
					Reporter.reportStep("Verifi Pick Your Plan for LAPSED User");
				//Login to HOOQ as Lapsed User
					verifyLoginWithExistingAndInvalidEmails(email,type,userType);
					Thread.sleep(20000);    
				// click(HomePageLocators.PickYourPlan,"Get HOOQ");
					boolean blnFound=isElementDisplayed(HomePageLocators.PickYourPlan,"Get Started");
					System.out.println(blnFound);
					if(blnFound)
					{
						System.out.println("Get HOOQ Text is Present");
						Reporter.SuccessReport("Verify Pick Your Plan", "Pick Your Plan is Displayed Successfully");
					}
					else
					{
						System.out.println("Get HOOQ Text is not Present");
						Reporter.failureReportContinue("Verify Pick Your Plan", "Pick Your Plan is not Displayed Successfully");
					}
				//Logout From Hooq
					logOut();
			}			
			else if(email.equals("Anonymos")) 
	        {
					Reporter.reportStep("Verifi Pick Your Plan for Anonymos User");
	        	//Click on SignUp                
	        		click(HeaderLocators.headerSignUp,"Sign Up");          
	        	//Click on Skip                
	        		click(HeaderLocators.lnkSkip,"Skip");                
	            	click(HeaderLocators.lnkSkip,"Skip");                
	            	Thread.sleep(20000);    
	                boolean blnFound=isElementDisplayed(HomePageLocators.PickYourPlan,"Get HOOQ");
	                if(blnFound)
	                {
	                 	   String strText=getText(HomePageLocators.PickYourPlan,"Get HOOQ");
	                 	   System.out.println(strText);
	                 	   if(strText.contentEquals("GET STARTED")==false)
	                 	   {
	                 		  Reporter.failureReportContinue("Verify Pick Your Plan", "Pick Your Plan is Displayed Successfully");
	                 	   }
	                 	   else
	                 	   { 
	                 		   System.out.println("Get HOOQ! Text is not Present");
	                 		  Reporter.SuccessReport("Verify Pick Your Plan", "Pick Your Plan is not Displayed.");
	                 	   }
	                }
			}
			else
	        {
					Reporter.reportStep("Verifi Pick Your Plan for " + userType + " User");
	           	//Login to Application
					verifyLoginWithExistingAndInvalidEmails(email,type,userType);
					Thread.sleep(10000);
					List<WebElement> eleList=driver.findElement(By.id("concierge-mount")).findElements(By.tagName("div"));
					System.out.println(eleList.size());					
					if(eleList.size()==0)
					{
						System.out.println("Get HOOQ! Text is not Present");
						Reporter.SuccessReport("Verify Pick Your Plan", "Pick Your Plan is not Displayed.");
					}
					else
					{
						System.out.println("Get HOOQ! Text is Present");
						Reporter.failureReportContinue("Verify Pick Your Plan", "Pick Your Plan is Displayed Successfully");
					}					
	        }				
		}
			catch (Exception e) {
			e.printStackTrace();
			Reporter.failureReport("LogIn", "Failed");
		}
	}	
	@DataProvider(name="testData")
	public Object[][] createData() {
	    return (Object[][]) new Object[][] { 
	    		{xlsLogin.getCellValue("IN_FREE_USER", "Value"),"valid",xlsLogin.getCellValue("USER_TYPE_FREE", "Value"),"Verifying Pick Your Plan scenarios for IN_FREE_USER"},
	    		{xlsLogin.getCellValue("IN_LAPSED_USER", "Value"),"valid",xlsLogin.getCellValue("USER_TYPE_LAPSED", "Value"),"Verifying Pick Your Plan scenarios for IN_LAPSED_USER"},
	    		{xlsLogin.getCellValue("IN_ACTIVE_USER", "Value"),"valid",xlsLogin.getCellValue("USER_TYPE_ACTIVE", "Value"),"Verifying Pick Your Plan scenarios for IN_ACTIVERECCURING_USER"},
	    		{xlsLogin.getCellValue("IN_ACTIVE_USER", "Value"),"valid",xlsLogin.getCellValue("USER_TYPE_ACTIVE", "Value"),"Verifying Pick Your Plan scenarios for IN_ACTIVE_USER"},
	    		{"Anonymos","Anonymos","Anonymos","Verifying Pick Your Plan  scenarios for IN_ANONYMOS_USER"}	 
	    		
	};
	}

}
