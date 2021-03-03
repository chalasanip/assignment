import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected void NavigateTo(String url) {
        if(url == null || url.length() < 1)
            throw new IllegalArgumentException("Url to navigate cannot be null or empty");

        driver.navigate().to(url);
    }
}
