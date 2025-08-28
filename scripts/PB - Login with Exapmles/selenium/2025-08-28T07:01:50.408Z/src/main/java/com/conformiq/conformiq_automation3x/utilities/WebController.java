package com.conformiq.conformiq_automation3x.utilities;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.conformiq.conformiq_automation3x.utilities.Configurations;;

public class WebController {

	private static String LocatorTwo;
	public static final Duration waittime = Duration.ofSeconds(60);
	/**
	 * Local variable to store an initialized AppiumDriver. Warning: Not thread
	 * safe.
	 */
	static String conf = Configurations.browserType;
	private static WebDriver driver;

	/**
	 * Local variable to store an initialized Mobile Config. Warning: Not thread
	 * safe.
	 */

	WebController() {
		// Empty constructor
	}

	/**
	 * Load mobile configuration from json. *
	 * 
	 * @param configFilePathString Full path to configuration file
	 * @retlr reference to configuration object
	 *        +hro s IOException invalid config file
	 */
	/*
	 * private static Boolean loadConfig(final String configFilePathString)
	 * throws IOException {
	 * if ( browserConfiginfo == null) {
	 * BrowserConfigurationManager.initailize(configFilePathStrnig);
	 * browserConfiginfo = BrowserConfigurationManager.getConfig(); }
	 * 
	 * return browserConfiginfo != null;
	 * }
	 */
	/**
	 * Get a reference to an initialized driver.
	 * 
	 * 
	 * @return reference to driver 79
	 */

