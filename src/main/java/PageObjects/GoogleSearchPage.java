package PageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleSearchPage {

	@FindBy(id = "APjFqb")
	private WebElement googleSearchBox;

	@FindBy(xpath = "//div[@class='yuRUbf']//a/descendant::h3[@class='LC20lb MBeuO DKV0Md']")
	private WebElement ebayHomeLink;

	public GoogleSearchPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void Search(String search) {
		googleSearchBox.sendKeys(search);
		googleSearchBox.sendKeys(Keys.ENTER);
		
	}

	public void clickeBayLink() {

		ebayHomeLink.click();
	}
}
