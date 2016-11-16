package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getNavigationHelper().gotoHomePage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("firstname1", "lastname1", "groupname1"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().chooseEditContact(before.size() - 1);
        //pri modifikacii contacta vse meniaem, krome id
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "newfirstname1", "newlastname1", "groupname1");
        app.getContactHelper().fillContactForm((contact), false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(before.size(), after.size());

        //uberaem iz spiska element do modifikacii
        before.remove(before.size() - 1);
        //dobavlaem v spisok element posle modofikacii
        before.add(contact);
        //preobrazuem list v set, tak kak set ne imeet poriadka, i sravnim oba set
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }
}