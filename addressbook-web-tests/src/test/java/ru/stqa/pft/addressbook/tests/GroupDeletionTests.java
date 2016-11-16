package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.getNavigationHelper().gotoGroupPage();
        if (! app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("groupname1", "header1", "footer1"));
        }
    }

    @Test
    public void testGroupDeletion() {
        List<GroupData> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().selectGroup(before.size() - 1);
        //fizicheskoe udalenie gruppi so stranici, no ne iz spiska "before"
        app.getGroupHelper().deleteSelectedGroups();
        app.getGroupHelper().returnToGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(before.size() - 1, after.size());

        //udaliaem tot ze element iz spiska "before", chto i so stranici (before.size() - 1)
        before.remove(before.size() - 1);

        //teper starii spisok "before" soderzit te ze elementi, chto i novii "after"
        Assert.assertEquals(before, after);


    }

}