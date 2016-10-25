package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {
    
    @Test
    public void testContactCreation() {
        gotoGroupPage();
        initContactCreation();
        fillContactForm(new ContactData("jbfvkfgydju", "yuitdjftyu", "fdjykjhxdfyue", "86469", "4648"));
        submitContactCreation();
        gotoHomePage();

    }

}
