package Application.EditTask;

import Application.Task;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;

public class EditControler {
    public static int listChooser;
    public static Stage primaryStage;
    static int index;
    static Task element;
    public static ObservableList<Task> toDO;
    public static ObservableList<Task> inProgress;
    public static ObservableList<Task> done;
    public static ListView<Task> toDoID;
    public static ListView<Task> inProgressID;
    public static ListView<Task> doneID;

    public EditControler(ObservableList<Task> toDO, ListView<Task> toDoID, ObservableList<Task> inProgress, ListView<Task> inProgressID,
                         ObservableList<Task> done, ListView<Task> doneID) {
        this.primaryStage = new Stage();
        this.toDoID = toDoID;
        this.toDO = toDO;
        this.inProgress = inProgress;
        this.inProgressID = inProgressID;
        this.done = done;
        this.doneID = doneID;
    }

    @FXML
    public void initialize() {
    }

    public void show(Task element, int index) {
        this.index = index;
        this.element = element;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getClassLoader().getResource("Application/EditTask/Edit.fxml"));
            Parent rootLayout = loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setTitle("Edit task");
            primaryStage.setScene(scene);
            primaryStage.setMaximized(false);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Task getElement() {
        return element;
    }

    public static int getIndex() {
        return index;
    }

    public static void closeStage() {
        primaryStage.close();
    }

    public static void add(Task element, int index) {
        if (listChooser == 1) {
            toDoID.getItems().remove(toDoID.getItems().get(index));
            toDoID.getItems().add(element);
          //  toDO.remove(index);
            //toDO.add(element);
        } else if (listChooser == 2) {
            inProgressID.getItems().remove(inProgressID.getItems().get(index));
            inProgressID.getItems().add(element);
           // inProgress.remove(index);
           // inProgress.add(element);
        } else if (listChooser == 3) {
            doneID.getItems().remove(doneID.getItems().get(index));
            doneID.getItems().add(element);
       //     done.remove(index);
       //     done.add(element);
        }
    }

}
