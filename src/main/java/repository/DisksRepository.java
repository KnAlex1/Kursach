package repository;

import entities.disks.Disk;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisksRepository extends MongoRepository<Disk, String> {//створюємо інтерфейс для роботи з базою даних

    void deleteDiskByName(String name);//створюємо метод для видалення диску з бази даних

}
