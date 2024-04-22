package org.duplicateFinder;

import org.duplicateFinder.entities.Contact;

public class TestUtils {

    public static Contact mockContact() {
        return Contact.builder()
                .contactId(1)
                .name("John")
                .surname("Doe")
                .email("john.doe@example.com")
                .postalZip(12345)
                .address("123 Main St, City")
                .build();
    }
}
