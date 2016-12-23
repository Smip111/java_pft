package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test (enabled = false)
    public void testContactCreation() {

        app.goTo().goToHomePage();
        List<ContactData> before=app.getContactHelper().getContactList();
        app.goTo().goToContactPage();
        System.out.println(before);
        ContactData contact=new ContactData("Peter", "Ivanov", "Moscow, Tverskaya str, 25, 8", "+7(495)111-11-11","test1");
        app.getContactHelper().createContact(contact);
        List<ContactData> after=app.getContactHelper().getContactList();
        System.out.println("***"+after);
        Assert.assertEquals(after.size(), before.size()+1);

        before.add(contact);
        Comparator<? super ContactData> byId=(c1,c2)->Integer.compare(c1.getId(),c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);
    }

}
