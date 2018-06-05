package Application.Gui;

import Application.AddTask.AddControler;
import Application.EditTask.EditControler;
import Application.ToSerialize;
import Application.Task;
import Application.Type;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import com.google.gson.Gson;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.ResourceBundle;
import org.json.JSONArray;
import org.json.JSONObject;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

public class GuiPresenter implements Initializable ,Serializable{
    public AnchorPane anchorPaneGlobalID;
    public AnchorPane anchorPaneMainID;
    private EditControler editControler;
    private AddControler addControler;
    public static ObservableList<Task> toDo = FXCollections.observableArrayList();
    public static ObservableList<Task> inProgress = FXCollections.observableArrayList();
    public static ObservableList<Task> done = FXCollections.observableArrayList();
    public Menu aboutID;
    public ListView<Task> toDoID = new ListView<>(toDo);
    public ListView<Task> inProgressID = new ListView<>(inProgress);
    public ListView<Task> doneID = new ListView<>(done);
    public Button newTaskButtonID;
    public MenuItem IdSave;
    public MenuItem IdLoad;
    public MenuItem importFromFileID;
    public MenuItem exportToFileID;
    ToSerialize toSerial = new ToSerialize();
    public GuiPresenter() {
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {

        //loadFromFile();

        Label menuLabel = new Label("About");
        menuLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                AuthorMessage();
            }
        });

        aboutID.setGraphic(menuLabel);

        importFromFileID.setOnAction(event -> importFromFile());
        exportToFileID.setOnAction(event -> exportToFile());



        IdSave.setOnAction(event -> {
            toSerial.list1 = new ArrayList<>(toDo);
            toSerial.list2 = new ArrayList<>(inProgress);
            toSerial.list3 = new ArrayList<>(done);
            try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("object.bin"))){
                outputStream.writeObject(toSerial);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        IdLoad.setOnAction(event -> loadFromFile());


        toDoID.setOnKeyPressed(event -> {
                    switch (event.getCode()) {
                        case RIGHT:
                            if (!toDoID.getItems().isEmpty()) {
                                inProgressID.getItems().add(toDoID.getItems().get(toDoID.getFocusModel().getFocusedIndex()));
                                toDoID.getItems().get(toDoID.getFocusModel().getFocusedIndex()).setType(Type.inProgress);
                                toDoID.getItems().remove(toDoID.getItems().get(toDoID.getFocusModel().getFocusedIndex()));
                            }
                            break;
                    }
                }
        );

        inProgressID.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case RIGHT:
                    if (!inProgressID.getItems().isEmpty()) {
                        doneID.getItems().add(inProgressID.getItems().get(inProgressID.getFocusModel().getFocusedIndex()));
                        inProgressID.getItems().get(inProgressID.getFocusModel().getFocusedIndex()).setType(Type.done);
                        inProgressID.getItems().remove(inProgressID.getItems().get(inProgressID.getFocusModel().getFocusedIndex()));
                    }
                    break;
                case LEFT:
                    if (!inProgressID.getItems().isEmpty()) {
                        toDoID.getItems().add(inProgressID.getItems().get(inProgressID.getFocusModel().getFocusedIndex()));
                        inProgressID.getItems().get(inProgressID.getFocusModel().getFocusedIndex()).setType(Type.toDo);
                        inProgressID.getItems().remove(inProgressID.getItems().get(inProgressID.getFocusModel().getFocusedIndex()));
                    }
                    break;
            }
        });

        doneID.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT:
                    if (!doneID.getItems().isEmpty()) {
                        inProgressID.getItems().add(doneID.getItems().get(doneID.getFocusModel().getFocusedIndex()));
                        doneID.getItems().get(doneID.getFocusModel().getFocusedIndex()).setType(Type.inProgress);
                        doneID.getItems().remove(doneID.getItems().get(doneID.getFocusModel().getFocusedIndex()));
                    }
                    break;
            }
        });

        toDoID.setCellFactory(new Callback<ListView<Task>, ListCell<Task>>() {
            @Override
            public ListCell<Task> call(ListView<Task> list) {
                return new ColorRectCell();
            }
        });

        inProgressID.setCellFactory(new Callback<ListView<Task>, ListCell<Task>>() {
            @Override
            public ListCell<Task> call(ListView<Task> list) {
                return new ColorRectCell();
            }
        });

        doneID.setCellFactory(new Callback<ListView<Task>, ListCell<Task>>() {
            @Override
            public ListCell<Task> call(ListView<Task> list) {
                return new ColorRectCell();
            }
        });




        toDoID.setItems(toDo);
        inProgressID.setItems(inProgress);
        doneID.setItems(done);

        newTaskButtonID.setOnMouseClicked(event -> {
                addControler.show();
        });

    }

    static class ColorRectCell extends ListCell<Task> {
        private Circle getCircle() {
            Circle rect = new Circle(10);
            rect.setFill(Color.web(getItem().getPriorityColor()));
            return rect;
        }

        @Override
        public void updateItem(Task item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setText(null);
                setGraphic(null);
                setTooltip(null);
            } else {
                setText(getItem().getTitle());
                setGraphic(getCircle());
                Tooltip tip = new Tooltip();
                tip.setText(getItem().getDescription());
                setTooltip(tip);

            }
        }
    }

    public void setEditControler(EditControler editControler) {
        this.editControler = editControler;
    }

    public void setAddControler(AddControler addControler) {
        this.addControler = addControler;
    }

    public void ClossApp() {
        System.exit(0);
    }

    public void AuthorMessage() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Author : Kamil Szczurkowski");
        alert.showAndWait();
    }

    public void delete1(ActionEvent actionEvent) {
        if (toDoID.getSelectionModel().getSelectedIndex() >= 0) {
            int index = toDoID.getSelectionModel().getSelectedIndex();
            toDoID.getItems().remove(index);
        }
    }

    public void edit1(ActionEvent actionEvent) throws IOException {
        if (toDoID.getSelectionModel().getSelectedIndex() >= 0) {
            int index = toDoID.getSelectionModel().getSelectedIndex();
            editControler.show(toDoID.getItems().get(index), index);
            EditControler.listChooser = 1;
        }
    }

    public void delete2(ActionEvent actionEvent) {
        if (inProgressID.getSelectionModel().getSelectedIndex() >= 0) {
            int index = inProgressID.getSelectionModel().getSelectedIndex();
            inProgressID.getItems().remove(index);
        }
    }

    public void edit2(ActionEvent actionEvent) throws IOException {
        if (inProgressID.getSelectionModel().getSelectedIndex() >= 0) {
            int index = inProgressID.getSelectionModel().getSelectedIndex();
            editControler.show(inProgressID.getItems().get(index), index);
            EditControler.listChooser = 2;
        }
    }

    public void delete3(ActionEvent actionEvent) {
        if (doneID.getSelectionModel().getSelectedIndex() >= 0) {
            int index = doneID.getSelectionModel().getSelectedIndex();
            doneID.getItems().remove(index);
        }
    }

    public void edit3(ActionEvent actionEvent) throws IOException {
        if (doneID.getSelectionModel().getSelectedIndex() >= 0) {
            int index = doneID.getSelectionModel().getSelectedIndex();
            editControler.show(doneID.getItems().get(index), index);
            EditControler.listChooser = 3;
        }
    }

    private void loadFromFile() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("object.bin"))){
            toSerial = (ToSerialize) inputStream.readObject();
        } catch (FileNotFoundException e){
            System.err.println("Nie znaleziono pliku "+e);
        }catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        toDo = FXCollections.observableArrayList(toSerial.list1);
        toDoID.setItems(toDo);

        inProgress = FXCollections.observableArrayList(toSerial.list2);
        inProgressID.setItems(inProgress);

        done = FXCollections.observableArrayList(toSerial.list3);
        doneID.setItems(done);

    }


    private void generateJSON(File file){
        Gson gson = new Gson();

        try(FileWriter writer = new FileWriter(file.getAbsolutePath())){
            gson.toJson(toSerial, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void generateCSV(File file) {
        try (
                BufferedWriter writer = Files.newBufferedWriter(Paths.get(file.getAbsolutePath()));

                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                        .withHeader("Title", "Description", "Priority","Color", "Y", "M", "D", "Type"));
        ) {


            for (int i = 0; i < toSerial.list1.size(); i++) {
                csvPrinter.printRecord(toSerial.list1.get(i).getTitle(),
                        toSerial.list1.get(i).getDescription().replace("\n"," "),
                        toSerial.list1.get(i).getPriority(),
                        toSerial.list1.get(i).getPriorityColor(),
                        toSerial.list1.get(i).getYear(),
                        toSerial.list1.get(i).getMonth(),
                        toSerial.list1.get(i).getDay(),
                        toSerial.list1.get(i).getType());
            }

            for (int i = 0; i < toSerial.list2.size(); i++) {
                csvPrinter.printRecord(toSerial.list2.get(i).getTitle(),
                        toSerial.list2.get(i).getDescription().replace("\n"," "),
                        toSerial.list2.get(i).getPriority(),
                        toSerial.list2.get(i).getPriorityColor(),
                        toSerial.list2.get(i).getYear(),
                        toSerial.list2.get(i).getMonth(),
                        toSerial.list2.get(i).getDay(),
                        toSerial.list2.get(i).getType());
            }

            for (int i = 0; i < toSerial.list3.size(); i++) {
                csvPrinter.printRecord(toSerial.list3.get(i).getTitle(),
                        toSerial.list3.get(i).getDescription().replace("\n"," "),
                        toSerial.list3.get(i).getPriority(),
                        toSerial.list3.get(i).getPriorityColor(),
                        toSerial.list3.get(i).getYear(),
                        toSerial.list3.get(i).getMonth(),
                        toSerial.list3.get(i).getDay(),
                        toSerial.list3.get(i).getType());
            }

            csvPrinter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void exportToFile() {
        FileChooser fileChooser = new FileChooser();
        Stage FCstage = (Stage) anchorPaneMainID.getScene().getWindow();
        File workingDirectory = new File(System.getProperty("user.dir"));
        fileChooser.setInitialDirectory(workingDirectory);
        fileChooser.setTitle("Zapisz dane do pliku");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JSON", "*.json"),
                new FileChooser.ExtensionFilter("CSV", "*.csv")
        );
        File file = fileChooser.showSaveDialog(FCstage);

        if (file != null) {
            toSerial.list1 = new ArrayList<>(toDo);
            toSerial.list2 = new ArrayList<>(inProgress);
            toSerial.list3 = new ArrayList<>(done);

            switch (fileChooser.getSelectedExtensionFilter().getDescription()) {
                case "JSON":
                    generateJSON(file);
                    break;
                case "CSV":
                    generateCSV(file);
                    break;
            }
        }
    }






    private void loadJSON(Reader reader){
        Gson gson = new Gson();
        toSerial =  gson.fromJson(reader, ToSerialize.class);
    }

    private void loadCSV(File file) {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(file.getAbsolutePath()));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                        .withFirstRecordAsHeader()
                        .withIgnoreHeaderCase()
                        .withTrim());
        ) {
            this.toDoID.getItems().remove(0, this.toDoID.getItems().size());
            this.inProgressID.getItems().remove(0, this.inProgressID.getItems().size());
            this.doneID.getItems().remove(0, this.doneID.getItems().size());

            toSerial.list1.clear();
            toSerial.list2.clear();
            toSerial.list3.clear();

            for (CSVRecord csvRecord : csvParser) {
                String title = csvRecord.get("Title");
                String description = csvRecord.get("Description");
                String priority = csvRecord.get("Priority");
                String priorityColor = csvRecord.get("Color");
                int y = Integer.parseInt(csvRecord.get("Y"));
                int m = Integer.parseInt(csvRecord.get("M"));
                int d = Integer.parseInt(csvRecord.get("D"));
                String type = csvRecord.get("Type");

                switch (type) {
                    case "toDo":
                        toSerial.list1.add(new Task(title, description, priority,priorityColor, y, m, d,Type.toDo));
                        break;
                    case "inProgress":
                        toSerial.list2.add(new Task(title, description, priority,priorityColor, y, m, d,Type.inProgress));
                        break;
                    case "done":
                        toSerial.list3.add(new Task(title, description, priority,priorityColor, y, m, d,Type.done));
                        break;
                }

            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    private void importFromFile(){
        toSerial.list1 = new ArrayList<>(toDo);
        toSerial.list2 = new ArrayList<>(inProgress);
        toSerial.list3 = new ArrayList<>(done);
        FileChooser fileChooser = new FileChooser();
        Stage FCstage = (Stage) anchorPaneMainID.getScene().getWindow();
        File workingDirectory = new File(System.getProperty("user.dir"));
        fileChooser.setInitialDirectory(workingDirectory);
        fileChooser.setTitle("Wybierz plik do wczytania");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JSON", "*.json"),
                new FileChooser.ExtensionFilter("CSV", "*.csv")
        );
        File file = fileChooser.showOpenDialog(FCstage);

        if (file != null) {
            try(Reader reader = new FileReader(file.getAbsolutePath())){
                switch (fileChooser.getSelectedExtensionFilter().getDescription()){
                    case "JSON" :
                        loadJSON(reader);
                        break;
                    case "CSV" :
                        loadCSV(file);
                        break;
                }

                toDo = FXCollections.observableArrayList(toSerial.list1);
                toDoID.setItems(toDo);
                inProgress = FXCollections.observableArrayList(toSerial.list2);
                inProgressID.setItems(inProgress);
                done = FXCollections.observableArrayList(toSerial.list3);
                doneID.setItems(done);
            } catch (FileNotFoundException | NullPointerException e){
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

