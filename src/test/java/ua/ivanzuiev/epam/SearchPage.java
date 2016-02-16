package ua.ivanzuiev.epam;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage extends Page{

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id="lst-ib")
    private WebElement  searchField; //Search box


    public ResultsPage search(String searchStatement){
        searchField.clear();
        searchField.sendKeys(searchStatement);
        searchField.sendKeys(Keys.ENTER);
        return PageFactory.initElements(_driver, ResultsPage.class);
    }
}