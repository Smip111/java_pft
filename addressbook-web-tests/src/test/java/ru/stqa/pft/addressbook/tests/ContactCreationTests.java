package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {

        app.goToContactForm();
        app.fillContactForm(new ContactData("Peter", "Ivanov", "Moscow, Tverskaya str, 25, 8", "+7(495)111-11-11"));
        app.submitContactCreation();
    }
    
}
