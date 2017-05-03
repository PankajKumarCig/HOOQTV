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

public class HOOQ_Web_TVOD_Rent_Flag extends HOOQWorkflows {
	/*
	 * Verify Login functionality from My Account screen
	 */	 
 
ExcelReader xlsLogin = new ExcelReader(configProps.getProperty("WEB_TEST_DATA"),"Subs_04");
	@Test(dataProvider = "testData",groups={"web"})
	public  void HOOQ_Web_TVOD_Rent_Flag (String email,String type,String userType,String description,String strRentPrice) throws Throwable {
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
			}
			else
			{
				//Login to The Hooq
					verifyLoginWithExistingAndInvalidEmails(email,type,userType);
					Thread.sleep(5000);					
			}
		//Get the Collection Name
			String[] strCollection=fnGetRentedMovieCollectionName();
			for(int i=0;i<strCollection.length;i++)
			{
				Reporter.reportStep("Verifying Duplicate Collection On Rent Section " +strCollection[i] );
				System.out.println(strCollection[i]);
				boolean blnCollection=fnClickRentedCollection(strCollection[i]);
				System.out.println(strCollection[i] + " ==> " + blnCollection);
				String[] strCollectionTitle=fnGetCollectionList(strCollection[i],"rent");
				if(strCollectionTitle==null)
				{
					System.out.println("No Collection Found under " + strCollection[i]);
					Reporter.failureReportContinue("Verify Collection Under " + strCollection[i], " No Collection Found Under " + strCollection[i]);
				}
				else
				{
					System.out.println("********************************************************");
					System.out.println("Total Collection Found Under " + strCollection[i] + "  ==>  "+ strCollectionTitle.length);
					Reporter.SuccessReport("Verify Collection Under " + strCollection[i], "Collection Found " + strCollectionTitle.length);
					//Get the Count
					List<WebElement> eleFlagList=driver.findElements(By.xpath(".//*[@class='poster rental-callout-flag']"));
					System.out.println(eleFlagList.size());
					if(eleFlagList.size() >0)
					{
						Reporter.SuccessReport("Verify Rental Callout Flag Under " + strCollection[i], "Rental Callout Flag is displayed successfully");
					}
					else
					{
						Reporter.failureReportContinue("Verify Rental Callout Flag Under " + strCollection[i], "Rental Callout Flag is not displayed.");
					}
					List<WebElement> eleRate=driver.findElements(By.xpath(".//*[@class='content-type rental-type']/span[3]/span[4]"));
					boolean blnPrice=true;
					for(int k=0;k<eleRate.size();k++)
					{
						String strAmount=eleRate.get(k).getText();
						if(strAmount.equals(strRentPrice)==false)
						{
							blnPrice=false;
							break;
						}					
					}
					if(blnPrice)
					{
						Reporter.SuccessReport("Verify Rental Price Under " + strCollection[i], "Rental Price "+strRentPrice +"  is displayed successfully");
					}
					else						
					{
						Reporter.failureReportContinue("Verify Rental Price Under " + strCollection[i], "Rental Price "+strRentPrice +"  is not displayed successfully");
					}
				}
			}			
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
	    	{xlsLogin.getCellValue("IN_FREE_USER", "Value"),"valid",xlsLogin.getCellValue("USER_TYPE_FREE", "Value"),"Verifying Rental Callout Flag scenarios for IN_FREE_USER","INR 150"},
    		{xlsLogin.getCellValue("IN_ACTIVE_USER", "Value"),"valid",xlsLogin.getCellValue("USER_TYPE_ACTIVE", "Value"),"Verifying Rental Callout Flag scenarios for IN_ACTIVE_USER","INR 150"},
    		{xlsLogin.getCellValue("IN_LAPSED_USER", "Value"),"valid",xlsLogin.getCellValue("USER_TYPE_LAPSED", "Value"),"Verifying Rental Callout Flag scenarios for IN_LAPSED_USER","INR 150"},
    		{"Anonymos","Anonymos","Anonymos","Verifying Rental Callout Flag scenarios for IN_ANONYMOS_USER","INR 150"},
	};
	}

}
