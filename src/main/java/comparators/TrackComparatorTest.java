package comparators;//Пакет з классами компараторів

import entities.artists.Artist;
import entities.disks.Disk;
import entities.tracks.Track;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tools.Genre;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TrackComparatorTest {//Клас тестування компаратора треків
    Track a;
    Track b;

    @BeforeEach
    void setUp() {//Метод створення треків
        a = new Track("AAA", new Artist("A", " "), 222, Genre.Unknown);//Створення трека
        b = new Track("BBB", new Artist("B", " "), 333, Genre.Ambient);
    }

    @Test
    void compareNamesAsc() {//Метод тестування компаратора імен треків за зростанням
        assertEquals(-1, new TrackComparator("name", true).compare(a, b));//Перевірка на відповідність
    }
    @Test
    void compareNamesDesc() {//Метод тестування компаратора імен треків за спаданням
        assertEquals(1, new TrackComparator("name", false).compare(a, b));
    }

    @Test
    void compareLengthAsc() {//Метод тестування компаратора тривалості треків за зростанням
        assertEquals(-1, new TrackComparator("length", true).compare(a, b));
    }
    @Test
    void compareLengthDesc() {//Метод тестування компаратора тривалості треків за спаданням
        assertEquals(1, new TrackComparator("length", false).compare(a, b));
    }
}