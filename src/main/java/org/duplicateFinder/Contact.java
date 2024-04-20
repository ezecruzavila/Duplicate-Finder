package org.duplicateFinder;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Contact {

    private final int contactId;
    private final String name;
    private final String surname;
    private final String email;
    private final int postalZip;
    private final String address;

}
