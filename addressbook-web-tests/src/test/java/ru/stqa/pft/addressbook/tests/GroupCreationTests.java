package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().gotoGroupPage();
        //spisok obektov tipa <GroupData>
        List<GroupData> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().createGroup(new GroupData("groupname1", "header1", "footer1"));
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(before.size() + 1, after.size());
    }

}