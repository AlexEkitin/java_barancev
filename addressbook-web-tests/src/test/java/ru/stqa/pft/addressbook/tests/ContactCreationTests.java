package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {
    
    @Test
    public void testContactCreation() {
        app.getContactHelper().createContact(new ContactData("jbfvkfgydju", "yuitdjftyu", "fdjykjhxdfyue", "86469", "4648", "test1"), true);
    }

}
