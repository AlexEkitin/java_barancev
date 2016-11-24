package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {

    //constructor for wd
    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void gotoHomePage() {
        click(By.linkText("home page"));
    }

    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"),contactData.getFirstname());
        type(By.name("lastname"),contactData.getLastname());

        //pri sozdanii kontakta est vibor gruppi, pri modificacii - net
        if (creation){
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void selectContact(int index) {
        //nahodim vse elementi po lokatoru i iz etogo spiska berem
        //element s nuznim indeksom, i klikaem po nemu
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void selectContactById(int id) {
        //nahodim vse elementi po lokatoru i iz etogo spiska berem
        //element s nuznim indeksom, i klikaem po nemu
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }




    public void deleteSelectContacts() {
        click(By.xpath("//div[2]/input"));
        wd.switchTo().alert().accept();
    }



    public void chooseEditContact(int index) {
        wd.findElements(By.cssSelector("img[alt=\"Edit\"]")).get(index).click();
    }

    public void chooseEditContactById(int id) {
        wd.findElement(By.xpath("//input[@id='"+ id +"']//..//..//img[@alt='Edit']")).click();
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void deleteContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void create(ContactData contact) {
        initContactCreation();
        fillContactForm(contact, true);
        submitContactCreation();
        gotoHomePage();
    }

    public void modify(ContactData contact) {
        chooseEditContactById(contact.getId());
        fillContactForm((contact), false);
        submitContactModification();
        gotoHomePage();
    }

    public void delete(int index) {
        selectContact(index);
        deleteSelectContacts();
    }

    //udalaem contakt po id
    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectContacts();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.cssSelector("img[alt=\"Edit\"]"));
    }

    public int getContactCount() {
        //nahodit vse elementi i vozvrashaet list, iv nego berem size()
        return wd.findElements(By.name("selected[]")).size();
    }


    //sbor informacii obo vseh polah vseh kontaktov so stranici kontaktov
    public Set<ContactData> all() {
        Set<ContactData> contacts = new HashSet<ContactData>();
        //nahodim vse elementi i pomesaem ih v list
        List<WebElement> rows = wd.findElements(By.name("entry"));
        //WebElement - tip, rows - nazvaie spiska
        //row - peremennaya, kotoraya posledovatelno ukazivaet na vse elementu spiska "rows"
        for (WebElement row : rows) {
            //iz kazdogo elementa poluchaem tekst
            List<WebElement> cells = row.findElements(By.tagName("td"));

            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String lastName = cells.get(1).getText();
            String firstName = cells.get(2).getText();
            String address = cells.get(3).getText();
            String allEmails = cells.get(4).getText();
            String allPhones = cells.get(5).getText();

            //kazdii raz dobavlaem novii obekt "contact" v contacts
            contacts.add(new ContactData().withId(id).withFirstname(firstName).withLastname(lastName)
                    .withAddress(address)
                    .withAllPhones(allPhones)
                    .withAllEmails(allEmails));
        }
        return contacts;
    }

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        //nahodim vse elementi i pomesaem ih v list
        List<WebElement> elements = wd.findElements(By.xpath(".//*[@name='entry']"));
        //element - peremennaya, kotoraya probegaet po spisku "elements"
        for (WebElement element : elements) {
            //iz kazdogo elementa poluchaem tekst
            String firstName = element.findElement(By.xpath(".//td[3]")).getText();
            String lastName = element.findElement(By.xpath(".//td[2]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            //dobavlaem obekt "contact" v spisok
            contacts.add(new ContactData().withId(id).withFirstname(firstName).withLastname(lastName));
        }
        return contacts;
    }

    public Contacts all2() {
        Contacts contacts = new Contacts();

        //nahodim vse elementi na stranice i pomesaem ih v list
        //<WebElement> - eto specialnii tip, on propisan v biblioteke Selenium
        List<WebElement> elements = wd.findElements(By.xpath(".//*[@name='entry']"));

        //element - peremennaya, kotoraya probegaet po list "elements"
        for (WebElement element : elements) {
            //iz kazdogo elementa poluchaem tekst
            String firstName = element.findElement(By.xpath(".//td[3]")).getText();
            String lastName = element.findElement(By.xpath(".//td[2]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            //kazdii raz dobavlaem novii obekt "contact" v contacts
            contacts.add(new ContactData().withId(id).withFirstname(firstName).withLastname(lastName));
        }
        return contacts;
    }

    public ContactData infoFromViewForm(ContactData contact) {
        initContactView(contact.getId());
        String info[] = wd.findElement(By.id("content")).getText().replaceAll("[-():]", "").replaceAll("[MWH]", "")
                .replaceAll("\\n+\\s*", "\n").replaceFirst(" ", "\n").split("\n");
        String email = wd.findElement(By.xpath("//a[contains(@href, 'mailto:E-mail')]")).getText();
        String email2 = wd.findElement(By.xpath("//a[contains(@href, 'mailto:E-mail2')]")).getText();
        String email3 = wd.findElement(By.xpath("//a[contains(@href, 'mailto:E-mail3')]")).getText();
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(info[0]).withLastname(info[1]).
                withAddress(info[2]).withHomePhone(info[3]).withMobilePhone(info[4]).withWorkPhone(info[5]).
                withEmail(email).withEmail2(email2).withEmail3(email3);
    }

    //sobiraem informaciyu s formi redaktirovaniya
    public ContactData infoFromEditForm(ContactData contact) {
        //perehod na stranicu redaktirovaniya kontakta
        initContactModificationById(contact.getId());
        //sbor informacii o kontakte dla obekta "infoFromEditForm"
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        //vozvrashenie so stranici redaktirovaniya na stranicu s kontaktami
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
                .withAddress(address)
                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work)
                .withEmail(email).withEmail2(email2).withEmail3(email3);

    }

    //viberaem kontakt dla prosmotra informacii po id
    private void initContactView(int id){
        wd.findElement(By.cssSelector(String.format("a[href='view.php?id=%s']",id))).click();
    }


    //viberaem kontakt dla modofikacii po id
    private void initContactModificationById(int id) {
        //%s - mesto, kuda podstavlaetsa parametr
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']",id))).click();
    }
}