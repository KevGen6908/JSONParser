package org.example;

import org.example.JavaCCParser.JSONParser;
import java.io.ByteArrayInputStream;

public class Parser extends JSONParser {
    public Parser (String text){
        super(new ByteArrayInputStream(text.getBytes()));
    }
}
