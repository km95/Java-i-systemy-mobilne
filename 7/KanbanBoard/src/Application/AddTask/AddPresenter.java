package Application.AddTask;


import Application.EditTask.EditControler;
import Application.Gui.GuiControler;
import Application.Gui.GuiPresenter;
import Application.Task;
import Application.Type;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

public class AddPresenter {
    public TextArea descriptionID;
    public TextField titleID;
    public ComboBox priorityComboBoxID;
    public DatePicker expDateID;
    ObservableList<String> priority_list = FXCollections.observableArrayList("Low", "Medium", "High");

    public AddPresenter() {
    }

    @FXML
    public void initialize() {
        priorityComboBoxID.setItems(priority_list);
        expDateID.setValue(LocalDate.now());
        priorityComboBoxID.setValue("Low");
    }


    @FXML
    public void handleaddButtonID() {
        if (titleID.getText().equals("") ||
                priorityComboBoxID.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText("Wprowadź wszystkie dane !");
            alert.show();
        } else {
            try {
                String title = titleID.getText();
                int d = (expDateID.getValue().getDayOfMonth());
                int m = (expDateID.getValue().getMonthValue());
                int y = (expDateID.getValue().getYear());
                String priority = (String) priorityComboBoxID.getValue();
                String description = descriptionID.getText();

              //  AddControler.add(new Task(title, description, priority, y, m, d));
                GuiPresenter.toDo.add(new Task(title, description, priority, y, m, d, Type.toDo));
                AddControler.closeStage();

            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Błąd");
                alert.setHeaderText("Wprowadzono nieprawidłową wartość !");
                alert.show();
            }
        }
    }


}
