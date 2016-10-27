package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {


    //@Test
    public void atestContactDeletionFromEdit() {
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().chooseEditContact();
        app.getContactHelper().deleteContact();

    }

    @Test
    public void testContactDeletion() {
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectContacts();

    }


}
