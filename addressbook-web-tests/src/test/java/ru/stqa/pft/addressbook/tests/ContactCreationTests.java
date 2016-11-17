package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test//(enabled = false)
    public void testContactCreation() {
        //peremennaya before soderzit spisok elementov tipa <ContactData>
        List<ContactData> before = app.contact().list();
        ContactData contact = new ContactData("firstname1", "lastname1", "groupname1");
        app.contact().create(contact);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(before.size() + 1, after.size());

        before.add(contact);
        Comparator<? super ContactData> byId = (c1 , c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        //preobrazuem list v set, tak kak set ne imeet poriadka, i sravnim oba set
        Assert.assertEquals(before, after);
    }

}