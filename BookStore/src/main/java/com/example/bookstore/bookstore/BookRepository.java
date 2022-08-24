package com.example.bookstore.bookstore;

import org.springframework.data.mongodb.repository.MongoRepository;

// Connecting to MongoDB
public interface BookRepository extends MongoRepository<Book, String> {
}
