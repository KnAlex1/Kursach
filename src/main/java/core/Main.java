package core;

import commands.*;

import entities.artists.Artist;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;
import tools.Tools;

import java.util.*;

public class Main extends Application {// створення класу Main, який наслідує Application

    private BorderPane root;// створення об'єкту типу BorderPane

    private HBox artistControlBox;// створення об'єкту типу HBox
    private HBox diskControlBox;
    private HBox trackControlBox;

    private TextArea infoPanel;// створення об'єкту типу TextArea

    private static Core core;// створення об'єкту типу Core
    static Scanner inputReader = new Scanner(System.in);// створення об'єкту типу Scanner

    public static boolean main_menu(){// створення методу main_menu
        Tools.clearScreen();// виклик методу clearScreen
        int userChoice;// створення змінної userChoice

        System.out.println("\t-- MAIN MENU --\n");

        System.out.println("1. Artists");
        System.out.println("2. Tracks");
        System.out.println("3. Disks");
        System.out.println("\n4. Exit\n");

        while (true) {// створення циклу
            System.out.print("Choose command: ");
            userChoice = Integer.parseInt(inputReader.nextLine());

            if (userChoice == 4) {// перевірка на виконання умови
                return false;// повернення значення false
            }
            else if (userChoice > 4){// перевірка на виконання умови
                System.out.println("Enter valid command (1-4)");// виведення повідомлення
                continue;// продовження циклу
            }

            break;// виход з циклу
        }

        Tools.clearScreen();// виклик методу clearScreen
        switch (userChoice) {// створення оператора switch
            case 1: {
                new ShowArtists(core, null).execute();//
            }
            case 2: {
//                new ShowTracks(core, infoPanel).execute();
            }
            case 3: {
//                new ShowDisks(core).execute();
            }
            case 4: {
                return false;
            }
        }

        return true;
    }

    public void initArtistControlBox() {// створення методу initArtistControlBox
        artistControlBox = new HBox();// ініціалізація об'єкту artistControlBox
        artistControlBox.setSpacing(20);// встановлення відступів між елементами
        artistControlBox.setAlignment(Pos.CENTER);// встановлення вирівнювання елементів по центру
        artistControlBox.setPadding(new Insets(20, 20, 20, 20));// встановлення відступів від країв
    }

    public void initDiskControlBox() {// створення методу initDiskControlBox
        diskControlBox = new HBox();
        diskControlBox.setSpacing(20);
        diskControlBox.setAlignment(Pos.CENTER);
        diskControlBox.setPadding(new Insets(20, 20, 20, 20));
    }

    public void initTrackControlBox() {// створення методу initTrackControlBox
        trackControlBox = new HBox();
        trackControlBox.setSpacing(20);
        trackControlBox.setAlignment(Pos.CENTER);
        trackControlBox.setPadding(new Insets(20, 20, 20, 20));
    }

