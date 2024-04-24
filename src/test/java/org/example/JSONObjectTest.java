package org.example;

import org.example.ClassToParsed.Book;
import org.example.ClassToParsed.Cat;
import org.example.ClassToParsed.Movie;
import org.example.JavaCCParser.ParseException;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class JSONObjectTest {

   @Test
   public void parserToClassCat() throws ParseException {
       String filepath = "src/main/java/org/example/ClassToParsed/TestClass.json";
       try {
           String value = Files.readString(Paths.get(filepath), StandardCharsets.UTF_8);
           Cat result  = JSONObject.parserToClass(value, Cat.class);

           String expect = "Cat{name='Whiskers', age='3', color='gray', eating=[12, 15, 17, 19, 21], toys=[ball, feather toy, scratching post]}";
           assertEquals(expect.toString(), result.toString());
       } catch (Exception e) {
           e.printStackTrace();
       }
    }

    @Test
    public void parserToClassBook() throws ParseException {
        String filepath = "src/main/java/org/example/ClassToParsed/BookClass.json";
        try {
            String value = Files.readString(Paths.get(filepath), StandardCharsets.UTF_8);
            Book result  = JSONObject.parserToClass(value, Book.class);

            String expect = "Book{title='The Hobbit', author='J.R.R. Tolkien', publicationYears=[1937, 1951, 1966], genres=[Fantasy, Adventure], isFiction=true}";
            assertEquals(expect.toString(), result.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void parserToClassMovies() throws ParseException {
        String filepath = "src/main/java/org/example/ClassToParsed/MovieClass.json";
        try {
            String value = Files.readString(Paths.get(filepath), StandardCharsets.UTF_8);
            Movie result  = JSONObject.parserToClass(value, Movie.class);

            String expect = "Movie{title='The Lion King', director='Roger Allers', actors=[Matthew Broderick, Jeremy Irons, James Earl Jones], genres=[Animation, Adventure, Drama], releaseYear=1994, isAnimated=true}";
            assertEquals(expect.toString(), result.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void parserObjectTest() throws ParseException {
        String jsonObject = "{\"val\":\"1984\"}";
        String expected = "{val=1984}";
        try {
            assertEquals(expected, JSONObject.parseObject(jsonObject).toString());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}