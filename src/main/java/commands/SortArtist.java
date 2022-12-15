package commands;

import comparators.ArtistComparator;
import core.Core;
import entities.artists.Artist;

import java.util.List;

public class SortArtist implements Command{//сортування артистів за ім'ям
    Core core;
    private int fieldIndex = 0;//індекс поля, за яким відбувається сортування
    private int orderIndex;//індекс порядку сортування

    public SortArtist(Core core, int fieldIndex, int orderIndex){//конструктор
        this.core = core;
        this.orderIndex = orderIndex;
        this.fieldIndex = fieldIndex;
    }

    public void execute(){
        core.log(1, "Command SortArtist executed");//логування

        if (fieldIndex < 1 || fieldIndex > 2) {//перевірка на валідність індексу поля
            return;
        }

        boolean asc = orderIndex == 1;//перевірка на валідність індексу порядку сортування

        List<Artist> artists = core.getArtistRepository().findAll();//отримання списку артистів

        if (fieldIndex == 1){//сортування за ім'ям
            artists.sort(new ArtistComparator("name", asc));//виклик методу сортування
        }

        if (fieldIndex == 2){//сортування за країною
            artists.sort(new ArtistComparator("country", asc));//виклик методу сортування
        }

        StringBuilder sb = new StringBuilder();//створення об'єкту для зберігання результату
        for (int i = 0; i < artists.size(); i++){//прохід по списку артистів
            sb.append(String.format("%3d.", i+1)).append(artists.get(i).toString()).append(System.lineSeparator());//додавання артиста до результату
        }
        core.getInfoPanel().setText(sb.toString());//виведення результату
    }
}

