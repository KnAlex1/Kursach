package commands;

import core.Core;
import entities.disks.Disk;
import javafx.scene.control.TextArea;

import java.util.List;

public class ShowDisks {//команда для вывода всех дисков
    Core core;
    private TextArea informationPanel;//панель для вывода информации

    public ShowDisks(Core core, TextArea informationPanel){//конструктор
        this.core = core;
        this.informationPanel = informationPanel;
    }

    public void execute(){
        core.log(1, "Command ShowDisks executed");//выводим в лог

        StringBuilder sb = new StringBuilder();//строка для вывода информации
        List<Disk> disks = core.getDisksRepository().findAll();//получаем список всех дисков
        for (int i = 0; i < disks.size(); i++){//проходим по всем дискам
            sb.append(String.format("\t\t%3d.", i+1)).append(disks.get(i).toString()).append(System.lineSeparator());//добавляем информацию о диске в строку
        }

        informationPanel.setText(sb.toString());//выводим информацию на панель
    }
}
