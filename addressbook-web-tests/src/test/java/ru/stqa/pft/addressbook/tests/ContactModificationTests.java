package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by admin on 29.11.2016.
 */
public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification(){
        app.getNavigationHelper().goToHomePage();
        if (! app.getContactHelper().isThereAContact()){
            app.getNavigationHelper().goToContactPage();
            app.getContactHelper().createContact(new ContactData("Peter", "Ivanov", "Moscow, Tverskaya str, 25, 8", "+7(495)111-11-11","test1"),true);
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Ivan", "Petrov", "Moscow, Tverskaya str, 25, 9", "+7(495)222-22-22",null),false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHomePage();
    }
}
