package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification(){
        app.getNavigationHelper().gotoHomePage();
        if (! app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("firstname", "lastname", "groupname1"));
        }
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().chooseEditContact();
        app.getContactHelper().fillContactForm(new ContactData("newfirstname", "newlastname", "newgroupname"), false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().gotoHomePage();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(before, after);
    }
}