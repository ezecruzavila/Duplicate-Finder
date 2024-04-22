package org.duplicateFinder.services;

import lombok.experimental.ExtensionMethod;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.duplicateFinder.entities.Contact;
import org.duplicateFinder.entities.DuplicateAccuracy;
import org.duplicateFinder.entities.DuplicateContact;
import org.duplicateFinder.utils.Accuracy;
import org.duplicateFinder.utils.ContactExtensions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@ExtensionMethod(ContactExtensions.class)
@Service
public class DuplicateFinderService {

    @Value("${filename}")
    private final String filename;

    public DuplicateFinderService(String filename) {
        this.filename = filename;
    }

    public List<Contact> getContacts() throws IOException {
        Workbook workbook = this.getWorkbook();
        Sheet sheet = workbook.getSheetAt(0);
        int rowsCount = sheet.getPhysicalNumberOfRows();
        List<Contact> contacts = new ArrayList<>(rowsCount);
        // Starting from n = 1 to skip headers
        for (int n = 1; n < rowsCount; n++) {
            contacts.add(sheet.getRow(n).toContact());
        }
        return contacts;
    }

    private Workbook getWorkbook() throws IOException {
        InputStream is = getClass().getResourceAsStream(filename);
        if (is == null)
            throw new FileNotFoundException();
        return WorkbookFactory.create(is);
    }

    public List<DuplicateAccuracy> findDuplicates() throws IOException {
        List<Contact> contacts = getContacts();
        List<DuplicateAccuracy> duplicateAccuracyList = new ArrayList<>();
        contacts.forEach(contact -> contacts.forEach(possibleDuplicateContact -> {
            DuplicateAccuracy duplicateAccuracy = new DuplicateAccuracy(contact);
            if (!(contact.getContactId() == (possibleDuplicateContact.getContactId()))) {
                String accuracy = ponderDuplicate(contact, possibleDuplicateContact);
                if (!accuracy.equals(Accuracy.ZERO.value)) {
                    DuplicateContact duplicateContact = DuplicateContact.builder()
                            .contact(possibleDuplicateContact)
                            .accuracy(accuracy)
                            .build();
                    duplicateAccuracy.getPossibleDuplicates().add(duplicateContact);
                }
            }
            if (!duplicateAccuracy.getPossibleDuplicates().isEmpty())
                duplicateAccuracyList.add(duplicateAccuracy);
        }));
        return duplicateAccuracyList;
    }

    private String ponderDuplicate(Contact contact, Contact anotherContact) {
        boolean nameMatches = false;
        boolean surnameMatches = false;
        boolean fullNameMatches = (contact.getName().equals(anotherContact.getName()) &&
                contact.getSurname().equals(anotherContact.getSurname()));
        if (!fullNameMatches) {
            nameMatches = contact.getName().equals(anotherContact.getName());
            surnameMatches = contact.getSurname().equals(anotherContact.getSurname());
        }
        boolean emailMatches = contact.getEmail().equals(anotherContact.getEmail());
        boolean postalZipMatches = contact.getPostalZip() == (anotherContact.getPostalZip());
        boolean addressMatches = contact.getAddress().equals(anotherContact.getAddress());
        List<Boolean> matches = List.of(
                nameMatches,
                surnameMatches,
                fullNameMatches,
                emailMatches,
                postalZipMatches, addressMatches);
        long matchesCount = matches.stream().filter(x -> x).count();
        if (matchesCount == 5)
            return Accuracy.EXACT.value;
        if ((fullNameMatches && matchesCount > 1) || (emailMatches && matchesCount > 1) || matchesCount == 4)
            return Accuracy.VERY_HIGH.value;
        if ((addressMatches && postalZipMatches) || matchesCount == 3 || emailMatches)
            return Accuracy.HIGH.value;
        if (matchesCount > 0)
            return Accuracy.LOW.value;
        return Accuracy.ZERO.value;
    }
}
