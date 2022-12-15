package repository;

import entities.artists.Artist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistsRepository extends MongoRepository<Artist, String> {//створюємо інтерфейс для роботи з базою даних

    void deleteArtistByName(String name);//створюємо метод для видалення артиста з бази даних

    Artist getArtistByName(String name);//створюємо метод для отримання артиста з бази даних

}
