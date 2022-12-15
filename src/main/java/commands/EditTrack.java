package commands;

import core.Core;
import entities.artists.Artist;
import entities.tracks.Track;
import tools.Genre;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class EditTrack implements Command {//команда редагування треку
    Core core;
    private Map<String, Object> paramsMap;//об'єкт треку

    public EditTrack(Core core, Map<String, Object> paramsMap){//конструктор
        this.core = core;
        this.paramsMap = paramsMap;
    }

    public void execute(){
        core.log(1, "Edit Track command execute");//логування

        String id = (String)paramsMap.get("id");//отримання ідентифікатора треку
        Artist artist = null;//об'єкт артиста
        int length = -1;//тривалість треку
        Genre genre = null;//жанр треку

        if(id.isEmpty())//якщо ідентифікатор треку порожній
            return;//вихід з методу

        String artistId = (String)paramsMap.get("artistId");//отримання ідентифікатора артиста

        if (!artistId.isEmpty()){//якщо ідентифікатор артиста не порожній
            artist = core.getArtistRepository().findById(artistId).orElse(null);//отримання артиста з бази даних
        }


        String lengthParam = (String)paramsMap.get("len");//отримання тривалості треку

        if (!lengthParam.isEmpty()) {//якщо тривалість треку не порожня
            length = Integer.parseInt(lengthParam);//перетворення тривалості треку в ціле число
        }

        // Genre
        // Друк всіх жанрів
        String genreId = (String)paramsMap.get("genreId");//отримання ідентифікатора жанру

        if (!genreId.isEmpty()){//якщо ідентифікатор жанру не порожній
            int genreIndex = Integer.parseInt(genreId) - 1;//перетворення ідентифікатора жанру в ціле число

            if (genreIndex < 0 || genreIndex > Genre.values().length-1){//якщо індекс жанру не в межах допустимих значень
                genre = Genre.Unknown;//жанр треку - невідомий
            }
            else {//якщо індекс жанру в межах допустимих значень
                genre = Genre.values()[genreIndex];//жанр треку - індекс жанру
            }
        }

        Optional<Track> trackOptional = core.getTracksRepository().findById(id);//отримання треку з бази даних

        if(!trackOptional.isPresent())//якщо трек не знайдено
            return;//вихід з методу

        Track track = trackOptional.get();//отримання треку

        if (artist != null){//якщо артист не порожній
            track.setArtist(artist);//встановлення артиста треку
        }

        if (length > 0){//якщо тривалість треку більше 0
            track.setLength(length);//встановлення тривалості треку
        }

        if (genre != null){//якщо жанр треку не порожній
            track.setGenre(genre);//встановлення жанру треку
        }

        core.getTracksRepository().save(track);//збереження треку в базу даних
        core.log(1, "Track edited");//логування
    }
}
