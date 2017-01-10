package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by admin on 27.11.2016.
 */
public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size()==0){
            app.goTo().homePage();
            app.goTo().contactPage();
            app.contact().create(new ContactData()
                    .withFirstName("Peter").withLastName("Ivanov").withAddress("Moscow, Tverskaya str, 25, 8")
                    .withHome("+7(495)111-11-11"));
        }
    }


    @Test
    public void testContactDeletion(){
        Contacts before=app.db().contacts();
        ContactData deletedContact=before.iterator().next();
        app.contact().delete(deletedContact);
        app.goTo().homePage();
        Contacts after=app.db().contacts();
        assertEquals(after.size(), before.size() - 1);
        assertThat(after, equalTo(before.without(deletedContact)));
        verifyContactListInUI();
    }




}
