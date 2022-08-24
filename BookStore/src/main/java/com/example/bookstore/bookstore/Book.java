package com.example.bookstore.bookstore;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Random;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Document
public class Book {

    @Id
    private String id;
    @NotBlank
    private String bookTitle;
    @NotBlank
    private String bookGenre;
    @NotNull
    private Double bookPrice;
    @NotNull
    private Integer bookQuantity;
    private Binary image;
    private String imageType;
    private long timestamp;

    public Book() {
        Random rand = new Random();
        final int max = 500;
        final int min = 1;
        int randomQuantity = rand.nextInt((max - min) +1) + min;
        this.bookQuantity = randomQuantity;
        this.timestamp = System.currentTimeMillis();
    }

    public Book(Integer bookQuantity) {
        this.bookQuantity = bookQuantity;
        this.timestamp = System.currentTimeMillis();
    }

    public Book(String id, String bookTitle, String bookGenre, Double bookPrice, Integer bookQuantity, long timestamp, MultipartFile file) throws IOException {
        this.id = id;
        this.bookTitle = bookTitle;
        this.bookGenre = bookGenre;
        this.bookPrice = bookPrice;
        this.bookQuantity = bookQuantity;
        this.timestamp = System.currentTimeMillis();
        this.image = new Binary(BsonBinarySubType.BINARY, file.getBytes());

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookGenre() {
        return bookGenre;
    }

    public void setBookGenre(String bookGenre) {
        this.bookGenre = bookGenre;
    }

    public Double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(Double bookPrice) {
        this.bookPrice = bookPrice;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public Integer getBookQuantity() {
        return bookQuantity;
    }

    public void setBookQuantity(Integer bookQuantity) {
        this.bookQuantity = bookQuantity;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Binary getImage() {
        return image;
    }

    public void setImage(Binary image) {
        this.image = image;
    }
}


