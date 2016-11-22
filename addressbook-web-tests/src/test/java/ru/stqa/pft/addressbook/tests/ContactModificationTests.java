package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

public class ContactModificationTests extends TestBase {

    //esli metod nachinaetsa s "get-", to eto oznachaet, chto etot metod nuzen dla
    //doateupa k atributu (polu klassa)

    @BeforeMethod //proverka preduslovii
    public void ensurePreconditions(){
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withFirstname("firstname1").withLastname("lastname1").withGroup("group1"));
        }
    }

    @Test//(enabled = false)
    public void testContactModification() {
        Contacts before = app.contact().all2();
        //vibiraem element sluchainim obrazom
        ContactData modifiedContact = before.iterator().next();
        //pri modifikacii contacta vse meniaem, krome id
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId())
                .withFirstname("newfirstname1")
                .withLastname("newlastname1")
                .withGroup("group1");
        app.contact().modify(contact);
        Contacts after = app.contact().all2();
        assertEquals(before.size(), after.size());
        assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));
    }


}