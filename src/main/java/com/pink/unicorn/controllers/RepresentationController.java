package com.pink.unicorn.controllers;

import com.pink.unicorn.domain.PlainObjects.MainPageRepresentation;
import com.pink.unicorn.services.RepresentationService;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RepresentationController {

    private static final Logger LOGGER = Logger.getLogger(UserController.class.getSimpleName());

    private final RepresentationService representationService;

    public RepresentationController(RepresentationService representationService) {
        this.representationService = representationService;
    }

    @GetMapping(path = "/main")
    public ResponseEntity<MainPageRepresentation> getMainPage() {
        return ResponseEntity.status(HttpStatus.OK).body(representationService.setMainPageData());
    }
}
