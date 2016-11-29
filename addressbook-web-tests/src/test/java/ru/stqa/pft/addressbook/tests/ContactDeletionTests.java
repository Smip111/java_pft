package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

/**
 * Created by admin on 27.11.2016.
 */
public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion(){
        app.getNavigationHelper().goToHomePage();
        app.getContactHelper().selectContact();
        app.getContactHelper().initContactModification();
        app.getContactHelper().deleteModificatedContacts();
    }


}
