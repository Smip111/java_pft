package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by admin on 08.01.2017.
 */
public class ContactToGroupAddTests  extends TestBase{

    @BeforeMethod
    private void ensurePreconditions() {
        if (app.db().contacts().size()==0){
            app.goTo().homePage();
            app.goTo().contactPage();
            app.contact().create(new ContactData()
                    .withFirstName("Peter").withLastName("Ivanov").withAddress("Moscow, Tverskaya str, 25, 8")
                    .withHome("+7(495)111-11-11"));
        }
        if (app.db().groups().size()==0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test2"));
        }
    }

    @Test
    public void testContactAddToGroup(){
        Contacts before=app.db().contacts();
        Groups groups=app.db().groups();
        GroupData group=groups.iterator().next();
        ContactData modifiedContact=before.iterator().next();
        Groups beforeGroups=app.db().getGroupsOfContactById(modifiedContact.getId());
        ContactData contact=new ContactData()
                .withId(modifiedContact.getId()).inGroup(group);
        app.goTo().homePage();
        app.contact().toGroup(contact);

        Groups afterGroups=app.db().getGroupsOfContactById(modifiedContact.getId());
        assertThat(afterGroups, equalTo(beforeGroups.withAdded(group)));


    }


}
