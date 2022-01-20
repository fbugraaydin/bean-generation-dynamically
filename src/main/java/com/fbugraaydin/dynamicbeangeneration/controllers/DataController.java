package com.fbugraaydin.dynamicbeangeneration.controllers;

import com.fbugraaydin.dynamicbeangeneration.services.DataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data")
public class DataController {

    private final DataService dataService;

    public DataController(DataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping
    public String getData(@RequestHeader("tenant") String repoName) {
        return dataService.getData(repoName);
    }

}
