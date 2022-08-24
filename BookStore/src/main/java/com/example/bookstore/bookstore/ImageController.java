package com.example.bookstore.bookstore;

import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.lang.NonNull;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/api/bookstore/images")
public class ImageController {

    @Autowired
    private BookRepository imageRepository;


    @PostMapping(value = {"", "/"})
    @ResponseBody
    public ResponseEntity<Void> uploadImage(@Valid @NonNull @RequestParam(value = "image") MultipartFile file,
                            @Valid @NonNull @RequestParam String bookTitle, @Valid @NonNull @RequestParam String bookGenre,
                            @Valid @NonNull @RequestParam Double bookPrice) {

//        String fileName = file.getOriginalFilename();
        try {
           Book book = new Book();
            book.setBookTitle(bookTitle);
            book.setBookGenre(bookGenre);
            book.setBookPrice(bookPrice);
            book.setImage(new Binary(file.getBytes()));
            book.setImageType(file.getContentType());

            Book savedFile = imageRepository.save(book);
            String url = "http://localhost:8080/api/bookstore/images" + savedFile.getId();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

//    @GetMapping
//    public ResponseEntity<List<Book>> getAllBooks() {
//        List<Book> books = imageRepository.findAll();
//        return new ResponseEntity<>(books, HttpStatus.OK);
//    }

//    @GetMapping(path ={"/id"})
//    public ResponseEntity<Book> getBookById(String id) {
//        Book book = imageRepository.findById(id).orElse(null);
//        if (book.equals(null)) {
//            return new ResponseEntity<>(book, HttpStatus.BAD_REQUEST);
//
//        } else {
//            return new ResponseEntity<>(book, HttpStatus.BAD_REQUEST);
//        }
//    }

//    @PutMapping(path = {"/id"})
//    @ResponseBody
//    public void updateImage(@PathVariable("id") String id,
//                            @RequestParam Integer bookQuantity) {
//
////        String fileName = file.getOriginalFilename();
//            Book book = imageRepository.findById(id).orElse(null);
//            book.setBookQuantity(bookQuantity);
//
//            imageRepository.save(book);
//    }




}