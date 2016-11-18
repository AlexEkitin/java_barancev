package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactCreationTests extends TestBase {

    @Test//(enabled = false)
    public void testContactCreation() {
        //peremennaya before soderzit spisok elementov tipa <ContactData>
        Set<ContactData> before = app.contact().all();
        ContactData contact = new ContactData().withFirstname("firstname1").withLastname("lastname1").withGroup("group1");
        app.contact().create(contact);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(before.size() + 1, after.size());

        //vichislaem max id
        //prevrashaem potok obektov tipa ContactData prevrashaem v potok id (to est chisel) - mapToInt()
        contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
        before.add(contact);
        //preobrazuem list v set, tak kak set ne imeet poriadka, i sravnim oba set
        Assert.assertEquals(before, after);
    }

}