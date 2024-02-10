package page;

import java.util.Random;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class BasePage {

	public void performValidation(String actual, String expected, String errorMsg) {
		Assert.assertEquals(actual, expected, errorMsg);
	}

	public void selectFromDropdown(WebElement element, String visibleText) {
		Select sel = new Select(element);
		sel.selectByVisibleText(visibleText);
	}

	public int generateRandomNum(int boundNum) {
		Random rnd = new Random();
		int randomNum = rnd.nextInt(boundNum);
		return randomNum;
	}

}
