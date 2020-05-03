package CharmboardtestCase;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import Charmboardutilities.ReadConfig;

public class BaseClass
{
	ReadConfig readconfig = new ReadConfig();
	public static WebDriver driver;
	public String baseURL = readconfig.getApplicationURL();
	public static Logger logger;

	@Parameters("browser")
	@BeforeClass
	public void setup(String br) {

		Logger logger = Logger.getLogger("ecommerce");
		PropertyConfigurator.configure("Log4j.properties");	
		if (br.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver",readconfig.getFirefoxPath()); 
			driver = new FirefoxDriver();
		}else if(br.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",readconfig.getChromePath()); 
			driver = new ChromeDriver();
			//System.setProperty("webdriver.chrome.driver",  System.getProperty("user.dir")+"//Browser//chromedriver.exe");
		}
		driver.get(baseURL);
	}

	@AfterClass
	public void teardown() {
		driver.quit();
	}

	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}
	
	public String randomestring()
	{
		String generatedstring=RandomStringUtils.randomAlphabetic(8);
		return(generatedstring);
	}
	
	public static String randomeNum() {
		String generatedString2 = RandomStringUtils.randomNumeric(4);
		return (generatedString2);
	}
	
}




