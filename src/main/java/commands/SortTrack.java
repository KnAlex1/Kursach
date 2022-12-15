package commands;

import comparators.TrackComparator;
import core.Core;
import entities.tracks.Track;

import java.util.List;

public class SortTrack implements Command{//команда сортування треків
    Core core;
    private int fieldIndex;//індекс поля, за яким буде відбуватись сортування
    private int orderIndex;//індекс порядку сортування

    public SortTrack(Core core, int fieldIndex, int orderIndex){//конструктор
        this.core = core;
        this.fieldIndex = fieldIndex;
        this.orderIndex = orderIndex;
    }

    public void execute(){
        core.log(1, "Command SortTrack executed");//логування

        if (fieldIndex < 1 || fieldIndex > 4)//перевірка на валідність індексів
            return;//якщо невалідні, то вихід

        boolean asc = orderIndex == 1;//порядок сортування

        List<Track> tracks = core.getTracksRepository().findAll();//отримання списку треків

        switch (fieldIndex){//вибір поля, за яким буде відбуватись сортування
            case 1: {//назва
                tracks.sort(new TrackComparator("name", asc));
                break;
            }
            case 2: {//виконавець
                tracks.sort(new TrackComparator("artist", asc));
                break;
            }
            case 3: {//довжина
                tracks.sort(new TrackComparator("length", asc));
                break;
            }
            case 4: {//рік
                tracks.sort(new TrackComparator("genre", asc));
                break;
            }
        }

        StringBuilder sb = new StringBuilder();//створення рядка для виведення результату
        for (int i = 0; i < tracks.size(); i++){//прохід по списку треків
            sb.append(String.format("%3d.", i+1)).append(tracks.get(i).toString()).append(System.lineSeparator());//додавання треку до рядка
        }

        core.getInfoPanel().setText(sb.toString());//виведення результату
    }
}

