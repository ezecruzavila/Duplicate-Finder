package org.duplicateFinder;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class DuplicateAccuracy {

    private final Contact contact;
    private final List<DuplicateContact> possibleDuplicates = new ArrayList<>();

    public DuplicateAccuracy(Contact contact) {
        this.contact = contact;
    }

}