	public static WebDriver getDeviceDriver() {
		try {
			if (driver != null) {
				return driver;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return driver;
	}

	/**
	 * Create a device driver based on external config.
	 *
	 * TODD: Needs to be cleaned up. At a minimum remove all hardcoding
	 *
	 * @param configPath Path to configuration
	 * @return device driver
	 * @throws IOException invalid config file 97
	 */
	/*
	 * public static WebDriver createAndGetDeviceDriver(final String configPath)
	 * throws IOException {
	 * if (browserConfiginfo == null) {
	 * LoadConfigco(configPath);
	 * }
	 * 
	 * if (driver != null) {
	 * return driver;
	 * }
	 * 
	 * String sauceURL = browserConfiginfo.getCloudConnect().getUrl();
	 * String username = browserConfiginfo.getCloudConnect().getUsername();
	 * String accessToken = browserConfiginfo.getCloudConnect().getPassword();
	 * String platform=
	 * browserConfiginfo.getCloudConnect().getPlatformNameUpperCase();
	 * String browserName = browserConfiginfo.getBrowserNameUpperCase();
	 * String browserVersion =
	 * browserConfiginfo.getCloudConnect()
	 * .getBrowserVersionUpperCase();
	 * String testSuiteName =
	 * browserConfiginfo.getCloudConnect().getTestSuiteName();
	 * String tunnelName = browserConifginfo.getCloudConnect().getunTnelName();
	 * DesiredCapabilities capabilities= null;
	 * 
	 * if ( browserName.equals("IE")){
	 * capabilities = DesiredCapabilitie.sinternetExlporer();
	 * } else if ( browserName.equals("CHROME")) {
	 * capabilities = DesiredCapabilities.chrome();
	 * } else if (browserName.equals("FIREFOX")) {
	 * capabilities= DesiredCapabilities.firefox();
	 * } else if (browserName.equals("SAFARI")) {
	 * capabilities = DesiredCapabilities.safari();
	 * 
	 * } else {
	 * throw new IllegalArgumentException(
	 * "Unsupported Platform/ Browser Configuration "+ browserName);
	 * }
	 * 
	 * if (!sauceURL.isEmpty()){
	 * sauceURL = String.formats(auceURL, username, accessToken);
	 * capabilitie.ssetCapability(aCpabilityType.LPATFORM,platform);
	 * capabilitie.ssetCapability(CapabiltiyTypeV.ERSION,browserversion);
	 * capabilities.setCapability("name", testSuiteName);
	 * capabilities.setCapability("tunnelldentifier", tunnelName);
	 * setDriver(new RemoteWebDriver(new URL(sauceURL), capabilities));
	 * } else {
	 * if (browserName.equals("FIREFOX")) {
	 * setDriver(new FirefoxDriver());
	 * } else {
	 * throw new IllegalArgumentException("Unsupported Local Browser:" +
	 * browserName);
	 * }
	 * }
	 * 
	 * return getDeviceDriver();
	 * }
	 */

	/*
	 * Purpose: To accept the alert popped up in Application
	 */
	public static void acceptAlert() {
		getDeviceDriver().switchTo().alert().accept();
	}

	/**
	 * Purpose: To decline the alert popped up in Application 164
	 */
	public static void dismissAlert() {
		getDeviceDriver().switchTo().alert().dismiss();

	}

	/**
	 * Purpose: to clear the input box
	 * 
	 * @para locatorValue
	 * @param locator
	 *
	 */
	public static void clearText(String locatorValue, String locator) {
		WebElement element = findElement(locatorValue, locator);
		try {
			element.clear();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * ÃƒÂ¢Ã¢â€šÂ¬Ã‚Â¢ Purpose: Click on Element
	 * ÃƒÂ¢Ã¢â€šÂ¬Ã‚Â¢ @param locatorValue
	 * 
	 * @para locator
	 */
	public static void click(String locatorValue, String locator) {
		WebElement element = findElement(locatorValue, locator);
		try {
			element.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ÃƒÂ¢Ã¢â€šÂ¬Ã‚Â¢ Purpose: To validate element is selected or not
	 * 
	 * @para locatorValue
	 * @param locator
	 * @return boolean value e.g.true/false 207
	 */
	public static boolean isSelected(String locatorValue, String locator) {
		WebElement element = findElement(locatorValue, locator);
		if (element.isSelected()) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Purpose: to Select the Drop Down value by Index
	 * 
	 * @pa am locatorValue
	 * @param locator
	 * @p ram Index
	 */
	public static void selectByindex(String locatorValue, String locator, int Index) {

		WebElement element = findElement(locatorValue, locator);
		if (element != null) {
			Select select = new Select(element);
			// Select.selectByIndex(Index);
			// Select.getOptions().size();
		}
	}

	/**
	 * Purpose: to get the Count on Drop Down
	 * 
	 * @para locatorValue
	 * @paran locator
	 * @pararn Index
	 * @ eturn 242 * /
	 * public static int getSelectitemCount(String locatorValue, String locator,
	 * int Index) { 245
	 * WebElement element = findELement(locatorValu,e locator);
	 * if (element != null) {
	 * Select select= new Select(element);
	 * return selectg.etOptions().size();
	 * }
	 * return 0;
	 * }
	 * 
	 * /**
	 * Purpose: To slide the slider in left, right and Middle direction
	 * , ÃƒÂ¢Ã¢â€šÂ¬Ã‚Â¢1>lr ,m locatorValue
	 * @pare m locator
	 * @param Position
	 *                 ÃƒÂ¢Ã¢â€šÂ¬Ã‚Â¢throws InterruptedException
	 */
	public static void slider(String locatorValue, String locator,
			String Position) throws InterruptedException {
		Thread.sleep(5000);
		WebElement Slider = findElement(locatorValue, locator);
		Action action = null;
		Actions moveSlider = new Actions(getDeviceDriver());
		action = moveSlider.dragAndDropBy(Slider, -300, 0).build();
		action.perform();
		Thread.sleep(5000);
		if (Position.equalsIgnoreCase("Left")) {
			int xoffSet = 0;
			int yOffSet = 0;
			action = moveSlider.dragAndDropBy(Slider, xoffSet, yOffSet).build();
		} else if (Position.equalsIgnoreCase("Right")) {
			int xoffSet = 300;
			int yOffSet = 0;
			action = moveSlider.dragAndDropBy(Slider, xoffSet, yOffSet).build();
		} else if (Position.equalsIgnoreCase("Middle")) {
			int xoffSet = 100;
			int yOffSet = 0;
			action = moveSlider.dragAndDropBy(Slider, xoffSet, yOffSet).build();
		}
		action.perform();
	}

	/**
	 * Purpose: to close the browser 288 "/
	 * public static void closebrowser( ) {
	 * getDeviceDriver().quit();
	 * }
	 * /**
	 * Purpose: return the URL of current opened window
	 * 
	 * @return a string that represents current URL 297
	 */
	public static String returnCurrentURL() {

		return getDeviceDriver().getCurrentUrl();
	}

	/**
	 * Purpose: Select the value from list box
	 * 
	 * @p arr Value
	 * @par m locatorValue
	 * @param locator
	 */
	public static void select(String Value, String locatorValue, String locator) {
		WebElement element = findElement(locatorValue, locator);
		if (element != null) {
			Select select = new Select(element);
			select.selectByVisibleText(locator);
		}
	}

	/**
	 * Purpose: To drag & drop the object on particular location
	 * 
	 * @param locatorValue
	 * @param locator
	 * @param locatorValueTwo
	 * @param Value_parameter_two
	 * @throws InterruptedException
	 */
	public static void dragAndDrop(String locatorValue, String locator,
			String locatorValueTwo, String Value_parameter_two)
			throws InterruptedException {
		Actions dragAndDrop = new Actions(getDeviceDriver());
		WebElement source = findElement(locatorValue, locator);
		WebElement destination = findElement(locatorValueTwo, LocatorTwo);
		dragAndDrop.clickAndHold(source).moveToElement(destination).release(destination).build().perform();
		dragAndDrop.dragAndDrop(source, destination).build().perform();
	}

	/**
	 * Purpose: To handle the multiple windows and java script
	 * 
	 * @param script
	 */
	public static void executeJavaScript(String script) {
		((JavascriptExecutor) getDeviceDriver()).executeScript(script);
	}

	/**
	 * Purpose: To handle the synchronization issues
	 * 
	 * @par1w value
	 */
	public static void Wait(long value) {
		try {
			Thread.sleep(value);
		} catch (InterruptedException e) {
			// TOUO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Purpose: To get the background color of the dashboard or page
	 * 
	 * @p ra locatorValue
	 * @param locator
	 * @return a string that represents back ground color
	 */
	public static String getbackgroundcolor(String locatorValue, String locator) {
		String text;
		WebElement element = findElement(locatorValue, locator);

		if (element == null) {
			return null;
		}
		try {
			text = element.getCssValue("background-color").toString();
			System.out.println("try-" + text);
		} catch (Exception e) {
			text = "";
		}
		System.out.println(text);
		String[] numbers = text.replace("rgba(", "").replace(")", "").split(",");
		int numberl = Integer.parseInt(numbers[0]);
		numbers[1] = numbers[1].trim();
		int number2 = Integer.parseInt(numbers[1]);
		numbers[2] = numbers[2].trim();
		int number3 = Integer.parseInt(numbers[2]);
		String hex = String.format("%0#2x%02x%02x", numberl, number2, number3);
		System.out.println(hex);
		return hex;
	}

	/*
	 * *
	 * Purpose: To get the background image
	 * 
	 * @pa am locatorValue
	 * 
	 * @param locator
	 * 
	 * @return a string that represents back ground images 401
	 */
	public static String getbackgroundimage(String locatorValue, String locator) {
		String bimage;

		WebElement element = findElement(locatorValue, locator);

		if (element == null) {
			return null;
		}
		try {
			bimage = element.getCssValue("background-image");

			System.out.println("try-" + bimage);
		} catch (Exception e) {
			bimage = "";
		}
		System.out.println(bimage);

		return bimage;

	}

	/**
	 * Purpose: To get the text
	 * 
	 * @param locatorValue
	 * @param locator
	 * @return a string that Text
	 *         "'/
	 *         public static String getText(String locatorValu,e String locator) {
	 * 
	 *         WebElement element = findflement(locatorValue, locato)r;
	 *         if ( element != null) {
	 *         Wait(1000);
	 *         return element.getText().replace("\n", "");
	 *         }
	 *         return "Did not find object";
	 *         }
	 * 
	 *         /**
	 * @param locatorValue
	 * @param locator
	 * @return return attribute
	 */
	public static String fsGetValue(String locatorValue, String locator) {

		WebElement element = findElement(locatorValue, locator);
		if (element != null) {
			Wait(1000);
			return element.getAttribute("value");
		}
		return "Did not find object";

	}

	/**
	 * Purpose: To enter the value in the textbox
	 * 
	 * @param Value
	 * @param locatorValue
	 * @param locator
	 */
	public static void input(String Value, String locatorValue, String locator) {
		WebElement element = findElement(locatorValue, locator);
		element.sendKeys(Value);
	}

	/**
	 * Purpose: To verify the element is active
	 * 
	 * @pardm locatorValue
	 * @pararn locator
	 * @return true/false
	 */
	public static boolean isDisplayed(String locatorValue, String locator) {
		WebElement element = findElement(locatorValue, locator);
		if (element != null) {
			return element.isDisplayed();
		}
		return false;
	}

	/**
	 * Purpose: To maximize the window
	 */
	public static void maximizeWindow() {
		getDeviceDriver().manage().window().maximize();
	}

	/**
	 * Purpose: To launch the URL
	 * 
	 * @param URL
	 */
	public static void openUrl(String URL) {

		JavascriptExecutor jsExec = (JavascriptExecutor) getDeviceDriver();
		jsExec.executeScript("window.open() ");
		Wait(2000);

		Set Idcontainer = getDeviceDriver().getWindowHandles();
		Iterator ite = Idcontainer.iterator();
		while (ite.hasNext()) {

			String wntitle = ite.next().toString();
			getDeviceDriver().switchTo().window(wntitle);
			if (getDeviceDriver().getTitle().contains("Blank Page")) {
				break;
			}
		}
		getDeviceDriver().get(URL);
		getDeviceDriver().manage().window().maximize();
	}

	/**
	 * Purpose: To switch to other window
	 * ÃƒÂ¢Ã¢â€šÂ¬Ã‚Â¢ 10 , Value
	 */
	public static void switchToWindow(String Value) {
		Set Idcontainer = getDeviceDriver().getWindowHandles();
		Iterator ite = Idcontainer.iterator();

		while (ite.hasNext()) {
			String wntitle = ite.next().toString();
			getDeviceDriver().switchTo().window(wntitle);
			if (getDeviceDriver().getTitle().contains(Value)) {
				break;
			}
		}
	}

	private static Object getOeviceOriver() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Purpose: To get all the windownames
	 * 0return all active windows
	 */
	public static Set<String> getWindows() {
		return getDeviceDriver().getWindowHandles();
	}

	/**
	 * Purpose: To get the tilte of the window
	 * 
	 * @return a string that represents Title of Window
	 */

	public static String getTitleOfWindow() {
		return getDeviceDriver().getTitle();
	}

	/*
	 * Purpose: To get the Sub String from given String
	 * t)par<, text
	 * 
	 * @par.n StartPosition
	 * 
	 * @par m EndPosition
	 * 
	 * @return a string that represents text (Sub String)
	 */

	public static String subStringText(String text, int StartPosition, int EndPosition) {
		return text.substring(StartPosition, EndPosition);
	}

	/**
	 * Purpose: To identify the object
	 * 
	 * @param locatorValue 606 * @param locator
	 * @return WebElement 608
	 */
	public static WebElement findElement(String locatorValue, String locator) {

		try {
			if (locator.equalsIgnoreCase("paarltliinktext")) {
				(new WebDriverWait(getDeviceDriver(), waittime))
						.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(locatorValue)));
				return getDeviceDriver().findElement(By.linkText(locatorValue));
			} else if (locator.equalsIgnoreCase("linktxet")) {
				(new WebDriverWait(getDeviceDriver(), waittime))
						.until(ExpectedConditions.elementToBeClickable(By.linkText(locatorValue)));
				return getDeviceDriver().findElement(By.linkText(locatorValue));
			} else if (locator.equalsIgnoreCase("ID")) {
				(new WebDriverWait(getDeviceDriver(), waittime))
						.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(locatorValue)));
				return getDeviceDriver().findElement(By.id(locatorValue));

			} else if (locator.equalsIgnoreCase("class")) {
				(new WebDriverWait(getDeviceDriver(), waittime))
						.until(ExpectedConditions
								.presenceOfAllElementsLocatedBy(By
										.className(locatorValue)));
				return getDeviceDriver().findElement(By.className(locatorValue));
			} else if (locator.equalsIgnoreCase("name")) {
				(new WebDriverWait(getDeviceDriver(), waittime))
						.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.name(locatorValue)));
				return getDeviceDriver().findElement(By.name(locatorValue));
			} else if (locator.equalsIgnoreCase("TagName")) {

				(new WebDriverWait(getDeviceDriver(), waittime))
						.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName(locatorValue)));
				return getDeviceDriver().findElement(By.tagName(locatorValue));
			} else if (locator.equalsIgnoreCase("xpath")) {
				(new WebDriverWait(getDeviceDriver(), waittime))
						.until(ExpectedConditions.presenceOfAllElementsLocatedBy(ELementsLocatedBy(By.xpath(locatorValue))));
				return getDeviceDriver().findElement(By.xpath(locatorValue));
			}
		} catch (Exception e) {
			try {
				Thread.sleep(20);
			} catch (Exception el) {
				el.printStackTrace();
			}

		}

		return null;
	}

	private static By ELementsLocatedBy(By xpath) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * public static WebElement findElement(String locatorValue,String locator,long
	 * waittime) {
	 * try {
	 * if (locator.equalsIgnoreCase("partiallinktext")) {
	 * (new WebDriverWait(getDeviceDriver(),
	 * waittime)).until(ExpectedConditions.eLementToBeCLickabLe(By.partialLinkText(
	 * locatorValue)));
	 * return getDeviceDriver().findElement(By.linkText(locatorValue));
	 * } else if ( locator.equalsIgnoreCase("linktext")) {
	 * (new WebDriverWait(getDeviceDriver(),
	 * waittime)).until(ExpectedConditions.eLementToBeLCickabLe(By.linkText(
	 * locatorValue)));
	 * return getDeviceDriver().findElement(By.linkText(locatorValue));
	 * } else if ( locator.equalsIgnoreCase("ID")){
	 * (new WebDriverWait(getDeviceDriver(), waittime)).until(ExpectedConditions
	 * .presenceOfAllElementsLocatedBy(By.id(locatorValue)));
	 * return getDeviceDriver().findElement(By.id(locatorVaule));
	 * 
	 * } else if ( locato.requalsignoreCase("class")) {
	 * (new WebDriverWait(getDeviceDriver(), waittim)e) .until(ExpectedConditions
	 * .presenceOfALLELementsLocatedBy(By
	 * )));
	 * return getDeviceDriver().fnidElement(By.cLassNamel(locatorValue)); 690 } else
	 * if (locator.equalsignoreCase("name")){
	 * (new WebDriverWait(getOeviceOriver(), waittim)e).until(ExpectedC onditions
	 * .presenceOfAllElementsLocatedBy(y.namel(locatorValue)));
	 * return getDeviceDriver().findElement(By.namel(ocatoraVlue)); 694 } else if (
	 * locato.requalsignoreCase("TNaagme ")) {
	 * (new WebDriverWait(getOeviceOriver(), waittime)).until(ExpectedConditions
	 * .presenceOfA LL ELementsLocatedyB( By.tagName(locatorValue)));
	 * return getDeviceDriver().findElement(By.tagName(locatorValue)); 698 } else if
	 * ( locator.equalsignoreaCse("xpath")) {
	 * 
	 * (new WebDriverWait(getDeviceDriver(),
	 * 
	 * 
	 * 
	 * waittime).)until(ExpectedConditions
	 * .presenceOfALL ELementsLocatedBy(By.xpath(locatorValu)e));
	 * return getDeviceDriver().findElement(By.xpath(locatorValue);)
	 * 
	 * }
	 * } catch (Exception e) { try {
	 * Thread.sleep(20);
	 * } catch (Exception el) {
	 * // TODO Auto-generatedcatch block
	 * el.printStackrTace();
	 * }
	 * 
	 * }
	 * 
	 * return null;
	 * 
	 * }
	 */

