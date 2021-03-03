package driver;

import com.thoughtworks.gauge.AfterSuite;
import com.thoughtworks.gauge.BeforeSuite;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Driver {

    public static WebDriver driver;
    private final String baseUrl="https://todomvc.com/examples/vue/";

    @BeforeSuite
    public void initializeDriver(){
        driver = DriverFactory.getDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl);
    }


    @AfterSuite
    public void closeDriver(){

        driver.quit();
    }

}