    void initArtistsControls() {// створення методу initArtistsControls
        // 1. Init AddArtist
        Button addArtistButton = new Button("Add");// створення об'єкту типу Button

        Dialog<Pair<String, String>> dialog = new Dialog<>();// створення об'єкту типу Dialog
        dialog.setTitle("Add new artist");// встановлення заголовку
        dialog.setHeaderText("Provide artist details");// встановлення заголовку

        // Set the button types.
        ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);// створення об'єкту типу ButtonType
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);// встановлення типів кнопок

        // Create the username and password labels and fields.
        GridPane grid = new GridPane();// створення об'єкту типу GridPane
        grid.setHgap(10);// встановлення відступів між елементами
        grid.setVgap(10);// встановлення відступів між елементами
        grid.setPadding(new Insets(20, 150, 10, 10));// встановлення відступів від країв

        TextField name = new TextField();// створення об'єкту типу TextField
        name.setPromptText("Name");// встановлення тексту
        TextField country = new TextField();// створення об'єкту типу TextField
        country.setPromptText("Country");// встановлення тексту

        grid.add(new Label("Name:"), 0, 0);// додавання елементу в об'єкт
        grid.add(name, 1, 0);
        grid.add(new Label("Country:"), 0, 1);
        grid.add(country, 1, 1);

        dialog.getDialogPane().setContent(grid);// встановлення вмісту

        dialog.setResultConverter(dialogButton -> {// встановлення результату
            if (dialogButton == addButtonType) {// перевірка на натискання кнопки
                return new Pair<>(name.getText(), country.getText());// повернення результату
            }
            return null;// повернення результату
        });

        addArtistButton.setOnAction(e -> {// встановлення обробника події
            Optional<Pair<String, String>> result = dialog.showAndWait();// встановлення результату
            result.ifPresent(nameCountry -> {// перевірка на наявність результату
                new AddArtist(core, new Artist(result.get().getKey(), result.get().getValue())).execute();// виконання команди
            });
        });

        // 2. Show artists
        Button showArtistsButton = new Button("Artists");// створення об'єкту типу Button
        showArtistsButton.setOnAction(e -> {// встановлення обробника події
            new ShowArtists(core, infoPanel).execute();// виконання команди
        });

        // 3. Edit Artist
        Dialog<Artist> editArtistDialog = new Dialog<>();// створення об'єкту типу Dialog
        editArtistDialog.setTitle("Edit artist");// встановлення заголовку
        editArtistDialog.setHeaderText("Provide new artist details");// встановлення заголовку

        // Set the button types.
        ButtonType editButtonType = new ButtonType("Edit", ButtonBar.ButtonData.OK_DONE);// створення об'єкту типу ButtonType
        editArtistDialog.getDialogPane().getButtonTypes().addAll(editButtonType, ButtonType.CANCEL);// встановлення типів кнопок

        // Create the username and password labels and fields.
        GridPane editGrid = new GridPane();// створення об'єкту типу GridPane
        editGrid.setHgap(10);// встановлення відступів між елементами
        editGrid.setVgap(10);// встановлення відступів між елементами
        editGrid.setPadding(new Insets(20, 150, 10, 10));// встановлення відступів від країв

        TextField editingArtistId = new TextField();// створення об'єкту типу TextField
        editingArtistId.setPromptText("Artist Name");// встановлення тексту
        TextField editNameField = new TextField();
        editNameField.setPromptText("Name");
        TextField editCountryField = new TextField();
        editCountryField.setPromptText("Country");

        editGrid.add(new Label("Artist Name:"), 0, 0);// додавання елементу в об'єкт
        editGrid.add(editingArtistId, 1, 0);
        editGrid.add(new Label("Name:"), 0, 1);
        editGrid.add(editNameField, 1, 1);
        editGrid.add(new Label("Country:"), 0, 2);
        editGrid.add(editCountryField, 1, 2);

        editArtistDialog.getDialogPane().setContent(editGrid);// встановлення вмісту

        editArtistDialog.setResultConverter(dialogButton -> {// встановлення результату
            if (dialogButton == editButtonType) {// перевірка на натискання кнопки
                return new Artist(editNameField.getText(), editCountryField.getText());// повернення результату
            }
            return null;
        });

        Button editArtistButton = new Button("Edit");// створення об'єкту типу Button
        editArtistButton.setOnAction(e -> {// встановлення обробника події
            Optional<Artist> artist = editArtistDialog.showAndWait();// встановлення результату
            artist.ifPresent(value -> new EditArtist(core, editingArtistId.getText(), value).execute());// виконання команди
        });

        // 4. Delete Artist
        TextInputDialog deleteArtistDialog = new TextInputDialog();// створення об'єкту типу TextInputDialog
        deleteArtistDialog.setGraphic(null);// встановлення графіки
        deleteArtistDialog.setContentText("Artist Id:");// встановлення тексту
        deleteArtistDialog.setHeaderText("Provide artist id to be deleted");// встановлення тексту
        deleteArtistDialog.setTitle("Delete artist");// встановлення заголовку

        Button deleteArtistButton = new Button("Delete");// створення об'єкту типу Button
        deleteArtistButton.setOnAction(e -> {// встановлення обробника події
            Optional<String> res = deleteArtistDialog.showAndWait();// встановлення результату
            res.ifPresent(v -> new DeleteArtist(core, Integer.parseInt(v)-1).execute());// виконання команди
        });

        // 5. Sort artists
        ComboBox<String> artistSortFieldsBox = new ComboBox<>(// створення об'єкту типу ComboBox
                FXCollections.observableArrayList(// створення об'єкту типу FXCollections
                        "Name",// додавання елементу в об'єкт
                        "Country"// додавання елементу в об'єкт
                )
        );

        ComboBox<String> artistSortOrderBox = new ComboBox<>(// створення об'єкту типу ComboBox
                FXCollections.observableArrayList(// створення об'єкту типу FXCollections
                        "asc",
                        "desc"
                )
        );

        Button sortArtistsButton = new Button("Sort");// створення об'єкту типу Button
        sortArtistsButton.setOnAction(e -> {// встановлення обробника події
            int fieldIndex = artistSortFieldsBox.getSelectionModel().getSelectedIndex() + 1;// встановлення результату
            int orderIndex = artistSortOrderBox.getSelectionModel().getSelectedIndex() + 1;// встановлення результату
            System.out.println(fieldIndex + " " + orderIndex);// виведення результату
            new SortArtist(core, fieldIndex, orderIndex).execute();// виконання команди
        });

        artistControlBox.getChildren().addAll(// додавання елементу в об'єкт
                addArtistButton,// додавання елементу в об'єкт
                showArtistsButton,
                editArtistButton,
                deleteArtistButton,
                sortArtistsButton,
                artistSortFieldsBox,
                artistSortOrderBox
        );

    }

    void initDiskControls() {// ініціалізація елементів
        // 1. Add disk
        Button addDiskButton = new Button("Add");// створення об'єкту типу Button

        Dialog<Map<String, Object>> addDiskDialog = new Dialog<>();// створення об'єкту типу Dialog
        addDiskDialog.setTitle("Add disk");// встановлення заголовку
        addDiskDialog.setHeaderText("Provide disk details");// встановлення тексту

        // Set the button types.
        ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);// створення об'єкту типу ButtonType
        addDiskDialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);// додавання елементу в об'єкт

        // Create the username and password labels and fields.
        GridPane addDiskGrid = new GridPane();// створення об'єкту типу GridPane
        addDiskGrid.setHgap(10);// встановлення горизонтального відступу
        addDiskGrid.setVgap(10);// встановлення вертикального відступу
        addDiskGrid.setPadding(new Insets(20, 150, 10, 10));// встановлення відступів

        TextField addTracksField = new TextField();// створення об'єкту типу TextField
        addTracksField.setPromptText("Tracks");// встановлення тексту
        TextField addNameField = new TextField();
        addNameField.setPromptText("Name");
        TextField addPriceField = new TextField();
        addPriceField.setPromptText("Price");

        addDiskGrid.add(new Label("Name:"), 0, 0);// додавання елементу в об'єкт
        addDiskGrid.add(addNameField, 1, 0);
        addDiskGrid.add(new Label("Price:"), 0, 1);
        addDiskGrid.add(addPriceField, 1, 1);
        addDiskGrid.add(new Label("Tracks:"), 0, 2);
        addDiskGrid.add(addTracksField, 1, 2);

        addDiskDialog.getDialogPane().setContent(addDiskGrid);// встановлення вмісту

        addDiskDialog.setResultConverter(dialogButton -> {// встановлення результату
            if (dialogButton == addButtonType) {// перевірка умови
                Map<String, Object> paramsMap = new HashMap<>();// створення об'єкту типу HashMap
                paramsMap.put("name", addNameField.getText());// додавання елементу в об'єкт
                paramsMap.put("price", Double.parseDouble(addPriceField.getText()));
                paramsMap.put("tracks", addTracksField.getText());// додавання елементу в об'єкт
                return paramsMap;// повернення результату
            }
            return null;
        });

        addDiskButton.setOnAction(e -> {// встановлення обробника події
            Optional<Map<String, Object>> paramsMapOptional = addDiskDialog.showAndWait();// встановлення результату
            if(paramsMapOptional.isPresent()) {// перевірка умови
                Map<String, Object> paramsMap = paramsMapOptional.get();// встановлення результату
                new AddDisk(core, paramsMap).execute();// виконання команди
            }
        });

        // 2. Show Disks
        Button showDisksButton = new Button("Disks");// створення об'єкту типу Button
        showDisksButton.setOnAction(e -> new ShowDisks(core, infoPanel).execute());// встановлення обробника події

        // 3. Delete Disk
        TextInputDialog deleteDiskDialog = new TextInputDialog();// створення об'єкту типу TextInputDialog
        deleteDiskDialog.setGraphic(null);// встановлення графіки
        deleteDiskDialog.setContentText("Disk Id:");// встановлення тексту
        deleteDiskDialog.setHeaderText("Provide disk id to be deleted");// встановлення тексту
        deleteDiskDialog.setTitle("Delete disk");// встановлення заголовку

        Button deleteDiskButton = new Button("Delete");// створення об'єкту типу Button
        deleteDiskButton.setOnAction(e -> {// встановлення обробника події
            Optional<String> res = deleteDiskDialog.showAndWait();// встановлення результату
            res.ifPresent(v -> new DeleteDisk(core, Integer.parseInt(v)-1).execute());// виконання команди
        });

        // 4. Edit Disk
        Button editDiskButton = new Button("Edit");// створення об'єкту типу Button

        Dialog<Map<String, Object>> editDiskDialog = new Dialog<>();// створення об'єкту типу Dialog
        editDiskDialog.setTitle("Edit disk");// встановлення заголовку
        editDiskDialog.setHeaderText("Provide disk details");// встановлення тексту

        // Set the button types.
        ButtonType editButtonType = new ButtonType("Edit", ButtonBar.ButtonData.OK_DONE);// створення об'єкту типу ButtonType
        editDiskDialog.getDialogPane().getButtonTypes().addAll(editButtonType, ButtonType.CANCEL);// додавання елементу в об'єкт

        // Create the username and password labels and fields.
        GridPane editDiskGrid = new GridPane();//
        editDiskGrid.setHgap(10);// встановлення горизонтального відступу
        editDiskGrid.setVgap(10);// встановлення вертикального відступу
        editDiskGrid.setPadding(new Insets(20, 150, 10, 10));// встановлення відступів

        TextField editingDiskId = new TextField();// створення об'єкту типу TextField
        editingDiskId.setPromptText("Disk Name");// встановлення тексту
        TextField editTracksField = new TextField();
        editTracksField.setPromptText("Tracks");
        TextField editPriceField = new TextField();
        editPriceField.setPromptText("Price");

        editDiskGrid.add(new Label("Disk Name"), 0, 0);// додавання елементу в об'єкт
        editDiskGrid.add(editingDiskId, 1, 0);
        editDiskGrid.add(new Label("Price:"), 0, 1);
        editDiskGrid.add(editPriceField, 1, 1);
        editDiskGrid.add(new Label("Tracks:"), 0, 2);
        editDiskGrid.add(editTracksField, 1, 2);

        editDiskDialog.getDialogPane().setContent(editDiskGrid);// встановлення вмісту

        editDiskDialog.setResultConverter(dialogButton -> {// встановлення обробника події
            if (dialogButton == editButtonType) {// перевірка умови
                Map<String, Object> paramsMap = new HashMap<>();// створення об'єкту типу HashMap
                paramsMap.put("id", editingDiskId.getText());// встановлення значення
                paramsMap.put("price", editPriceField.getText());
                paramsMap.put("tracks", editTracksField.getText());
                return paramsMap;// повернення значення
            }
            return null;
        });

        editDiskButton.setOnAction(e -> {// встановлення обробника події
            Optional<Map<String, Object>> paramsMapOptional = editDiskDialog.showAndWait();// встановлення результату
            if(paramsMapOptional.isPresent()) {// перевірка умови
                Map<String, Object> paramsMap = paramsMapOptional.get();// встановлення значення
                new EditDisk(core, paramsMap).execute();// виконання команди
            }
        });

        // 5. Sort Disks
        Button sortDisksButton = new Button("Sort");// створення об'єкту типу Button

        TextField diskChoiceField = new TextField();// створення об'єкту типу TextField
        diskChoiceField.setPromptText("Disk id");// встановлення тексту
        diskChoiceField.setMaxWidth(50);// встановлення максимальної ширини

        ComboBox<String> diskSortFieldsBox = new ComboBox<>(// створення об'єкту типу ComboBox
                FXCollections.observableArrayList(// створення об'єкту типу FXCollections
                        "[Disk] Name",
                        "[Disk] Price",
                        "[Track] Name",
                        "[Track] Artist",
                        "[Track] Length",
                        "[Track] Genre"
                )
        );

        ComboBox<String> diskSortOrderBox = new ComboBox<>(// створення об'єкту типу ComboBox
                FXCollections.observableArrayList(// створення об'єкту типу FXCollections
                        "asc",
                        "desc"
                )
        );

        sortDisksButton.setOnAction(e -> {// встановлення обробника події
            int fieldIndex = diskSortFieldsBox.getSelectionModel().getSelectedIndex() + 1;// встановлення значення
            int orderIndex = diskSortOrderBox.getSelectionModel().getSelectedIndex() + 1;
            int diskId = diskChoiceField.getText().isEmpty() ? -1 : Integer.parseInt(diskChoiceField.getText());
            new SortDisk(core, fieldIndex, orderIndex, diskId-1).execute();// виконання команди
        });

        diskControlBox.getChildren().addAll(// додавання елементів в об'єкт
                addDiskButton,
                showDisksButton,
                deleteDiskButton,
                editDiskButton,
                sortDisksButton,
                diskChoiceField,
                diskSortFieldsBox,
                diskSortOrderBox
        );

    }

    void initTrackControls() {// ініціалізація елементів
        // 1. Add Track
        Button addTrackButton = new Button("Add");// створення об'єкту типу Button

        Dialog<Map<String, Object>> addTrackDialog = new Dialog<>();// створення об'єкту типу Dialog
        addTrackDialog.setTitle("Add track");// встановлення заголовку
        addTrackDialog.setHeaderText("Provide track details");// встановлення тексту

        // Set the button types.
        ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);// створення об'єкту типу ButtonType
        addTrackDialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);// додавання елементів в об'єкт

        // Create the username and password labels and fields.
        GridPane addTrackGrid = new GridPane();// створення об'єкту типу GridPane
        addTrackGrid.setHgap(10);// встановлення горизонтального відступу
        addTrackGrid.setVgap(10);// встановлення вертикального відступу
        addTrackGrid.setPadding(new Insets(20, 150, 10, 10));// встановлення відступів

        TextField addTrackName = new TextField();// створення об'єкту типу TextField
        addTrackName.setPromptText("Name");// встановлення тексту
        TextField addTrackArtistId = new TextField();
        addTrackArtistId.setPromptText("Artist Id");
        TextField addTrackLength = new TextField();
        addTrackLength.setPromptText("Length");
        TextField addTrackGenre = new TextField();
        addTrackGenre.setPromptText("Genre Id");

        addTrackGrid.add(new Label("Name:"), 0, 0);// додавання елементів в об'єкт
        addTrackGrid.add(addTrackName, 1, 0);
        addTrackGrid.add(new Label("Artist Id:"), 0, 1);
        addTrackGrid.add(addTrackArtistId, 1, 1);
        addTrackGrid.add(new Label("Length:"), 0, 2);
        addTrackGrid.add(addTrackLength, 1, 2);
        addTrackGrid.add(new Label("Genre Id:"), 0, 3);
        addTrackGrid.add(addTrackGenre, 1, 3);

        addTrackDialog.getDialogPane().setContent(addTrackGrid);// встановлення вмісту

        addTrackDialog.setResultConverter(dialogButton -> {// встановлення обробника події
            if (dialogButton == addButtonType) {// якщо натиснуто кнопку Add
                Map<String, Object> paramsMap = new HashMap<>();// створення об'єкту типу HashMap
                paramsMap.put("name", addTrackName.getText());// додавання елементів в об'єкт
                paramsMap.put("artistId", Integer.parseInt(addTrackArtistId.getText())-1);// додавання елементів в об'єкт
                paramsMap.put("len", Integer.parseInt(addTrackLength.getText()));// додавання елементів в об'єкт
                paramsMap.put("genreId", Integer.parseInt(addTrackGenre.getText())-1);// додавання елементів в об'єкт
                return paramsMap;// повернення значення
            }
            return null;
        });

        addTrackButton.setOnAction(e -> {// встановлення обробника події
            Optional<Map<String, Object>> paramsMapOptional = addTrackDialog.showAndWait();// виклик діалогового вікна
            if(paramsMapOptional.isPresent()) {// якщо натиснуто кнопку Add
                Map<String, Object> paramsMap = paramsMapOptional.get();// створення об'єкту типу HashMap
                new AddTrack(core, paramsMap).execute();// виклик команди
            }
        });

        // 2. Show Tracks
        Button showTracksButton = new Button("Tracks");// створення об'єкту типу Button
        showTracksButton.setOnAction(e -> {// встановлення обробника події
            new ShowTracks(core, infoPanel).execute();// виклик команди
        });

        // 3. Delete Track
        TextInputDialog deleteTrackDialog = new TextInputDialog();// створення об'єкту типу TextInputDialog
        deleteTrackDialog.setGraphic(null);// встановлення значення
        deleteTrackDialog.setContentText("Track Id:");
        deleteTrackDialog.setHeaderText("Provide track id to be deleted");
        deleteTrackDialog.setTitle("Delete track");

        Button deleteTrackButton = new Button("Delete");// створення об'єкту типу Button
        deleteTrackButton.setOnAction(e -> {// встановлення обробника події
            Optional<String> res = deleteTrackDialog.showAndWait();// виклик діалогового вікна
            res.ifPresent(v -> new DeleteTrack(core, Integer.parseInt(v)-1).execute());// виклик команди
        });

        // 4. Edit Track
        Button editTrackButton = new Button("Edit");// створення об'єкту типу Button

        Dialog<Map<String, Object>> editTrackDialog = new Dialog<>();// створення об'єкту типу Dialog
        editTrackDialog.setTitle("Edit track");// встановлення значення
        editTrackDialog.setHeaderText("Provide track details");

        // Set the button types.
        ButtonType editButtonType = new ButtonType("Edit", ButtonBar.ButtonData.OK_DONE);// створення об'єкту типу ButtonType
        editTrackDialog.getDialogPane().getButtonTypes().addAll(editButtonType, ButtonType.CANCEL);// додавання елементів в об'єкт

        // Create the username and password labels and fields.
        GridPane editTrackGrid = new GridPane();// створення об'єкту типу GridPane
        editTrackGrid.setHgap(10);// встановлення значення
        editTrackGrid.setVgap(10);
        editTrackGrid.setPadding(new Insets(20, 150, 10, 10));

        TextField editTrackId = new TextField();// створення об'єкту типу TextField
        editTrackId.setPromptText("Track Name");// встановлення значення
        TextField editArtistField = new TextField();
        editArtistField.setPromptText("Artist Name");
        TextField editLengthField = new TextField();
        editLengthField.setPromptText("Length");
        TextField editGenreField = new TextField();
        editGenreField.setPromptText("Genre");

        editTrackGrid.add(new Label("Track Name:"), 0, 0);// додавання елементів в об'єкт
        editTrackGrid.add(editTrackId, 1, 0);
        editTrackGrid.add(new Label("Artist Name:"), 0, 1);
        editTrackGrid.add(editArtistField, 1, 1);
        editTrackGrid.add(new Label("Length:"), 0, 2);
        editTrackGrid.add(editLengthField, 1, 2);
        editTrackGrid.add(new Label("Genre Id:"), 0, 3);
        editTrackGrid.add(editGenreField, 1, 3);

        editTrackDialog.getDialogPane().setContent(editTrackGrid);// встановлення значення

        editTrackDialog.setResultConverter(dialogButton -> {// встановлення обробника події
            if (dialogButton == editButtonType) {// якщо натиснуто кнопку Edit
                Map<String, Object> paramsMap = new HashMap<>();// створення об'єкту типу HashMap
                paramsMap.put("id", editTrackId.getText());//// встановлення значення
                paramsMap.put("artistId", editArtistField.getText());
                paramsMap.put("len", editLengthField.getText());
                paramsMap.put("genreId", editGenreField.getText());
                return paramsMap;// повернення значення
            }
            return null;
        });

        editTrackButton.setOnAction(e -> {// встановлення обробника події
            Optional<Map<String, Object>> paramsMapOptional = editTrackDialog.showAndWait();// виклик діалогового вікна
            if(paramsMapOptional.isPresent()) {// якщо натиснуто кнопку Edit
                Map<String, Object> paramsMap = paramsMapOptional.get();// створення об'єкту типу HashMap
                new EditTrack(core, paramsMap).execute();// виклик команди
            }
        });

        // 5. Sort tracks
        Button sortTracksButton = new Button("Sort");// створення об'єкту типу Button

        ComboBox<String> trackSortFieldBox = new ComboBox<>(// створення об'єкту типу ComboBox
                FXCollections.observableArrayList(
                       "Name",
                        "Artist",
                        "Length",
                        "Genre"
                )
        );

        ComboBox<String> trackSortOrderBox = new ComboBox<>(// створення об'єкту типу ComboBox
                FXCollections.observableArrayList(// створення об'єкту типу ObservableList
                        "asc",
                        "desc"
                )
        );

        sortTracksButton.setOnAction(e -> {// встановлення обробника події
            int fieldIndex = trackSortFieldBox.getSelectionModel().getSelectedIndex() + 1;// створення змінної
            int orderIndex = trackSortOrderBox.getSelectionModel().getSelectedIndex() + 1;
            new SortTrack(core, fieldIndex, orderIndex).execute();// виклик команди
        });

        trackControlBox.getChildren().addAll(// додавання елементів в об'єкт
                addTrackButton,
                showTracksButton,
                deleteTrackButton,
                editTrackButton,
                sortTracksButton,
                trackSortFieldBox,
                trackSortOrderBox
        );
    }

    @Override
    public void init() throws Exception {// ініціалізація
        root = new BorderPane();// створення об'єкту типу BorderPane

        VBox infoBox = new VBox();// створення об'єкту типу VBox
        infoBox.setSpacing(20);// встановлення значення
        infoBox.setAlignment(Pos.CENTER);
        infoBox.setPadding(new Insets(0, 20, 0, 20));

        Text infoPanelCaption = new Text("Information panel");// створення об'єкту типу Text

        infoPanel = new TextArea();// створення об'єкту типу TextArea
        infoPanel.setEditable(false);// встановлення значення
        infoPanel.setFocusTraversable(false);
        infoPanel.setMinHeight(300);

        infoBox.getChildren().addAll(// додавання елементів в об'єкт
                infoPanelCaption,
                infoPanel
        );

        root.setCenter(infoBox);// встановлення значення

        // Init control boxes
        initArtistControlBox();// виклик методу
        initDiskControlBox();
        initTrackControlBox();

        // Root bottom panels switchers
        Button showArtistControlBox = new Button("Artists");// створення об'єкту типу Button
        showArtistControlBox.setOnAction(e -> root.setBottom(artistControlBox));// встановлення обробника події

        Button showDiskControlBox = new Button("Disks");
        showDiskControlBox.setOnAction(e -> root.setBottom(diskControlBox));

        Button showTrackControlBox = new Button("Tracks");
        showTrackControlBox.setOnAction(e -> root.setBottom(trackControlBox));

        Button clearInfoPanel = new Button("Clear Panel");
        clearInfoPanel.setStyle("-fx-background-color: LIGHTBLUE");// встановлення значення
        clearInfoPanel.setOnAction(e -> infoPanel.setText(""));// встановлення обробника події

        // Init switchers holder
        HBox sectionSwitchersBox = new HBox();// створення об'єкту типу HBox
        sectionSwitchersBox.setSpacing(25);// встановлення значення
        sectionSwitchersBox.setAlignment(Pos.CENTER);
        sectionSwitchersBox.setPadding(new Insets(20, 0, 0, 0));
//        sectionSwitchersBox.setMaxHeight(50);

        sectionSwitchersBox.getChildren().addAll(// додавання елементів в об'єкт
                showArtistControlBox,
                showDiskControlBox,
                showTrackControlBox,
                clearInfoPanel
        );

        root.setTop(sectionSwitchersBox);// встановлення значення
    }

    @Override
    public void start(Stage primaryStage) throws Exception {// запуск
        Scene scene = new Scene(root, 640, 480);// створення об'єкту типу Scene
        primaryStage.setTitle("Курсова робота студента КН-205 Обуханича Олександра");// встановлення значення
        primaryStage.setScene(scene);// встановлення значення
        primaryStage.show();// відображення

        // Init controls in application thread
        initArtistsControls();// виклик методу
        initDiskControls();
        initTrackControls();

        core = new Core(infoPanel);// створення об'єкту типу Core
    }

    public static void main(String[] args) {

//        core.getArtists().addAll(Tools.readArtistsFromFile("artists.txt"));
//        core.getTracks().addAll(Tools.readTracksFromFile("tracks.txt", core.getArtists()));
//        core.getDisks().addAll(Tools.readDisksFromFile("disks.txt", core.getTracks()));

        launch(args);// запуск
    }
}