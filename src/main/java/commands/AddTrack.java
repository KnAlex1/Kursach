package commands;

import core.Core;
import entities.artists.Artist;
import entities.tracks.Track;
import tools.Genre;

import java.util.Map;

public class AddTrack implements Command{//команда добавлення треку
    Core core;
    private Map<String, Object> paramsMap;//об'єкт треку

    public AddTrack(Core core, Map<String, Object> paramsMap){//конструктор
        this.core = core;
        this.paramsMap = paramsMap;
    }

    public void execute(){
        core.log(1, "Add Track command execute");//логування

        String name = (String)paramsMap.get("name");//отримання імені треку
        int artistId = (int)paramsMap.get("artistId");//отримання ідентифікатора артиста
        Artist artist;
        int length = (int)paramsMap.get("len");//отримання тривалості треку
        Genre genre;

        artist = core.getArtistRepository().findAll().get(artistId);//отримання артиста з бази даних

        int genreId = (int)paramsMap.get("genreId");//отримання ідентифікатора жанру
        if (genreId < 0 || genreId >= Genre.values().length){//якщо ідентифікатор жанру не вірний
            System.out.println("Wrong index, set to \"Unknown\"");//виведення повідомлення
            genre = Genre.Unknown;//встановлення жанру "Невідомий"
        }
        else {//якщо ідентифікатор жанру вірний
            genre = Genre.values()[genreId];//встановлення жанру
        }

        core.getTracksRepository().save(new Track(name, artist, length, genre));//збереження треку в базу даних
//        core.log(1, "Track added: " + core.getTracks().get(core.getTracks().size()-1));//логування
    }
}
