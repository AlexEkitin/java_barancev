package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class GroupModificationTests extends TestBase {

    @Test
    public void testGroupModification() {
        app.getNavigationHelper().gotoGroupPage();
        if (! app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("groupname1", "header1", "footer1"));
        }
        List<GroupData> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().selectGroup(before.size() - 1);
        app.getGroupHelper().initGroupModification();
        //pri modifikacii gruppi mi ukazivaem novie dannie, a id ostavlaem starii
        GroupData group = new GroupData(before.get(before.size() - 1).getId(), "groupname1", "header1", "footer1");
        app.getGroupHelper().fillGroupForm(group);
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(before.size(), after.size());

        //udaliaem staruu versiu elementa, kotorogo mi modificirovali na saite i...
        before.remove(before.size() - 1);
        //...dobavim novii element, kotorii poyavitsa posle modifikacii s parametrami "group"
        before.add(group);
        //preobrazuem list v set (HashSet<Object>) i sravnim ih
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }
}