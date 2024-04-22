package org.duplicateFinder.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.duplicateFinder.entities.Contact;
import org.junit.jupiter.api.Test;

import static org.duplicateFinder.TestUtils.mockContact;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactExtensionsTest {


    private Row mockRow() {
        Workbook workbook = new HSSFWorkbook();
        Contact contact = mockContact();
        Sheet sheet = workbook.createSheet();

        Row row = sheet.createRow(0);
        Cell cell0 = row.createCell(0, CellType.NUMERIC);
        Cell cell1 = row.createCell(1, CellType.STRING);
        Cell cell2 = row.createCell(2, CellType.STRING);
        Cell cell3 = row.createCell(3, CellType.STRING);
        Cell cell4 = row.createCell(4, CellType.NUMERIC);
        Cell cell5 = row.createCell(5, CellType.STRING);
        cell0.setCellValue(contact.getContactId());
        cell1.setCellValue(contact.getName());
        cell2.setCellValue(contact.getSurname());
        cell3.setCellValue(contact.getEmail());
        cell4.setCellValue(contact.getPostalZip());
        cell5.setCellValue(contact.getAddress());
        return row;
    }


    @Test
    public void testToContact() {
        Contact contact = mockContact();
        Contact contactFromRow = ContactExtensions.toContact(mockRow());
        assertEquals(contact, contactFromRow);
    }
}