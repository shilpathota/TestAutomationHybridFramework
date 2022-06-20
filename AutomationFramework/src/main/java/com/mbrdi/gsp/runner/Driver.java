package com.mbrdi.gsp.runner;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.mbrdi.gsp.test.common.GlobalVariables;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class Driver {
		public static WebDriver driver ;
//		public static WebDriver portaldriver = null;
		public static AppiumDriver Appdriver = null ;
		public static String Iteration ="";
		protected File classpathRoot;
		private File appDir;
		private File app;
		private DesiredCapabilities capabilities = new DesiredCapabilities();


		public enum typeOfBrowser {
			Firefox, Chrome, IE, Android, AndroidChrome, WS, iOS, iOSSafari, HybridChromeAndroid, HybridSafariiOS;
		}

		public void openDriver(String browser) {

			try {
				switch (typeOfBrowser.valueOf(browser)) {
				case Firefox:
					
					//objlog4j.info("FF is selected");
				//	System.setProperty("webdriver.firefox.bin","C:\\Program Files\\Mozilla Firefox\\firefox.exe");
					System.setProperty("webdriver.gecko.driver", GlobalVariables.Firefoxdriver);
					System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"true");
					System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"/dev/null");
			        driver = new FirefoxDriver(); 
			        driver.manage().window().maximize();
				    break;
					
				case Chrome:
					//objlog4j.info("Google chrome is selected");
					System.setProperty("webdriver.chrome.driver", GlobalVariables.Chromedriver);
					HashMap<String, Object> prefs = new HashMap<String, Object>();
					prefs.put("download.default_directory", "reports" + File.separator + "Downloads");
					DesiredCapabilities caps = DesiredCapabilities.chrome();
					ChromeOptions options = new ChromeOptions();
					options.setExperimentalOption("useAutomationExtension", false);
					options.setExperimentalOption("prefs", prefs);
					caps.setCapability(ChromeOptions.CAPABILITY, options);
					// options.setExperimentalOption("prefs", prefs);
					// options.addArguments("--test-type");
				    options.addArguments("start-maximized");
					driver = new ChromeDriver(caps);
					driver.manage().deleteAllCookies();
					driver.manage().window().maximize();
					break;
					
				case IE:
					/*objlog4j.info("Internet Explorer is selected");
					System.setProperty("webdriver.ie.driver", GlobalVariables.IEdriver);
					DesiredCapabilities dcie = DesiredCapabilities.internetExplorer();
					dcie.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
					driver = new InternetExplorerDriver(dcie);
					driver.manage().window().maximize();*/
					
					System.setProperty("webdriver.ie.driver", GlobalVariables.IEdriver);
					DesiredCapabilities dcie = DesiredCapabilities.internetExplorer();
					dcie.setCapability("nativeEvents", false);
	                dcie.setCapability("ignoreZoomSettings", true);
	                dcie.setCapability("ie.ensureCleanSession", true);
	                driver = new InternetExplorerDriver(dcie);
	                driver.manage().window().maximize();
					break;
					
				case HybridChromeAndroid:
					//objlog4j.info("Chrome is selected");
					System.setProperty("webdriver.chrome.driver", GlobalVariables.Chromedriver);
					HashMap<String, Object> prefs1 = new HashMap<String, Object>();
					prefs1.put("download.default_directory", System.getProperty("user.dir") + "MBRDIFramework/Download");
					DesiredCapabilities caps1 = DesiredCapabilities.chrome();
					ChromeOptions options1 = new ChromeOptions();
					options1.setExperimentalOption("useAutomationExtension", false);
					options1.setExperimentalOption("prefs", prefs1);
					caps1.setCapability(ChromeOptions.CAPABILITY, options1);
					driver = new ChromeDriver(caps1);

					// caps.setCapability(ChromeOptions.CAPABILITY, options);
					// options.setExperimentalOption("prefs", prefs);
					options1.addArguments("--test-type");
					options1.addArguments("start-maximized");
					driver.manage().window().maximize();

					//objlog4j.info("Android Broswer is selected");

					capabilities = new DesiredCapabilities();
					capabilities.setCapability(CapabilityType.BROWSER_NAME, GlobalVariables.g_strAndroidBrowserName);
					capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,GlobalVariables.g_strAndroidPlatformName);
					capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,GlobalVariables.g_strAndroidPlatformVersion);
					capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,GlobalVariables.g_strAndroidDeviceName);	
					capabilities.setCapability(MobileCapabilityType.UDID, GlobalVariables.g_strAndroidUDID);
					capabilities.setCapability("orientation", "LANDSCAPE");
					capabilities.setCapability("newCommandTimeout", 50000);
					capabilities.setCapability("unicodeKeyboard", true);
					capabilities.setCapability("resetKeyboard", true);

					driver = new AndroidDriver<AndroidElement>(new URL("http://localhost:4723/wd/hub"), capabilities);
					Thread.sleep(10000);

					break;
				case HybridSafariiOS:
					//objlog4j.info("Chrome is selected");
					System.setProperty("webdriver.chrome.driver", GlobalVariables.Chromedriver);
					HashMap<String, Object> prefs2 = new HashMap<String, Object>();
					prefs2.put("download.default_directory", System.getProperty("user.dir") + "nuvizz/downloads");
					caps = DesiredCapabilities.chrome();
					options = new ChromeOptions();
					options.setExperimentalOption("prefs", prefs2);
					caps.setCapability(ChromeOptions.CAPABILITY, options);
					driver = new ChromeDriver(caps);

					// caps.setCapability(ChromeOptions.CAPABILITY, options);
					// options.setExperimentalOption("prefs", prefs);
					options.addArguments("--test-type");
					options.addArguments("start-maximized");
					driver.manage().window().maximize();

					//objlog4j.info("iOS Safari Broswer is selected");
					capabilities = new DesiredCapabilities();
					capabilities.setCapability(CapabilityType.BROWSER_NAME, GlobalVariables.g_strIOSBrowserName);
					capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, GlobalVariables.g_strIOSDeviceName);
					capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,
							GlobalVariables.g_strIOSPlatformVersion);
					capabilities.setCapability(MobileCapabilityType.UDID, GlobalVariables.g_strIOSUDID);
					capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, GlobalVariables.g_strIOSPlatformName);
					capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");

					Appdriver = new IOSDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
					Thread.sleep(10000);

					break;

				default:
					break;

				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}


}
