package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactModificationTests extends TestBase {

    //esli metod nachinaetsa s "get-", to eto oznachaet, chto etot metod nuzen dla
    //doateupa k atributu (polu klassa)

    @BeforeMethod //proverka preduslovii
    public void ensurePreconditions(){
        app.goTo().homePage();
        if (app.contact().list().size() == 0) {
            app.contact().create(new ContactData().withFirstname("firstname1").withLastname("lastname1").withGroup("group1"));
        }
    }

    @Test//(enabled = false)
    public void testContactModification() {
        Set<ContactData> before = app.contact().all();
        //vibiraem element sluchainim obrazom
        ContactData modifiedContact = before.iterator().next();
        //pri modifikacii contacta vse meniaem, krome id
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId())
                .withFirstname("newfirstname1")
                .withLastname("newlastname1")
                .withGroup("group1");
        app.contact().modify(contact);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(before.size(), after.size());

        //uberaem iz spiska element do modifikacii
        before.remove(modifiedContact);
        //dobavlaem v spisok element posle modofikacii
        before.add(contact);
        //preobrazuem list v set, tak kak set ne imeet poriadka, i sravnim oba set
        Assert.assertEquals(before, after);
    }


}