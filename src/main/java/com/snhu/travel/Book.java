package com.snhu.travel;
//source: https://learnjava.co.in/how-to-create-a-spring-boot-rest-service-with-xml-output/

public class Book {

    private int id;
    private String name;
    private String author;

    // constructor, getters and setters

    public Book(int i, String string, String string2) {
        id = i;
        name = string;
        author = string2;
    }

    public Book getBook()
    {
        return this;
    }

    public int getID()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getAuthor()
    {
        return author;
    }
}