package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.*;

import java.io.File;

/**
 * Created by admin on 27.11.2016.
 */
public class HelperBase {
    protected ApplicationManager app;
    protected WebDriver wd;


    public HelperBase(ApplicationManager app) {
        this.app=app;
        this.wd=app.getDriver();
    }

    protected void click(By locator) {
        getElement(locator).click();
    }

    private WebElement getElement(By locator) {
        return wd.findElement(locator);
    }

    protected void type(By locator, String text) {
        WebElement e = getElement(locator);
        if (text!=null) {
            String existingText = e.getAttribute("value");
            if (!text.equals(existingText)) {
                e.clear();
                e.sendKeys(text);
            }
        }
    }

    protected void attach(By locator, File file) {
        if (file!=null) {
            wd.findElement(locator).sendKeys(file.getAbsolutePath());
        }
    }

    public boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    protected boolean isElementPresent(By locator) {
        try {
            wd.findElement(locator);
            return true;
        } catch (NoSuchElementException ex){
            return false;
        }
    }
}
