package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {


    @Test
    public void testContactDeletionFromEdit() {
        app.getNavigationHelper().gotoHomePage();
        if (! app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("firstname", "lastname", "groupname1"));
        }
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().chooseEditContact();
        app.getContactHelper().deleteContact();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(before - 1, after);
    }





    //@Test
    public void testContactDeletion() {
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectContacts();

    }


}