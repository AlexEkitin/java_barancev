package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().gotoGroupPage();
        //spisok obektov tipa <GroupData>
        List<GroupData> before = app.getGroupHelper().getGroupList();
        GroupData group = new GroupData("groupname1", "header1", "footer1");
        app.getGroupHelper().createGroup(group);
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(before.size() + 1, after.size());

        //v starii spisok dobavili tu gruppu, kotoruyu mi realno sozdali na saite
        before.add(group);
        //funkcia na vhod prinimaet 2 parametra -2 gruppi, i sravnivaet ih id
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(),g1.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

}