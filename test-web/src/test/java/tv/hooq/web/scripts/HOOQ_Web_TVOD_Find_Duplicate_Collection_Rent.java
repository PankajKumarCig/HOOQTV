package tv.hooq.web.scripts;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ctaf.accelerators.TestEngine;
import com.ctaf.support.ExcelReader;
import com.ctaf.support.HtmlReportSupport;
import com.ctaf.utilities.Reporter;

import tv.hooq.web.testObjects.HeaderLocators;
import tv.hooq.web.testObjects.TVOD;
import tv.hooq.web.workflows.HOOQWorkflows;

public class HOOQ_Web_TVOD_Find_Duplicate_Collection_Rent extends HOOQWorkflows {
	/*
	 * Verify Login functionality from My Account screen
	 */	 
 
ExcelReader xlsLogin = new ExcelReader(configProps.getProperty("WEB_TEST_DATA"),"Subs_04");
	@Test(dataProvider = "testData",groups={"web"})
	public  void HOOQ_Web_TVOD_Find_Duplicate_Collection_Rent (String email,String type,String userType,String description) throws Throwable {
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
						Reporter.SuccessReport("Verify Collection Under " + strCollection[i], "Collection Found Under " + strCollectionTitle.length);
						//Find the Duplicate Movies
						String[] strDuplicateCollection=fnFindDuplicate(strCollectionTitle);
						System.out.println("Duplicate Details");
						if(strDuplicateCollection==null)
						{
							System.out.println("No Duplicate Collection Found in " + strCollection[i]);
							Reporter.SuccessReport("Verify Duplicate Under " + strCollection[i], "No Duplicate Item Found in " + strCollection[i]);
						}
						else
						{
							System.out.println( strDuplicateCollection.length  + "  Duplicate Collection Found in " + strCollection[i]);
							Reporter.failureReportContinue("Verify Duplicate Under " + strCollection[i], "Duplicate Item Found in " + strDuplicateCollection.length);
							for(int k=0;k<strDuplicateCollection.length;k++)
							{
								System.out.println(strDuplicateCollection[k]);
								Reporter.failureReportContinue("Verify Duplicate Collection Under " + strCollection[i], "Duplicate Collection Found : " + strDuplicateCollection[k]);
							}
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
	    	{xlsLogin.getCellValue("IN_FREE_USER", "Value"),"valid",xlsLogin.getCellValue("USER_TYPE_FREE", "Value"),"Verifying TVOD_Find_Duplicate_Collection_Rent scenarios for IN_FREE_USER"},
    		{xlsLogin.getCellValue("IN_ACTIVE_USER", "Value"),"valid",xlsLogin.getCellValue("USER_TYPE_ACTIVE", "Value"),"Verifying TVOD_Find_Duplicate_Collection_Rent scenarios for IN_ACTIVE_USER"},
    		{xlsLogin.getCellValue("IN_LAPSED_USER", "Value"),"valid",xlsLogin.getCellValue("USER_TYPE_LAPSED", "Value"),"Verifying TVOD_Find_Duplicate_Collection_Rent scenarios for IN_LAPSED_USER"},
    		{"Anonymos","Anonymos","Anonymos","Verifying TVOD_Find_Duplicate_Collection_Rent scenarios for IN_ANONYMOS_USER"},
};
	}

}
