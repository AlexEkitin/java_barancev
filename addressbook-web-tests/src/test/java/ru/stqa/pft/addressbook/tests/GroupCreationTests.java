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

        //v sozdannom elemente budet samii bolsoi id, i dla ego nahozdenia mi dolzni
        //proitis po vsemu spisku, sravnit vse elementi poocheredi i vibtam max id:
        //list prevresaem v stream, po nemu prohodit specialnaia funkcia sravnenia i sravnivaet
        //ih id, iz gruppi s max id berem id i peredaem v group.setId()
        group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        //v starii spisok dobavili tu gruppu, kotoruyu mi realno sozdali na saite
        before.add(group);
        //preobrazuem list v set (HashSet<Object>) i sravnim ih
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }

}