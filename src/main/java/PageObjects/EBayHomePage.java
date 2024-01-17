package PageObjects;

import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EBayHomePage {

	private WebDriver driver;

	@FindBy(xpath = "//a[@href='https://signin.ebay.com/ws/eBayISAPI.dll?SignIn&ru=https%3A%2F%2Fwww.ebay.com%2F']")
	private WebElement signInLink;

	@FindBy(id = "gh-shop-a")
	private WebElement elementofHomePage;

	@FindBy(id = "userid")
	private WebElement email;

	@FindBy(id = "signin-continue-btn")
	private WebElement signInContinueBtn;

	@FindBy(id = "pass")
	private WebElement password;

	@FindBy(id = "sgnBt")
	private WebElement signInBtn;

	@FindBy(id = "gh-ac")
	private WebElement itemSearchBox;

	@FindBy(id = "gh-btn")
	private WebElement submitSearch;

	@FindBy(xpath = "//img[@alt='Apple iPhone 12 64GB AT&T (Black Blue Purple) Smartphone 5G']")
	private WebElement itemLink;

	// @FindBy(xpath
	// ="//div[@id='srp-river-results']//ul//li[@data-view='mi:1686|iid:1']")
	// private WebElement itemLink;

	@FindBy(xpath = "//select[@id='x-msku__select-box-1000']")
	private WebElement colourDropDown;

	@FindBy(id = "x-msku__option-box-0")
	private WebElement colourselector;

//	@FindBy(xpath ="//a[@href='javascript:;']")
//  private WebElement addToCartBtn;

	@FindBy(linkText = "Add to cart")
	private WebElement addToCartBtn;

	@FindBy(xpath = "//div[@class='item-variations']")
	private WebElement itemColourIncart;

	@FindBy(xpath = "//h3[@class='item-title text-truncate-multiline black-link lines-2']")
	private WebElement itemDescriptionIncart;

	@FindBy(xpath = "//div[@class='vim x-item-title']")
	private WebElement itemDescriptionSelected;

	public EBayHomePage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public boolean userSignIn(String emailID, String passwordText) {

		try {

			signInLink.click();
			email.sendKeys(emailID);
			signInContinueBtn.click();
			WebDriverWait wait1 = new WebDriverWait(driver, 40);
			wait1.until(ExpectedConditions.visibilityOf(password));
			password.sendKeys(passwordText);
			signInBtn.click();
			return elementofHomePage.isDisplayed();

		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("navigated to the security checking page or any other exception occured");
			driver.navigate().back();

			return false;

		}

	}

	public String Itemselect(String item) {
		try {
			itemSearchBox.sendKeys(item);
			submitSearch.click();

			String mainWindowHandle = driver.getWindowHandle();
			itemLink.click();
			Set<String> allWindowHandles = driver.getWindowHandles();

			for (String windowHandle : allWindowHandles) {
				if (!windowHandle.equals(mainWindowHandle)) {
					// Switch to the new window
					driver.switchTo().window(windowHandle);

				}

			}

			WebDriverWait wait2 = new WebDriverWait(driver, 40);
			wait2.until(ExpectedConditions.visibilityOf(colourDropDown));
			colourDropDown.click();
			colourselector.click();

		} catch (Exception e) {

			e.printStackTrace();

		}

		String itemDescriptionSelect = itemDescriptionSelected.getText();
		String colourSelect = colourselector.getText();
		String finalSelectedItm = itemDescriptionSelect + "-" + colourSelect;
		return finalSelectedItm;

	}

	public String goToCart() {
		try {
			// Scroll down using JavaScriptExecutor
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,500)");

			addToCartBtn.click();
			WebDriverWait wait3 = new WebDriverWait(driver, 40);
			wait3.until(ExpectedConditions.visibilityOf(itemDescriptionIncart));

		} catch (Exception e) {

			e.printStackTrace();

		}
		String cartItemDescription = itemDescriptionIncart.getText();
		String colourIncart = itemColourIncart.getText();
		String finalAddToCartItem = cartItemDescription + "-" + colourIncart;
		return finalAddToCartItem;
	}

}
