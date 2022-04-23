import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class App {

    WebDriver driver;
    String baseUrl = "https:/www.instagram.com/";

    By usernamaLocator = new By.ByCssSelector("input[name='username']");
    By passwordLocator = new By.ByCssSelector("input[name='password']");
    By loginButtonLocator = new By.ByCssSelector("button[type='submit']");
    By profileNameLocator = By.className("XBGH5");
    By postLocator = By.className("eLAPa");
    By likeButtonLocator = By.className("fr66n");
    By postCountLocator = By.className("g47SY");
    By htmlTag = By.tagName("html");
    By infoSaveLocator = By.className("olLwo");


    public App(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(baseUrl);
        driver.manage().window().maximize();
    }

    public void login(String username, String password) {
        waitMethod(usernamaLocator);
        driver.findElement(usernamaLocator).sendKeys(username);
        driver.findElement(passwordLocator).sendKeys(password);
        driver.findElement(loginButtonLocator).click();
    }

    public void navigateToProfile(String profileName) {
        waitMethod(infoSaveLocator);
        driver.navigate().to(baseUrl.concat(profileName));
    }

    public void clickFirstPost(){
        waitMethod(profileNameLocator);
        driver.findElements(postLocator).get(0).click();
    }

    private void waitMethod(By locator){
        WebDriverWait wait = new WebDriverWait(driver,5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void likeAllPosts() {
        int count = getPostCount();
        while(count > 0){
            waitMethod(likeButtonLocator);
            driver.findElement(likeButtonLocator).click();
            driver.findElement(htmlTag).sendKeys(Keys.ARROW_RIGHT);
            count --;
        }
    }

    private int getPostCount(){
        String count = driver.findElement(postCountLocator).getText();
        return Integer.parseInt(count);
    }
}
