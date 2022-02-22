package wniemiec.util.java;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class StringUtilsTest {

    @Test
    void testHelloWorld() {
        List<String> items = List.of("hello", "world");
        String obtained = StringUtils.implode(items, " ");
        
        Assertions.assertEquals("hello world", obtained);
    }
    
    @Test
    void testNullList() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            StringUtils.implode(null, " ");
        });
    }

    @Test
    void testNullDelimiter() {
        List<String> items = List.of("hello", "world");
        
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            StringUtils.implode(items, null);
        });
    }

    @Test
    void testNullDelimiterAndList() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            StringUtils.implode(null, null);
        });
    }

    @Test
    void testCapitalizeTextWith1Character() {
        String text = "h";
        String obtained = StringUtils.capitalize(text);

        Assertions.assertEquals("H", obtained);
    }

    @Test
    void testCapitalizeTextWithMoreThan1Characters() {
        String text = "hello wOrLd";
        String obtained = StringUtils.capitalize(text);

        Assertions.assertEquals("Hello World", obtained);
    }

    @Test
    void testCapitalizeEmptyText() {
        String text = "";
        String obtained = StringUtils.capitalize(text);

        Assertions.assertEquals("", obtained);
    }

    @Test
    void testCapitalizeNullText() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            StringUtils.capitalize(null);
        });
    }
}
