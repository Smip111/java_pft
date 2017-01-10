package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by admin on 25.12.2016.
 */
public class ContactPhoneTests extends TestBase {

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
    public void testContactPhones(){
        app.goTo().homePage();
        ContactData contact=app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm=app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(),contact.getMobilePhone(),contact.getWorkPhone())
                .stream().filter((s)->!s.equals(""))
                .map(ContactPhoneTests::cleaned)
                .collect(Collectors.joining("\n"));

    }

    public static String cleaned(String phone){
        return phone.replaceAll("\\s", "").replaceAll("[-()]","");
    }

}
