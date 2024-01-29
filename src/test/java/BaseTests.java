import Pages.CreateYourMonsterPage;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

public class BaseTests {
    private final String DRIVER_LOCATION = System.getProperty("user.dir") + "/chromedriver/chromedriver.exe";
    private WebDriver driver;
    protected CreateYourMonsterPage createYourMonsterPage;

    @BeforeClass
    public void setup() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setBrowserName("chrome");
        desiredCapabilities.setPlatform(Platform.WIN11);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability("acceptInsecureCerts", true);
        System.setProperty("webdriver.chrome.driver", DRIVER_LOCATION);
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.get("http://localhost:3000/register");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        createYourMonsterPage = new CreateYourMonsterPage(driver);
    }

    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }
}
