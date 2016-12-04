package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

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
        app.getContactHelper().selectContact();
        app.getContactHelper().initContactModification();
        app.getContactHelper().deleteModificatedContacts();
    }


}
