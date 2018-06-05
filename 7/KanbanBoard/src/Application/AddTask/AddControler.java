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

    public AddControler(ObservableList<Task> toDO, ListView<Task> toDoID) {
        this.primaryStage = new Stage();
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

}