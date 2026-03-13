import java.awt.*;
import java.util.List;
import javax.swing.*;

public class TaskManagerGUI extends JFrame {
    private TaskController controller;
    private DefaultListModel<String> listModel;
    private JList<String> taskList;

    public TaskManagerGUI(TaskController controller) {
        this.controller = controller;
        setTitle("SimpleTasker");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Task list model and JList
        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(taskList);
        add(scrollPane, BorderLayout.CENTER);

        // Buttonssss
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 5));

        JButton addTaskBtn = new JButton("Add Task");
        JButton editTaskBtn = new JButton("Edit Task");
        JButton deleteTaskBtn = new JButton("Delete Task");
        JButton doneTaskBtn = new JButton("Mark as Done");
        JButton exitBtn = new JButton("Exit");

        buttonPanel.add(addTaskBtn);
        buttonPanel.add(editTaskBtn);
        buttonPanel.add(deleteTaskBtn);
        buttonPanel.add(doneTaskBtn);
        buttonPanel.add(exitBtn);

        add(buttonPanel, BorderLayout.SOUTH);

        
        addTaskBtn.addActionListener(e -> showAddTaskDialog());
        editTaskBtn.addActionListener(e -> {
            int index = taskList.getSelectedIndex();
            if (index >= 0) {
                controller.editTask(String.valueOf(index + 1));
                refreshTasks();
            } else {
                JOptionPane.showMessageDialog(this, "Select a task to edit.");
            }
        });

        deleteTaskBtn.addActionListener(e -> {
            int index = taskList.getSelectedIndex();
            if (index >= 0) {
                controller.deleteTask(String.valueOf(index + 1));
                refreshTasks();
            } else {
                JOptionPane.showMessageDialog(this, "Select a task to delete.");
            }
        });

        doneTaskBtn.addActionListener(e -> {
            int index = taskList.getSelectedIndex();
            if (index >= 0) {
                controller.markTaskAsDone(String.valueOf(index + 1));
                refreshTasks();
            } else {
                JOptionPane.showMessageDialog(this, "Select a task to mark as done.");
            }
        });

        exitBtn.addActionListener(e -> System.exit(0));

        setVisible(true);
        refreshTasks();
    }

    private void showAddTaskDialog() {
        String[] options = {"Regular", "Urgent", "Recurring"};
        int choice = JOptionPane.showOptionDialog(this, "Select task type to add:", "Add Task",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, options, options[0]);

        switch (choice) {
            case 0: // Regular d2
                String regDesc = JOptionPane.showInputDialog("Enter regular task description:");
                if (regDesc != null && !regDesc.isEmpty()) {
                    controller.addRegularTask(regDesc);
                    refreshTasks();
                }
                break;

            case 1: // Urgent d2
                String urgDesc = JOptionPane.showInputDialog("Enter urgent task description:");
                String urgLevel = JOptionPane.showInputDialog("Enter urgency level (1–5):");
                try {
                    int level = Integer.parseInt(urgLevel);
                    controller.addUrgentTask(urgDesc, level);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Invalid urgency level.");
                }
                refreshTasks();
                break;

            case 2: // Recurring d2
                String recDesc = JOptionPane.showInputDialog("Enter recurring task description:");
                String frequency = JOptionPane.showInputDialog("Enter frequency (e.g., Daily):");
                controller.addRecurringTask(recDesc, frequency);
                refreshTasks();
                break;
        }
    }

    private void refreshTasks() {
        listModel.clear();
        List<String> tasks = controller.getTaskList();
        for (String task : tasks) {
            listModel.addElement(task);
        }
    }
}
