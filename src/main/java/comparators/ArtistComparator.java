package comparators;

import entities.artists.Artist;

import java.util.Comparator;

public class ArtistComparator implements Comparator<Artist> {//Компаратоп для сортування артистів по імені
    String field;//Поле по якому буде відбуватись сортування
    boolean asc;//Напрямок сортування

    public ArtistComparator(String field, boolean asc){//Конструктор
        this.field = field;
        this.asc = asc;
    }

    @Override
    public int compare(Artist o1, Artist o2) {//Метод для порівняння двох об'єктів
        if (field.equals("name")){//Якщо поле name
            if (asc){//Якщо asc
                return o1.getName().compareTo(o2.getName()) > 0 ? 1 : -1;//Порівнюємо імена
            }
            else{//Якщо desc
                return o1.getName().compareTo(o2.getName()) > 0 ? -1 : 1;
            }
        }

        else {//Якщо поле не name
            if (asc){//Якщо asc
                return o1.getCountry().compareTo(o2.getCountry()) > 0 ? 1 : -1;//Порівнюємо країни
            }
            else{//Якщо desc
                return o1.getCountry().compareTo(o2.getCountry()) > 0 ? -1 : 1;
            }
        }
    }
}
