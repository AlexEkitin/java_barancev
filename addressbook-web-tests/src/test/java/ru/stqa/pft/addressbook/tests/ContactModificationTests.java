package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    //esli metod nachinaetsa s "get-", to eto oznachaet, chto etot metod nuzen dla
    //doateupa k atributu (polu klassa)

    @BeforeMethod //proverka preduslovii
    public void ensurePreconditions(){
        app.goTo().homePage();
        if (app.contact().list().size() == 0) {
            app.contact().create(new ContactData("firstname1", "lastname1", "groupname1"));
        }
    }

    @Test//(enabled = false)
    public void testContactModification() {
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        //pri modifikacii contacta vse meniaem, krome id
        ContactData contact = new ContactData(before.get(index).getId(), "newfirstname1", "newlastname1", "groupname1");
        app.contact().modify(index, contact);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(before.size(), after.size());

        //uberaem iz spiska element do modifikacii
        before.remove(index);
        //dobavlaem v spisok element posle modofikacii
        before.add(contact);
        //sortiruem dva list dla budusego sravneniia, tak kak posle modificiruemii
        //kontakt mog peremestitsa v luboe mesto v list, a dla list vazen poriadok,
        //i esle poriadok ne sovpadaet, to i dva list ne ravni
        Comparator<? super ContactData> byId = (c1 , c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        //preobrazuem list v set, tak kak set ne imeet poriadka, i sravnim oba set
        Assert.assertEquals(before, after);
    }


}