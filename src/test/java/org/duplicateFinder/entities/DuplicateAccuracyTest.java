package org.duplicateFinder.entities;

import org.duplicateFinder.utils.Accuracy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.duplicateFinder.TestUtils.mockContact;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DuplicateAccuracyTest {

    private Contact contact;
    private DuplicateAccuracy duplicateAccuracy;

    @BeforeEach
    public void setUp() {
        contact = mockContact();
        duplicateAccuracy = new DuplicateAccuracy(contact);
    }

    @Test
    public void testGetContact() {
        assertEquals(contact, duplicateAccuracy.getContact());
    }

    @Test
    public void testPossibleDuplicatesInitiallyEmpty() {
        List<DuplicateContact> possibleDuplicates = duplicateAccuracy.getPossibleDuplicates();
        assertTrue(possibleDuplicates.isEmpty());
    }

    @Test
    public void testAddPossibleDuplicate() {
        contact = mockContact();
        DuplicateContact duplicateContact = DuplicateContact.builder()
                .contact(contact)
                .accuracy(Accuracy.ZERO.value)
                .build();
        duplicateAccuracy.getPossibleDuplicates().add(duplicateContact);

        List<DuplicateContact> possibleDuplicates = duplicateAccuracy.getPossibleDuplicates();
        assertEquals(1, possibleDuplicates.size());
        assertEquals(duplicateContact, possibleDuplicates.get(0));
    }
}