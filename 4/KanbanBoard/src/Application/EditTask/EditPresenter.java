package Application.EditTask;

import Application.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class EditPresenter {
    public TextArea descriptionID;
    public TextField titleID;
    public ComboBox priorityID;
    public DatePicker expDateID;
    ObservableList<String> priority_list = FXCollections.observableArrayList("Low", "Medium", "High");

    public EditPresenter() {
    }

    @FXML
    public void initialize() {
        titleID.setText(EditControler.getElement().getTitle());
        priorityID.setItems(priority_list);
        priorityID.setValue(EditControler.getElement().getPriority());
        expDateID.setValue(EditControler.getElement().getDate());
        descriptionID.setText(EditControler.getElement().getDescription());
    }

    @FXML
    public void handleEditButtonID() {
        if (titleID.getText().equals("") ||
                priorityID.getSelectionModel().isEmpty() ||
                expDateID.getValue().equals(null)) {
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
                String priority = (String) priorityID.getValue();
                String description = descriptionID.getText();

                EditControler.add(new Task(title, description, priority, y, m, d), EditControler.getIndex());
                EditControler.closeStage();

            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Błąd");
                alert.setHeaderText("Wprowadzono nieprawidłową wartość !");
                alert.show();
            }
        }
    }

}
