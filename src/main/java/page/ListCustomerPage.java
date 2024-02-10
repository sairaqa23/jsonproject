package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;


public class ListCustomerPage extends BasePage{
	
	WebDriver driver;
	
	public ListCustomerPage(WebDriver driver) {
		this.driver = driver;
	}
	
	@FindBy(how = How.XPATH, using = "//strong[text()='Customer List']") WebElement LIST_CUSTOMER_VALIDATION_ELEMENT;
	@FindBy(how = How.XPATH, using = "//tbody/tr[1]/td[9]/a[1]") WebElement EDIT_BUTTON_1ST_ELEMENT;
	
	
	public void validatenListCustomerPage(String listCustomerText) {
		Assert.assertEquals(LIST_CUSTOMER_VALIDATION_ELEMENT.getText(), listCustomerText, "List Customer page is not available!");
	}
	
	public void clickOnEditButtonOf1stElement() {
		EDIT_BUTTON_1ST_ELEMENT.click();
	}
	
	
	
	//tbody/tr[1]/td[2]/a
	//tbody/tr[2]/td[2]/a
	//tbody/tr[3]/td[2]/a
	//tbody/tr[ + i + ]/td[2]/a
	//tbody/tr[1]/td[9]/button
	
	String before_xpath = "//tbody/tr[";
	String after_xpath = "]/td[2]/a";
	String after_xpath_delete = "]/td[9]/button";
	
	public void validateInsertedNameAndDelete() {
		
		for(int i = 1; i <= 10; i ++) {
			String actualName = driver.findElement(By.xpath(before_xpath + i + after_xpath)).getText();
			System.out.println(actualName);
			if(actualName.contains(NewCustomerPage.getInsertedName())) {
				System.out.println("Name exist..");
				driver.findElement(By.xpath(before_xpath + i + after_xpath_delete)).click();
			}
			break;
		}
		
	}
	

	
}
