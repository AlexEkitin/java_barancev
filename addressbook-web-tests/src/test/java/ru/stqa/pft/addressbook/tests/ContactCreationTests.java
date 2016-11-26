package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactCreationTests extends TestBase {

    //provaider testovih dannih nuzen, chtobi zagruzat testovie dannie v test
    @DataProvider
    public Iterator<Object[]> validContactsFromXml() throws IOException {
        //put k failu otnositelno rabochei derrictorii "addressbook-web-tests"
        //File photo = new File("src/test/resources/stru.png");
        //spisok massivov
        List<Object[]> list = new ArrayList<Object[]>();
        //sozdaetsa obekt tipa "BufferedReader", i cherez nego proishodit chtenie dannih iz faila
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")));
        String xml = "";
        //readLine() - chitaet stroku i srazu ee vozvrachaet
        String line = reader.readLine();
        //cikl "while" nuzet, tak kak mi ne znaem skolko strok budet v faile
        while (line != null) {
            //dobavlaem strochki k peremennoi "xml"
            xml += line;
            line = reader.readLine();
        }
        XStream xstream = new XStream();
        //xstream obrabativaet annotacii v klasse "ContactData.class"
        xstream.processAnnotations(ContactData.class);
        List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
        //k kazdomu obektu v potoke primeniaem funkciu, kotoraya zavorachivaet obekt v massiv,
        //sostoiachii iz odnogo etogo ibekta
        //collect(Collectors.toList()) - iz stream delaet list
        return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
    }

    //provaider testovih dannih nuzen, chtobi zagruzat testovie dannie v test
    @DataProvider
    public Iterator<Object[]> validContactsFromJson() throws IOException {
        //put k failu otnositelno rabochei derrictorii "addressbook-web-tests"
        //File photo = new File("src/test/resources/stru.png");
        //spisok massivov
        List<Object[]> list = new ArrayList<Object[]>();
        //sozdaetsa obekt tipa "BufferedReader", i cherez nego proishodit chtenie dannih iz faila
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")));
        String json = "";
        //readLine() - chitaet stroku i srazu ee vozvrachaet
        String line = reader.readLine();
        //cikl "while" nuzet, tak kak mi ne znaem skolko strok budet v faile
        while (line != null) {
            //dobavlaem strochki k peremennoi "xml"
            json += line;
            line = reader.readLine();
        }
        Gson gson = new Gson();
        //new TypeToken<List<ContactData>>(){}.getType() - tip dannih, motorie dolzni bit serrializovani
        List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType());
        //k kazdomu obektu v potoke primeniaem funkciu, kotoraya zavorachivaet obekt v massiv,
        //sostoiachii iz odnogo etogo ibekta
        //collect(Collectors.toList()) - iz stream delaet list
        return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
    }


    @Test(dataProvider = "validContactsFromJson")
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