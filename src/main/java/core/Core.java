package core;

import config.MongoConfig;// імпорт класу MongoConfig
import entities.artists.Artist;// імпорт класу Artist
import entities.disks.Disk;// імпорт класу Disk
import entities.tracks.Track;// імпорт класу Track
import javafx.scene.control.TextArea;// імпорт класу TextArea
import org.springframework.context.ApplicationContext;// імпорт класу ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext;// імпорт класу AnnotationConfigApplicationContext
import repository.ArtistsRepository;// імпорт класу ArtistsRepository
import repository.DisksRepository;// імпорт класу DisksRepository
import repository.TracksRepository;// імпорт класу TracksRepository
import tools.Logger;// імпорт класу Logger

import java.util.ArrayList;// імпорт класу ArrayList
import java.util.List;// імпорт класу List

public class Core {// створення класу Core
    static List<Artist> artists = new ArrayList<>();// створення списку об'єктів типу Artist
    static List<Track> tracks = new ArrayList<>();// створення списку об'єктів типу Track
    static List<Disk> disks = new ArrayList<>();// створення списку об'єктів типу Disk
    static Logger logger = new Logger();// створення об'єкту типу Logger
    private final ApplicationContext context = new AnnotationConfigApplicationContext(MongoConfig.class);// створення об'єкту типу ApplicationContext
    private final ArtistsRepository artistRepository;// створення об'єкту типу ArtistsRepository
    private final DisksRepository disksRepository;// створення об'єкту типу DisksRepository
    private final TracksRepository tracksRepository;// створення об'єкту типу TracksRepository
    private TextArea infoPanel;// створення об'єкту типу TextArea

    public Core(TextArea infoPanel) {// конструктор класу Core
        this.infoPanel = infoPanel;
        artistRepository = context.getBean(ArtistsRepository.class);
        disksRepository = context.getBean(DisksRepository.class);
        tracksRepository = context.getBean(TracksRepository.class);
    }

    public TextArea getInfoPanel() {// гетер для поля infoPanel
        return infoPanel;
    }

    public List<Artist> getArtists() {// гетер для поля artists
        return artists;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public List<Disk> getDisks() {
        return disks;
    }

    public void log(int level, String text){
        logger.log(level, text);
    }

    public void closeLogger(){
        logger.close();
    }

    public ArtistsRepository getArtistRepository() {
        return artistRepository;
    }

    public DisksRepository getDisksRepository() {
        return disksRepository;
    }

    public TracksRepository getTracksRepository() {
        return tracksRepository;
    }

    public static void setArtists(List<Artist> artists) {
        Core.artists = artists;
    }

    public static void setTracks(List<Track> tracks) {
        Core.tracks = tracks;
    }

    public static void setDisks(List<Disk> disks) {
        Core.disks = disks;
    }
}
