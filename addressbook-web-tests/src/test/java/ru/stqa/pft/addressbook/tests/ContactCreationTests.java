package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {

        app.goTo().homePage();
        Contacts before=app.contact().all();
        app.goTo().contactPage();
        File photo=new File("src/test/resources/003.jpg");
        ContactData contact=new ContactData()
                .withFirstName("Peter").withLastName("Ivanov").withAddress("Moscow, Tverskaya str, 25, 8")
                .withHome("+7(495)111-11-11").withGroup("test1").withPhoto(photo);
        app.contact().create(contact);
//        app.goTo().homePage();
        Contacts after=app.contact().all();
        assertThat(after.size(), equalTo(before.size()+1));

        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c)->c.getId()).max().getAsInt()))));
    }



}
