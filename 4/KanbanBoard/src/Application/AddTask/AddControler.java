package Application.AddTask;

import Application.Task;
import javafx.beans.property.ListProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;

public class AddControler {
    public static Stage primaryStage;
    public static ObservableList<Task> toDO;
    public static ObservableList<Task> inProgress;
    public static ObservableList<Task> done;
    public static ListProperty<Task> listProperty;
    public static ListView<Task> toDoID;

    public AddControler(ObservableList<Task> toDO, ListView<Task> toDoID) {
        this.primaryStage = new Stage();
        this.toDO = toDO;
        this.toDoID = toDoID;
    }

    public void show() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getClassLoader().getResource("Application/AddTask/Add.fxml"));
            Parent rootLayout = loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setTitle("Add new task");
            primaryStage.setScene(scene);
            primaryStage.setMaximized(false);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void closeStage() {
        primaryStage.close();
    }

    public static void add(Task element) {
        toDO.add(element);
    }
}