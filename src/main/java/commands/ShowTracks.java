package commands;

import core.Core;
import entities.tracks.Track;
import javafx.scene.control.TextArea;

import java.util.List;

public class ShowTracks implements Command{//команда для вывода списка треков
    Core core;
    private TextArea infoPanel;//панель для вивода інформації

    public ShowTracks(Core core, TextArea infoPanel){//конструктор
        this.core = core;
        this.infoPanel = infoPanel;
    }

    public void execute(){
        core.log(1, "Command ShowTracks executed");//виводимо в лог

        StringBuilder sb = new StringBuilder();//створюємо об'єкт для зберігання інформації
        List<Track> tracks = core.getTracksRepository().findAll();//отримуємо список треків
        for (int i = 0; i < tracks.size(); i++){//проходимо по списку
            sb.append(String.format("%3d.", i+1)).append(tracks.get(i).toString()).append(System.lineSeparator());//додаємо інформацію про трек
        }

        infoPanel.setText(sb.toString());//виводимо інформацію на панель
    }
}
