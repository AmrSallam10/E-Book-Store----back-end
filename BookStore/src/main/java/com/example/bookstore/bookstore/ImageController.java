package com.example.bookstore.bookstore;

import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping(value = "/api/bookstore/images")
public class ImageController {

    @Autowired
    private BookRepository imageRepository;

    @PostMapping(value = {"", "/"})
    @ResponseBody
    public void uploadImage(@RequestParam(value = "image") MultipartFile file,@RequestParam String bookTitle, @RequestParam String bookGenre,
                            @RequestParam Double bookPrice) {

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

    }




}