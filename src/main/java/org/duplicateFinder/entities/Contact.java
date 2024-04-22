package org.duplicateFinder.entities;

import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Builder
@Getter
public class Contact {

    private final int contactId;
    private final String name;
    private final String surname;
    private final String email;
    private final int postalZip;
    private final String address;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return contactId == contact.contactId &&
                postalZip == contact.postalZip &&
                Objects.equals(name, contact.name) &&
                Objects.equals(surname, contact.surname) &&
                Objects.equals(email, contact.email) &&
                Objects.equals(address, contact.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contactId, name, surname, email, postalZip, address);
    }
}
