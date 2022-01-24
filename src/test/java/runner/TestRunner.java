package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import poms.HomePage;
import poms.SharkHomePage;

import java.io.File;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features", glue = "runner") // may have to put features folder in resources
public class TestRunner {
    public static WebDriver driver;
    public static HomePage homePage;
    public static SharkHomePage sharkHomePage;


    @BeforeClass
    public static void setup() {
        File file = new File("src/test/chromedriver");
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
        driver = new ChromeDriver();
        homePage = new HomePage(driver);
        sharkHomePage = new SharkHomePage(driver);
    }

    @AfterClass
    public static void teardown() {
        driver.quit();
    }
}
