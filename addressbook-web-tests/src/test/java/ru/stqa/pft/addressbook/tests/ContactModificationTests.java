package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

public class ContactModificationTests extends TestBase {

    public Properties properties = new Properties();

    //esli metod nachinaetsa s "get-", to eto oznachaet, chto etot metod nuzen dla
    //doateupa k atributu (polu klassa)

    @BeforeMethod //proverka preduslovii
    public void ensurePreconditions() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData()
                    .withFirstname(properties.getProperty("contactFirstname"))
                    .withLastname(properties.getProperty("contactLastname"))
                    .withGroup(properties.getProperty("contactPreconditionGroup"))
                    .withAddress(properties.getProperty("contactAddress"))
                    .withHomePhone(properties.getProperty("contactHomePhone"))
                    .withMobilePhone(properties.getProperty("contactMobilePhone"))
                    .withWorkPhone(properties.getProperty("contactWorkPhone"))
                    .withEmail1(properties.getProperty("contactEmail1"))
                    .withEmail2(properties.getProperty("contactEmail2"))
                    .withEmail3(properties.getProperty("contactEmail3")));
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
                .withFirstname(properties.getProperty("contactNewFirstname"))
                .withLastname(properties.getProperty("contactNewLastname"))
                .withAddress(properties.getProperty("contactNewAddress"))
                .withHomePhone(properties.getProperty("contactNewHomePhone"))
                .withMobilePhone(properties.getProperty("contactNewMobilePhone"))
                .withWorkPhone(properties.getProperty("contactNewWorkPhone"))
                .withEmail1(properties.getProperty("contactNewEmail1"))
                .withEmail2(properties.getProperty("contactNewEmail2"))
                .withEmail3(properties.getProperty("contactNewEmail3"));
        app.contact().modify(contact);
        Contacts after = app.contact().all2();
        assertEquals(before.size(), after.size());
        assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));
    }


}