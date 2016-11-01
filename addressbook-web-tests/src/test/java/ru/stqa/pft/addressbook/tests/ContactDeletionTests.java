package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {


    @Test
    public void testContactDeletionFromEdit() {
        app.getNavigationHelper().gotoHomePage();
        if (! app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("jbfvkfgydju", "yuitdjftyu", "fdjykjhxdfyue", "86469", "4648", "test1"), true);
        }
        app.getContactHelper().chooseEditContact();
        app.getContactHelper().deleteContact();

    }

    //@Test
    public void testContactDeletion() {
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectContacts();

    }


}
