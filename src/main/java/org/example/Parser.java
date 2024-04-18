package org.example;

import java.io.Reader;
import java.util.Objects;
import java.util.regex.Pattern;

public class Parser {
    static final Pattern LITERAL_PATTERNS = Pattern.compile("^(null|true|false)");
    private static final Pattern OCTAL_PATTERNS = Pattern.compile("^0[0-7]*$");
    private static final Pattern UNQUOTED_ID_PATTERNS =
            Pattern.compile("^(?:[_\\$\\p{L}]|\\\\u\\p{XDigit}{4})" +
                    "(?:[_\\$\\p{L}\\p{Nd}\\p{Mn}\\p{Mc}\\p{Pc}\\u200C\\u200D]|\\\\u\\p{XDigit}{4})*$");
    static final Pattern NEW_DATE_PATTERN = Pattern.compile("^(new\\s+Date\\s*\\(\\s*('[^']+'|\"[^\"]+\")\\s*\\))$");

    enum TokenType{
        START_OBJECT,
        END_OBJECT,
        START_ARRAY,
        END_ARRAY,
        COMMA,
        COLON,
        STRING,
        FLOATING_POINT_NUMBER,
        INTEGER_NUMBER,
        LITERAL,
        UNQUOTED_ID,
        DATE
    }


    static class Token{
        TokenType tokenType;
        String value;

        Token(TokenType token, String val){
            tokenType = token;
            value = val;
        }
    }

    public static Objects parserJSON(String json){
        return parserJSON(json);
    }

}
