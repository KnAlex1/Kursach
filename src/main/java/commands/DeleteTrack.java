package commands;

import core.Core;
import entities.tracks.Track;

import java.util.List;

public class DeleteTrack implements Command{//команда видалення треку
    Core core;
    private int trackId;//індекс треку

    public DeleteTrack(Core core, int trackId){//конструктор
        this.core = core;
        this.trackId = trackId;
    }

    public void execute() {
        core.log(1, "Delete Track command execute");//логування

        List<Track> tracks = core.getTracksRepository().findAll();//отримання списку треків з бази даних
        core.getTracksRepository().deleteTrackByName(tracks.get(trackId).getName());//видалення треку з бази даних
        core.log(1, "Track " + trackId + " deleted");//логування
    }
}
