package Application.Gui;

import Application.AddTask.AddControler;
import Application.EditTask.EditControler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class GuiControler
{
    private Stage primaryStage;

    public GuiControler(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void init()
    {
        try {
            // load layout from FXML file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getClassLoader().getResource("Application/Gui/Gui.fxml"));
            AnchorPane rootLayout = loader.load();

            GuiPresenter guiPresenter = loader.getController();
            guiPresenter.setEditControler(new EditControler(guiPresenter.toDo, guiPresenter.toDoID, guiPresenter.inProgress, guiPresenter.inProgressID,
                    guiPresenter.done, guiPresenter.doneID));
            guiPresenter.setAddControler(new AddControler(guiPresenter.toDo, guiPresenter.toDoID));

            // add layout to a scene and show them all
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.setMaximized(false);
            primaryStage.show();

        } catch (IOException e)
        {
            // don't do this in common apps
            e.printStackTrace();
        }
    }



}