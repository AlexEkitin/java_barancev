package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupModificationTests extends TestBase {

    public Properties properties = new Properties();

    @BeforeMethod
    public void ensurePreconditions() throws IOException {
        if (app.db().groups().size() == 0) {
            String target = System.getProperty("target", "local");
            properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
            app.goTo().groupPage();
            app.group().create(new GroupData()
                    .withName(properties.getProperty("groupName"))
                    .withHeader(properties.getProperty("groupHeader"))
                    .withFooter(properties.getProperty("groupFooter")));
        }
    }

    @Test
    public void testGroupModification() {
        Groups before = app.db().groups();
        GroupData modifiedGroup = before.iterator().next();
        //pri modifikacii gruppi mi ukazivaem novie dannie, a id ostavlaem starii
        GroupData group = new GroupData()
                .withId(modifiedGroup.getId())
                .withName(properties.getProperty("groupNewName"))
                .withHeader(properties.getProperty("groupNewHeader"))
                .withFooter(properties.getProperty("groupNewFooter"));
        app.goTo().groupPage();
        app.group().modify(group);
        assertThat(app.group().count(), equalTo(before.size()));
        Groups after = app.db().groups();
        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
    }
}