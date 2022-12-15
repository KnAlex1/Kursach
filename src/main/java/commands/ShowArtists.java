package commands;

import core.Core;
import entities.artists.Artist;
import javafx.scene.control.TextArea;

import java.util.List;

public class ShowArtists implements Command{//команда для виводу інформації про всіх виконавців
    private Core core;
    private TextArea informationPanel;//панель для виводу інформації

    public ShowArtists(Core core, TextArea informationPanel){//конструктор
        this.core = core;
        this.informationPanel = informationPanel;
    }

    public void execute(){
        core.log(1, "Command ShowArtists executed");//вивід в лог

        StringBuilder sb = new StringBuilder();//створення об'єкту для зберігання інформації
        List<Artist> artists = core.getArtistRepository().findAll();//отримання списку виконавців
        for (int i = 0; i < artists.size(); i++){//прохід по списку
            sb.append(String.format("%3d.", i+1)).append(artists.get(i).toString()).append(System.lineSeparator());//додавання інформації про виконавця
        }

        informationPanel.setText(sb.toString());//вивід інформації на панель
    }
}
