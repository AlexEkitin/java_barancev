package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {


    @Test
    public void testContactDeletion() {
        app.getNavigationHelper().gotoHomePage();

        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("firstname1", "lastname1", "groupname1"));
        }

        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().deleteSelectContacts();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(before.size() - 1, after.size());

        //udalaem iz before tot element, kotorii mi udalili so stranici
        before.remove(before.size() - 1);
        //stavnivaem dva spiska
        Assert.assertEquals(before, after);


    }
}