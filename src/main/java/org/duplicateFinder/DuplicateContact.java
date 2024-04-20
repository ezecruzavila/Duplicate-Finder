package org.duplicateFinder;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DuplicateContact {

    private final Contact contact;
    private final String accuracy;

}
