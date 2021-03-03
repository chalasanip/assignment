import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.concurrent.TimeUnit;

public class Driver {

    protected static WebDriver driver;

    public Driver(){
        WebDriverManager.chromedriver().setup();
    }

    protected static WebDriver GetDriver() {
        if (driver == null) driver = new ChromeDriver();
        return driver;
    }

    @BeforeClass
    public static void setUp(){
        driver = GetDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @After
    public void cleanUp(){
        driver.manage().deleteAllCookies();
    }

    @AfterClass
    public static void tearDown(){
        driver.close();
    }

}
