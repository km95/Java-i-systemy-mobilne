package Application.Gui;

import Application.AddTask.AddControler;
import Application.EditTask.EditControler;
import Application.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GuiPresenter implements Initializable {
    private EditControler editControler;
    private AddControler addControler;
    public ObservableList<Task> toDo = FXCollections.observableArrayList();
    public ObservableList<Task> inProgress = FXCollections.observableArrayList();
    public ObservableList<Task> done = FXCollections.observableArrayList();
    public Menu aboutID;
    public ListView<Task> toDoID = new ListView<>();
    public ListView<Task> inProgressID = new ListView<>();
    public ListView<Task> doneID = new ListView<>();

    public GuiPresenter() {
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {

        toDoID.setItems(toDo);
        inProgressID.setItems(inProgress);
        doneID.setItems(done);

        Label menuLabel = new Label("About");
        menuLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                AuthorMessage();
            }
        });

        aboutID.setGraphic(menuLabel);


        toDoID.setOnKeyPressed(event -> {
                    switch (event.getCode()) {
                        case RIGHT:
                            if (!toDoID.getItems().isEmpty()) {
                                inProgressID.getItems().add(toDoID.getItems().get(toDoID.getFocusModel().getFocusedIndex()));
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
                        inProgressID.getItems().remove(inProgressID.getItems().get(inProgressID.getFocusModel().getFocusedIndex()));
                    }
                    break;
                case LEFT:
                    if (!inProgressID.getItems().isEmpty()) {
                        toDoID.getItems().add(inProgressID.getItems().get(inProgressID.getFocusModel().getFocusedIndex()));
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

    @FXML
    public void handlenewTaskButton() {
        addControler.show();
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
}

