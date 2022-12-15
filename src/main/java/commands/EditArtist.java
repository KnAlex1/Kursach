package commands;

import core.Core;
import entities.artists.Artist;

import java.util.List;
import java.util.Optional;

public class EditArtist implements Command {//команда редагування артиста
    Core core;
    private String artistName;//ім'я артиста
    private Artist newArtist;//об'єкт нового артиста

    public EditArtist(Core core, String artistName, Artist newArtist){//конструктор
        this.core = core;
        this.artistName = artistName;
        this.newArtist = newArtist;
    }

    public void execute(){
        core.log(1, "Edit Artist command execute");//логування

        if(artistName.isEmpty())//якщо ім'я артиста пусте
            return;//вихід з методу

        Optional<Artist> artistOptional = core.getArtistRepository().findById(artistName);//отримання об'єкта артиста з бази даних
        if(!artistOptional.isPresent())//якщо об'єкт артиста не існує
            return;//вихід з методу

        Artist artist = artistOptional.get();//отримання об'єкта артиста з бази даних

        if (!newArtist.getName().isEmpty()) {//якщо ім'я артиста не пусте
            artist.setName(newArtist.getName());//зміна імені артиста
        }

        if (!newArtist.getCountry().isEmpty()) {//якщо країна артиста не пуста
            artist.setCountry(newArtist.getCountry());//зміна країни артиста
        }

        core.getArtistRepository().save(artist);//збереження артиста в базу даних

        core.log(1, "Artist edited");//логування
    }
}
