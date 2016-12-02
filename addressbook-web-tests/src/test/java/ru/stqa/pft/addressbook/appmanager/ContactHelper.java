package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by admin on 27.11.2016.
 */
public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        click(By.name("submit"));
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"),contactData.getFirstName());
        type(By.name("lastname"),contactData.getLastName());
        type(By.name("address"),contactData.getAddress());
        type(By.name("home"),contactData.getHome());

    }

    public void selectContact() {
        click(By.name("selected[]"));
    }

    public void deleteModificatedContacts() {
        click(By.xpath("//div[@id='content']/form[2]/input[2]"));
    }

    public void initContactModification() {
        click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
    }

    public void submitContactModification() {
        click(By.xpath("//div[@id='content']/form[1]/input[22]"));
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }
}
