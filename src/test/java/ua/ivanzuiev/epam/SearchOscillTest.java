package ua.ivanzuiev.epam;

import java.io.File;
import java.util.List;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class SearchOscillTest {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    private int count = 1;

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        baseUrl = "https://www.google.com.ua/";
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
    }

    @Test
    public void testOscillograf() throws Exception {
        driver.get(baseUrl + "/?gws_rd=ssl");
        SearchPage mainPage = PageFactory.initElements(driver, SearchPage.class);
        ResultsPage resultsPage = mainPage.search("осциллограф");

        for (int i = 0; i < 30; i++) {
            Page page = resultsPage.goToPageIfContains("vit.ua");
            if (page == null) {
                resultsPage = resultsPage.nextPage();
                count++;
            } else {

                File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(screenshot, new File("C:\\Selenium\\Screenshots\\Screen01.jpeg"));
                System.out.println(driver.getCurrentUrl());
                System.out.println("Uraaah! We've found it on the " + count + " page!");
                return;
            }
        }
        System.out.println("Sorry, vit.ua is not found!");


    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }
}