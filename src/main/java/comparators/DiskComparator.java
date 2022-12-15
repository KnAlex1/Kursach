package comparators;

import entities.disks.Disk;

import java.util.Comparator;

public class DiskComparator implements Comparator<Disk> {//компаратор для сортировки дисков по цене
    String field;//поле, по которому сортуємо
    boolean asc;//порядок сортування

    public DiskComparator(String field, boolean asc){//конструктор
        this.field = field;
        this.asc = asc;
    }

    @Override
    public int compare(Disk o1, Disk o2) {//метод сортування
        if (field.equals("name")){//по назві
            if (asc){//по зростанню
                return o1.getName().compareTo(o2.getName()) > 0 ? 1 : -1;//порівнюємо назви дисків
            }
            else{//по спаданню
                return o1.getName().compareTo(o2.getName()) > 0 ? -1 : 1;
            }
        }

        else {//по ціні
            if (asc){//по зростанню
                return o1.getPrice() > o2.getPrice() ? 1 : -1;//порівнюємо ціни дисків
            }
            else{//по спаданню
                return o1.getPrice() > o2.getPrice() ? -1 : 1;//порівнюємо ціни дисків
            }
        }
    }
}
