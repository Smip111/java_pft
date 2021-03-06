package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Comparator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by admin on 29.11.2016.
 */
public class ContactModificationTests extends TestBase {

    @BeforeMethod
    private void ensurePreconditions() {
        if (app.db().contacts().size()==0){
            app.goTo().homePage();
            app.goTo().contactPage();
            app.contact().create(new ContactData()
                    .withFirstName("Peter").withLastName("Ivanov").withAddress("Moscow, Tverskaya str, 25, 8")
                    .withHome("+7(495)111-11-11"));
        }

    }

    @Test
    public void testContactModification(){
        Contacts before=app.db().contacts();
        ContactData modifiedContact=before.iterator().next();
        ContactData contact=new ContactData()
                    .withId(modifiedContact.getId()).withFirstName("Ivan").withLastName("Petrov")
                    .withAddress("Moscow, Tverskaya str, 25, 9").withHome("+7(495)222-22-22");
        app.goTo().homePage();
        app.contact().modify(contact);
        Contacts after=app.db().contacts();
        assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
        verifyContactListInUI();
    }




}
