package commands;

import core.Core;
import entities.artists.Artist;

public class AddArtist implements Command{//команда добавлення артиста
    Core core;
    private Artist artist;//об'єкт артиста

    public AddArtist(Core core, Artist artist){//конструктор
        this.artist = artist;
        this.core = core;
    }

    public void execute(){//виконання команди
        core.log(1, "Add Artist command execute");//логування

        core.getArtistRepository().save(artist);//збереження артиста в базу даних
//        core.log(1, "Added artist: " + core.getArtists().get(core.getArtists().size()-1));//логування
    }
}
