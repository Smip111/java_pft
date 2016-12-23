package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by admin on 29.11.2016.
 */
public class ContactModificationTests extends TestBase {

    @Test (enabled = false)
    public void testContactModification(){
        app.goTo().goToHomePage();
        if (! app.getContactHelper().isThereAContact()){
            app.goTo().goToContactPage();
            app.getContactHelper().createContact(new ContactData("Peter", "Ivanov", "Moscow, Tverskaya str, 25, 8", "+7(495)111-11-11","test1"));
        }
        List<ContactData> before=app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size()-1);
        app.getContactHelper().initContactModification(before.size()+1);
        ContactData contact=new ContactData(before.get(before.size()-1).getId(),"Ivan", "Petrov", "Moscow, Tverskaya str, 25, 9", "+7(495)222-22-22",null);
        app.getContactHelper().fillContactForm(contact,false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHomePage();
        List<ContactData> after=app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size()-1);
        before.add(contact);
        Comparator<? super ContactData> byId=(c1,c2)->Integer.compare(c1.getId(),c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);
    }
}
