package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {


    //@Test
    //public void testContactDeletionFromEdit() {
     //   app.getNavigationHelper().gotoHomePage();
     //   if (! app.getContactHelper().isThereAContact()){
     //       app.getContactHelper().createContact(new ContactData("jbfvkfgydju", "yuitdjftyu", "fdjykjhxdfyue"), true );
     //   }
      //  app.getContactHelper().chooseEditContact();
       // app.getContactHelper().deleteContact();


    //}

    @Test
    public void testContactDeletion() {
        app.getNavigationHelper().gotoHomePage();
        if (! app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("jbfvkfgydju", "yuitdjftyu", "fdjykjhxdfyue"), true );
        }
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectContacts();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before - 1);

    }


}
