package sample.view;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import sample.Model.DrawerTask;

public class Controller {
    @FXML
    private Canvas canvas;
    @FXML
    private Label integral;
    @FXML
    private TextField repetition;
    @FXML
    private ProgressBar progressBar;

    double valOfIntegral;
    private DrawerTask task;

    @FXML
    public void handleRunBtnAction() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        String tmp = repetition.getText();
        task = new DrawerTask(gc, Integer.parseInt(tmp));
        task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                valOfIntegral = (double) task.getValue();
                integral.setText((String.valueOf(valOfIntegral)));
            }
        });
        progressBar.progressProperty().bind(task.progressProperty());
        new Thread(task).start();
    }

    @FXML
    private void handleStopBtnAction() {
        task.cancel();
    }

    @FXML
    private void initialize() {

        integral.setText("Obliczam...");
        repetition.setText("10001");
        repetition.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (newValue.matches("\\d*")) {
                    int value = Integer.parseInt(newValue);
                } else {
                    repetition.setText(oldValue);
                }
            }
        });
    }
}
