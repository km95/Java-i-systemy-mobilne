package Application;

import Application.Gui.GuiControler;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application
{
    public Main() {}

    public static void main(String[] args) {
        launch(args); }

    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("Kanban Board");
        GuiControler guiControler = new GuiControler(primaryStage);
        guiControler.init();
    }
}