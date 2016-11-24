package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactCreationTests extends TestBase {

    @Test//(enabled = false)
    public void testContactCreation() {
        //peremennaya before soderzit spisok elementov tipa <ContactData>
        Contacts before = app.contact().all2();
        File photo = new File("src/test/resources/stru.png");
        ContactData contact = new ContactData().withFirstname("firstname1").withLastname("lastname1").withGroup("group1").withPhoto(photo);
        app.contact().create(contact);
        Contacts after = app.contact().all2();
        assertThat(before.size() + 1, equalTo(after.size()));

        //before.withAdded - eto kopiya obekta "before"
        //prevrashaem potok obektov tipa ContactData prevrashaem v potok id (to est chisel) - mapToInt()
        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }
}