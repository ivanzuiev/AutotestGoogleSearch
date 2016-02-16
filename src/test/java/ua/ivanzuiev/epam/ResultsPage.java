package ua.ivanzuiev.epam;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ResultsPage extends Page{

    public ResultsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='g']")
    private List<WebElement> list;

    @FindBy(linkText = "Следующая")
    private WebElement next;

    //в случае ненахождения строки в результатах  возвращает null
    public Page goToPageIfContains (String string){
        Page page=null;

        for (WebElement element : list) {
            String str = element.getText();
            if (str.contains(string)) {
                element.findElement(By.tagName("a")).click();
                long end = System.currentTimeMillis() + 5000;
                while (System.currentTimeMillis() < end) {
                }
                page=PageFactory.initElements(_driver,Page.class);
                break;
            }
        }
        return page;
    }



    public ResultsPage nextPage(){
        next.click();
        return PageFactory.initElements(_driver,ResultsPage.class);
    }
}