package service;

import java.util.List;

public interface ServiceTask {
    void addTask (String category, String taskDescription);
    List<String> tasks(String category);
    List<String> tasks();
    void done (String category, String taskDescription);
    List<String>pendingTasks(String category);
    void done (String category);
}
