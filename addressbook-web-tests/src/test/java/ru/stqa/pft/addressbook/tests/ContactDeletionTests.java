package ru.stqa.pft.addressbook.tests;


import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod //proverka preduslovii
    public void ensurePreconditions(){
        app.goTo().homePage();
        if (app.contact().list().size() == 0) {
            app.contact().create(new ContactData().withFirstname("firstname1").withLastname("lastname1").withGroup("group1"));
        }
    }

    @Test//(enabled = false)
    public void testContactDeletion() {
        Contacts before = app.contact().all();
        //sozdaem obekt, kotorii budem udalat iz set
        //iterator() - pozvolaet posledovatelno pereberat elementi set, a
        //next() - vozvrashaet pervii popavsiisa element set
        ContactData deletedContact = before.iterator().next();
        //udalaem etot za element so stranici
        app.contact().delete(deletedContact);
        Contacts after = app.contact().all();
        assertEquals(before.size() - 1, after.size());
        assertThat(after, equalTo(before.withOut(deletedContact)));


    }


}