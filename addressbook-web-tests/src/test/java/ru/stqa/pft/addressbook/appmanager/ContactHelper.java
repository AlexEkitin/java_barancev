package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
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


    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        //nahodim vse elementi i pomesaem ih v list
        List<WebElement> elements = wd.findElements(By.xpath(".//*[@name='entry']"));
        //element - peremennaya, kotoraya probegaet po spisku "elements"
        for (WebElement element : elements) {
            //iz kazdogo elementa poluchaem tekst - imia gruppi
            String firstName = element.findElement(By.xpath(".//td[3]")).getText();
            String lastName = element.findElement(By.xpath(".//td[2]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            //dobavlaem obekt "contact" v spisok
            contacts.add(new ContactData().withId(id).withFirstname(firstName).withLastname(lastName));
        }
        return contacts;
    }

    //metod vozvrashaet set
    public Set<ContactData> all() {
        //sozdaem set tipa <ContactData>, kotorii budem zapolnat
        Set<ContactData> contacts = new HashSet<ContactData>();

        //nahodim vse elementi na stranice i pomesaem ih v list
        //<WebElement> - eto specialnii tip, on propisan v biblioteke Selenium
        List<WebElement> elements = wd.findElements(By.xpath(".//*[@name='entry']"));

        //element - peremennaya, kotoraya probegaet po list "elements"
        for (WebElement element : elements) {
            //iz kazdogo elementa poluchaem tekst
            String firstName = element.findElement(By.xpath(".//td[3]")).getText();
            String lastName = element.findElement(By.xpath(".//td[2]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            //kazdii raz dobavlaem novii obekt "contact" v set
            contacts.add(new ContactData().withId(id).withFirstname(firstName).withLastname(lastName));
        }
        return contacts;//vozvrashaem set
    }


}