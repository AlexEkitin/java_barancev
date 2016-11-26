package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.*;
import java.util.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactCreationTests extends TestBase {

    //provaider testovih dannih nuzen, chtobi zagruzat testovie dannie v test
    @DataProvider
    public Iterator<Object[]> validContacts() throws IOException {
        //put k failu otnositelno rabochei derrictorii "addressbook-web-tests"
        //File photo = new File("src/test/resources/stru.png");
        //spisok massivov
        List<Object[]> list = new ArrayList<Object[]>();
        //sozdaetsa obekt tipa "BufferedReader", i cherez nego proishodit chtenie dannih iz faila
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")));
        //readLine() - chitaet stroku i srazu ee vozvrachaet
        String line = reader.readLine();
        //cikl nuzet, tak kak mi ne znaem skolko strok budet v faile
        while (line != null) {
            //delim kazduu stroku na chasti, metkoi dla deleniia bedet ';'
            String[] split = line.split(";");
            //iz poluchenih kusochkov stroim massiv iz odnogo obekta
            //i dobavlaem ego v List<Object[]> list = new ArrayList<Object[]>()
            list.add(new Object[] {new ContactData().withFirstname(split[0]).withLastname(split[1]).withGroup(split[2])});
            line = reader.readLine();
        }
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