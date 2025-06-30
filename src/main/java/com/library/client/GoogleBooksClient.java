package com.library.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.library.dto.GoogleBooksResponse;

@FeignClient(name = "googleBooksClient", url = "https://www.googleapis.com")
public interface GoogleBooksClient {
    @GetMapping("/books/v1/volumes")
    GoogleBooksResponse searchBooks(@RequestParam("q") String query);
}