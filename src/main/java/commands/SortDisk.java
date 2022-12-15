package commands;

import comparators.DiskComparator;
import comparators.TrackComparator;
import core.Core;
import entities.disks.Disk;

import java.util.List;

public class SortDisk implements Command{//сортування дисків за назвою
    Core core;
    private int fieldIndex;//індекс поля, за яким відбувається сортування
    private int orderIndex;//індекс порядку сортування
    private int diskChoise;//індекс типу диску

    public SortDisk(Core core, int fieldIndex, int orderIndex, int diskChoice){//конструктор
        this.core = core;
        this.fieldIndex = fieldIndex;
        this.orderIndex = orderIndex;
        this.diskChoise = diskChoice;
    }

    public void execute(){
        core.log(1, "Command SortDisk executed");//логування

        if (fieldIndex >= 3 && diskChoise < 0)//перевірка на валідність індексу поля
            return;

        boolean asc = orderIndex == 1;//перевірка на валідність індексу порядку сортування
        List<Disk> disks = core.getDisksRepository().findAll();//отримання списку дисків

        switch (fieldIndex){//сортування за назвою
            case 1: {//сортування за назвою
                disks.sort(new DiskComparator("name", asc));//виклик методу сортування
                break;
            }

            case 2: {//сортування за роком
                disks.sort(new DiskComparator("price", asc));
                break;
            }

            case 3: {//сортування за назвою
                disks.get(diskChoise).getTracks().sort(new TrackComparator("name", asc));
                break;
            }
            case 4: {//сортування за артистом
                disks.get(diskChoise).getTracks().sort(new TrackComparator("artist", asc));
                break;
            }
            case 5: {//сортування за довжиною
                disks.get(diskChoise).getTracks().sort(new TrackComparator("length", asc));
                break;
            }
            case 6: {//сортування за жанром
                disks.get(diskChoise).getTracks().sort(new TrackComparator("genre", asc));
                break;
            }
        }

        StringBuilder sb = new StringBuilder();//створення об'єкту для зберігання результату
        for (int i = 0; i < disks.size(); i++){//формування результату
            sb.append(String.format("\t\t%3d.", i+1)).append(disks.get(i).toString()).append(System.lineSeparator());//додавання диску
        }

        core.getInfoPanel().setText(sb.toString());//виведення результату
    }
}

