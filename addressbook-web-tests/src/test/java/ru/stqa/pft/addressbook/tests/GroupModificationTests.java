package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if (app.group().all().size() == 0){
            app.group().create(new GroupData().withName("groupname1"));
        }
    }

    @Test
    public void testGroupModification() {
        Set<GroupData> before = app.group().all();
        GroupData modifiedGroup = before.iterator().next();
        //pri modifikacii gruppi mi ukazivaem novie dannie, a id ostavlaem starii
        GroupData group = new GroupData()
                .withId(modifiedGroup.getId())
                .withName("groupname1")
                .withHeader("header1")
                .withFooter("footer1");
        app.group().modify(group);
        Set<GroupData> after = app.group().all();
        Assert.assertEquals(before.size(), after.size());

        //udaliaem staruu versiu elementa, kotorogo mi modificirovali na saite i...
        before.remove(modifiedGroup);
        //...dobavim novii element, kotorii poyavitsa posle modifikacii s parametrami "group"
        before.add(group);
        //sravnivaem 2 spiska
        Assert.assertEquals(before, after);
    }
}