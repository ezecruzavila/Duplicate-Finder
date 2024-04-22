package org.duplicateFinder.services;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.duplicateFinder.entities.Contact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class DuplicateFinderServiceTest {

    private DuplicateFinderService duplicateFinderService;

    @BeforeEach
    public void setUp() {
        duplicateFinderService = new DuplicateFinderService("test.xlsx");
    }

    @Test
    public void testGetContacts() throws IOException {
        Workbook mockWorkbook = Mockito.mock(Workbook.class);
        Sheet mockSheet = Mockito.mock(Sheet.class);
        Mockito.when(mockWorkbook.getSheetAt(0)).thenReturn(mockSheet);
        Mockito.when(mockSheet.getPhysicalNumberOfRows()).thenReturn(3); // Including header
        Mockito.when(mockSheet.getRow(1)).thenReturn(Mockito.mock(Row.class));
        Mockito.when(mockSheet.getRow(2)).thenReturn(Mockito.mock(Row.class));

        InputStream mockInputStream = Mockito.mock(InputStream.class);
        Mockito.when(getClass().getResourceAsStream("test.xlsx")).thenReturn(mockInputStream);
        Mockito.when(WorkbookFactory.create(mockInputStream)).thenReturn(mockWorkbook);

        List<Contact> contacts = duplicateFinderService.getContacts();

        Assertions.assertEquals(2, contacts.size());
    }

}
