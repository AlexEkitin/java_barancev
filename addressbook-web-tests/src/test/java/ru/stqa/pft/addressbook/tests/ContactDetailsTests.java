package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDetailsTests extends TestBase {

    public Properties properties = new Properties();

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
    public void testContactPages() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        app.goTo().homePage();
        ContactData contactInfoFromViewForm = app.contact().infoFromViewForm(contact);
        assertThat((contactInfoFromEditForm), equalTo(contactInfoFromViewForm));

    }

    private String mergeAll(ContactData contact) {
        return Arrays.asList(contact.getFirstname(),contact.getLastname(),contact.getAddress(),
                contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone(),
                contact.getEmail(),contact.getEmail2(),contact.getEmail3()).
                stream().filter((s) -> ! s.equals("")).map(ContactDetailsTests::clean).collect(Collectors.joining("\n"));
    }

    public static String clean(String contact) {

        return  contact.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}
