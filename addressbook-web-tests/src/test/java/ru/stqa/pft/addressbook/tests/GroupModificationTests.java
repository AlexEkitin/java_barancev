package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if (app.group().list().size() == 0){
            app.group().create(new GroupData().withName("groupname1"));
        }
    }

    @Test
    public void testGroupModification() {
        List<GroupData> before = app.group().list();
        int index = before.size() - 1;
        //pri modifikacii gruppi mi ukazivaem novie dannie, a id ostavlaem starii
        GroupData group = new GroupData()
                .withId(before.get(index).getId())
                .withName("groupname1")
                .withHeader("header1")
                .withFooter("footer1");
        app.group().modify(index, group);
        List<GroupData> after = app.group().list();
        Assert.assertEquals(before.size(), after.size());

        //udaliaem staruu versiu elementa, kotorogo mi modificirovali na saite i...
        before.remove(index);
        //...dobavim novii element, kotorii poyavitsa posle modifikacii s parametrami "group"
        before.add(group);
        //sravnivaem elementi spiska po id
        //Comparator - opisanie pravil sravneniia
        //byId - localnaya peremennaya
        //funkcia na vhod prinimaet 2 parametra -2 gruppi, i sravnivaet ih id
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(),g1.getId());
        //spiski sortiruem po id
        before.sort(byId);
        after.sort(byId);
        //sravnivaem 2 spiska
        Assert.assertEquals(before, after);
    }
}