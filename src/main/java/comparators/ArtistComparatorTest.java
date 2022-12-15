package comparators;

import entities.artists.Artist;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArtistComparatorTest {//Тест для компаратора
    @Test
    void compareAscTrue() {//Тест для порівняння двох об'єктів по полю name в порядку asc
        assertEquals(-1, new ArtistComparator("name", true).compare(new Artist("Aaaa", " "), new Artist("Bbbb", " ")));
    }

    @Test
    void compareAscFalse() {//Тест для порівняння двох об'єктів по полю name в порядку desc
        assertEquals(1, new ArtistComparator("name", true).compare(new Artist("Bbb", " "), new Artist("Aaa", " ")));
    }

    @Test
    void compareDescTrue() {//Тест для порівняння двох об'єктів по полю country в порядку asc
        assertEquals(1, new ArtistComparator("name", false).compare(new Artist("Aaaa", " "), new Artist("Bbbb", " ")));
    }

    @Test
    void compareDescFalse() {//Тест для порівняння двох об'єктів по полю country в порядку desc
        assertEquals(-1, new ArtistComparator("name", false).compare(new Artist("Bbbb", " "), new Artist("Aaaa", " ")));
    }
}