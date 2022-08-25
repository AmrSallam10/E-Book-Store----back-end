package com.example.bookstore.bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/api/bookstore")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(value = {"", "/"})
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> result = bookService.getAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable String id) {
        Book book = bookService.getById(id);
        if(book == null)
            return new ResponseEntity<>(book, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PostMapping(value = {"", "/"})
    public ResponseEntity<Book> createNewBook(@Valid @RequestBody Book book) {
        Book result = bookService.saveBook(book);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable String id) {
        bookService.deleteBook(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> putBook(@PathVariable String id, @Valid @NotNull @RequestBody Book newBook) {
        
        Book book = bookService.getById(id);
        if(book == null)
            return new ResponseEntity<>(book, HttpStatus.NOT_FOUND);
        else {
            newBook.setId(id);
            newBook.setImage(book.getImage());
            newBook.setImageType(book.getImageType());

            Book result = bookService.updateBook(id, newBook);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }


    }

    @PatchMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable String id, @Valid @NotNull @RequestBody Book newBook) {
        Book book = bookService.getById(id);
        if(book == null)
            return new ResponseEntity<>(book, HttpStatus.NOT_FOUND);
        else {
            book.setBookQuantity(newBook.getBookQuantity());
            Book result = bookService.updateBook(id, book);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }



    }

}
