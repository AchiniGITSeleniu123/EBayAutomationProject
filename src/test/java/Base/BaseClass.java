package Base;

import PageObjects.GoogleSearchPage;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseClass {

	protected WebDriver driver;

	@BeforeClass
	public void setup() throws IOException {

		System.setProperty("webdriver.chrome.driver", "D:\\Softwares\\chrome_new\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.google.com/");
		GoogleSearchPage google = new GoogleSearchPage(driver);
		google.Search("eBay");
		google.clickeBayLink();

	}

	@AfterClass
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

}