package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    //sozdaem pola, komorie budut otobrozat onformaciyu na komandnoi stroke,
    //esli programma padaet
    //annotaciia "@Parameter" iz biblioteki JCommander,
    //v skobkah - imia parametra v komandnoi stroke i ego opisanie
    //olichestvo kontactov
    @Parameter(names = "-c", description = "Contact count")
    public int count;
    //put k failu
    @Parameter(names = "-f", description = "Target file")
    public String file;
    //format faila
    @Parameter(names = "-d", description = "Data format")
    public String format;

    //cherez komandnuiu stroku mi peredaem 2 parametra:
    public static void main(String[] args) throws IOException {
        //sozdaem obekt tekusego klassa
        ContactDataGenerator generator = new ContactDataGenerator();
        //sozdaem obekt tipa "JCommander", parametr "generator" - eto obekt, v kotorom
        //dolzni bit zapolneni sootvetstvuyusie atributi
        JCommander jCommander = new JCommander(generator);
        try {
            //parametr "args" - eto opcii, kotorie peredautsa v komandnoi stroke
            jCommander.parse(args);
        } catch (ParameterException ex) {
            //esli iskluchenie vozniklo, to vivodim na konsol informaciiu o dostupnih opciyah
            jCommander.usage();
            return;
        }
        //zapusk generatora
        generator.run();
    }

    private void run() throws IOException {
        //generaciya dannih
        List<ContactData> contacts = generateContacts(count);
        if (format.equals("csv")){
            //sohranenie dannih v fail
            //new File(file) - preobrazovivaem iz tipa String v tip File
            saveAsCsv(contacts, new File(file));
        } else if (format.equals("xml")) {
            saveAsXml(contacts, new File(file));
        } else {
            System.out.println("Unrecognized format "+ format);
        }
    }

    //contacts - eto spisok contactov, kotorii nuzno sohranat
    //file - eto fail, v kotorii nuzno sohranat
    private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
        XStream xstream = new XStream();
        //teg "contact" teper ispolzuetsa pri sozdanii kazdoi novoi gruppi v faike
        xstream.processAnnotations(ContactData.class);
        //prevrashaem iz obekta "contacts" v stroku v formate xml
        String xml = xstream.toXML(contacts);
        //otkrivaem fail na zapis
        Writer writer = new FileWriter(file);
        //zapisivaem dannie v fail
        writer.write(xml);
        //zakrivaem fail
        writer.close();
    }

    private List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        //zapolniaem list znacheniuami
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData().withFirstname(String.format("Firstname%s", i))
                    .withLastname(String.format("Lastname%s", i)).withGroup(String.format("test%s", i)));
        }
        return contacts;
    }

    //kazdii kontakt sohraniaetsa f fail v vide otdelnoi stroki, Firstname i Lastnamerazdeleni ;
    //IOException - pri vozniknovenii Exception ono ne perehvativaetsa, a otpravlaetsa na 1 stupupen vverh
    private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
        //otkrivaem fail na zapis
        Writer writer = new FileWriter(file);
        //dla kazdogo kontakta v faile budet sozdana otdelnaya srtoka s informaciei o kontakte
        for (ContactData contact : contacts) {
            writer.write(String.format("%s;%s;%s\n", contact.getFirstname(), contact.getLastname(), contact.getGroup()));
        }
        //zakritie faila, to est vsa sgenerirovannaya informaciya o kontaktah zapisivaetsa na disk
        writer.close();
    }

}
