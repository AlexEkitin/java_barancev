package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod //proverka preduslovii
    public void ensurePreconditions(){
        app.goTo().homePage();
        if (app.contact().list().size() == 0) {
            app.contact().create(new ContactData().withFirstname("firstname1").withLastname("lastname1").withGroup("group1"));
        }
    }

    @Test//(enabled = false)
    public void testContactDeletion() {
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        app.contact().delete(index);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(before.size() - 1, after.size());

        //udalaem iz before tot element, kotorii mi udalili so stranici
        before.remove(index);
        //stavnivaem dva spiska
        Assert.assertEquals(before, after);


    }


}