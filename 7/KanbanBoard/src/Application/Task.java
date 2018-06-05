package Application;

import javafx.scene.shape.Circle;


import java.awt.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Task implements Serializable {

    private String title;
    private String description;
    private LocalDate date = LocalDate.now();
    private String priority;
    private String priorityColor;
    private Type type;
    public Task() {
    }

    public Task(String title, String description, String priority, int y, int m, int d) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        date = LocalDate.of(y, m, d);
        this.type = Type.toDo;
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
    public Task(String title, String description, String priority,String priorityColor, int y, int m, int d) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        date = LocalDate.of(y, m, d);
         this.priorityColor =priorityColor;

    }
    public Task(String title, String description, String priority, int y, int m, int d,Type typ) {
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
        this.type = typ;
    }
    public Task(String title, String description, String priority,String priorityColor, int y, int m, int d,Type typ) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        date = LocalDate.of(y, m, d);
        this.priorityColor =priorityColor;
        this.type = typ;
    }
    //public String toString() {
       // String a ;

       // return "Task{" +
       //         "title='" + title + '\'' +
       //         '}';
    //}
       @Override
        public String toString() {
            return  (title + ", " +
                    description + ", " +
                    priority + ", " +
                    date.getYear() + ", " +
                    date.getMonthValue() + ", " +
                    date.getDayOfMonth() + ", " +
                    type);
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
    public int getYear() {
        return date.getYear();
    }

    public int getMonth(){
        return date.getMonthValue();
    }

    public int getDay(){
        return date.getDayOfMonth();
    }
    public void setType(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }
}
