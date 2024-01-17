package TestClasses;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Base.BaseClass;
import PageObjects.EBayHomePage;

public class EBayHomeTest extends BaseClass {

	private EBayHomePage eBayHome;

	@BeforeMethod()

	public void objectInitialize() {

		eBayHome = new EBayHomePage(driver);

	}

	@Test(priority = 1)

	public void loginTest() {

		boolean actualResult = eBayHome.userSignIn("achiniwimalasiri@gmail.com", "Nimhas2020");
		boolean expectedResult = true;

		SoftAssert verifyLogin = new SoftAssert();
		verifyLogin.assertEquals(actualResult, expectedResult, "User Login is not successful");
		verifyLogin.assertAll();

	}

	@Test(priority = 2)

	public void itemSelectAndAddToCart() {

		String finalSelectedItem = eBayHome.Itemselect("iphone 12");
		String finalAddToCartItem = eBayHome.goToCart();


		SoftAssert verifyItem = new SoftAssert();
		verifyItem.assertEquals(finalAddToCartItem, finalSelectedItem, "mismatch of selected item and AddToCart item");
		verifyItem.assertAll();

	}

}
