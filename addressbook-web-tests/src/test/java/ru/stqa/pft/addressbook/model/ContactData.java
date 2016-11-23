package ru.stqa.pft.addressbook.model;

public class ContactData {

    //po umolchaniu novomu kontaktu prisvaevaetsa samoe bolshoe celoe chislo
    private int id = Integer.MAX_VALUE;
    private String firstname;
    private String lastname;
    private String group;
    private String homePhone;
    private String mobilePhone;
    private String workPhone;
    private String allPhones;
    private String email;
    private String email2;
    private String email3;
    private String allEmails;

    public String getEmail() {
        return email;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
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

    public String getHomePhone() {
        return homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public String getAllEmails() {
        return allEmails;
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

    public ContactData withEmail(String email) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

}