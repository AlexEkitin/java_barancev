package ru.stqa.pft.addressbook.generators;

import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.channels.Channel;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
    public static void main(String[] args) throws IOException {
        //pervii parametr - kolichestvo kontactov
        //Integer.parseInt(args[0]) - preobrazovivaem v chislo
        int count = Integer.parseInt(args[0]);
        //vtoroi parametr - put k failu
        //File(args[1]) - preobrazovivael v fail
        File file = new File(args[1]);

        //generaciya dannih
        List<ContactData> contacts = generateContacts(count);
        //sohranenie dannih v fail
        save(contacts, file);
    }

    private static List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        //zapolniaem list znacheniuami
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData().withFirstname(String.format("Firstname%s", i))
                    .withLastname(String.format("Lastname%s", i)));
        }
        return contacts;
    }

    //kazdii kontakt sohraniaetsa f fail v vide otdelnoi stroki, Firstname i Lastnamerazdeleni ;
    //IOException - pri vozniknovenii Exception ono ne perehvativaetsa, a otpravlaetsa na 1 stupupen vverh
    private static void save(List<ContactData> contacts, File file) throws IOException {
        //otkrivaem fail na zapis
        Writer writer = new FileWriter(file);
        //dla kazdogo kontakta v faile budet sozdana otdelnaya srtoka s informaciei o kontakte
        for (ContactData contact : contacts) {
            writer.write(String.format("%s; %s\n", contact.getFirstname(), contact.getLastname()));
        }
        //zakritie faila, to est vsa sgenerirovannaya informaciya o kontaktah zapisivaetsa na disk
        writer.close();
    }

}