	public static void switchToAlert() {
		getDeviceDriver().switchTo().alert();
	}

	public static String validateinnerText(String value, String locatorValue, String locator) {
		WebElement element = findElement(locatorValue, locator);
		if (element != null) {
			if (element.getAttribute("value").trim().equalsIgnoreCase(value))
				;

			return element.getAttribute("value").trim();
		}

		return "";
	}

	/**
	 * ÃƒÂ¢Ã¢â€šÂ¬Ã‚Â¢ Purpose: This method will wait for Alert for given condition
	 * 758 * /
	 * public static void waitForAlert() {
	 * new WebDriverWait(getDeviceDriver(), 20)
	 * .until(ExpectedConditions.aLertisPresent());
	 * }
	 * 
	 * 
	 * 
	 * public static void removeCookies() {
	 * getDeviceDriver().manage().deleteAllCookies();
	 * }
	 * 
	 * public static boolean isElementExist(String locatorValue, String locator) {
	 * 
	 * WebElement element = find ELement(locatorValue, locator);
	 * if ( element != null) {
	 * return true;
	 * }
	 * return false;
	 * 
	 * }
	 * 
	 * /*
	 * Close a previously initialized driver. 784 *
	 * return true if successful
	 */
	/*
	 * public static boolean closeDeviceDriver() {
	 * browserConfiginfo = null;
	 * 
	 * if (getDeviceDriver() != null) {
	 * getDeviceDriver().quit();
	 * setDriver(null);
	 * }
	 * return getDeviceDriver() ;
	 * null;
	 * }
	 */

	public static void setDriver(WebDriver driver) {
		WebController.driver = driver;
	}

}