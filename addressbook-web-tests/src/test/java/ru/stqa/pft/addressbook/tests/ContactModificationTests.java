package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification(){
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().chooseEditContact();
        app.getContactHelper().fillContactForm(new ContactData("7777", "7777", "7777", "7777", "7777", null), false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().gotoHomePage();
    }
}
