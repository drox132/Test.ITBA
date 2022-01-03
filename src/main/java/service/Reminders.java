package service;

import exception.CategoryNotFoundException;
import model.Task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Reminders implements ServiceTask{
    List<Task>taskList= new ArrayList<>();
///////////////////////////////METHOD CREATE TASKS////////////////////////////////////

    @Override
    public void addTask(String category, String taskDescription) {
        Task newTask= new Task(false,category,taskDescription);
        taskList.add(newTask);
    }

//////////////////////////METHOD SELECT LIST TASKS BY CATEGORY////////////////////////

    @Override
    public List<String> tasks(String category) {
        List<String>tasks=new ArrayList<>();
        if (this.categoryExistenceVerification(category)==false){
            throw new CategoryNotFoundException();
        }
        for (Task particular:taskList){
            if (particular.getCategory()==category){
                tasks.add(particular.getTaskDescription());
            }
        }
        Collections.sort(tasks);
        return tasks;
    }
/////////METHOD SELECT ALL LIST TASKS BY ORDERED ALPHABETIC AND BY TASKDESCRIPTION ALPHABETIC ORDERED///////

    @Override
    public List<String> tasks() {
        List<String> categorylistAlphabeticOrdered=new ArrayList<>();
        List<String> tasksListAlphabeticOrderedByCategoryAndTaskDescription=new ArrayList<>();
        for (Task particular:taskList){
            if (!categorylistAlphabeticOrdered.contains(particular.getCategory())){
                categorylistAlphabeticOrdered.add(particular.getCategory());
            }
        }
        Collections.sort(categorylistAlphabeticOrdered);

        for (String particular:categorylistAlphabeticOrdered){
            tasksListAlphabeticOrderedByCategoryAndTaskDescription.addAll(tasks(particular));
        }
        return tasksListAlphabeticOrderedByCategoryAndTaskDescription;
    }
/////////////////////////////METHOD CHANGE STATUS A TASK BY CATEGORY////////////////////////
    @Override
    public void done(String category, String taskDescription) {
        if (this.categoryExistenceVerification(category)==false){
            throw new CategoryNotFoundException();
        }
        for (Task particular:taskList){
            if (particular.getCategory()==category&&particular.getTaskDescription()==taskDescription){
                particular.setStatus(true);
            }
        }
    }

/////////////////////////////METHOD SELECT LIST STATUS TASK BY CATEGORY////////////////////////
    @Override
    public List<String> pendingTasks(String category) {
        List<String>pendingTask=new ArrayList<>();
        for (Task particular:taskList){
            if (particular.getCategory()==category&&particular.getStatus()==false){
                pendingTask.add(particular.getTaskDescription());
            }
        }
        Collections.sort(pendingTask);
        return pendingTask;
    }
////////////////////////////////METHOD CHANGE STATUS ALLTASKS BY CATEGORY////////////////////////
    @Override
    public void done(String category) {
        if (this.categoryExistenceVerification(category)==false){
            throw new CategoryNotFoundException();
        }
        for (Task particular:taskList){
            if (particular.getCategory()==category&&particular.getStatus()==false){
                particular.setStatus(true);
            }
        }
    }


    ///////////METHOD PRIVATE CATEGORY EXISTENCE VERIFICATION/////////////////////////////////

    private boolean categoryExistenceVerification (String category){
        for (Task particular:taskList){
            if (particular.getCategory()==category){
                return true;
            }
        }
        return false;
    }




}
