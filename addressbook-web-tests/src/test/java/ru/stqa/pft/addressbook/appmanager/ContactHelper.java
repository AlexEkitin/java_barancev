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
import java.util.List;

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

    public void deleteSelectContacts() {
        click(By.xpath("//div[2]/input"));
        wd.switchTo().alert().accept();
    }

    public void chooseEditContact(int index) {
        wd.findElements(By.cssSelector("img[alt=\"Edit\"]")).get(index).click();
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void deleteContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void createContact(ContactData contact) {
        initContactCreation();
        fillContactForm(contact, true);
        submitContactCreation();
        gotoHomePage();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.cssSelector("img[alt=\"Edit\"]"));
    }

    public int getContactCount() {
        //nahodit vse elementi i vozvrashaet list, iv nego berem size()
        return wd.findElements(By.name("selected[]")).size();
    }


    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        //nahodim vse elementi i pomesaem ih v list
        List<WebElement> elements = wd.findElements(By.xpath(".//*[@name='entry']"));
        //element - peremennaya, kotoraya probegaet po spisku "elements"
        for (WebElement element : elements) {
            //iz kazdogo elementa poluchaem tekst - imia gruppi
            String firstName = element.findElement(By.xpath(".//td[3]")).getText();
            String lastName = element.findElement(By.xpath(".//td[2]")).getText();
            //int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String id = element.findElement(By.tagName("input")).getAttribute("value");
            ContactData contact = new ContactData(id, firstName, lastName, null);
            //dobavlaem obekt "contact" v spisok
            contacts.add(contact);
        }
        return contacts;
    }
}