package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

public class Contacts extends ForwardingSet<ContactData> {

    //obekt, kotoromu delegiruutsa deistviya
    private Set<ContactData> delegate;

    //konstruktor delaet kopiu suchestvuusego obekta "Contacts"
    //berem sushestvuushii set iz obekta "contakts", kotorii peredan v kachestve perametra,
    //stroim novii set, kotorii soderzit te ze elementi, i prisvaevaem eto novoe mnozestvo
    //v kachestve parametra v novii sozdavaemii konstruktorom obekt
    public Contacts(Contacts contacts) {
        this.delegate = new HashSet<ContactData>(contacts.delegate);
    }

    public Contacts() {
        this.delegate = new HashSet<ContactData>();
    }

    //obyzatelnii metod, on neponiatno chto delaet
    @Override
    protected Set<ContactData> delegate() {
        return delegate;
    }

    //statii obekt "contacts" ne meniaetsa,
    // a etot metod delaet kopiu obekta s dobavlennim novim kintaktom
    public Contacts withAdded (ContactData contact) {
        Contacts contacts = new Contacts(this);
        contacts.add(contact);
        return contacts;
    }

    //metod udalaet kontakt
    public Contacts withOut (ContactData contact) {
        Contacts contacts = new Contacts(this);
        contacts.remove(contact);
        return contacts;
    }
}
