public class TaskController {
    private TaskManager taskManager;

    public TaskController() {
        taskManager = new TaskManager();
    }

    public void addRegularTask(String description) {
        taskManager.addRegularTask(description);
    }

    public void addUrgentTask(String description, int urgency) {
        taskManager.addUrgentTask(description, urgency);
    }

    public void addRecurringTask(String description, String frequency) {
        taskManager.addRecurringTask(description, frequency);
    }

    public void markTaskAsDone(String id) {
        try {
            int index = Integer.parseInt(id) - 1;
            taskManager.markTaskAsDone(index);
        } catch (NumberFormatException e) {
            System.out.println("Invalid task ID.");
        }
    }

    public void editTask(String id) {
        try {
            int index = Integer.parseInt(id) - 1;
            String newDescription = javax.swing.JOptionPane.showInputDialog("Enter new description:");
            if (newDescription != null && !newDescription.isEmpty()) {
                taskManager.editTask(index, newDescription);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid task ID.");
        }
    }

    public void deleteTask(String id) {
        try {
            int index = Integer.parseInt(id) - 1;
            taskManager.deleteTask(index);
        } catch (NumberFormatException e) {
            System.out.println("Invalid task ID.");
        }
    }

    public java.util.List<String> getTaskList() {
        return taskManager.getAllTasksAsList();
    }
}
