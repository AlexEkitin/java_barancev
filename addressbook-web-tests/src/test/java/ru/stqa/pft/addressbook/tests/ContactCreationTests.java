package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;
import java.util.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactCreationTests extends TestBase {

    //provaider testovih dannih
    @DataProvider
    public Iterator<Object[]> validContacts() {
        //put k failu otnositelno rabochei derrictorii "addressbook-web-tests"
        File photo = new File("src/test/resources/stru.png");
        //spisok massivov
        List<Object[]> list = new ArrayList<Object[]>();
        //kazdii massiv sostoit iz odnogo elementa, on soderzit nabor dannih dla odnogo zapuska testovogo metoda
        //skolko budet takih naborov, stolko raz zapustitsa testovii metod
        list.add(new Object[]{new ContactData().withFirstname("firstname1").withLastname("lastname").withGroup("test1").withPhoto(photo)});
        list.add(new Object[]{new ContactData().withFirstname("firstname2").withLastname("lastname2").withGroup("test1").withPhoto(photo)});
        list.add(new Object[]{new ContactData().withFirstname("firstname3").withLastname("lastname3").withGroup("test1").withPhoto(photo)});
        return list.iterator();
    }

    @Test(dataProvider = "validContacts")
    public void testContactCreation(ContactData contact) {
        //peremennaya before soderzit spisok elementov tipa <ContactData>
        Contacts before = app.contact().all2();
        app.contact().create(contact);
        Contacts after = app.contact().all2();
        assertThat(before.size() + 1, equalTo(after.size()));
        //before.withAdded - eto kopiya obekta "before"
        //prevrashaem potok obektov tipa ContactData prevrashaem v potok id (to est chisel) - mapToInt()
        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }
}