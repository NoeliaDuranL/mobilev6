package tests;

import activities.whendo.MainActivity;
import activities.whendo.TaskActivity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sesion.Sesion;

public class AddTaskTest {

    MainActivity mainActivity = new MainActivity();
    TaskActivity taskActivity = new TaskActivity();

    @BeforeEach
    public void setup(){

    }

    @AfterEach
    public void tearDown(){
        Sesion.getSesion().closeSesion();
    }

    @Test
    public void addTask(){
        String title = "UCB"+System.currentTimeMillis();
        mainActivity.addButton.click();
        taskActivity.titleTextBox.setText(title);
        taskActivity.notesTextBox.setText("New Task Notes");
        taskActivity.saveButton.click();
        Assertions.assertEquals(title, mainActivity.firstItemLabel.getText());
    }
}
