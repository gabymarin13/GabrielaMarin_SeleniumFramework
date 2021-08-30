package Selenium;

import DataProviders.UsersProvider;
import PageObjects.*;
import Pojo.UserAccount;
import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;


public class TestAccount extends BaseClass {
    public static final String ERROR_EMAIL_AND_PASSWORD_INVALID_MESSAGE = "warning: no match for e-mail address and/or password.";

    public By logOutButtonLocator = By.linkText("Logout");
    public By alertMessageLocator = By.xpath("//div[contains(@class, 'alert-danger')]");

    @Description("Validate test login was successful")
    @Test(description = " Test Login Success")
    public void Test_Login_Successful(){
        String username = "gabriela.marin.chaves@ucreativa.com";
        String password = "asdgk";


        //Go to Login page
        headerPage().clickOnMyAccount();
        headerPage().clickOnLoginButton();


        //Llenar el formulario
        loginPage().EnterEmail(username);
        loginPage().EnterPassword(password);
        loginPage().ClickSubmitButton();

        WebElement logOutButton = driver.findElement(By.linkText("Logout"));
        Assert.assertTrue(logOutButton.isDisplayed());
    }


    @Description("Validate that the login is working with non valid credentials")
    @Test(description = " Test Login Not Success")
    public void Test_Login_Unsuccessful(){
        LoginPage loginPage = new LoginPage(driver);
        String username = "gabriela.marin.chaves@ucreativa.com";
        String password = "aaaaa";
        String expectedMessage = "warning: no match for e-mail address and/or password.";

        loginPage.GoTo();
        loginPage.login(username,password);


        WebElement alertMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-danger')]"));
        Assert.assertEquals(expectedMessage.toLowerCase(), alertMessage.getText().toLowerCase().trim());
    }

    @Test
    public void Test_Create_New_Account(){
        //SETUP
        String firstName = "David";
        String lastName = "Vargas";
        String email = Utils.generadorDeEmailRandom();
        String telephone = "888888";
        String password = "555hola.";
        String expectedMessage = "Your Account Has Been Created!";

        RegisterPage registerPage = new RegisterPage(driver);

        //RUN
        registerPage.GoTo();
        registerPage.FillForm(firstName, lastName, email, telephone, password);

        //VALIDATION
        Assert.assertEquals(registerPage.GetConfirmationMessage(), expectedMessage);
    }

    @Test (dataProvider = "getUsersData", dataProviderClass = UsersProvider.class)
    public void Test_Login_With_Data(UserAccount testUser){
        loginPage().GoTo();
        loginPage().login(testUser.getEmail(), testUser.getPassword());

        if(testUser.userExistsMethod())
            Assert.assertTrue(driver.findElement(logOutButtonLocator).isDisplayed());
        else
            Assert.assertEquals(ERROR_EMAIL_AND_PASSWORD_INVALID_MESSAGE.toLowerCase(), driver.findElement(alertMessageLocator));
    }


    @Test
    public void Test_Duplicated_Email(){
        //SETUP
        String firstName = "Gabriela";
        String lastName = "Marin Chaves";
        String email = "gabriela.marin.chaves@ucreativa.com";
        String telephone = "888888";
        String password = "asdgk";
        String expectedMessage = "Warning: E-Mail Address is already registered!";
        RegisterPage registerPage = new RegisterPage(driver);

        //RUN
        registerPage.GoTo();
        registerPage.FillForm(firstName, lastName, email, telephone, password);

        //VALIDATION
        Assert.assertEquals(registerPage.GetEmailDuplicatedMessage().trim(), expectedMessage.trim());
    }
}

