package commands;

import core.Core;
import entities.disks.Disk;

import java.util.List;

public class DeleteDisk implements Command{//команда видалення диску
    Core core;
    private int diskId = -1;//індекс диску

    public DeleteDisk(Core core, int diskId){//конструктор
        this.core = core;
        this.diskId = diskId;
    }

    public void execute() {
        core.log(1, "Delete Disk command execute");//логування

        List<Disk> disks = core.getDisksRepository().findAll();//отримання списку дисків з бази даних
        core.getDisksRepository().deleteDiskByName(disks.get(diskId).getName());//видалення диску з бази даних
        core.log(1, "Disk " + diskId + " deleted");//логування
    }
}
