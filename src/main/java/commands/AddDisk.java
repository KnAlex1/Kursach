package commands;

import core.Core;
import entities.disks.Disk;
import entities.tracks.Track;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AddDisk implements Command{//команда добавлення диску
    Core core;
    private Map<String, Object> paramsMap;//об'єкт диску

    public AddDisk(Core core, Map<String, Object> paramsMap){//конструктор
        this.core = core;
        this.paramsMap = paramsMap;
    }

    public void execute(){
        core.log(1, "Add Disk command execute");//логування
        List<Track> tracks = new ArrayList<>();//список треків

        String tracksString = (String)paramsMap.get("tracks");//перетворення треків в список
        String name = (String)paramsMap.get("name");
        double price = (double)paramsMap.get("price");

        List<Track> availableTracks = core.getTracksRepository().findAll();//отримання всіх треків з бази даних
        if (tracksString.contains(",")){//якщо треків більше одного
            String[] indexes = tracksString.split("(,)+( )*");//отримання індексів треків
            for (String index : indexes) {//для кожного індексу
                int audioIndex = Integer.parseInt(index) - 1;//перетворення індексу в число

                tracks.add(availableTracks.get(audioIndex));//додавання треку в список
            }
        }
        else {//якщо трек один
            int audioIndex = Integer.parseInt(tracksString)-1;//перетворення індексу в число
            tracks.add(availableTracks.get(audioIndex));//додавання треку в список
        }

        core.getDisksRepository().save(new Disk(name, price, tracks));//збереження диску в базу даних
        core.log(1, "Disk added");//логування
    }
}
