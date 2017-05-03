/**
 * com.ctaf is a group of Selenium accelerators  
 */
package com.ctaf.accelerators;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.ctaf.utilities.Reporter;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.TouchShortcuts;
import io.appium.java_client.ios.IOSDriver;

/**
 *  ActionEngine is a wrapper class of Selenium actions
 */
@SuppressWarnings("deprecation")
public class ActionEngine extends TestEngine {
	public static WebDriverWait wait;

	static boolean b = true; // /Boolean.parseBoolean(bool);

	// public static boolean flag=false;

	/**
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:Login Button, SignIn Link
	 *            etc..)
	 * @return --boolean (true or false)
	 * @throws Throwable
	 */
	
	
	public static boolean click(By locator, String locatorName)
			throws Throwable {
		boolean flag = false;
		try {
			waitForElementPresent(locator,locatorName);
			waitForElementClickable(locator,locatorName);
			driver.findElement(locator).click();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (!flag) {
				Reporter.failureReport("Click", "<b>" +locatorName + "</b>" + " was "+"<b>"+"NOT"+"</b>"+" clicked"
						);
				return flag;
			} else if (b && flag) {
				Reporter.SuccessReport("Click", "Clicked on " +"<b>" +locatorName + "</b>" 
						);
				logger.info("Clicked on "+locatorName);
			}
		}
		return flag;
	}


	
	/**
	 * This method returns check existence of element
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:Textbox, checkbox etc)
	 * @return: Boolean value(True or False)
	 * @throws NoSuchElementException
	 */
	public static boolean isElementPresent(By by, String locatorName)
			throws Throwable {
		boolean flag = false;
		try {
			driver.findElement(by);
			flag = true;
			return true;
		} catch (Exception e) {
			Assert.assertTrue(flag,"Element: <b>"+ locatorName+"</b> is <b>NOT</b> present on the page ");
			e.printStackTrace();
			return false;
		} finally {
			if (!flag) {
				Reporter.failureReport("Is Element Present ", locatorName
						+ "Element: <b>"+ locatorName+"</b> is <b>NOT</b> present on the page ");
				Assert.assertTrue(flag,"Element: <b>"+ locatorName+"</b> is <b>NOT</b> present on the page ");
			} else if (b && flag) {
				Reporter.SuccessReport("IsElementPresent ",
						"Able to locate element <b>" + locatorName + "</b>");
			}

		}
	}
    public static boolean isElementDisplayedTemp(WebElement we)
    throws Throwable {
        boolean flag = false;
        try {
            driver.manage().timeouts().implicitlyWait(50, TimeUnit.MILLISECONDS);
            flag  = we.isDisplayed();
            if(flag){
                System.out.println("found the element ");
            }
        } catch (Exception e) {
            return false;
        }
        return flag;
    }
    
    public static boolean verifyElementDisplayed(By by, String elementName) throws Throwable{
        boolean displayFlag = false;
        try {
	        if(isElementDisplayed(by)){
	        	displayFlag = true;
	        	Reporter.SuccessReport("Verify Element is displayed","<b>" + elementName + "</b>"  + " is displayed");
	        }else{
	        	
	        	Reporter.failureReportContinue("Verify Element is displayed","<b>" + elementName + "</b>" + " is NOT displayed");
	        }
        }
        catch (Exception e) {
			e.printStackTrace();
        }
		return displayFlag;
        
    }
    

