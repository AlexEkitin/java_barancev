package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


public class ApplicationManager {
    // attribute with ssilka na driver
    private final Properties properties;
    WebDriver wd;

    // declaration without initialization
    //eto atributi - peremennie klassa
    private ContactHelper contactHelper;
    private SessionHelper sessionHelper;
    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private String browser;
    private DbHelper dbHelper;


    public ApplicationManager(String browser) {
        this.browser = browser;
        //inicializaciya obekta
        properties = new Properties();
    }

    public void init() throws IOException {
        //konfiguracionnii fail
        String target = System.getProperty("target", "local");
        //zagruzaem konfiguracionnii fail
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

        dbHelper = new DbHelper();

        if (browser.equals(BrowserType.CHROME)) {
            wd = new ChromeDriver();
        } else if (browser.equals(BrowserType.FIREFOX)) {
            wd = new FirefoxDriver();
        } else if (browser.equals(BrowserType.IE)) {
            wd = new InternetExplorerDriver();
        }
        //wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        //vmesto konkretnogo URL budet ispolzovatsa znachenie svoistva, kotoroe zagruzaetsa iz vneshnego faila
        wd.get(properties.getProperty("web.baseUrl"));
        // initialisation
        // wd - parameter in constructor for all helpers
        groupHelper = new GroupHelper(wd);
        contactHelper = new ContactHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        //znacheniya obektov budut zagruzatsa iz konfiguracionnogo faila
        sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
    }

    public void stop() {
        wd.quit();
    }

    public GroupHelper group() {
        return groupHelper;
    }

    public NavigationHelper goTo() {
        return navigationHelper;
    }

    public ContactHelper contact() {
        return contactHelper;
    }

    public DbHelper db() {
        return dbHelper;
    }

}