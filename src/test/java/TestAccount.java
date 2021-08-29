import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;


public class TestAccount extends BaseClass{

    @Description("Validate test login was successful")
    @Test(description = " Test Login Success")
    public void Test_Login_Successful(){
        String username = "gabriela.marin.chaves@ucreativa.com";
        String password = "asdgk";


        //Go to Login page
        driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/a/span[1]")).click();
        driver.findElement(By.linkText("Login")).click();


        //Llenar el formulario
        driver.findElement(By.name("email")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.cssSelector("[value='Login']")).click();

        WebElement logOutButton = driver.findElement(By.linkText("Logout"));
        Assert.assertTrue(logOutButton.isDisplayed());
    }


    @Description("Validate that the login is working with non valid credentials")
    @Test(description = " Test Login Not Success")
    public void Test_Login_Unsuccessful(){
        String username = "gabriela.marin.chaves@ucreativa.com";
        String password = "aaaaa";
        String expectedMessage = "warning: no match for e-mail address and/or password.";


        //Go to Login page
        driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/a/span[1]")).click();
        driver.findElement(By.linkText("Login")).click();


        //Llenar el formulario
        driver.findElement(By.name("email")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.cssSelector("[value='Login']")).click();


        WebElement alertMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-danger')]"));
        Assert.assertEquals(expectedMessage.toLowerCase(), alertMessage.getText().toLowerCase().trim());
    }
}

