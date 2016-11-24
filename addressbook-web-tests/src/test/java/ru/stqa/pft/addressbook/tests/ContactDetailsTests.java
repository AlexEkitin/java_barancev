package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDetailsTests extends TestBase {

    @BeforeMethod //proverka preduslovii
    public void ensurePreconditions(){
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withFirstname("firstname1").withLastname("lastname1").withGroup("group1"));
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
