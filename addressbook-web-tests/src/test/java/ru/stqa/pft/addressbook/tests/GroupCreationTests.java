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
        // list of groups before
        app.getNavigationHelper().gotoGroupPage();
        List<GroupData> before = app.getGroupHelper().getGroupList();
        GroupData group = new GroupData("test2", null, null);
        app.getGroupHelper().createGroup(group);
        // list of groups after
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() + 1);

        // compare I type
        before.add(group);
        int max = 0;
        for (GroupData g : after){
            if (g.getId()>max) {
                max = g.getId();
            }
        }

        // compare II type

        // compare 2 objects type GroupData (o1 and o2)
        Comparator<? super GroupData> byId = new Comparator<GroupData>() {
            @Override
            public int compare(GroupData o1, GroupData o2) {
                return Integer.compare(o1.getId(), o2.getId());
            }
        };
        // find max objects and get his Id
        int max1 = after.stream().max(byId).get().getId();


        group.setId(max1);
        before.add(group);
        // modify from lists to sets
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }

}
