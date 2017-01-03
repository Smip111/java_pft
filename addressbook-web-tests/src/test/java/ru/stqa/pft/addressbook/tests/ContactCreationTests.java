package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.*;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContactsFromJson() throws IOException {
        BufferedReader reader=new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")));
        String json="";
        String line=reader.readLine();
        while (line != null){
            json+=line;
            line=reader.readLine();
        }
        Gson gson=new Gson();
        List<ContactData> contacts=gson.fromJson(json,new TypeToken<List<ContactData>>(){}.getType());
        return contacts.stream().map((c)->new Object[]{c}).collect(Collectors.toList()).iterator();
    }


    @Test(dataProvider = "validContactsFromJson")
    public void testContactCreation(ContactData contact) {

        app.goTo().homePage();
        Contacts before=app.contact().all();
        app.goTo().contactPage();
//        File photo=new File("src/test/resources/003.jpg");
//        ContactData contact=new ContactData()
//                .withFirstName("Peter").withLastName("Ivanov").withAddress("Moscow, Tverskaya str, 25, 8")
//                .withHome("+7(495)111-11-11").withGroup("test1").withPhoto(photo);
        app.contact().create(contact);
//        app.goTo().homePage();
        assertThat(app.contact().count(), equalTo(before.size()+1));
        Contacts after=app.contact().all();


        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c)->c.getId()).max().getAsInt()))));
    }



}
