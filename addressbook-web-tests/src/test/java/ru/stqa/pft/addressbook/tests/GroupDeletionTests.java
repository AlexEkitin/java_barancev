package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if (app.group().list().size() == 0){
            app.group().create(new GroupData("groupname1", "header1", "footer1"));
        }
    }

    @Test
    public void testGroupDeletion() {
        List<GroupData> before = app.group().list();
        int index = before.size() - 1;
        app.group().delete(index);
        List<GroupData> after = app.group().list();
        Assert.assertEquals(before.size() - 1, after.size());

        //udaliaem tot ze element iz spiska "before", chto i so stranici (before.size() - 1)
        before.remove(index);

        //teper starii spisok "before" soderzit te ze elementi, chto i novii "after"
        Assert.assertEquals(before, after);


    }


}