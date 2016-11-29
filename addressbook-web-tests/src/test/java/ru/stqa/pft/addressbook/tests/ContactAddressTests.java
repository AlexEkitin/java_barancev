package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase  {

    public Properties  properties = new Properties();

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

    @Test
    public void testContactAdress() {
        app.goTo().homePage();
        //berem list kontaktov, iz nego vibiraem odin sluchainim obrazom
        ContactData contact = app.contact().all().iterator().next();
        //perehodim na stranicu redaktirovaniya vibrannogo kontakta,
        //i sobiraem informaciyu s formi redaktirovaniya
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
    }



}
