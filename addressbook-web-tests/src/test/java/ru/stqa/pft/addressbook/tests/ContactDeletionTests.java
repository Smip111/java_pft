package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

/**
 * Created by admin on 27.11.2016.
 */
public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion(){
        app.getNavigationHelper().goToHomePage();
        if (! app.getContactHelper().isThereAContact()){
            app.getNavigationHelper().goToContactPage();
            app.getContactHelper().createContact(new ContactData("Peter", "Ivanov", "Moscow, Tverskaya str, 25, 8", "+7(495)111-11-11","test1"));
        }
        List<ContactData> before=app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size()-1);
        app.getContactHelper().initContactModification(before.size()+1);
        app.getContactHelper().deleteModificatedContacts();
        app.getNavigationHelper().goToHomePage();
        List<ContactData> after=app.getContactHelper().getContactList();

        before.remove(before.size()-1);
        Assert.assertEquals(before,after);
    }


}
