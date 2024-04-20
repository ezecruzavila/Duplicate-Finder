package org.duplicateFinder.controllers;

import org.duplicateFinder.DuplicateAccuracy;
import org.duplicateFinder.services.DuplicateFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/duplicates")
public class DuplicateFinderController {

    private final DuplicateFinderService service;

    public DuplicateFinderController(@Autowired DuplicateFinderService service) {
        this.service = service;
    }

    @GetMapping()
    public List<DuplicateAccuracy> getAllDuplicates() throws IOException {
        return service.findDuplicates();
    }
}
