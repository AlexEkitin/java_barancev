package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTests extends TestBase {

    @BeforeMethod //proverka preduslovii
    public void ensurePreconditions(){
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withFirstname("firstname1").withLastname("lastname1").withGroup("group1"));
        }
    }

    @Test
    public void testContactPhones() {
        app.goTo().homePage();
        //berem list kontaktov, iz nego vibiraem odin sluchainim obrazom
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        //sravnivaem s ochishennoi ot probelov versiei
        //contact.getEmail() - beretsa to, chto mi videm na stranice
        //contactInfoFromEditForm.getEmail() - eto to, chto mi zapolnaem pri sozdanii kontakta
        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
    }

    private String mergeEmails(ContactData contact) {
        //iz vseh emails formiruem list, elementi kotorogo budem skleivat
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                //prevrashaem list v stream
                .stream()
                //filtruem stream, to est vibrasivaem iz nego pustie elementi
                //s - kazdaya stroka, kotoraya proveriaitsa, ee nuzno ostavit,
                //esli ona ne ravna pustoi stroke to est  ! s.equals("")
                .filter((s) -> ! s.equals(""))
                //ochishaem ot probelov kazdii element,
                // map - oznachaet, chto eto nuzno primenit ko vsem elementam
                .map(ContactEmailTests::cleaned)
                //mi poluchili novii potok elementov bez pustih strok, ih nuzno skleit
                //"\n" - eto to, chto budet skleivatsa mezdu skleivsemimi fragmentami
                .collect(Collectors.joining("\n"));
    }

    //udalenie nenuznih simvolov
    public static String cleaned(String phone){
        //  \\s - oznachaet probel, nam nuzno ih udalit, to est zamenit na nichego
        return phone.replaceAll("\\s", "");
    }
}
