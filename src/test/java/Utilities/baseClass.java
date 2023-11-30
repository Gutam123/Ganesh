package Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class baseClass {

	private static WebDriver driver;

	@BeforeMethod(alwaysRun = true)

	public static WebDriver getDriver() {
		if (driver == null) {
			switch (baseClass.getProperty("browser")) {
			case "chrome":
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				break;
			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				break;
			case "safari":
				if (!System.getProperty("os.name").toLowerCase().contains("mac")) {
					throw new WebDriverException("your os doesnt support safari");
				}
				WebDriverManager.safaridriver().setup();
				driver = new SafariDriver();
				break;
			case "edge":
				if (!System.getProperty("os.name").toLowerCase().contains("window")) {
					throw new WebDriverException("your os doesnt support Edge");
				}
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
				break;
			}
			driver.get(baseClass.getProperty("url"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			driver.findElement(By.xpath("xpath")).click();
		}
		return driver;
	}

	@AfterMethod(alwaysRun = true)
	public static void tearDown() {
		if (driver != null) {
			driver.close();
			driver = null;
		}
	}

	// propertiesReader

	private static Properties properties;

	static {
		try {
			String filepath = "src\\test\\resources\\properties/userData.properties ";
			// open connection to the file

			FileInputStream file = new FileInputStream(filepath);
			properties = new Properties();
			properties.load(file);
			file.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}// this it the end of the static block

	public static String getProperty(String KeyName) {
		return properties.getProperty(KeyName);

	}
}
