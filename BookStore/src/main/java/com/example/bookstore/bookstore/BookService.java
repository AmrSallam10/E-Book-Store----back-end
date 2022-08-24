package com.example.bookstore.bookstore;


import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // function to return all books from database
    public List<Book> getAll() {return bookRepository.findAll();}

    // function to return a certain book by id
    public Book getById(String id) {
        return bookRepository.findById(id).orElse(null);
    }

    // function to add a new book to the database
    public Book saveBook(Book book) {
        return bookRepository.insert(book);
    }

    // function to update a book through id
    public Book updateBook(String id, @NotNull Book newBook) {
        Book book = bookRepository.findById(id).get();

        book.setBookGenre(newBook.getBookGenre());
        book.setBookTitle(newBook.getBookTitle());
        book.setBookPrice(newBook.getBookPrice());
        book.setBookQuantity(newBook.getBookQuantity());
        book.setTimestamp(newBook.getTimestamp());

        bookRepository.save(book);

        return book;
    }

    // function to delete a book by id
    public void deleteBook(String id) {
        bookRepository.deleteById(id);
    }



}
