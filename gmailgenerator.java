package com.qmetry.qaf.example.stepdefinitions;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.netty.util.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.BeforeTest;
import java.util.List;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

#Comments

public class gmailgenerator {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        //navigate to URL
        driver.navigate().to("https://accounts.google.com/signup/v2/webcreateaccount?service=mail&continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&ltmpl=default&gmb=exp&biz=false&flowName=GlifWebSignIn&flowEntry=SignUp");

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        int count = 0;
        int y = 0;
        for (int i = 100 ; i <= 999 ; i++) {
            driver.findElement(By.xpath("//*[@id='firstName']")).sendKeys("Muhammad");
            driver.findElement(By.xpath("//*[@id='lastName']")).sendKeys("Ahmad");
            y = (int)(Math.random()*((999-100)+1))+100;
            driver.findElement(By.xpath("//*[@id='username']")).sendKeys("mahmad"+y);

            driver.findElement(By.name("Passwd")).sendKeys("mahmad_321#");

            driver.findElement(By.name("ConfirmPasswd")).sendKeys("mahmad_321#");

            driver.findElement(By.id("accountDetailsNext")).click();
            // explicit wait - to wait for the compose button to be click-able
            String x = null;
            try{
                wait.until(ExpectedConditions.presenceOfElementLocated(By.className("jXeDnc")));
                x = driver.findElement(By.className("jXeDnc")).getText();
                if(x!=null) {
                    driver.quit();
                    System.out.println("This is the number: " +y);
                    break;
                }
            }
            catch (Exception ignore)
            {
                //driver.navigate().refresh();
                y = (int)(Math.random()*((999-100)+1))+100;
                driver.findElement(By.xpath("//*[@id='username']")).clear();
                driver.findElement(By.xpath("//*[@id='username']")).sendKeys("mahmad"+y);
                driver.findElement(By.id("accountDetailsNext")).click();
                try{
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.className("jXeDnc")));
                    x = driver.findElement(By.className("jXeDnc")).getText();
                    if(x!=null) {
                        driver.quit();
                        System.out.println("This is the number: " +y);
                        break;
                    }
                    count++;
                    System.out.println(count);
                }
                catch (Exception ignoree) {
                    driver.findElement(By.xpath("//*[@id='firstName']")).clear();
                    driver.findElement(By.xpath("//*[@id='lastName']")).clear();
                    driver.findElement(By.xpath("//*[@id='username']")).clear();
                    driver.findElement(By.name("Passwd")).clear();
                    driver.findElement(By.name("ConfirmPasswd")).clear();
                    count++;
                    System.out.println(count);
                }
            }
            //x = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("jXeDnc")));

            //x = driver.findElement(By.xpath("//*[@class='o6cuMc']")).getText();
            //x = driver.findElement(By.className("jXeDnc")).getText();
            System.out.println(y);
            System.out.println(count);
        }
    }
}
