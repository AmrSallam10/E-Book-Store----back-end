package com.example.bookstore.bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(value = "/api/bookstore")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(value = {"", "/"})
    public List<Book> getAllBooks() {
        return bookService.getAll();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable String id) {
        return bookService.getById(id);
    }

    @PostMapping(value = {"", "/"})
    public Book createNewBook(@Valid @RequestBody Book book) {
        return bookService.saveBook(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable String id) {
        bookService.deleteBook(id);
    }

    @PutMapping("/{id}")
    public Book putBook(@PathVariable String id, @Valid @NotNull @RequestBody Book newBook) {
        
        Book book = bookService.getById(id);
        newBook.setId(id);
        newBook.setImage(book.getImage());
        newBook.setImageType(book.getImageType());

        bookService.updateBook(id, newBook);
        return newBook;

    }

    @PatchMapping("/{id}")
    public Book updateBook(@PathVariable String id, @Valid @NotNull @RequestBody Book newBook) {
        Book book = bookService.getById(id);
        book.setBookQuantity(newBook.getBookQuantity());

        return bookService.updateBook(id, book);
    }

}
