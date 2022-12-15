package comparators;

import entities.disks.Disk;
import entities.tracks.Track;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DiskComparatorTest {//тестуємо компаратор
    Disk a;
    Disk b;

    @BeforeEach
    void setUp() {//створюємо диски
        a = new Disk("Aaa", 2.4, new ArrayList<Track>());
        b = new Disk("Bbb", 54.4, new ArrayList<Track>());
    }

    @Test
    void compareNamesAsc() {//тестуємо сортування по назві по зростанню
        assertEquals(-1, new DiskComparator("name", true).compare(a, b));
    }//порівнюємо назви дисків
    @Test
    void compareNamesDesc() {//тестуємо сортування по назві по спаданню
        assertEquals(1, new DiskComparator("name", false).compare(a, b));
    }
    @Test
    void comparePriceAsc() {//тестуємо сортування по ціні по зростанню
        assertEquals(-1, new DiskComparator("price", true).compare(a, b));
    }
    @Test
    void comparePriceDesc() {//тестуємо сортування по ціні по спаданню
        assertEquals(1, new DiskComparator("price", false).compare(a, b));
    }
}