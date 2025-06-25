package com.library.LibraryAppAplication.controller;

import com.library.LibraryAppAplication.Entity.Publisher;
import com.library.LibraryAppAplication.service.PublisherService;
import com.library.LibraryAppAplication.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publishers")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @GetMapping
    public List<Publisher> getAllPublishers() {
        return publisherService.getAllPublishers();
    }
}