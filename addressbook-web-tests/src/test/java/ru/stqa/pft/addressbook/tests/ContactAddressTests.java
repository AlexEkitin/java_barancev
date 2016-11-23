package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase  {

    @BeforeMethod //proverka preduslovii
    public void ensurePreconditions(){
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withFirstname("firstname1").withLastname("lastname1").withGroup("group1"));
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
