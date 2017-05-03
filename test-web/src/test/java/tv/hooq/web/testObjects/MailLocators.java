package tv.hooq.web.testObjects;

import org.openqa.selenium.By;

import com.ctaf.accelerators.ActionEngine;

public class MailLocators extends ActionEngine {
	
	//Home page 
	
	public static By txtLoginEmail = By.xpath(".//*[@id='login']");
	public static By btnCheckEmail = By.xpath(".//*[@value='Check Inbox']");  
	public static By lblVerificationEmail = By.xpath(".//*[text()='HOOQ - Your verification code'][1]");
	public static By btnConfirmEmail = By.xpath(".//a[text()='Confirm Email']");
	public static By lnkDelete = By.xpath(".//a[@class='igif lmenudelall']");
	public static By lnkDeleteAll = By.xpath(".//a[text()='Empty Inbox']");
	
	
	
	
	
}
