package comparators;

import entities.artists.Artist;
import tools.Genre;
import entities.tracks.Track;

import java.util.Comparator;

public class TrackComparator implements Comparator<Track> {//Порівняння треків
    String field;//Поле, за яким відбувається порівняння
    boolean asc;//Напрямок порівняння

    public TrackComparator(String field, boolean asc){//Конструктор
        this.field = field;
        this.asc = asc;
    }

    private String name;
    private Artist artist;
    private int length;
    private Genre genre;

    @Override
    public int compare(Track o1, Track o2) {//Порівняння
        switch (field) {//Вибір поля
            case "name"://Якщо назва
                if (asc) {//Якщо зростання
                    return o1.getName().compareTo(o2.getName()) > 0 ? 1 : -1;//Порівняння
                } else {//Якщо спадання
                    return o1.getName().compareTo(o2.getName()) > 0 ? -1 : 1;
                }
            case "artist"://Якщо виконавець
                if (asc) {
                    return o1.getArtist().getName().compareTo(o2.getArtist().getName()) > 0 ? 1 : -1;
                } else {
                    return o1.getArtist().getName().compareTo(o2.getArtist().getName()) > 0 ? -1 : 1;
                }
            case "length"://Якщо тривалість
                if (asc) {
                    return o1.getLength() > o2.getLength() ? 1 : -1;
                } else {
                    return o1.getLength() > o2.getLength() ? -1 : 1;
                }
            case "genre"://Якщо жанр
                if (asc) {
                    return o1.getGenre().toString().compareTo(o2.getGenre().toString()) > 0 ? 1 : -1;
                } else {
                    return o1.getGenre().toString().compareTo(o2.getGenre().toString()) > 0 ? -1 : 1;
                }
            default://Якщо нічого не вибрано
                return 0;//Повернути 0
        }
    }
}
