package org.duplicateFinder.entities;

import org.junit.jupiter.api.Test;

import static org.duplicateFinder.TestUtils.mockContact;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ContactTest {

    @Test
    public void testContactBuilder() {
        Contact contact = Contact.builder()
                .contactId(1)
                .name("John")
                .surname("Doe")
                .email("john.doe@example.com")
                .postalZip(12345)
                .address("123 Main St, City")
                .build();

        assertEquals(1, contact.getContactId());
        assertEquals("John", contact.getName());
        assertEquals("Doe", contact.getSurname());
        assertEquals("john.doe@example.com", contact.getEmail());
        assertEquals(12345, contact.getPostalZip());
        assertEquals("123 Main St, City", contact.getAddress());
    }

    @Test
    public void testEqualsAndHashCode() {
        // Create two identical contacts
        Contact contact1 = mockContact();

        Contact contact2 = mockContact();

        assertEquals(contact1, contact2);
        assertEquals(contact1.hashCode(), contact2.hashCode());

        contact2 = Contact.builder()
                .contactId(1)
                .name("John")
                .surname("Doe")
                .email("john.doe@example.com")
                .postalZip(54321) // Changed postalZip
                .address("123 Main St, City")
                .build();

        assertNotEquals(contact1, contact2);
        assertNotEquals(contact1.hashCode(), contact2.hashCode());
    }

}