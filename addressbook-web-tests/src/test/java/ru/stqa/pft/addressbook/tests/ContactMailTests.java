package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by admin on 26.12.2016.
 */
public class ContactMailTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size()==0){
            app.goTo().contactPage();
            app.contact().create(new ContactData()
                    .withFirstName("Peter").withLastName("Ivanov").withAddress("Moscow, Tverskaya str, 25, 8")
                    .withHome("+7(495)111-11-11"));
        }
    }

    @Test
    public void testContactEmails(){
        app.goTo().homePage();
        ContactData contact=app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm=app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
    }

    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(),contact.getEmail2(),contact.getEmail3())
                .stream().filter((s)->!s.equals(""))
                .collect(Collectors.joining("\n"));

    }
}
