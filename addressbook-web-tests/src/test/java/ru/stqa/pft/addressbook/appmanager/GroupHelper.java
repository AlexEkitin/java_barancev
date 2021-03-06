package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GroupHelper extends HelperBase {

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    public void submitGroupCreation() {
        click(By.name("submit"));
    }

    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }

    public void initGroupCreation() {
        click(By.name("new"));
    }

    public void deleteSelectedGroups() {
        click(By.name("delete"));
    }

    public void selectGroupById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }


    public void initGroupModification() {
        click(By.name("edit"));
    }

    public void submitGroupModification() {
        click(By.name("update"));
    }

    public void create(GroupData group) {
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        //spisok grupp pomenialsa, i cache nuzno obnulit
        groupCache = null;
        returnToGroupPage();
    }

    public void modify(GroupData group) {
        selectGroupById(group.getId());
        initGroupModification();
        fillGroupForm(group);
        submitGroupModification();
        //spisok grupp pomenialsa, i cache nuzno obnulit
        groupCache = null;
        returnToGroupPage();
    }

    public void delete(GroupData group) {
        selectGroupById(group.getId());
        deleteSelectedGroups();
        //spisok grupp pomenialsa, i cache nuzno obnulit
        groupCache = null;
        returnToGroupPage();
    }


    public boolean isThereAGroup() {
        return isElementPresent(By.name("selected[]"));
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    //sozdanie i zapolnenie list dannimi so stranici - id i name
    public List<GroupData> list() {
        //sozdanie spiska "groups" tipa <GroupData>, kotorii budem zapolniat
        List<GroupData> groups = new ArrayList<GroupData>();
        //so stranici sobiraem opredelennie elementi - nazvaniya vseh grupp
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        //probegaem po spisku elementov i izvlekaem iz kazdogo tekst - imia gruppi i id
        for (WebElement element : elements) {
            String name = element.getText();
            //isem odin element vnutri drugova po tegu "input", i berem u nego atribut "value"...
            //... i preobrazovivaem stroku v chislo
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            //sozdaem obekt tipa GroupData, mi znaem tolko imya,
            //poetomu ostalnie znachenia (header i footer) ne zapolniaem
            //dobavlaem sozdannii obekt  v spisok
            groups.add(new GroupData().withId(id).withName(name));
        }
        return groups;
    }

    //cache vnachale pust
    private Groups groupCache = null;

    public Groups all() {
        //esli cache ne pustoi, to nuzno vernut ego kopiyu
        //kopiya nuzna, chtobi cache nikto ne isportil
        if (groupCache != null){
            return new Groups(groupCache);
        }
        //esli cache ne pustoi, to nuzno ego zapolnit, prochitav spisok grupp so stranici
        groupCache = new Groups();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            groupCache.add(new GroupData().withId(id).withName(name));
        }
        //vozvraschaem kopiyu
        return new Groups(groupCache);
    }


}