	public static boolean scrollToText(final String text)
			throws Throwable {
		boolean flag = false;
		try {
			if(browser.toLowerCase().contains("iphone")){
				Iosdriver.scrollTo(text);
			}else if(browser.toLowerCase().contains("android")){
				AndroidDriver2.scrollTo(text);
			}
			flag = true;
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		} /*finally {
			if (!flag) {
				Reporter.failureReport("Check IsElementPresent ", locatorName
						+ " Element is not present on the page");
				Assert.assertTrue(flag,"Unable find the element "+ locatorName);
			} else if (b && flag) {
				Reporter.SuccessReport("IsElementPresent ",
						"Able to locate element " + locatorName);
			}
		}*/
	}
	public static boolean scrollToElement(By locator)
    throws Throwable {
        boolean flag = false;
        WebElement we1 = null;
        try {
            if(!(isElementDisplayedTemp(locator))){
                try
                {
                    for(int i=1;(!(isElementDisplayedTemp(locator)))|(i<300);i=i+1){
                        
                        /*if((i==1)){
                         //we1 = driver.findElement(By.xpath("//*[1]"));
                         List<WebElement> wes = driver.findElements(By.xpath("//*"));
                         System.out.println(wes.size()-1);
                         if(!(we1.equals(wes.get(1)))true){
                         Point pt = wes.get(1).getLocation();
                         if(browser.toLowerCase().contains("android")){
                         AndroidDriver2.swipe(pt.getX(), pt.getY(), pt.getX()+i, pt.getY()+i, 3000);
                         }else {
                         Iosdriver.swipe(pt.getX(), pt.getY(), pt.getX()+i, pt.getY()+i, 3000);
                         }
                         we1 = wes.get((wes.size())-1);
                         }
                         }else{*/
                        try{
                            List<WebElement> wes = driver.findElements(By.xpath("//*"));
                            System.out.println("i val "+i+" "+(wes.size()-1));
                            if(!(isElementDisplayedTemp(we1))){
                                Point pt = wes.get(1).getLocation();
                                Point pt2 = wes.get(2).getLocation();
                                if(browser.toLowerCase().contains("android")){
                                    AndroidDriver2.swipe(pt.getX(), pt.getY(), pt2.getX(),
                                                         pt2.getY(), 8000);
                                }else{
                                    Iosdriver.swipe(pt.getX(), pt.getY(), pt.getX()+i, pt.getY()+i, 8000);
                                }
                            }else{
                                System.out.println("reached end of screen , unable to find elemenet");
                                break;
                            }
                            we1 = wes.get((wes.size())-1);
                        }catch(Exception e1){
                            e1.printStackTrace();
                        }
                        //}//
                        System.out.println("scrolling..");
                        if((isElementDisplayedTemp(locator))){
                            Thread.sleep(1000);
                            flag = true;
                            break;
                        }
                    }
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            return flag;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } /*finally {
           if (!flag) {
           Reporter.failureReport("Check IsElementPresent ", locatorName
           + " Element is not present on the page");
           Assert.assertTrue(flag,"Unable find the element "+ locatorName);
           } else if (b && flag) {
           Reporter.SuccessReport("IsElementPresent ",
           "Able to locate element " + locatorName);
           }
           }*/
    }
	
	public static boolean waitForElementHasSomeText(final By by, String locator)
			throws Throwable {
		boolean flag = false;
		try {
			wait = new WebDriverWait(driver, 20,30);
			flag = wait.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver arg0) {
					return  driver.findElement(by).getText().length() != 0;
				}
			});
		} catch (Exception e) {
			Assert.assertTrue(flag,
					"waitForElementHasSomeText : Falied to locate element"+locator
					+" with some text");
			e.printStackTrace();
			return false;
		}finally {
		if (!flag) {
			Reporter.failureReport("waitForElementHasSomeText", "Failed to find element "+locator
					+" with some text");
		} else if (flag) {
			Reporter.SuccessReport("waitForElementHasSomeText", " found element "+locator
					+" with some text");
			return flag;
		}
	
		}
		return flag;
	}
	
	public static boolean verifyElementAbsent(By by, String locatorName)
			throws Throwable {
		boolean flag = true;
		try {
			driver.findElement(by);
			flag = false;
			return false;
		} catch (Exception e) {
			Reporter.SuccessReport("Verify Element is NOT displayed",
					"Element: <b>" + locatorName + " </b> is <b> NOT </b> displayed");
			return true;
		} finally {
			if (!flag) {
				Reporter.failureReportContinue("Verify Element is NOT displayed",
						"Element: <b>" + locatorName + " </b> is <b> displayed</b>");
				Assert.assertTrue(flag,"Failed to Assert Element is absent"+ locatorName);
			} else if (b && flag) {
				Reporter.SuccessReport("Verify Element is NOT displayed",
						"Element: <b>" + locatorName + " </b> is <b> NOT </b> displayed");
				return flag;
			}

		}
	}
	
	
	public static boolean isPopUpElementPresent(By by, String locatorName)
			throws Throwable {
		boolean flag = false;
		try {
			if (driver.findElement(by).isDisplayed())
				flag = true;
			else
				flag = false;
			return flag;
		} catch (Exception e) {

			System.out.println(e.getMessage());
			return false;
		} finally {
			if (!flag) {
				Reporter.failureReport("check IsElementPresent", locatorName
				+ " Element is not present on the page");
				Assert.assertTrue(flag,"Unable find the pop-up "+ locatorName);
			} else if (b && flag) {
				Reporter.SuccessReport("IsElementPresent ",
						"Able to locate element " + locatorName);
			}

		}
	}

	/**
	 * This method used type value in to text box or text area
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param testdata
	 *            : Value wish to type in text box / text area
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:Textbox,Text Area etc..)
	 * 
	 * @throws NoSuchElementException
	 */
	public static boolean type(By locator, String testdata, String locatorName)
			throws Throwable {
		boolean flag = false;
		try { 
			Thread.sleep(1000);
			WebElement we = driver.findElement(locator);
			we.clear();
			//driver.findElement(locator).click(); //temp
			/*Actions act = new Actions(driver);
			act.sendKeys(driver.findElement(locator), testdata).build().perform();*/
			we.sendKeys(testdata);
			flag = true;
			if(browser.toLowerCase().contains("android")){
				try{
					(AndroidDriver2).hideKeyboard();
				}catch(Exception e){
					
				}
			}

		} catch (Exception e) {
			e.printStackTrace();   
		} finally {
			if (!flag) {
				Reporter.failureReport("Enter value",
						"Value: "+"<b>"+testdata+"</b>" + " is " + "<b>"+" NOT "+"</b>"+" entered in: <b>" + locatorName + "</b>"
								);
			} else if (b && flag) {
				Reporter.SuccessReport("Enter value",
						"Value: "+"<b>"+testdata+"</b>"+" entered in: <b>" + locatorName + "</b>");
				logger.info("Entered value "+testdata+" in: "+locatorName);
			}
		}
		return flag;
	}

	/**
	 * Moves the mouse to the middle of the element. The element is scrolled
	 * into view and its location is calculated using getBoundingClientRect.
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:link,menus etc..)
	 * 
	 */
	public static boolean mouseover(By locator, String locatorName)
			throws Throwable {
		boolean flag = false;
		try {
			WebElement mo = driver.findElement(locator);
			new Actions(driver).moveToElement(mo).build().perform();
			
			flag = true;
			return true;
		} catch (Exception e) {
			Assert.assertTrue(flag,"MouseOver action is not performed on " + locatorName);
			return false;
		} finally {
			if (!flag) {
				Reporter.failureReport("MouseOver",
						"MouseOver action is not performed on " + locatorName);
				Assert.assertTrue(flag,"Unable find the element "+ locatorName);
			} else if (b && flag) {

				Reporter.SuccessReport("MouseOver ",
						"MouserOver Action is performed on <b>" + locatorName + "</b>");
			}
		}
	}

	/**
	 * A convenience method that performs click-and-hold at the location of the
	 * source element, moves by a given offset, then releases the mouse.
	 * 
	 * @param source
	 *            : Element to emulate button down at.
	 * 
	 * @param xOffset
	 *            : Horizontal move offset.
	 * 
	 * @param yOffset
	 *            : Vertical move offset.
	 * 
	 */
	public static boolean draggable(By source, int x, int y, String locatorName)
			throws Throwable {
		boolean flag = false;
		try {

			WebElement dragitem = driver.findElement(source);
			new Actions(driver).dragAndDropBy(dragitem, x, y).build().perform();
			Thread.sleep(5000);
			flag = true;
			return true;

		} catch (Exception e) {

			return false;
		} finally {
			if (!flag) {
				Reporter.failureReport("Draggable ",
						"Draggable action is not performed on " + locatorName);

			} else if (b && flag) {

				Reporter.SuccessReport("Draggable ",
						"Draggable Action is Done on " + locatorName);
			}
		}
	}

	/**
	 * A convenience method that performs click-and-hold at the location of the
	 * source element, moves to the location of the target element, then
	 * releases the mouse.
	 * 
	 * @param source
	 *            : Element to emulate button down at.
	 * 
	 * @param target
	 *            : Element to move to and release the mouse at.
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:Button,image etc..)
	 * 
	 */
	public static boolean draganddrop(By source, By target, String locatorName)
			throws Throwable {
		boolean flag = false;
		try {
			WebElement from = driver.findElement(source);
			WebElement to = driver.findElement(target);
			new Actions(driver).dragAndDrop(from, to).perform();
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (!flag) {
				Reporter.failureReport("DragAndDrop ",
						"DragAndDrop action is not perform on " + locatorName);

			} else if (b && flag) {

				Reporter.SuccessReport("DragAndDrop ",
						"DragAndDrop Action is Done on " + locatorName);
			}
		}
	}

	/**
	 * To slide an object to some distance
	 * 
	 * @param slider
	 *            : Action to be performed on element
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:Login Button, SignIn Link
	 *            etc..)
	 * 
	 */
	public static boolean slider(By slider, int x, int y, String locatorName)
			throws Throwable {

		boolean flag = false;
		try {
			WebElement dragitem = driver.findElement(slider);
			// new Actions(driver).dragAndDropBy(dragitem, 400, 1).build()
			// .perform();
			new Actions(driver).dragAndDropBy(dragitem, x, y).build().perform();// 150,0
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (!flag) {
				Reporter.failureReport("Slider ",
						"Slider action is not perform on " + locatorName);
				// throw new ElementNotFoundException("", "", "");

			} else if (b && flag) {
				Reporter.SuccessReport("Slider ", "Slider Action is Done on "
						+ locatorName);
			}
		}
	}

	/**
	 * To right click on an element
	 * 
	 * @param by
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:Login Button, SignIn Link
	 *            etc..)
	 * 
	 * @throws Throwable
	 */

	public static boolean rightclick(By by, String locatorName)
			throws Throwable {

		boolean flag = false;
		try {
			WebElement elementToRightClick = driver.findElement(by);
			Actions clicker = new Actions(driver);
			clicker.contextClick(elementToRightClick).perform();
			flag = true;
			return true;
			// driver.findElement(by1).sendKeys(Keys.DOWN);
		} catch (Exception e) {

			return false;
		} finally {
			if (!flag) {
				Reporter.failureReport("RightClick ",
						"RightClick action is not perform on " + locatorName);

			} else if (b && flag) {
				Reporter.SuccessReport("RightClick ",
						"RightClick Action is Done on " + locatorName);
			}
		}
	}

	/**
	 * Wait for an element
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 */

	public static boolean waitForTitlePresent(By locator) throws Throwable {

		boolean flag = false;
		boolean bValue = false;

		try {
			for (int i = 0; i < 200; i++) {
				if (driver.findElements(locator).size() > 0) {
					flag = true;
					bValue = true;
					break;
				} else {
					driver.wait(50);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (!flag) {
				Reporter.failureReport("WaitForTitlePresent ", "Title is wrong");

			} else if (b && flag) {
				Reporter.SuccessReport("WaitForTitlePresent ",
						"Launched successfully expected Title ");
			}
		}
		return bValue;
	}
	
	/**
	 * Wait for an ElementPresent
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @return Whether or not the element is displayed
	 */
	public static boolean waitForElementClickable(By by, String locator)
			throws Throwable {
		boolean flag = false;
		try {
				/*wait = new WebDriverWait(driver, 20000);*/
				wait = new WebDriverWait(driver,20,30);
				WebElement  element =  null;
					element = wait.until(ExpectedConditions.elementToBeClickable(by));
				    boolean enabled = element.getSize().getHeight()>0;
				    if(enabled){ 
				    	flag = true;
				    }else {
				    	driver.wait(50);
					}
		} catch (Exception e) {
			
			Assert.assertTrue(flag,"waitForElementPresent : Falied to locate element "+locator);

			e.printStackTrace();
			
			return false;
		} finally {
			if (!flag) {
				Reporter.failureReport("WaitForElementPresent ",
						"Falied to locate element: <b>" + locator + "</b>");
			} else if (b && flag) {
				Reporter.SuccessReport("WaitForElementPresent ",
						"Successfully located element: <b>" + locator + "</b>");
			}
		}

		return flag;

	}

	

	/**
	 * Wait for an ElementPresent
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @return Whether or not the element is displayed
	 */
	public static boolean waitForElementPresent(By by, String locator)
			throws Throwable {
		boolean flag = false;
		try {
				/*wait = new WebDriverWait(driver, 20000);*/
				wait = new WebDriverWait(driver,20,30);
				WebElement  element =  null;
					element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
					boolean enabled = element.getSize().getHeight()>0;
				    if(enabled){ 
				    	flag = true;
				    }else {
				    	driver.wait(50);
					}
		} catch (Exception e) {
			
			Assert.assertTrue(flag,"waitForElementPresent : Falied to locate element "+locator);

			e.printStackTrace();
			
			return false;
		} finally {
			if (!flag) {
				Reporter.failureReport("WaitForElementPresent ",
						"Falied to locate element: <b>" + locator + "</b>");
			} else if (b && flag) {
				Reporter.SuccessReport("WaitForElementPresent ",
						"Successfully located element: <b>" + locator + "</b>");
			}
		}

		return flag;

	}

	/**
	 * This method Click on element and wait for an element
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param waitElement
	 *            : Element name wish to wait for that (Get it from Object
	 *            repository)
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:Login Button, SignIn Link
	 *            etc..)
	 */
	public static boolean clickAndWaitForElementPresent(By locator,
			By waitElement, String locatorName) throws Throwable {
		boolean flag = false;
		try {
			click(locator, locatorName);
			waitForElementPresent(waitElement, locatorName);
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (!flag) {
				Reporter.failureReport("ClickAndWaitForElementPresent ",
						"Failed to perform clickAndWaitForElementPresent action");
			} else if (b && flag) {
				Reporter.SuccessReport("ClickAndWaitForElementPresent ",
						"successfully performed clickAndWaitForElementPresent action");
			}
		}
	}

	/**
	 * Select a value from Dropdown using send keys
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param value
	 *            : Value wish type in dropdown list
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:Year Dropdown, items
	 *            Listbox etc..)
	 * 
	 */
	public static boolean selectBySendkeys(By locator, String value,
			String locatorName) throws Throwable {

		boolean flag = false;
		try {
			driver.findElement(locator).sendKeys(value);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (!flag) {
				Reporter.failureReport("Select ", value
						+ "is Not Select from the DropDown " + locatorName);
				// throw new ElementNotFoundException("", "", "");

			} else if (b && flag) {
				Reporter.SuccessReport("Select ", value
						+ " is Selected from the DropDown " + locatorName);
			}
		}
	}

	/**
	 * select value from DropDown by using selectByIndex
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param index
	 *            : Index of value wish to select from dropdown list.
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:Year Dropdown, items
	 *            Listbox etc..)
	 * 
	 */
	public static boolean selectByIndex(By locator, int index,
			String locatorName) throws Throwable {
		boolean flag = false;
		try {
			Select s = new Select(driver.findElement(locator));
			s.selectByIndex(index);
			flag = true;
			return true;
		} catch (Exception e) {
			Assert.assertTrue(flag,"Option at index " + index
					+ " is Not Selected from the DropDown" + locatorName);
			e.printStackTrace();
			return false;
		} finally {
			if (!flag) {
				Reporter.failureReport("Select ", "Option at index " + index
						+ " is Not Select from the DropDown" + locatorName);

			} else if (b && flag) {
				Reporter.SuccessReport("Select ", "Option at index " + index
						+ " is Selected from the DropDown" + locatorName);
			}
		}
	}

	/**
	 * select value from DD by using value
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param value
	 *            : Value wish to select from dropdown list.
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:Year Dropdown, items
	 *            Listbox etc..)
	 */

	public static boolean selectByValue(By locator, String value,
			String locatorName) throws Throwable {
		boolean flag = false;
		try {
			Select s = new Select(driver.findElement(locator));
			s.selectByValue(value);
			flag = true;
			return true;
		} catch (Exception e) {
			Assert.assertTrue(flag,"Option with value attribute " + value
					+ " is Not Selected from the DropDown "
					+ locatorName);
			e.printStackTrace();
			return false;
		} finally {
			if (!flag) {
				Reporter.failureReport("Select",
						"Option with value attribute " + value
								+ " is Not Select from the DropDown "
								+ locatorName);

			} else if (b && flag) {
				Reporter.SuccessReport("Select ",
						"Option with value attribute " + value
								+ " is  Selected from the DropDown "
								+ locatorName);
			}
		}
	}

	public static boolean selectByOptionText(By locator, String value,
			String locatorName) throws Throwable {
		boolean flag = false;
		try {
			WebElement ListBox = driver.findElement(locator);
			List<WebElement> options = ListBox.findElements(By.tagName("option"));
			for(WebElement option : options){
				String opt = option.getText().trim();
				//System.out.println("optionsM  "+opt);
				if(opt.equalsIgnoreCase(value.trim())){
					flag = true;
					option.click();
					break;
				}
			}
			return true;
		} catch (Exception e) {
			Assert.assertTrue(flag,"Option with value attribute " + value
					+ " is Not Selected from the DropDown "
					+ locatorName);
			e.printStackTrace();
			return false;
		} finally {
			if (!flag) {
				Reporter.failureReport("Select",
						"Option with value attribute " + value
								+ " is Not Select from the DropDown "
								+ locatorName);

			} else if (b && flag) {
				Reporter.SuccessReport("Select ",
						"Option with value attribute " + value
								+ " is  Selected from the DropDown "
								+ locatorName);
			}
		}
	}
	
	
	
	/**
	 * select value from DropDown by using selectByVisibleText
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param visibletext
	 *            : VisibleText wish to select from dropdown list.
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:Year Dropdown, items
	 *            Listbox etc..)
	 */

	public static boolean selectByVisibleText(By locator, String visibletext,
			String locatorName) throws Throwable {
		boolean flag = false;
		try {
			Select s = new Select(driver.findElement(locator));
			s.selectByVisibleText(visibletext);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (!flag) {
				Reporter.failureReport("Select ", visibletext
						+ " is Not Select from the DropDown " + locatorName);

			} else if (b && flag) {
				Reporter.SuccessReport("Select ", visibletext
						+ "  is Selected from the DropDown " + locatorName);
			}
		}
	}

	/**
	 * select value from DropDown by using selectByVisibleText
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param visibletext
	 *            : VisibleText wish to select from dropdown list.
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:Year Dropdown, items
	 *            Listbox etc..)
	 */

	public static String getSelectedOption(By locator,
			String locatorName) throws Throwable {
		String strSelectdOption="";
		boolean flag = false;
		try {
			Select s = new Select(driver.findElement(locator));
			strSelectdOption=s.getFirstSelectedOption().getText();
			flag = true;
			return strSelectdOption;
		} catch (Exception e) {

			return strSelectdOption;
		} finally {
			if (!flag) {
				Reporter.failureReport("Get Selected Value","Not Able to Get Selected Value from the DropDown " + locatorName);
			} else if (b && flag) {
				Reporter.SuccessReport("Get Selected Value ",strSelectdOption + "is Selected from the DropDown " + locatorName);
			}
		}
	}
	
	
	/**
	 * SWITCH TO WINDOW BY USING TITLE
	 * 
	 * @param windowTitle
	 *            : Title of window wish to switch
	 * 
	 * @param count
	 *            : Selenium launched Window id (integer no)
	 * 
	 * @return: Boolean value(true or false)
	 * 
	 */
	//
	public static boolean switchWindowByTitle(String windowTitle, int count)
			throws Throwable {
		boolean flag = false;
		try {
//			Set<String> windowList = driver.getWindowHandles();
//			int windowCount = windowList.size();
			// Calendar calendar = new GregorianCalendar();
			// int second = calendar.get(Calendar.SECOND); // /to get current
			// time
			// int timeout = second + 40;
			/*
			 * while (windowCount != count && second < timeout) {
			 * Thread.sleep(500); windowList = driver.getWindowHandles();
			 * windowCount = windowList.size();
			 * 
			 * }
			 */

//			String[] array = windowList.toArray(new String[0]);

//			for (int i = 0; i <= windowCount; i++) {
//
//				driver.switchTo().window(array[count - 1]);
//
//				// if (driver.getTitle().contains(windowTitle))
//				flag = true;
//				return true;
//			}
			return false;
		} catch (Exception e) {

			return false;
		} finally {
			if (!flag) {
				Reporter.failureReport("SelectWindow ",
						"The Window with title " + windowTitle
								+ " is not Selected");

			} else if (b && flag) {
				Reporter.SuccessReport("SelectWindow ",
						"Focus navigated to the window with title "
								+ windowTitle);
			}
		}
	}

	/**
	 * Function To get column count and print data in Columns
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @return: Returns no of columns.
	 * 
	 */
	public static int getColumncount(By locator) throws Exception {

		WebElement tr = driver.findElement(locator);
		List<WebElement> columns = tr.findElements(By.tagName("td"));
		int a = columns.size();
		System.out.println(columns.size());
		for (WebElement column : columns) {
			System.out.print(column.getText());
			System.out.print("|");
		}
		return a;

	}

	/**
	 * Function To get row count and print data in rows
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @return: returns no of rows.
	 */
	public static int getRowCount(By locator) throws Exception {

		WebElement table = driver.findElement(locator);
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		int a = rows.size() - 1;
		return a;
	}

	/**
	 * Verify alert present or not
	 * 
	 * @return: Boolean (True: If alert preset, False: If no alert)
	 * 
	 */
	public static boolean Alert() throws Throwable {
		boolean flag = false;
		boolean presentFlag = false;
		Alert alert = null;

		try {

			// Check the presence of alert
			alert = driver.switchTo().alert();
			// if present consume the alert
			alert.accept();
			presentFlag = true;
		} catch (NoAlertPresentException ex) {
			// Alert present; set the flag

			// Alert not present
			ex.printStackTrace();
		} finally {
			if (presentFlag) {
				Reporter.failureReport("Alert ", "There was no alert to handle");
			} else if (b && flag) {
				Reporter.SuccessReport("Alert ",
						"The Alert is handled successfully ");
			}
		}

		return presentFlag;
	}

	/**
	 * To launch URL
	 * 
	 * @param url
	 *            : url value want to launch
	 * @throws Throwable
	 * 
	 */
	public static boolean launchUrl(String url) throws Throwable {
		boolean flag = false;
		try {
			driver.get(url);
			ImplicitWait();
			flag = true;
			return true;
		} catch (Exception e) {
			Assert.assertTrue(flag,"Failed to launch "
					+ url);
			e.printStackTrace();
			return false;
		} finally {
			if (!flag) {
				Reporter.failureReport("Launching URL ", "Failed to launch "
						+ url);
			} else if (b && flag) {
				Reporter.SuccessReport("Launching URL ",
						"Successfully launched " + url);
			}
		}
	}

	/*
	 * public static int getResponseCode(String url) { try { return
	 * Request.Get(url).execute().returnResponse().getStatusLine()
	 * .getStatusCode(); } catch (Exception e) { throw new RuntimeException(e);
	 * } }
	 */
	/**
	 * This method verify check box is checked or not
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:sign in Checkbox etc..)
	 * 
	 * @return: boolean value(True: if it is checked, False: if not checked)
	 * 
	 */
	public static boolean isChecked(By locator, String locatorName)
			throws Throwable {
		boolean bvalue = false;
		boolean flag = false;
		try {
			if (driver.findElement(locator).isSelected()) {
				flag = true;
				bvalue = true;
			}

		} catch (NoSuchElementException e) {

			bvalue = false;
		} finally {
			if (!flag) {
				Reporter.SuccessReport("IsChecked ", locatorName
						+ " is Selected ");
				// throw new ElementNotFoundException("", "", "");

			} else if (b && flag) {
				Reporter.failureReport("IsChecked ", locatorName
						+ " is not Select ");
			}
		}
		return bvalue;
	}

	/**
	 * Element is enable or not
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:Login Button, UserName
	 *            Textbox etc..)
	 * 
	 * @return: boolean value (True: if the element is enabled, false: if it not
	 *          enabled).
	 * 
	 */

	public static boolean isEnabled(By locator, String locatorName)
			throws Throwable {
		Boolean value = false;
		boolean flag = false;
		try {
			if (driver.findElement(locator).isEnabled()) {
				flag = true;
				value = true;
			}

		} catch (Exception e) {

			flag = false;

		} finally {
			if (!flag) {
				Reporter.failureReport("IsEnabled ", locatorName
						+ " is not Enabled");

			} else if (b && flag) {
				Reporter.SuccessReport("IsEnabled ", locatorName + " is Enable");
			}
		}
		return value;
	}

	/**
	 * Element visible or not
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:Login Button, SignIn Link
	 *            etc..)
	 * 
	 * @return: boolean value(True: if the element is visible, false: If element
	 *          not visible)
	 * 
	 */

	public static boolean isVisible(By locator, String locatorName)
			throws Throwable {
		Boolean value = false;
		boolean flag = false;
		try {

			value = driver.findElement(locator).isDisplayed();
			value = true;
			flag = true;
		} catch (Exception e) {
			flag = false;
			value = false;
			Assert.assertTrue(flag,locatorName
					+ " Element is Not Visible");

		} finally {
			if (!flag) {
				Reporter.failureReport("IsVisible ", locatorName
						+ " Element is Not Visible");
			} else if (b && flag) {
				Reporter.SuccessReport("IsVisible ", locatorName
						+ " Element is Visible ");

			}
		}
		return value;
	}

	/**
	 * Get the CSS value of an element
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:Login Button, label color
	 *            etc..)
	 * 
	 * @param cssattribute
	 *            : CSS attribute name wish to verify the value (id, name,
	 *            etc..)
	 * 
	 * @return: String CSS value of the element
	 * 
	 */

	public static String getCssValue(By locator, String cssattribute,
			String locatorName) throws Throwable {
		String value = "";
		boolean flag = false;
		try {
			if (isElementPresent(locator, "locatorName")) {
				value = driver.findElement(locator).getCssValue(cssattribute);
				flag = true;
			}
		} catch (Exception e) {

		} finally {
			if (!flag) {
				Reporter.failureReport("GetCssValue ",
						" Was able to get Css value from " + locatorName);

			} else if (b & flag) {
				Reporter.SuccessReport("GetCssValue ",
						" Was not able to get Css value from " + locatorName);
			}
		}
		return value;
	}

	/**
	 * Check the expected value is available or not
	 * 
	 * @param expvalue
	 *            : Expected value of attribute
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param attribute
	 *            : Attribute name of element wish to assert
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:link text, label text
	 *            etc..)
	 * 
	 */
	public static boolean assertValue(String expvalue, By locator,
			String attribute, String locatorName) throws Throwable {

		boolean flag = false;
		try {
			Assert.assertEquals(expvalue,
					getAttribute(locator, attribute, locatorName));
			flag = true;
		} catch (Exception e) {

		} finally {
			if (!flag) {
				Reporter.failureReport("AssertValue ", locatorName
						+ " present in the page");
				return false;
			} else if (b & flag) {
				Reporter.SuccessReport("AssertValue ", locatorName
						+ " is not present in the page ");
			}
		}
		return flag;
	}

	/**
	 * Check the text is presnt or not
	 * 
	 * @param text
	 *            : Text wish to assert on the page.
	 * 
	 */
	public static boolean assertTextPresent(String text) throws Throwable {
		boolean flag = false;
		try {
			Assert.assertTrue(isTextPresent(text));
			flag = true;
		} catch (Exception e) {

		} finally {
			if (!flag) {
				Reporter.failureReport("AssertTextPresent ", text
						+ " present in the page ");
				return false;
			} else if (b & flag) {
				Reporter.SuccessReport("AssertTextPresent ", text
						+ " is not present in the page ");
			}
		}
		return flag;
	}

	/**
	 * Assert element present or not
	 * 
	 * @param by
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:Login Button, SignIn Link
	 *            etc..)
	 * 
	 */
	public static boolean assertElementPresent(By by, String locatorName)
			throws Throwable {

		boolean flag = false;
		try {
			Assert.assertTrue(isElementPresent(by, locatorName));
			flag = true;
		} catch (Exception e) {
			Assert.assertTrue(flag, locatorName
					+ " present in the page ");
			e.printStackTrace();
		} finally {
			if (!flag) {
				Reporter.failureReport("AssertElementPresent ", locatorName
						+ " present in the page ");
				return false;
			} else if (b & flag) {
				Reporter.SuccessReport("AssertElementPresent ", locatorName
						+ " is not present in the page ");
			}
		}
		return flag;

	}

	/**
	 * Assert text on element
	 * 
	 * @param by
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param text
	 *            : expected text to assert on the element
	 * 
	 */

	public static boolean assertText(By by, String text) throws Throwable {
		boolean flag = false;
		try {
			Assert.assertEquals(getText(by, text).trim(), text.trim());
			flag = true;
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (!flag) {
				Reporter.failureReportContinue("AssertText ", text
						+ " is not present in the element ");
				return false;

			} else if (b && flag) {
				Reporter.SuccessReport("AssertText ", text
						+ " is  present in the element ");
			}
		}

	}

	/**
	 * Assert text on element
	 * 
	 * @param by
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param text
	 *            : expected text to assert on the element
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:link text, label text
	 *            etc..)
	 * 
	 */
	public static boolean verifyText(By by, String text, String locatorName)
			throws Throwable {
		boolean flag = false;

		try {

			String vtxt = getText(by, locatorName).trim();
			vtxt.equals(text.trim());
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (!flag) {
				Reporter.failureReport("VerifyText ", "<b>" +text
						+ "</b> is <b>NOT</b> present in the location: <b>" + locatorName + "</b>");
				flag = true;
			} else if (b && flag) {
				Reporter.SuccessReport("VerifyText ", "<b>" +text
						+ "</b> is present in the location: <b>" + locatorName + "</b>");
				flag = false;
			}
		}
	}

	/**
	 * @return: return title of current page.
	 * 
	 * @throws Throwable
	 */

	public static String getTitle() throws Throwable {

		String text = driver.getTitle();
		if (b) {
			Reporter.SuccessReport("Title ", "Title of the page is " + text);
		}
		return text;
	}

	/**
	 * Assert Title of the page.
	 * 
	 * @param title
	 *            : Expected title of the page.
	 * 
	 */
	public static boolean asserTitle(String title) throws Throwable {
		boolean flag = false;

		try {
			By windowTitle = By.xpath("//title[contains(text(),'" + title
					+ "')]");
			if (waitForTitlePresent(windowTitle)) {
				Assert.assertEquals(getTitle(), title);
				flag = true;
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		} finally {

			if (!flag) {
				Reporter.failureReport("AsserTitle ",
						"Page title is not matched with " + title);
				return false;
			} else if (b && flag) {
				Reporter.SuccessReport("AsserTitle ",
						" Page title is verified with " + title);
			}
		}

	}

	/**
	 * Verify Title of the page.
	 * 
	 * @param title
	 *            : Expected title of the page.
	 * 
	 */
	public static boolean verifyTitle(String title) throws Throwable {

		boolean flag = false;

		try {
			getTitle().equals(title);
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		}

		finally {
			if (!flag) {
				Reporter.failureReport("VerifyTitle ",
						"Page title is not matched with " + title);

			} else if (b && flag) {
				Reporter.SuccessReport("VerifyTitle ",
						" Page title is verified with " + title);

			}
		}
	}

	/**
	 * Verify text present or not
	 * 
	 * @param text
	 *            : Text wish to verify on the current page.
	 * 
	 */
	public static boolean verifyTextPresent(String text) throws Throwable {
		boolean flag = false;
		;
		if (!(driver.getPageSource()).contains(text)) {

			Reporter.failureReport("VerifyTextPresent ", text
					+ " is not present in the page ");
			flag = false;
		} else if (b && flag) {
			Reporter.SuccessReport("VerifyTextPresent ", text
					+ " is present in the page ");
			flag = true;

		}
		return flag;
	}

	/**
	 * Get the value of a the given attribute of the element.
	 * 
	 * @param by
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param attribute
	 *            : Attribute name wish to assert the value.
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:label, SignIn Link etc..)
	 * 
	 * @return: String attribute value
	 * 
	 */

	public static String getAttribute(By by, String attribute,
			String locatorName) throws Throwable {
		boolean flag = false;
		String value = "";
		try{
			if (isElementPresent(by, locatorName)) {
				value = driver.findElement(by).getAttribute(attribute);
				flag=true;
			}
		}catch (Exception e) {
			Assert.assertTrue(flag,"Unable to get Attribute <b>"+ attribute +"</b> from <b>"
					+ locatorName + "</b>");
			e.printStackTrace();
		} finally {
			if (!flag) {
				Reporter.failureReport("GetAttribute ", "Unable to get Attribute <b>"+ attribute +"</b> from <b>"
						+ locatorName + "</b>");
			} else if (b && flag) {
				Reporter.SuccessReport("GetAttribute ", "Able to get Attribute <b>"+ attribute +"</b> from <b>"
						+ locatorName + "</b>");
			}
		}
		return value;
	}

	/**
	 * Text present or not
	 * 
	 * @param text
	 *            : Text wish to verify on current page
	 * 
	 * @return: boolean value(true: if Text present, false: if text not present)
	 */

	public static boolean isTextPresent(String text) throws Throwable {

		boolean value = false;
		if(driver.getPageSource().toLowerCase().contains(text.toLowerCase())){
			value = true;
			flag = true;
		}else{
		System.out.println("is text "+text+" present  " + value);
		flag = false;
		}
		if (!value) {
			Reporter.failureReport("IsTextPresent ", text
					+ " is  not presented in the page ");
			Assert.assertTrue(value,text
					+ " is  not presented in the page ");
			return false;
			
		} else if (b && flag) {
			Reporter.SuccessReport("IsTextPresent ", "'" + text + "'"
					+ " is presented in the page ");
			
			return true;
		}
		return value;
	}

	/**
	 * The innerText of this element.
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:label text, SignIn Link
	 *            etc..)
	 * 
	 * @return: String return text on element
	 * 
	 */

	public static String getText(By locator, String locatorName)
			throws Throwable {
		String text = "";
		boolean flag = false;
		try {
			if (isElementPresent(locator, locatorName)) {
				text = driver.findElement(locator).getText();
				flag = true;
			}
		} catch (Exception e) {
			Assert.assertTrue(flag," Unable to get Text from "
					+ locatorName);
			e.printStackTrace();
		} finally {
			if (!flag) {
				Reporter.failureReport("GetText ", " Unable to get Text from <b>"
						+ locatorName + "</b>");
			} else if (b && flag) {
				Reporter.SuccessReport("GetText ", "Retrieved text: <b>" + text + "</b> from: <b>"
						+ locatorName + "</b>");
			}
		}
		return text;
	}

	public static String getValue(String locator, String locatorName)
			throws Throwable {
		String text = "";
		boolean flag = false;
		try {
			if (driver.findElement(By.xpath(locator)).isDisplayed()) {
				text = driver.findElement(By.xpath(locator)).getAttribute(
						"value");
				flag = true;
			}
		} catch (Exception e) {
			Assert.assertTrue(flag," Unable to get Text from "
					+ locatorName);
			e.printStackTrace();
		} finally {
			if (!flag) {
				Reporter.failureReport("GetValue ", " Unable to get Text from "
						+ locatorName);
			} else if (b && flag) {
				Reporter.SuccessReport("GetValue ", " Able to get Text from "
						+ locatorName);
			}
		}
		return text;
	}

	public static int getElementsSize(By locator, String locatorName)
			throws Throwable {
		int text = 0;
		try {
			if (driver.findElement(locator).isDisplayed()) {
				text = driver.findElements(locator).size();
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return text;
	}

	/**
	 * Capture Screenshot
	 * 
	 * @param fileName
	 *            : FileName screenshot save in local directory
	 * @throws Throwable 
	 * 
	 */
	public static void screenShot(String fileName) throws Throwable {
		File scrFile = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		try {
			// Now you can do whatever you need to do with it, for example copy
			// somewhere
			FileUtils.copyFile(scrFile, new File(fileName));
			flag=true;
		} catch (IOException e) {
			//Assert.assertTrue(flag,"Unable to take Screenshot");
			e.printStackTrace();
		}finally {
			if (!flag) {
				//Reporter.failureReport("screenShot ", " Unable to get screenShot ");
				logger.info( " Unable to get TscreenShot");
				System.out.println(" Unable to get TscreenShot");
			} else if (b && flag) {
				//Reporter.SuccessReport("screenShot ", " Able to get TscreenShot");
				logger.info( " Able to get TscreenShot");
				System.out.println(" Able to get TscreenShot");
			}
		}
	}
	
	public static void fullScreenShot(String fileName) throws Exception {
		 
		   Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		   Rectangle screenRectangle = new Rectangle(screenSize);
		   Robot robot = new Robot();
		   BufferedImage image = robot.createScreenCapture(screenRectangle);
		   ImageIO.write(image, "jpeg", new File(fileName));
		 
		}
	public static boolean isScroolPresent(){
		boolean result = false;
		result = ((JavascriptExecutor)driver).
				executeScript("return document.documentElement.scrollHeight>document.documentElement.clientHeight;") != null;
	return result;
	}

	/**
	 * Click on the Link
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:SignIn Link, menu's
	 *            etc..)
	 */

	public static boolean mouseHoverByJavaScript(By locator, String locatorName)
			throws Throwable {
		boolean flag = false;
		try {
			WebElement mo = driver.findElement(locator);
			String javaScript = "var evObj = document.createEvent('MouseEvents');"
					+ "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
					+ "arguments[0].dispatchEvent(evObj);";
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript(javaScript, mo);
			flag = true;
			return true;
		}

		catch (Exception e) {

			return false;
		} finally {
			if (!flag) {
				Reporter.failureReport("MouseOver ",
						"MouseOver Action was <b>NOT</b> perform on <b>" + locatorName + "</b>");
			} else if (b && flag) {
				Reporter.SuccessReport("MouseOver ",
						"MouserOver Action was performed on <b>" + locatorName + "</b>");
			}
		}
	}
	
	public static boolean mouseHoverByJavaScript(WebElement we)
			throws Throwable {
		//boolean flag = false;
		try {
			WebElement mo = we;
			String javaScript = "var evObj = document.createEvent('MouseEvents');"
					+ "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
					+ "arguments[0].dispatchEvent(evObj);";
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript(javaScript, mo);
			flag = true;
			return true;
		}
		catch (Exception e) {
			return false;
		} /*finally {
			if (!flag) {
				Reporter.failureReport("MouseOver ",
						" MouseOver action is not perform on " + locatorName);
			} else if (b && flag) {
				Reporter.SuccessReport("MouseOver ",
						" MouserOver Action is Done on " + locatorName);
			}
		}*/
	}

	public static boolean JSClick(By locator, String locatorName)
			throws Throwable {
		boolean flag = false;
		try {
			WebElement element = driver.findElement(locator);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
			// driver.executeAsyncScript("arguments[0].click();", element);
			flag = true;
		}
		catch (Exception e) {

		} finally {
			if (!flag) {
				Reporter.failureReport("Click",
						"<b>" + locatorName + "</b> was <b> NOT </b> clicked");
				Assert.assertTrue(flag, "MouseClick action is not perform on " + locatorName);
				return flag;
			} else if (b && flag) {
				Reporter.SuccessReport("Click",
						"Clicked on <b>" + locatorName + "</b>");
				logger.info("Clicked on "+locatorName);
				return flag;
			}
		}
		return flag;
	}

	/**
	 * This method switch the focus to selected frame using frame index
	 * 
	 * @param index
	 *            : Index of frame wish to switch
	 * 
	 */
	public static boolean switchToFrameByIndex(int index) throws Throwable {
		boolean flag = false;
		try {
			driver.switchTo().frame(index);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (!flag) {
				Reporter.failureReport("SelectFrame ", " Frame with index "
						+ index + " is not selected");
			} else if (b && flag) {
				Reporter.SuccessReport("SelectFrame ", " Frame with index "
						+ index + " is selected");
			}
		}
	}

	/**
	 * This method switch the to frame using frame ID.
	 * 
	 * @param idValue
	 *            : Frame ID wish to switch
	 * 
	 */
	public static boolean switchToFrameById(String idValue) throws Throwable {
		boolean flag = false;
		try {
			driver.switchTo().frame(idValue);
			flag = true;
			return true;
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		} finally {
			if (!flag) {
				Reporter.failureReport("SelectFrame ", " Frame with Id "
						+ idValue + " is not selected");
			} else if (b && flag) {
				Reporter.SuccessReport("SelectFrame ", " Frame with Id "
						+ idValue + " is selected");
			}
		}
	}

	/**
	 * This method switch the to frame using frame Name.
	 * 
	 * @param nameValue
	 *            : Frame Name wish to switch
	 * 
	 */
	public static boolean switchToFrameByName(String nameValue)
			throws Throwable {
		boolean flag = false;
		try {
			driver.switchTo().frame(nameValue);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (!flag) {
				Reporter.failureReport("SelectFrame ", " Frame with Name "
						+ nameValue + " is not selected");
			} else if (b && flag) {
				Reporter.SuccessReport("SelectFrame ", " Frame with Name "
						+ nameValue + " is selected");
			}
		}
	}

	/**
	 * This method switch the to Default Frame.
	 * 
	 * @throws Throwable
	 */
	public static boolean switchToDefaultFrame() throws Throwable {
		boolean flag = false;
		try {
			driver.switchTo().defaultContent();
			flag = true;
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (!flag) {
				Reporter.failureReport("SelectFrame ",
						" The Frame is not selected");
			} else if (b && flag) {
				Reporter.SuccessReport("SelectFrame ",
						" Frame with Name is selected");
			}
		}
	}

	/**
	 * This method switch the to frame using frame Name.
	 * 
	 * @param nameValue
	 *            : Frame Name wish to switch
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:SignIn Link, login button
	 *            etc..)
	 * 
	 * 
	 */
	public static boolean switchToFrameByLocator(By lacator, String locatorName)
			throws Throwable {
		boolean flag = false;
		try {
			driver.switchTo().frame(driver.findElement(lacator));
			flag = true;
			return true;
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		} finally {
			if (!flag) {
				Reporter.failureReport("SelectFrame ", " The Frame "
						+ locatorName + " is not selected");
			} else if (b && flag) {
				Reporter.SuccessReport("SelectFrame ", " Frame with Name "
						+ locatorName + " is selected");
			}
		}
	}

	public static ExpectedCondition<Boolean> docReadyState = new ExpectedCondition<Boolean>() {
			        public Boolean apply(WebDriver driver) {
			          return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");			         
			        }
			      };
	
	/**
	 * This method wait selenium until element present on web page.
	 */
	public static void ImplicitWait() {

		driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
		 wait = new WebDriverWait(driver,20,30);
		 wait.until(docReadyState);
		 try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static boolean waitUntilTextPresents(By by, String 
			expectedText, String locator) throws Throwable {
		wait = new WebDriverWait(driver, 20,30);
		boolean flag = false;
		
		try {
				wait.until(ExpectedConditions.textToBePresentInElementLocated(by,
					expectedText));
			
					flag = true;
					return  true;

			} catch (Exception e) {
			Assert.assertTrue(false," Falied to locate element " + locator
					+ " with text " +expectedText);
			e.printStackTrace();
			return false;
		} finally {
			if (!flag) {
				Reporter.failureReport("WaitUntilTextPresent ",
						" Falied to locate element " + locator
						+ " with text " +expectedText);
			} else if (b && flag) {
				Reporter.SuccessReport(" WaitUntilTextPresent ",
						" Successfully located element " + locator+
						" with text " +expectedText);
			}
			
		}

	}

	/**
	 * Click on Element
	 * 
	 * @param locator
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param locatorName
	 *            : Meaningful name to the element (Ex:SignIn Link, login button
	 *            etc..)
	 * 
	 * @throws StaleElementReferenceException
	 *             - If the element no longer exists as initially defined
	 */

	

	/**
	 * 
	 * This method wait driver until given driver time.
	 * 
	 */
	public static WebDriverWait driverwait() {

		WebDriverWait wait = new WebDriverWait(driver, 20,30);
		return wait;
	}

	/**
	 * This method wait selenium until visibility of Elements on WebPage.
	 * 
	 * @param by
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * @throws Throwable
	 * 
	 */

	public static boolean waitForVisibilityOfElement(By by, String locator)
			throws Throwable {
		boolean flag = false;
		WebDriverWait wait = new WebDriverWait(driver, 20,30);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			flag = true;
			return true;
		} catch (Exception e) {
			Assert.assertTrue(flag," Element "
					+ locator + " is not visible");
			return false;
		} finally {
			if (!flag) {
				Reporter.failureReport("WaitForVisibilityOfElement ", " Element "
						+ locator + " is not visible");
			} else if (b && flag) {
				Reporter.SuccessReport("WaitForVisibilityOfElement ", " Element "
						+ locator + "  is visible");
			}
		}
	}
	
	/**
	 * This method wait driver until Invisibility of Element's attribute on WebPage.
	 * 
	 * @param by
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 */
	public static boolean waitUntilElementAttributeIsVisible(By by, String attributeName,String locator)
			throws Throwable {
		boolean flag = false;
		try {
			for(int i = 0; i < 200; i++){
			    WebElement element = driver.findElement(by);
			    boolean visible = element.getAttribute(attributeName).length()>0;
			    if(visible){ 
			    	flag = true;
			    	break; 
			    }else {
					driver.wait(50);
				}
			 }
			flag = true;
			return flag;
		} catch (Exception e) {
			/*Assert.assertTrue(flag," "+locator +" Element's "
					+ attributeName + " is not visible");*/
			return false;
		} /*finally {
			if (!flag) {
				Reporter.failureReport("waitUntilElementAttributeIsVisible ",locator +" Element's "
						+ attributeName + " is not visible");
			} else if (b && flag) {
				Reporter.SuccessReport("waitUntilElementAttributeIsVisible ",locator +" Element's "
						+ attributeName + "  is visible");
			}
		}*/
	}
	
	
	/**
	 * This method wait driver until Invisibility of Elements on WebPage.
	 * 
	 * @param by
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 * @param by
	 *            : Action to be performed on element (Get it from Object
	 *            repository)
	 * 
	 */
	public static boolean waitForInVisibilityOfElement(By by, String locator)
			throws Throwable {
		boolean flag = false;
		WebDriverWait wait = new WebDriverWait(driver, 20,30);
		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (!flag) {
				Reporter.failureReport("WaitForInVisibilityOfElement ",
						" Element  " + locator + " is  visible");
			} else if (b && flag) {
				Reporter.SuccessReport("WaitForInVisibilityOfElement ",
						" Element  " + locator + " is not visible");
			}
		}

	}
	
	public static boolean waitUntilElementAttributeChanges(final By by, final String attributeName, final String 
			expectedAttrubuteValue, String locator) throws Throwable {
				boolean flag = false;
		try {
			
			wait = new WebDriverWait(driver, 20,30);
			flag = wait.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver arg0) {
					return  driver.findElement(by).getAttribute(attributeName).
							contains(expectedAttrubuteValue);
				}
			});
			 return flag;	

			} catch (Exception e) {
			Assert.assertTrue(flag," Falied to locate element "+locator+
					" 's "+attributeName+" attribute with value " + expectedAttrubuteValue);
			e.printStackTrace();
			return false;
		} finally {
			if (!flag) {
				Reporter.failureReport("waitUntilElementAttributeChanges ",
						 "Falied to locate element "+locator+
							"  "+attributeName+" attribute with value " + expectedAttrubuteValue);
			} else if (b && flag) {
				Reporter.SuccessReport("waitUntilElementAttributeChanges ",
						" Successfully located element "+locator+
					" "+attributeName+" attribute with value " + expectedAttrubuteValue);
			}
			
		}

	}

	public static boolean waitForTextOnElementIsPresent(By by,String expectedText ,String locator)
			throws Throwable {
		boolean flag = false;
		WebDriverWait wait = new WebDriverWait(driver, 20,30);
		try {
			wait.until(ExpectedConditions.textToBePresentInElement(by,expectedText));
			flag = true;
		} catch (Exception e) {
			return false;
		} finally {
			if (!flag) {
				Reporter.failureReport("waitForTextOnElementIsPresent ",
						" Unabel to find the text "+ expectedText + " on Element "+locator + " is  visible");
			} else if (b && flag) {
				Reporter.SuccessReport("waitForTextOnElementIsPresent ",
						"Successfully found the text "+ expectedText + " on Element  " + locator + " is not visible");
			}
		}
		return flag;
	}
	
	
	public static boolean waitForAllSuchElementsPresent(By by, String locator)
			throws Throwable {
		boolean flag = false;
		try {
				wait = new WebDriverWait(driver, 20,30);
				List<WebElement>  element =  null;
				for(int i = 0; i < 300; i++){
					element = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
				    boolean enabled = element.size()>0;
				    if(enabled){ 
				    	flag = true;
				    	break; 
				    }else {
				    	driver.wait(50);
					}
				 }
		} catch (Exception e) {
			
			Assert.assertTrue(flag,"waitForAllSuchElementsPresent : Falied to locate elements "+locator);

			e.printStackTrace();
			
			return false;
		} finally {
			if (!flag) {
				Reporter.failureReport("waitForAllSuchElementsPresent ",
						"Falied to locate all elements " + locator);
			} else if (b && flag) {
				Reporter.SuccessReport("waitForAllSuchElementsPresent ",
						"Successfully locate all elements " + locator);
			}
		}

		return flag;

	}
	
	public static List<WebElement> getElements(By locator) throws Throwable {
		boolean flag = false;
		List<WebElement> ele = null;
		try {
			
		ele = driver.findElements(locator);

		if (ele.size()>0) {
			flag = true;
		} else {
			flag = false;
		}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(flag,
					"Failed to fetch any elements with locator \""+locator+"\"");
		} finally {
			if (!flag) {
				Reporter.failureReport("Verify getElements",
						 "Unable to fetch any elements with locator \""+locator+"\"");
			} else if (flag) {
				Reporter.SuccessReport("Verify getElements" ,
						"successfully found "+ele.size()+" elements with locator \""+locator+"\"");
			}
		}
		return ele;
	}
	
	public static List<WebElement> getElementsByIosUIAutomation(String locator,String locatorName) throws Throwable {
		boolean flag = false;
		List<WebElement> elements = null;
		try {
			
			elements = ((IOSDriver)driver).findElementsByIosUIAutomation(locator);

		if (elements.size()>0) {
			flag = true;
		} else {
			flag = false;
		}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(flag,
					"Failed to fetch any elements with locator \""+locator+"\"");
		} finally {
			if (!flag) {
				Reporter.failureReport("Verify getElements",
						 "Unable to fetch any elements with locator \""+locator+"\"");
			} else if (flag) {
				Reporter.SuccessReport("Verify getElements" ,
						"successfully found "+elements.size()+" elements with locator \""+locatorName+"\"");
			}
		}
		return elements;
	}
	
	public static boolean assertTextMatching(By by, String text,
			String locatorName) throws Throwable {
		boolean flag = false;
		try {
			String ActualText = getText(by, text).trim();
			if (ActualText.contains(text)) {
				flag = true;
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (!flag) {
				Reporter.failureReport("Verify " + locatorName, text
						+ " is not present in the element");
				return false;

			} else if (b && flag) {
				Reporter.SuccessReport("Verify " + locatorName, text
						+ " is  present in the element ");
			}
		}

	}


	public static boolean isElementDisplayed(By loc)
			throws Throwable {
		boolean flag = false;
		try {
			 WebDriverWait newWait = new WebDriverWait(driver,20,30);
				WebElement element = null;
				element  = newWait.until(ExpectedConditions.presenceOfElementLocated(loc));
					flag = element.isDisplayed();
		} catch (Exception e) {
			return false;
		}
		return flag;
	}
	
	public static boolean isElementDisplay(By loc)
			throws Throwable {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		boolean flag = false;
		try {
			 WebDriverWait newWait = new WebDriverWait(driver,5,2);
				WebElement element = null;
				element  = newWait.until(ExpectedConditions.presenceOfElementLocated(loc));
					flag = element.isDisplayed();
		} catch (Exception e) {
			return false;
		}
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
		return flag;
	}
	
	public static boolean isElementDisplayed(By locator, String locatorName) throws Throwable {
		boolean flag = false;
		try {
			WebDriverWait newWait = new WebDriverWait(driver, 20,30);
			WebElement element = null;
			element = newWait.until(ExpectedConditions.presenceOfElementLocated(locator));
			flag = element.isDisplayed();
		} catch (Exception e) {
			return false;
		} finally {
			if (!flag) {
				Reporter.failureReport("Verify ", "<b>" + locatorName + "</b> is not present in the element");
				return false;

			} else if (b && flag) {
				Reporter.SuccessReport("Verify ", "<b>" + locatorName + "</b> is  present in the element ");
			}
		}
		return flag;
	}

	public static boolean isElementDisplayedTemp(By loc)
			throws Throwable {
		boolean flag = false;
		try {
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.MILLISECONDS);
			 flag  = driver.findElement((loc)).isDisplayed();
			 if(flag){
				 System.out.println("found the element "+loc);
			 }
		} catch (Exception e) {
			return false;
		}
		return flag;
	}
	
	public static boolean isElementPresent(By loc)
			throws Throwable {
		boolean flag = false;
		try {
			for (int i = 0; i < 200; i++) {
				if (driver.findElement(loc).isDisplayed()) {
					flag = true;
					break;
				} else {
					Thread.sleep(50);
				}
			}
		} catch (Exception e) {
			return false;
		}
		return flag;
	}
	public static void executeJavaScriptOnElement(String script) {
		((JavascriptExecutor) driver).executeScript(script);
	}

	public static void closeBrowser() {
		driver.close();
		driver.quit();
	}

	public static boolean hitKey(By locator, Keys keyStroke, String locatorName)
			throws Throwable {
		boolean flag = false;
		try {
			driver.findElement(locator).sendKeys(keyStroke);
			flag = true;
			return true;
		} catch (NoSuchElementException e) {
			return false;
		} finally {
			if (flag) {
				// Reporter.SuccessReport("Type ","Data typing action is performed on"
				// + locatorName+" with data is "+testdata);

			} else {
				Reporter.failureReport("Type ",
						" Data typing action is not perform on" + locatorName
								+ " with data is " + keyStroke);

			}
		}
	}

	public static Collection<WebElement> getWebElementsByTagInsideLocator(
			By locator, String tagName, String locatorName) throws Throwable {
		boolean flag = false;
		Collection<WebElement> elements;
		try {
			WebElement element = driver.findElement(locator);
			elements = element.findElements(By.tagName(tagName));
			flag = true;
		} catch (NoSuchElementException e) {
			throw e;
		} finally {
			if (!flag) {
				Reporter.failureReport("Type ",
						"Data typing action is not perform on " + locatorName
								+ " with data is " + tagName);
			}
		}
		return elements;
	}
	

	public static void mouseOverElement(WebElement element, String locatorName)
			throws Throwable {
		boolean flag = false;
		try {
			new Actions(driver).moveToElement(element).build().perform();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (!flag) {
				Reporter.failureReport("MouseOver ",
						" MouseOver action is not perform on" + locatorName);
				// throw new ElementNotFoundException("", "", "");

			} else {
				 Reporter.SuccessReport("MouseOver ",
				 " MouserOver Action is Done on " + locatorName);
			}
		}
	}
	
	public static boolean refreshPage() throws Throwable {
		boolean flag = false;
		try {
			driver.navigate().refresh();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (!flag) {
				Reporter.failureReport("RefreshPage ",
						" Failed to Refresh the page " );
		} else {
				 Reporter.SuccessReport("RefreshPage ",
				 " Refreshed page successfully " );
			}
		}
		return flag;
	}
	
	private static CookieStore seleniumCookiesToCookieStore() {
	    Cookie seleniumCookies = driver.manage().getCookieNamed(".QFXAUTH");	    
	    CookieStore cookieStore = new BasicCookieStore();
	    	System.out.println("Selenium Cookie name = "+seleniumCookies.getName());
	        BasicClientCookie basicClientCookie =
	            new BasicClientCookie(seleniumCookies.getName(), seleniumCookies.getValue());
	        basicClientCookie.setDomain(seleniumCookies.getDomain());
	        basicClientCookie.setExpiryDate(seleniumCookies.getExpiry());
	        basicClientCookie.setPath(seleniumCookies.getPath());
	        cookieStore.addCookie(basicClientCookie);	 
	    return cookieStore;
	}
	public static boolean isLinkSuccess(String URLName) throws Throwable {
		boolean flag = false;
		System.out.println(URLName);
		int respCode = 0;
		try {
			if (URLName.contains("http")) {
				@SuppressWarnings("resource")
				DefaultHttpClient httpClient = new DefaultHttpClient();
				HttpParams params = new BasicHttpParams();
				params.setParameter("http.protocol.handle-redirects",true);
				CookieStore cookieStore = seleniumCookiesToCookieStore();
				((AbstractHttpClient) httpClient).setParams(params);								
				((AbstractHttpClient) httpClient).setCookieStore(cookieStore);
				HttpGet httpget = new HttpGet(URLName);
				HttpResponse httpResp =  httpClient.execute(httpget);
				respCode = httpResp.getStatusLine().getStatusCode();
				System.out.println("response  "+respCode);
				if ((respCode==200)|(respCode==302)) {
					flag = true;
				} else {
					flag = false;
				}
			} else {
				flag = false;
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			if (!flag) {
				Reporter.failureReport("IsLinkSuccess ",
						" Failed to veirfy if Link " + URLName
								+ " response code " + respCode);
			} else { 
				Reporter.SuccessReport("IsLinkSuccess ",
						"Successfully veirfied Link " + URLName
								+ " response code " + respCode);
			}
		}
		return flag;
	}
	
	
	public static boolean isLinkSuccessWithOutAuth(String URLName) throws Throwable {
		boolean flag = false;
		System.out.println(URLName);
		int respCode = 0;
		try {
			if (URLName.contains("http")) {
				
				@SuppressWarnings("resource")
				DefaultHttpClient httpClient = new DefaultHttpClient();
				HttpParams params = new BasicHttpParams();
				params.setParameter("http.protocol.handle-redirects",true);
				((AbstractHttpClient) httpClient).setParams(params);
				HttpGet httpget = new HttpGet(URLName);
				HttpResponse httpResp =  httpClient.execute(httpget);
				respCode = httpResp.getStatusLine().getStatusCode();
				System.out.println("response  "+respCode);
				
				if ((respCode==200)|(respCode==302)) {
					flag = true;
				} else {
					flag = false;
				}
			} else {
				flag = false;
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			if (!flag) {
				Reporter.failureReportContinue("IsLinkSuccess ",
						" Failed to veirfy if Link " + URLName
								+ " response code " + respCode);
			} else { 
				Reporter.SuccessReport("IsLinkSuccess ",
						"Successfully veirfied Link " + URLName
								+ " response code " + respCode);
			}
		}
		return flag;
	}
	public static String parseCookie(String raw) {
	    String c = raw;

	    if (raw != null) {
	      int endIndex = raw.indexOf(";");
	      if (endIndex >= 0) {
	        c = raw.substring(0, endIndex);
	      }
	    }
	    return c;
	  }
	
	public void tapAction(By locator){
		TouchActions act = new TouchActions(driver);
		act.singleTap((WebElement) locator);
		
	}
	public static void tapOn(By loginLogo) throws Throwable{
        MobileElement swt = (MobileElement) driver.findElementById("fragmentTitle");
        new TouchAction((MobileDriver) driver).tap(swt).tap(swt).tap(swt).tap(swt).tap(swt).perform();
       
        MobileElement swt1 = (MobileElement) driver.findElementById("fragmentTitle");
        new TouchAction((MobileDriver) driver).press(swt1).press(swt1).press(swt1).press(swt1).press(swt1).tap(swt1).perform();
        
        
        WebElement el = driver.findElement(loginLogo);
        Point p = ((Locatable) el).getCoordinates().onPage();
        ((TouchShortcuts) driver).tap(1,p.getX(),p.getY(),1);
        
        ((TouchShortcuts) driver).tap(1,p.getX(),p.getY(),1);
        
        ((TouchShortcuts) driver).tap(1,p.getX(),p.getY(),1);
        
        ((TouchShortcuts) driver).tap(1,p.getX(),p.getY(),1);
        
        ((TouchShortcuts) driver).tap(1,p.getX(),p.getY(),1);
        
        ((TouchShortcuts) driver).tap(1,p.getX(),p.getY(),1);
        ((TouchShortcuts) driver).tap(1,p.getX(),p.getY(),1);
        ((TouchShortcuts) driver).tap(1,p.getX(),p.getY(),1);
        ((TouchShortcuts) driver).tap(1,p.getX(),p.getY(),1);
        ((TouchShortcuts) driver).tap(1,p.getX(),p.getY(),1);
        ((TouchShortcuts) driver).tap(1,p.getX(),p.getY(),1);
        
        
       /* if(!isElementPresent(LoginPageLocators.Region)){
        	 ((TouchShortcuts) driver).tap(1,p.getX(),p.getY(),1);
        }
        System.out.println("tap on successful");*/
    }
	
	public static void seekTo(){
		 /*MobileElement element = (MobileElement) driver.findElement(By.xpath("//[@resource-id='tv.hooq.androidbetaapp:id/seekBar'][@bounds='[0,2303][1440,2399]']"));
        new TouchAction((MobileDriver) driver).moveTo(element).perform();*/
		
		 WebElement el = driver.findElement(By.xpath("//*[@resource-id='tv.hooq.androidbetaapp:id/seekBar']"));
	        Point p = ((Locatable) el).getCoordinates().onPage();
	        ((TouchShortcuts) driver).tap(1,p.getX(),p.getY(),1);
	        
	        ((TouchShortcuts) driver).tap(1,p.getX(),p.getY(),1);
	        
	        
	 
		
    }
	
	public void SeekBarTest(){
		WebElement slider=driver.findElement(By.xpath("//*[@resource-id='tv.hooq.androidbetaapp:id/seekBar']"));
		int xAxisStartPoint = slider.getLocation().getX();
		int xAxisStartPoint1 = xAxisStartPoint+30;
		int xAxisEndPoint = xAxisStartPoint + slider.getSize().getWidth();
		int yAxis = slider.getLocation().getY();
		TouchAction act=new TouchAction((MobileDriver) driver);
		//pressed x axis & y axis of seekbar and move seekbar till the end
		System.out.println("X:"+xAxisStartPoint);
		System.out.println("Y:"+yAxis);
		System.out.println("X End Point:"+xAxisEndPoint);
		
		act.press(xAxisStartPoint1,yAxis).moveTo(xAxisEndPoint-1,yAxis).release().perform();
		}
	
	public static void main(String args[]){
		isScroolPresent();
	}
	
	public static boolean checkRegularExpression(String pattern, String actual)
			throws Throwable {
		boolean flag = false;
		try {
			 Pattern patternRegex;
			 Matcher matcher;
			 patternRegex = Pattern.compile(pattern);
		       matcher = patternRegex.matcher(actual);
			 flag = matcher.matches();
		} catch (Exception e) {
			return false;
		}
		return flag;
	}
}


