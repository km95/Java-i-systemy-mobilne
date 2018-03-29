package Application;

import javafx.scene.shape.Circle;


import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Task {

    private String title;
    private String description;
    private LocalDate date = LocalDate.now();
    private String priority;
    private String priorityColor;

    public Task() {
    }

    public Task(String title, String description, String priority, int y, int m, int d) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        date = LocalDate.of(y, m, d);
        if(priority == "Low")
        {
            priorityColor ="green";
        }else if(priority == "Medium")
        {
            priorityColor ="yellow";
        }if(priority == "High")
        {
            priorityColor ="red";
        }
    }

    @Override
    public String toString() {
        String a ;

        return "Task{" +
                "title='" + title + '\'' +
                '}';
    }

    public String getPriorityColor() {
        return priorityColor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
    public String isPriority() {
        return priority ;
    }
}
