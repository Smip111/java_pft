package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by admin on 08.01.2017.
 */
public class ContactFromGroupDeleteTests extends TestBase{
    @BeforeMethod
    private void ensurePreconditions() {
        if (app.db().groups().size()==0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test2"));
        }

        if (app.db().contacts().size()==0){
            app.goTo().homePage();
            app.goTo().contactPage();
            app.contact().create(new ContactData()
                    .withFirstName("Peter").withLastName("Ivanov").withAddress("Moscow, Tverskaya str, 25, 8")
                    .withHome("+7(495)111-11-11"));
        }

    }

    @Test
    public void testContactDeleteFromGroup(){
        Contacts before=app.db().contacts();
        ContactData modifiedContact=before.iterator().next();
        Groups beforeGroups=app.db().getGroupsOfContactById(modifiedContact.getId());
        GroupData group;
        if (beforeGroups.size()==0){
            Groups groups=app.db().groups();
            group=groups.iterator().next();
        }else{
            group=beforeGroups.iterator().next();
        }

        ContactData contact=new ContactData()
                .withId(modifiedContact.getId()).inGroup(group);
        app.goTo().homePage();
        if (beforeGroups.size()==0){
            app.contact().toGroup(contact);
        }
        app.goTo().homePage();
        app.contact().fromGroup(contact);
        app.goTo().homePage();
        Groups afterGroups=app.db().getGroupsOfContactById(modifiedContact.getId());
        System.out.println(afterGroups);
        assertThat(afterGroups, equalTo(beforeGroups.without(group)));


    }

}
