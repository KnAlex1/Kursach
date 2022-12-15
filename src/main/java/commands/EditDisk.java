package commands;

import core.Core;
import entities.disks.Disk;
import entities.tracks.Track;
import repository.DisksRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class EditDisk implements Command{//команда редагування диску
    Core core;
    private Map<String, Object> paramsMap;//об'єкт диску

    public EditDisk(Core core, Map<String, Object> paramsMap){//конструктор
        this.core = core;
        this.paramsMap = paramsMap;
    }

    public void execute(){
        core.log(1, "Edit Disk command execute");//логування

        String id = (String)paramsMap.get("id");//отримання індексу диску
        String name = (String)paramsMap.get("name");//отримання імені диску
        String price = (String)paramsMap.get("price");//отримання ціни диску
        String tracksList = (String)paramsMap.get("tracks");//отримання треків диску

        Optional<Disk> diskOptional = core.getDisksRepository().findById(id);//отримання диску з бази даних
        if(!diskOptional.isPresent())//якщо диску не існує
            return;//вихід

        Disk diskToEdit = diskOptional.get();//отримання диску

        List<Track> tracks = core.getTracksRepository().findAll();//отримання всіх треків з бази даних

        List<Track> tracksToRemove = new ArrayList<>();//список треків для видалення
        List<Track> tracksToAdd = new ArrayList<>();//список треків для додавання

        if (tracksList.contains(",")){//якщо треків більше одного
            String[] indexes = tracksList.split("(,)+( )*");//отримання індексів треків

            for (String index : indexes) {//для кожного індексу
                int audioIndex = Integer.parseInt(index);//перетворення індексу в число

                if (audioIndex < 0){//якщо індекс менший за нуль
                    if ((-audioIndex)-1 < 0 ||(-audioIndex)-1 >= diskToEdit.getTracks().size()){//якщо індекс виходить за межі списку треків
                        continue;//пропуск
                    }
                    tracksToRemove.add(diskToEdit.getTracks().get(-audioIndex-1));//додавання треку в список для видалення
                }

                else {//якщо індекс більший за нуль
                    if (audioIndex-1 < 0 || audioIndex-1 >= tracks.size()){//якщо індекс виходить за межі списку треків
                        continue;//пропуск
                    }
                    tracksToAdd.add(tracks.get(audioIndex-1));//додавання треку в список для додавання
                }
            }
        }

        else if (!tracksList.isEmpty()){//якщо трек один
            int index = Integer.parseInt(tracksList);//перетворення індексу в число

            if (index < 0){//якщо індекс менший за нуль
                diskToEdit.getTracks().remove(-index-1);//видалення треку
            }
            else{//якщо індекс більший за нуль
                diskToEdit.getTracks().add(tracks.get(index-1));//додавання треку
            }
        }

        diskToEdit.getTracks().addAll(tracksToAdd);//додавання треків в список треків диску
        diskToEdit.getTracks().removeAll(tracksToRemove);//видалення треків зі списку треків диску

        if (!price.isEmpty()){//якщо ціна не пуста
            diskToEdit.setPrice(Double.parseDouble(price));//встановлення ціни
        }

        core.getDisksRepository().save(diskToEdit);//збереження диску в базу даних

        core.log(1, "Disk edited");//логування
    }
}
