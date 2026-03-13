import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    public void addRegularTask(String description) {
        tasks.add(new RegularTask(description));
    }

    public void addUrgentTask(String description, int urgency) {
        tasks.add(new UrgentTask(description, urgency));
    }

    public void addRecurringTask(String description, String frequency) {
        tasks.add(new RecurringTask(description, frequency));
    }

    public boolean markTaskAsDone(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).setDone(true);
            return true;
        }
        return false;
    }

    public boolean editTask(int index, String newDescription) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).setDescription(newDescription);
            return true;
        }
        return false;
    }

    public boolean deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
            return true;
        }
        return false;
    }

    public List<String> getAllTasksAsList() {
        List<String> list = new ArrayList<>();
        int i = 1;
        for (Task t : tasks) {
            list.add(i++ + ". " + t.toString());
        }
        return list;
    }
}
