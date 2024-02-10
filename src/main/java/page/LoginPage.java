package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

public class LoginPage extends BasePage{
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	//attributes or webElements
	@FindBy(how = How.XPATH, using = "//*[@id=\"user_name\"]") WebElement USER_NAME_ELEMENT;
	@FindBy(how = How.XPATH, using = "//*[@id=\"password\"]") WebElement PASSWORD_ELEMENT;
	@FindBy(how = How.XPATH, using = "//*[@id=\"login_submit\"]") WebElement SIGNIN_BUTTON;
	
	
	//methods or actions
	
	public void insertUserName(String userName) {
		USER_NAME_ELEMENT.sendKeys(userName);
	}
	
	public void insertPassword(String password) {
		PASSWORD_ELEMENT.sendKeys(password);
	}
	
	public void clickSigninButton() {
		SIGNIN_BUTTON.click();
	}
	
	public void validateUserNameErrorMsg(String expectedErrorMsg) {
		SIGNIN_BUTTON.click();
		String errorMsg = driver.switchTo().alert().getText();
//		Assert.assertEquals(errorMsg, expectedErrorMsg, "Error Msg is not available!");
		performValidation(errorMsg, expectedErrorMsg, "Error Msg is not available!");
		driver.switchTo().alert().accept();
	}
	
	public void validatePasswordErrorMsg(String userName, String expectedErrorMsg) {
		USER_NAME_ELEMENT.sendKeys(userName);
		SIGNIN_BUTTON.click();
		String errorMsg = driver.switchTo().alert().getText();
		performValidation(errorMsg, expectedErrorMsg, "Error Msg is not available!");
		driver.switchTo().alert().accept();
	}
	

}
