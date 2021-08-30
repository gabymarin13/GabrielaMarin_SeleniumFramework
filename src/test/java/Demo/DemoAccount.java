package Demo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;


public class DemoAccount {

    @BeforeTest
    public void setup(){
        WebDriverManager.chromedriver().setup();
    }

    @Ignore
    @Test
    public void test_capabilites(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1920,1080");
        //options.addArguments("--headless");
        options.setHeadless(true);
        options.setAcceptInsecureCerts(true);

        WebDriver driver = new ChromeDriver(options);
        //driver.get("https://www.seleniumeasy.com/test/");
        driver.get("https://expired.badssl.com/");
        Assert.assertTrue(driver.findElement(By.id("content")).isDisplayed());

        driver.quit();
        driver.close();
    }

    @Test
    public void test_waits(){
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver,10);

        driver.get("https://www.seleniumeasy.com/test/jquery-download-progress-bar-demo.html");
        driver.findElement(By.id("downloadButton")).click();

        boolean result = false;
        //manejo excepciones
        try{
            result = wait.until(ExpectedConditions.textToBe(By.className("progress-label"), "Complete!"));
        }
        catch(WebDriverException exception){
            System.out.println("No funcionó");
        }

        driver.quit();
        driver.close();
    }


    @Test
    public void drag_and_drop(){
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.seleniumeasy.com/test/drag-and-drop-demo.html");

        Actions actions = new Actions(driver);

        WebElement box1 = driver.findElement(By.xpath("//span[text()='Draggable 1']"));
        WebElement drop = driver.findElement(By.id("mydropzone"));

        Point punto = drop.getLocation();

        actions.dragAndDrop(box1, drop).build().perform();
        actions.dragAndDropBy(box1, punto.getX(), punto.getY()).release().build().perform();

        actions.perform();

        WebElement dropeado = driver.findElement(By.xpath("//div[@id='droppedlist']/span[text()='Draggable 1']"));
        Assert.assertTrue(dropeado.isDisplayed());
    }

}
