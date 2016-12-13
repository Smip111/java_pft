package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

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

    public void fillContactForm(ContactData contactData,boolean creation) {
        type(By.name("firstname"),contactData.getFirstName());
        type(By.name("lastname"),contactData.getLastName());
        type(By.name("address"),contactData.getAddress());
        type(By.name("home"),contactData.getHome());
        if (creation){
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        }else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void deleteModificatedContacts() {
        click(By.xpath("//div[@id='content']/form[2]/input[2]"));
    }

    public void initContactModification(int index) {
        click(By.xpath("//table[@id='maintable']/tbody/tr["+index+"]/td[8]/a/img"));
    }

    public void submitContactModification() {
        click(By.xpath("//div[@id='content']/form[1]/input[22]"));
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }
    public void createContact(ContactData contact) {
        fillContactForm(contact,true);
        submitContactCreation();
        returnToHomePage();
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts=new ArrayList<ContactData>();
        List<WebElement> elements=wd.findElements(By.name("entry"));
        for (WebElement element : elements){
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            List<WebElement> tableElements=element.findElements(By.tagName("td"));
            String firstName =tableElements.get(2).getText();
            String lastName =tableElements.get(1).getText();
            ContactData contact = new ContactData(id, firstName, lastName, null, null, null);
            contacts.add(contact);

        }
        return contacts;
    }
}
