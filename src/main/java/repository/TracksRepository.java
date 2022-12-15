package repository;

import entities.disks.Disk;
import entities.tracks.Track;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TracksRepository extends MongoRepository<Track, String> {//створюємо інтерфейс для роботи з базою даних

    void deleteTrackByName(String name);//створюємо метод для видалення треку з бази даних

}
