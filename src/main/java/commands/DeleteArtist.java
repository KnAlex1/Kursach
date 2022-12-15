package commands;

import core.Core;
import entities.artists.Artist;

import java.util.List;

public class DeleteArtist implements Command{//команда видалення артиста
    private Core core;
    private int artistId;

    public DeleteArtist(Core core, int artistId){//конструктор
        this.core = core;
        this.artistId = artistId;
    }

    public void execute() {
        core.log(1, "Delete Artist command execute");//логування

        List<Artist> artists = core.getArtistRepository().findAll();//отримання списку артистів з бази даних
        core.getArtistRepository().deleteArtistByName(artists.get(artistId).getName());//видалення артиста з бази даних

        core.log(1, "Artist " + artistId + " deleted");//логування
    }
}
