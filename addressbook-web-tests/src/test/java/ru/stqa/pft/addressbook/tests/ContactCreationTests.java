package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {

        app.getNavigationHelper().goToContactPage();
        app.getContactHelper().fillContactForm(new ContactData("Peter", "Ivanov", "Moscow, Tverskaya str, 25, 8", "+7(495)111-11-11"));
        app.getContactHelper().submitContactCreation();
    }

}
