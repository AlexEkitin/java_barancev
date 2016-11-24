package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

public class HelperBase {
    protected WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    protected void click(By locator) {
        wd.findElement(locator).click();
    }


    protected void type(By locator, String text) {
        click(locator);
        //don't touch the field, if new text for the field is "null"
        if (text != null) {
            String existingText = wd.findElement(locator).getAttribute("value");
            //don't touch the field, if new text equal old text
            if (! text.equals(existingText)) {
                wd.findElement(locator).clear();
                wd.findElement(locator).sendKeys(text);
            }
        }
    }

    //rabota s failom
    protected void attach(By locator, File file) {
        //don't touch the field, if new text for the field is "null"
        if (file != null) {
                wd.findElement(locator).sendKeys(file.getAbsolutePath());
            }
    }

    public boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    //check presence or absence of the element by locator
    //return true or false
    protected boolean isElementPresent(By locator) {
        try {
            wd.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }
}