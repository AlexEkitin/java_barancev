package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().gotoGroupPage();
        //spisok obektov tipa <GroupData>
        List<GroupData> before = app.getGroupHelper().getGroupList();
        GroupData group = new GroupData("groupname2", "header1", "footer1");
        app.getGroupHelper().createGroup(group);
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(before.size() + 1, after.size());

        //u novoi gruppi maksimalnii id, mi vichislaem max id ispolzuia cikl
        //zadaem nachalnoe znachenie dla peremennoi max
        int max = 1;
        //g - eto uslovnaia ssilka na konkretnii element spiska
        //after - spisok, v kotorom mi ishem max element
        for (GroupData g : after) {
            if (g.getId() > max) {
                max = g.getId();
            }
        }
        //v konce cikla mi nahodim max id i prisvaevaem ego obektu "group"
        group.setId(max);
        //v starii spisok dobavili tu gruppu, kotoruyu mi realno sozdali na saite
        before.add(group);
        //preobrazuem list v set (HashSet<Object>) i sravnim ih
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }

}