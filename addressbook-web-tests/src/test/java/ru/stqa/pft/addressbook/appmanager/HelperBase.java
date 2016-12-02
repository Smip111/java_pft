package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by admin on 27.11.2016.
 */
public class HelperBase {
    private WebDriver wd;


    public HelperBase(WebDriver wd) {
        this.wd=wd;
    }

    protected void click(By locator) {
        getElement(locator).click();
    }

    private WebElement getElement(By locator) {
        return wd.findElement(locator);
    }

    protected void type(By locator, String text) {
        WebElement e = getElement(locator);
        e.clear();
        e.sendKeys(text);
    }

    public boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}
