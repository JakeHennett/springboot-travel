package com.snhu.travel;
//source https://learnjava.co.in/how-to-create-a-spring-boot-rest-service-with-xml-output/

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

   @GetMapping(value="/books",produces = { "application/xml", "text/xml" })
    public List<Book> getAllBooks(){
      //Returns hardcoded data, a real world application would return from the database
      List<Book> books = new ArrayList<Book>();
      books.add(new Book(1,"Emma", "Jane Austen"));
      books.add(new Book(2,"Harry Potter", "JK Rowling"));
      books.add(new Book(3,"The Partner", "John Grisham"));
      return books;
    } 
   
   @GetMapping(value="/book", produces = { "application/xml", "text/xml" })
    public Book getBook(){
      //Returns hardcoded data, a real world application would return from the database
      return new Book(1,"Emma", "Jane Austen");
    } 
}