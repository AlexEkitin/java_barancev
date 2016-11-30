package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
@Entity
@Table(name="addressbook")
@XStreamAlias("contact")
public class ContactData {

    @Id
    @Column(name="id")
    @XStreamOmitField //propuskaet eto pole pri zapolnenii xml faila
    //po umolchaniu novomu kontaktu prisvaevaetsa samoe bolshoe celoe chislo
    private int id = Integer.MAX_VALUE;
    @Expose
    @Column(name="firstname")
    private String firstname;
    @Expose
    @Column(name="lastname")
    private String lastname;
    @Expose
    @Transient
    private String group;
    @Expose
    private String address;
    @Expose
    @Column(name="home")
    @Type(type = "text")
    private String homePhone;
    @Expose
    @Column(name="mobile")
    @Type(type = "text")
    private String mobilePhone;
    @Expose
    @Column(name="work")
    @Type(type = "text")
    private String workPhone;
    @Transient
    private String allPhones;
    @Expose
    private String email;
    @Expose
    private String email2;
    @Expose
    private String email3;
    private String allEmails;
    @Column(name="photo")
    @Type(type = "text")
    private String photo;


    public String getAllPhones() {
        return allPhones;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getGroup() {
        return group;
    }

    public String getAddress() {
        return address;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public String getEmail() {
        return email;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public File getPhoto() {
        return new File(photo);
    }

    //etot metod vizvrashaet tot ze obekt, v kotorom on vizvan
    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    //etot metod vizvrashaet tot ze obekt, v kotorom on vizvan
    public ContactData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    //etot metod vizvrashaet tot ze obekt, v kotorom on vizvan
    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    //etot metod vizvrashaet tot ze obekt, v kotorom on vizvan
    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    //etot metod vizvrashaet tot ze obekt, v kotorom on vizvan
    public ContactData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    //etot metod vizvrashaet tot ze obekt, v kotorom on vizvan
    public ContactData withMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    //etot metod vizvrashaet tot ze obekt, v kotorom on vizvan
    public ContactData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }

    public ContactData withEmail1(String email) {
        this.email = email;
        return this;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        //return this - nuzno dla vitagivaniya v cipochku
        return this;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", group='" + group + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
        return group != null ? group.equals(that.group) : that.group == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (group != null ? group.hashCode() : 0);
        return result;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

}