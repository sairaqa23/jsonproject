package test;

import java.io.FileReader;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.testng.Assert;

import page.DashboardPage;
import page.LoginPage;
import util.BrowserFactory;


public class LoginTest {

	WebDriver driver;
	JsonParser parserObj;
	JsonElement jsonEleObj;
	
	@SuppressWarnings("deprecation")
	@BeforeMethod(alwaysRun=true)
	public void readJson() {
		
		try {
			FileReader reader = new FileReader("src\\main\\java\\testData\\TF_TestData.json");
			parserObj = new JsonParser();
			jsonEleObj = parserObj.parse(reader);
			jsonEleObj.isJsonObject();
			String str = jsonEleObj.getAsJsonObject().get("TestData").getAsString();
			System.out.println("===============" + str);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test(groups={"smoke", "regression"})
	public void validUserShouldBeAbleToLogin() {

		driver = BrowserFactory.init();
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.insertUserName(jsonEleObj.getAsJsonObject().get("LoginInfo").getAsJsonObject().get("UserName").getAsString());
		loginPage.insertPassword(jsonEleObj.getAsJsonObject().get("LoginInfo").getAsJsonObject().get("Password").getAsString());
		loginPage.clickSigninButton();

		DashboardPage dashboardPage = PageFactory.initElements(driver, DashboardPage.class);
		dashboardPage.validateDashboardPage(jsonEleObj.getAsJsonObject().get("LoginInfo").getAsJsonObject().get("ValidationTextLogin").getAsString());
		BrowserFactory.tearDown();
	}
	
	@Test(groups= {"regression"})
	public void validateLoginPageErrorMsgs() {
		driver = BrowserFactory.init();
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.validateUserNameErrorMsg(jsonEleObj.getAsJsonObject().get("LoginInfo").getAsJsonObject().get("alertUserValidationText").getAsString());
		loginPage.validatePasswordErrorMsg(jsonEleObj.getAsJsonObject().get("LoginInfo").getAsJsonObject().get("UserName").getAsString(), jsonEleObj.getAsJsonObject().get("LoginInfo").getAsJsonObject().get("alertPasswordValidationText").getAsString());
		BrowserFactory.tearDown();
	}

}
