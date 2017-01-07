package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

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
//        attach(By.name("photo"),contactData.getPhoto());
        if (creation){
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        }else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='"+id+"']")).click();
    }

    public void deleteModificatedContacts() {
        click(By.xpath("//div[@id='content']/form[2]/input[2]"));
    }

    public void initContactModificationById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']",id))).click();
    }

    public void seeContactDetailsById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='view.php?id=%s']",id))).click();
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
    public void create(ContactData contact) {
        fillContactForm(contact,true);
        submitContactCreation();
        returnToHomePage();
    }

    public void modify(ContactData contact) {
        selectContactById(contact.getId());
        initContactModificationById(contact.getId());
        fillContactForm(contact,false);
        submitContactModification();
        returnToHomePage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        initContactModificationById(contact.getId());
        deleteModificatedContacts();
    }

    public Contacts all() {
        Contacts contacts=new Contacts();
        List<WebElement> elements=wd.findElements(By.name("entry"));
        for (WebElement element : elements){
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            List<WebElement> tableElements=element.findElements(By.tagName("td"));
            String firstName =tableElements.get(2).getText();
            String lastName =tableElements.get(1).getText();
            String allPhones = tableElements.get(5).getText();
            String address = tableElements.get(3).getText();
            String allEmails = tableElements.get(4).getText();
            contacts.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName)
            .withAllPhones(allPhones).withAddress(address).withAllEmails(allEmails));

        }
        return contacts;
    }

    public String infoFromDetailsPage(ContactData contact) {
        seeContactDetailsById(contact.getId());
        String details=wd.findElement(By.id("content")).getText();
        wd.navigate().back();

        return details.replaceAll("\\s", "").replaceAll("H:","").replaceAll("M:","").replaceAll("W:","");

    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname=wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname=wd.findElement(By.name("lastname")).getAttribute("value");
        String home=wd.findElement(By.name("home")).getAttribute("value");
        String mobile=wd.findElement(By.name("mobile")).getAttribute("value");
        String work=wd.findElement(By.name("work")).getAttribute("value");
        String address=wd.findElement(By.name("address")).getAttribute("value");
        String email=wd.findElement(By.name("email")).getAttribute("value");
        String email2=wd.findElement(By.name("email2")).getAttribute("value");
        String email3=wd.findElement(By.name("email3")).getAttribute("value");

        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstName(firstname).withLastName(lastname)
                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withAddress(address).withEmail(email)
                .withEmail2(email2).withEmail3(email3);
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }
}
