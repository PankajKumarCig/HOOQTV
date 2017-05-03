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
import tv.hooq.web.testObjects.TVOD;
import tv.hooq.web.workflows.HOOQWorkflows;

public class HOOQ_Web_TVOD_Rent_Terms_And_Condition extends HOOQWorkflows {
	/*
	 * Verify Login functionality from My Account screen
	 */	 
 
ExcelReader xlsLogin = new ExcelReader(configProps.getProperty("WEB_TEST_DATA"),"TVOD_Rent");
	@Test(dataProvider = "testData",groups={"web"})
	public  void HOOQ_Web_TVOD_Rent_Terms_And_Condition (String email,String type,String userType,String description,boolean blnTicketEnable) throws Throwable {
		try{
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
					//Purchase a Movie Using Ticket								
					fnRentTermsAndCondition(userType,blnTicketEnable);
					
			}
			else
			{
				//Login to The Hooq
					verifyLoginWithExistingAndInvalidEmails(email,type,userType);
					Thread.sleep(5000);		
					//Get Ticket Information
					String strTicket=fnGetTicketCount(blnTicketEnable);				
				//Click on Rent
					JSClick(TVOD.Rent,"Rent Button");	
				//Verify Rental On board Message
					fnVerifyOnBoardMessage(strTicket);				
					fnVerifyRentPage(strTicket);
				//Purchase a Movie Using Ticket								
					fnRentTermsAndCondition(userType,blnTicketEnable);
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
	    	{xlsLogin.getCellValue("IN_FREE_USER", "Value"),"valid",xlsLogin.getCellValue("USER_TYPE_FREE", "Value"),"Verifying TVOD_Rent_With_CC scenarios for IN_FREE_USER",true},
    		{xlsLogin.getCellValue("IN_ACTIVE_USER", "Value"),"valid",xlsLogin.getCellValue("USER_TYPE_ACTIVE", "Value"),"Verifying TVOD_Rent_With_CC scenarios for IN_ACTIVE_USER",false},
    		{xlsLogin.getCellValue("IN_LAPSED_USER", "Value"),"valid",xlsLogin.getCellValue("USER_TYPE_LAPSED", "Value"),"Verifying TVOD_Rent_With_CC scenarios for IN_LAPSED_USER",false},
    		{"Anonymos","Anonymos","Anonymos","Verifying  TVOD_Rent_With_CC scenarios for IN_ANONYMOS_USER",false},
	};
	}

}
