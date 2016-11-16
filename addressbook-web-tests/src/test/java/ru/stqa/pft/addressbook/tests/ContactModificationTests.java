package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test(enabled = false)
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
        //sortiruem dva list dla budusego sravneniia, tak kak posle modificiruemii
        //kontakt mog peremestitsa v luboe mesto v list, a dla list vazen poriadok,
        //i esle poriadok ne sovpadaet, to i dva list ne ravni
        Comparator<? super ContactData> byId = (c1 , c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        //preobrazuem list v set, tak kak set ne imeet poriadka, i sravnim oba set
        Assert.assertEquals(before, after);
    }
}