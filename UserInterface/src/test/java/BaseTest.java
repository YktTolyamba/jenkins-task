import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {

    private final String urlValue = "/url";

    @BeforeMethod
    public void setUp() {
        ISettingsFile environment = new JsonSettingsFile("config.json");
        String url = environment.getValue(urlValue).toString();
        AqualityServices.getBrowser().maximize();
        AqualityServices.getBrowser().goTo(url);
        AqualityServices.getBrowser().waitForPageToLoad();
    }

    @AfterMethod
    public void tearDown() {
        AqualityServices.getBrowser().quit();
    }

}
