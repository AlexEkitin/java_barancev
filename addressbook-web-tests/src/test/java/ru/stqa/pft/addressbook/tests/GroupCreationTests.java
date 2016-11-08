package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        // list of groups before
        app.getNavigationHelper().gotoGroupPage();
        List<GroupData> before = app.getGroupHelper().getGroupList();
        GroupData group = new GroupData("test8", null, null);
        app.getGroupHelper().createGroup(group);
        // list of groups after
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() + 1);

        // compare 2 objects type GroupData (o1 and o2)
        // find max objects and get his Id
        // transform from spisok to flow, find max element (use Id), get group with max id
        group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        before.add(group);
        // modify from lists to sets
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }

}
