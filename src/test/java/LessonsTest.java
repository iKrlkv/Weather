import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class LessonsTest {

//  TC_11_01
//  1.  Открыть базовую ссылку
//  2.  Нажать на пункт меню Guide
//  3.  Подтвердить, что вы перешли на страницу со ссылкой
//  https://openweathermap.org/guide и что title этой страницы OpenWeatherMap API guide - OpenWeatherMap

    String baseUrl = "https://openweathermap.org/";

    @Test
    public void testFirstTask() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "F:\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        String expectedUrl = "https://openweathermap.org/guide";
        String expectedTitle = "OpenWeatherMap API guide - OpenWeatherMap";

        driver.get(baseUrl);
        Thread.sleep(5000);
        driver.findElement(By.xpath("//div//a[@href='/guide']")).click();

        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
        Assert.assertEquals(driver.getTitle(), expectedTitle);

        driver.quit();
    }

//  1.  Открыть базовую ссылку
//  2.  Нажать на единицы измерения Imperial: °F, mph
//  3.  Подтвердить, что температура для города показана в Фарингейтах


    @Test
    public void testSecondLesson() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "F:\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get(baseUrl);
        Thread.sleep(5000);
        driver.findElement(By.xpath("//div[contains(text(), 'Imperial')]")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//span[@class='heading']")).getText().contains("F"));

        driver.quit();
    }

//  1. Открыть базовую ссылку
//  2. Подтвердить, что внизу страницы есть панель с текстом “We use cookies which are essential
//  for the site to work. We also use non-essential cookies to help us improve our services. Any
//  data collected is anonymised. You can allow all cookies or manage them individually.”
//  3. Подтвердить, что на панели внизу страницы есть 2 кнопки “Allow all” и “Manage cookies”

    @Test
    public void testThirdTask() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "F:\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get(baseUrl);
        Thread.sleep(5000);

        String expectedText = "We use cookies which are essential for the site to work. We also use non-essential " +
                "cookies to help us improve our services. Any data collected is anonymised. You can allow " +
                "all cookies or manage them individually.";
        String allowAllButtonText = "Allow all";
        String manageCookiesButtonText = "Manage cookies";

        Assert.assertEquals(driver.findElement(By.xpath("//p[@class='stick-footer-panel__description']"))
                .getText(), expectedText);
        Assert.assertEquals(driver.findElement(By.xpath("//button[@class='stick-footer-panel__link']"))
                .getText(), allowAllButtonText);
        Assert.assertEquals(driver.findElement(By.xpath("//a[@class='stick-footer-panel__link']"))
                .getText(), manageCookiesButtonText);

        driver.quit();
    }

//  1.  Открыть базовую ссылку
//  2.  Подтвердить, что в меню Support есть 3 подменю с названиями “FAQ”, “How to start” и “Ask a question”

    @Test
    public void testFourthTask() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "F:\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        List<String> textLinks = new ArrayList<>(List.of("FAQ", "How to start", "Ask a question"));

        driver.get(baseUrl);
        Thread.sleep(5000);

        driver.findElement(By.id("support-dropdown")).click();

        List<WebElement> supportLinks = driver.findElements(By.xpath("//ul[@id='support-dropdown-menu']//li"));

        for (int i = 0; i < supportLinks.size(); i++) {
            Assert.assertEquals(supportLinks.get(i).getText(), textLinks.get(i));
        }

        driver.quit();
    }

//  1. Открыть базовую ссылку
//  2. Нажать пункт меню Support → Ask a question
//  3. Заполнить поля Email, Subject, Message
//  4. Не подтвердив CAPTCHA, нажать кнопку Submit
//  5. Подтвердить, что пользователю будет показана ошибка “reCAPTCHA verification failed, please try again.”

    @Test
    public void testFifthTask() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "F:\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        String expectedText = "reCAPTCHA verification failed, please try again.";

        driver.get(baseUrl);
        Thread.sleep(5000);

        driver.findElement(By.id("support-dropdown")).click();
        driver.findElement(By.xpath("//ul[@id='support-dropdown-menu']//a[contains(text(), 'Ask a question')]")).click();

        ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));

        driver.findElement(By.xpath("//input[@id='question_form_email']")).sendKeys("johncena@123.com");

        Select subject = new Select(driver.findElement(By.xpath("//select[@id='question_form_subject']")));
        subject.selectByVisibleText("Other");
        driver.findElement(By.xpath("//textarea[@id='question_form_message']")).sendKeys("send nudes");

        driver.findElement(By.xpath("//input[@class='btn btn-default']")).submit();

        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='help-block']")).getText(), expectedText);

        driver.quit();
    }

//  1.  Открыть базовую ссылку
//  2.  Нажать на единицы измерения Imperial: °F, mph
//  3.  Нажать на единицы измерения Metric: °C, m/s
//  4.  Подтвердить, что в результате этих действий, единицы измерения температуры изменились с F на С

    @Test
    public void testSeventhTask() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "F:\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get(baseUrl);
        Thread.sleep(5000);

        driver.findElement(By.xpath("//div[contains(text(), 'Imperial')]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//div[contains(text(), 'Metric')]")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='current-temp']//span")).getText().contains("C"));

        driver.quit();
    }

//  1.  Открыть базовую ссылку
//  2.  Нажать на лого компании
//  3.  Дождаться, когда произойдет перезагрузка сайта, и подтвердить, что текущая ссылка не изменилась

    @Test
    public void testEighthTask() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "F:\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get(baseUrl);
        Thread.sleep(5000);

        String expectedUrl = driver.getCurrentUrl();

        driver.findElement(By.xpath("//ul[@id='first-level-nav']/li/a/img")).click();

        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);

        driver.quit();
    }

//  1.  Открыть базовую ссылку
//  2.  В строке поиска в навигационной панели набрать “Rome”
//  3.  Нажать клавишу Enter
//  4.  Подтвердить, что вы перешли на страницу в ссылке которой содержатся слова “find” и “Rome”
//  5.  Подтвердить, что в строке поиска на новой странице вписано слово “Rome”
    
//  1.  Открыть базовую ссылку
//  2.  Нажать на пункт меню API
//  3.  Подтвердить, что на открывшейся странице пользователь видит 30 оранжевых кнопок

    @Test
    public void testTenthsTask() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "F:\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get(baseUrl);
        Thread.sleep(5000);

        driver.findElement(By.xpath("//div[@id='desktop-menu']//a[@href='/api']")).click();

        List<WebElement> orangeButtons = driver.findElements(By.xpath("//a[contains(@class, 'orange')]"));

        Assert.assertEquals(orangeButtons.size(), 30);

        driver.quit();
    }
}
