package org.duplicateFinder.extensions;

import org.apache.poi.ss.usermodel.Row;
import org.duplicateFinder.Contact;

public class ContactExtensions {

    public static Contact toContact(Row row) {
        return Contact.builder()
                .contactId((int) row.getCell(0).getNumericCellValue())
                .name(row.getCell(1).getStringCellValue())
                .surname(row.getCell(2).getStringCellValue())
                .email(row.getCell(3).getStringCellValue())
                .postalZip((int) row.getCell(4).getNumericCellValue())
                .address(row.getCell(5).getStringCellValue())
                .build();
    }
}
