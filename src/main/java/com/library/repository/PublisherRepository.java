package com.library.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.Entity.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    Optional<Publisher> findByPublisherName(String publisherName);
}