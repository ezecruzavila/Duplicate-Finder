package org.duplicateFinder.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.duplicateFinder.TestUtils.mockContact;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DuplicateContactTest {

    private Contact contact;
    private DuplicateContact duplicateContact;

    @BeforeEach
    public void setUp() {
        contact = mockContact();
        duplicateContact = DuplicateContact.builder()
                .contact(contact)
                .accuracy("High")
                .build();
    }

    @Test
    public void testGetContact() {
        assertEquals(contact, duplicateContact.getContact());
    }

    @Test
    public void testGetAccuracy() {
        assertEquals("High", duplicateContact.getAccuracy());
    }

    @Test
    public void testBuilder() {
        DuplicateContact anotherDuplicateContact = DuplicateContact.builder()
                .contact(contact)
                .accuracy("Low")
                .build();

        assertNotNull(anotherDuplicateContact);
        assertEquals(contact, anotherDuplicateContact.getContact());
        assertEquals("Low", anotherDuplicateContact.getAccuracy());
    }
}