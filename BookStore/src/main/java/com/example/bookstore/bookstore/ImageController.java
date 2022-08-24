package com.example.bookstore.bookstore;

import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.lang.NonNull;

import java.io.IOException;

import javax.validation.Valid;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/api/bookstore/images")
public class ImageController {

    @Autowired
    private BookRepository imageRepository;


    @PostMapping(value = {"", "/"})
    @ResponseBody
    // Adding a Book with its image to the database
    public ResponseEntity<Void> uploadImage(@Valid @NonNull @RequestParam(value = "image") MultipartFile file,
                            @Valid @NonNull @RequestParam String bookTitle, @Valid @NonNull @RequestParam String bookGenre,
                            @Valid @NonNull @RequestParam Double bookPrice) {

        try {
           Book book = new Book();
            book.setBookTitle(bookTitle);
            book.setBookGenre(bookGenre);
            book.setBookPrice(bookPrice);
            book.setImage(new Binary(file.getBytes()));
            book.setImageType(file.getContentType());

            imageRepository.save(book);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

}