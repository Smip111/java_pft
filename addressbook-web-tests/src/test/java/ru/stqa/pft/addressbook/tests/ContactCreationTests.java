package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.*;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        if (app.db().groups().size()==0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test2"));
        }
    }

    @DataProvider
    public Iterator<Object[]> validContactsFromJson() throws IOException {
        try (BufferedReader reader=new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))){
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
    }


    @Test(dataProvider = "validContactsFromJson")
    public void testContactCreation(ContactData contact) {
        Groups groups=app.db().groups();
        File photo=new File("src/test/resources/003.jpg");
        app.goTo().homePage();
        Contacts before=app.contact().all();
        app.goTo().contactPage();
        app.contact().create(contact.inGroup(groups.iterator().next()));
        assertThat(app.contact().count(), equalTo(before.size()+1));
        Contacts after=app.contact().all();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c)->c.getId()).max().getAsInt()))));
        verifyContactListInUI();
    }



}
