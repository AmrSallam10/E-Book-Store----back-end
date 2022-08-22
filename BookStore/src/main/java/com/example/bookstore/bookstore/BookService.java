package com.example.bookstore.bookstore;


import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAll() {return bookRepository.findAll();}

    public Book getById(String id) {
        return bookRepository.findById(id).get();
    }

    public Book saveBook(Book book) {
        return bookRepository.insert(book);
    }

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
    public void deleteBook(String id) {
        bookRepository.deleteById(id);
    }



}
