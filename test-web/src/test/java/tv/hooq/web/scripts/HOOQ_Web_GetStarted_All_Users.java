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

public class HOOQ_Web_GetStarted_All_Users extends HOOQWorkflows {
	/*
	 * Verify Login functionality from My Account screen
	 */	 
 
ExcelReader xlsLogin = new ExcelReader(configProps.getProperty("WEB_TEST_DATA"),"PLRST_04");
	@Test(dataProvider = "testData",groups={"web"})
	public  void HOOQ_Web_GetStarted_All_Users (String email,String type,String userType,String description) throws Throwable {
		try{
			
			TestEngine.testDescription.put(HtmlReportSupport.tc_name, description);  
		    if(email.equals("Anonymos")) 
	         {
		    	Reporter.reportStep("Verify GetStarted for Anonymos User");
	        	//Click on SignUp                
		    		click(HeaderLocators.headerSignUp,"Sign Up");          
	            //Click on Skip                
	            	click(HeaderLocators.lnkSkip,"Skip");                
	            	click(HeaderLocators.lnkSkip,"Skip");                
	            	Thread.sleep(20000);    
	                boolean blnFound=isElementDisplayed(HomePageLocators.GetStarted,"Get Started");
	                System.out.println(blnFound);
	                if(blnFound)
	                {
	               	   System.out.println("GetStartedtext Text is Present");
	               	   Reporter.SuccessReport("Verify GetStartedtext Text ", " GetStartedtext Text is Displayed Successfully");
	                }
	                else
	                {
	                	System.out.println("GetStartedtext Text is not Present");
	                	Reporter.failureReportContinue("Verify GetStartedtext Text ", " GetStartedtext Text is not Displayed Successfully");
	                }                       
	            }
	            else if(userType.equals("LAPSED"))
	            {
	            	Reporter.reportStep("Verify GetStarted for LAPSED User");
	            	//Login to Application
						verifyLoginWithExistingAndInvalidEmails(email,type,userType);
						Thread.sleep(20000);
						boolean blnFound=isElementDisplayed(HomePageLocators.GetStarted,"Get Started");
						if(blnFound)
						{
							String strText=getText(HomePageLocators.GetStarted,"Get Started");
							System.out.println(strText);
							if(strText.contentEquals("GET HOOQ! Choose a plan that is right for you."))
							{
								Reporter.failureReportContinue("Verify GetStartedtext Text ", " GetStartedtext Text is Displayed Successfully");
							}
							else
							{
								System.out.println("GetStartedtext Text is not Present");
								Reporter.SuccessReport("Verify GetStartedtext Text ", " GetStartedtext Text is not Displayed Successfully");
							}
						}
						else
						{
							Reporter.SuccessReport("Verify GetStartedtext Text ", " GetStartedtext Text is not Displayed Successfully");
						}
	            } 
	            else
	            {
	            	Reporter.reportStep("Verify GetStarted for "+userType+" User");
	            	//Login to Application
					verifyLoginWithExistingAndInvalidEmails(email,type,userType);
					Thread.sleep(10000);
					List<WebElement> eleList=driver.findElement(By.id("concierge-mount")).findElements(By.tagName("div"));
					System.out.println(eleList.size());					
					if(eleList.size()==0)
					{
						System.out.println("GetStartedtext Text is not Present");
						Reporter.SuccessReport("Verify GetStartedtext Text ", " GetStartedtext Text is not Displayed Successfully");
					}
					else
					{
						System.out.println("GetStartedtext Text is Present");
						Reporter.failureReportContinue("Verify GetStartedtext Text ", " GetStartedtext Text is Displayed Successfully");
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
	    	//{xlsLogin.getCellValue("IN_FREE_USER", "Value"),"valid",xlsLogin.getCellValue("USER_TYPE_FREE", "Value"),"Verifying login scenarios for IN_FREE_USER"},
    		{xlsLogin.getCellValue("IN_LAPSED_USER", "Value"),"valid",xlsLogin.getCellValue("USER_TYPE_LAPSED", "Value"),"Verifying login scenarios for IN_LAPSED_USER"},
    		//{xlsLogin.getCellValue("IN_ACTIVE_USER", "Value"),"valid",xlsLogin.getCellValue("USER_TYPE_ACTIVE", "Value"),"Verifying login scenarios for IN_ACTIVE_USER"},
    		//{xlsLogin.getCellValue("IN_ACTIVE_USER", "Value"),"valid",xlsLogin.getCellValue("USER_TYPE_ACTIVE", "Value"),"Verifying login scenarios for IN_ACTIVERECCURING_USER"},
    		//{"Anonymos","Anonymos","Anonymos","Verifying login scenarios for IN_ANONYMOS_USER"}	    	
	};
	}

}
