package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification(){
        app.getNavigationHelper().gotoHomePage();
        if (! app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("firstname", "lastname", "groupname1"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().chooseEditContact(before.size() - 1);
        app.getContactHelper().fillContactForm(new ContactData("newfirstname", "newlastname", "newgroupname"), false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(before.size(), after.size());
    }
}