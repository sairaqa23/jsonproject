package test;

import java.io.FileReader;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import page.DashboardPage;
import page.ListCustomerPage;
import page.LoginPage;
import page.NewCustomerPage;
import util.BrowserFactory;


public class ListCustomerTest {
	
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

	
	@Test(groups= {"regression"})
	public void userShouldBeAbleToEditCustomer() {
		
		driver = BrowserFactory.init();
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.insertUserName(jsonEleObj.getAsJsonObject().get("LoginInfo").getAsJsonObject().get("UserName").getAsString());
		loginPage.insertPassword(jsonEleObj.getAsJsonObject().get("LoginInfo").getAsJsonObject().get("Password").getAsString());
		loginPage.clickSigninButton();
		
		DashboardPage dashboardPage = PageFactory.initElements(driver, DashboardPage.class);
		dashboardPage.validateDashboardPage(jsonEleObj.getAsJsonObject().get("LoginInfo").getAsJsonObject().get("ValidationTextLogin").getAsString());
		dashboardPage.clickOnCustomer();
		dashboardPage.clickOnListCustomer();
		
		ListCustomerPage listCustomerPage = PageFactory.initElements(driver, ListCustomerPage.class);
		listCustomerPage.validatenListCustomerPage("Customer List");
		listCustomerPage.clickOnEditButtonOf1stElement();
		
		NewCustomerPage newCustomerPage = PageFactory.initElements(driver, NewCustomerPage.class);
		newCustomerPage.validatenNewCustomerPage(jsonEleObj.getAsJsonObject().get("AddContact").getAsJsonObject().get("ValidationTextAddCustomer").getAsString());
		newCustomerPage.insertFullName(jsonEleObj.getAsJsonObject().get("AddContact").getAsJsonObject().get("FullName").getAsString());
		newCustomerPage.selectCompany(jsonEleObj.getAsJsonObject().get("AddContact").getAsJsonObject().get("Company").getAsString());
		newCustomerPage.insertEmail(jsonEleObj.getAsJsonObject().get("AddContact").getAsJsonObject().get("Email").getAsString());
		newCustomerPage.insertPhone(jsonEleObj.getAsJsonObject().get("AddContact").getAsJsonObject().get("Phone").getAsString());
		newCustomerPage.insertAddress(jsonEleObj.getAsJsonObject().get("AddContact").getAsJsonObject().get("Address").getAsJsonArray().get(0).getAsJsonObject().get("Street").getAsString());
		newCustomerPage.insertCity(jsonEleObj.getAsJsonObject().get("AddContact").getAsJsonObject().get("Address").getAsJsonArray().get(0).getAsJsonObject().get("City").getAsString());
		newCustomerPage.insertZip(jsonEleObj.getAsJsonObject().get("AddContact").getAsJsonObject().get("Address").getAsJsonArray().get(0).getAsJsonObject().get("Zip").getAsString());
		newCustomerPage.selectCountry(jsonEleObj.getAsJsonObject().get("AddContact").getAsJsonObject().get("Address").getAsJsonArray().get(0).getAsJsonObject().get("Country").getAsString());
		newCustomerPage.clickSaveButton();

		
	
		
		
	}

}
