package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.goTo().groupPage();
        //spisok obektov tipa <GroupData>
        List<GroupData> before = app.group().list();
        GroupData group = new GroupData().withName("groupname1");
        app.group().create(group);
        List<GroupData> after = app.group().list();
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