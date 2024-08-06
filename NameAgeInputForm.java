import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class NameAgeInputForm {
    private static CardLayout cardLayout;
    private static JPanel cardPanel;
    private static DefaultListModel<String> habitListModel;
    private static DefaultListModel<String> habitListModel1;
    private static DefaultListModel<String> habitListModel2;
    private static JList<String> habitList;
    private static JList<String> habitList1;
    private static JList<String> habitList2;
    private static Map<String, List<Boolean>> progressMap;
    private static List<String> habits;
    private static JFrame progressFrame;
    private static String viewProgressByDate(String habit) {
        // Replace this with your logic to fetch actual daily progress data for the selected habit
        // For demonstration, we'll simulate data for the last 30 days
        StringBuilder progress = new StringBuilder();
        progress.append("Date\tProgress\n");
        Calendar calendar = Calendar.getInstance();

        for (int i = 0; i < 30; i++) {
            Date currentDate = calendar.getTime();
            String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(currentDate);
            boolean isComplete = progressMap.get(habit).size() > i ? progressMap.get(habit).get(i) : false;
            String progressStatus = isComplete ? "Complete" : "Incomplete";
            progress.append(formattedDate + "\t" + progressStatus + "\n");

            // Subtract one day from the current date
            calendar.add(Calendar.DAY_OF_MONTH, -1);
        }

        return progress.toString();
    }

    // Logic to view progress by week
    private static String viewProgressByWeek(String habit) {
        // Replace this with your logic to fetch actual weekly progress data for the selected habit
        // For demonstration, we'll simulate data for the last 4 weeks
        StringBuilder progress = new StringBuilder();
        progress.append("Week\tProgress\n");

        Calendar calendar = Calendar.getInstance();

        for (int i = 0; i < 4; i++) {
            String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
            boolean isComplete = progressMap.get(habit).size() > i * 7 ? progressMap.get(habit).get(i * 7) : false;
            String progressStatus = isComplete ? "Complete" : "Incomplete";
            progress.append("Week of " + formattedDate + "\t" + progressStatus + "\n");

            // Subtract one week from the current date
            calendar.add(Calendar.DAY_OF_MONTH, -7);
        }

        return progress.toString();
    }

    // Logic to view progress by month
    private static String viewProgressByMonth(String habit) {
        // Replace this with your logic to fetch actual monthly progress data for the selected habit
        // For demonstration, we'll simulate data for the last 12 months
        StringBuilder progress = new StringBuilder();
        progress.append("Month\tProgress\n");

        Calendar calendar = Calendar.getInstance();

        for (int i = 0; i < 12; i++) {
            String formattedDate = new SimpleDateFormat("yyyy-MM").format(calendar.getTime());
            boolean isComplete = progressMap.get(habit).size() > i * 30 ? progressMap.get(habit).get(i * 30) : false;
            String progressStatus = isComplete ? "Complete" : "Incomplete";
            progress.append("Month of " + formattedDate + "\t" + progressStatus + "\n");

            // Subtract one month from the current date
            calendar.add(Calendar.MONTH, -1);
        }

        return progress.toString();
    }

    // Logic to view progress by year
    private static String viewProgressByYear(String habit) {
        // Replace this with your logic to fetch actual yearly progress data for the selected habit
        // For demonstration, we'll simulate data for the last 8 years
        StringBuilder progress = new StringBuilder();
        progress.append("Year\tProgress\n");

        Calendar calendar = Calendar.getInstance();

        for (int i = 0; i < 8; i++) {
            int year = calendar.get(Calendar.YEAR);
            boolean isComplete = progressMap.get(habit).size() > i * 365 ? progressMap.get(habit).get(i * 365) : false;
            String progressStatus = isComplete ? "Complete" : "Incomplete";
            progress.append(year + "\t" + progressStatus + "\n");

            // Subtract one year from the current date
            calendar.add(Calendar.YEAR, -1);
        }

        return progress.toString();
    }
    private static void showProgressData(String title, String data) {
        JTextArea textArea = new JTextArea(data, 15, 30);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setCaretPosition(0);
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);

        JDialog dialog = new JDialog(progressFrame, title, true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.getContentPane().add(scrollPane);
        dialog.pack();
        dialog.setLocationRelativeTo(progressFrame);
        dialog.setVisible(true);
    }
    public static void main(String[] args) {
        // Create the main frame for entering details
        JFrame frame = new JFrame("Habit Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.getContentPane().setBackground(new Color(230, 230, 230)); // Set background color
        frame.setLayout(new BorderLayout());
        // Create labels, text fields, and a button
        JLabel nameLabel = new JLabel("Name:");
        JLabel ageLabel = new JLabel("Age:");
        Font labelFont = new Font("Arial", Font.BOLD, 18);
        nameLabel.setFont(labelFont);
        ageLabel.setFont(labelFont);
        JTextField nameField = new JTextField(20);
        JTextField ageField = new JTextField(5);
        Font fieldFont = new Font("Arial", Font.PLAIN, 16);
        nameField.setFont(fieldFont);
        ageField.setFont(fieldFont);
        JButton submitButton = new JButton("Submit");
        Font buttonFont = new Font("Arial", Font.BOLD, 16);
        submitButton.setFont(buttonFont);
        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);
        JPanel inputPanel = new JPanel(new GridBagLayout());
        // Create constraints for centering components
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.LINE_END;
        // Add components to the input panel with centered constraints
        inputPanel.add(nameLabel, constraints);
        constraints.gridx = 1;
        inputPanel.add(nameField, constraints);
        constraints.gridy = 1;
        constraints.gridx = 0;
        constraints.anchor = GridBagConstraints.LINE_START;
        inputPanel.add(ageLabel, constraints);
        constraints.gridx = 1;
        inputPanel.add(ageField, constraints);
        // Center the "Submit" button
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        inputPanel.add(submitButton, constraints);
        cardPanel.add(inputPanel, "inputPanel");
        frame.add(cardPanel, BorderLayout.CENTER);

// The rest of your code remains the same

        // Create the greeting frame
        JFrame greetingFrame = new JFrame("Habit Tracker");
        greetingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        greetingFrame.setSize(frame.getSize()); // Set the size to match the input frame
        greetingFrame.getContentPane().setBackground(frame.getContentPane().getBackground()); // Set background color

        // Calculate the center position for the greeting frame
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - greetingFrame.getWidth()) / 2;
        int y = (screenSize.height - greetingFrame.getHeight()) / 2;
        greetingFrame.setLocation(x, y);

        greetingFrame.setVisible(false); // Initially hidden

        JLabel greetingLabel = new JLabel();
        greetingLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JPanel greetingPanel = new JPanel(new BorderLayout());

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 10, 10)); // GridLayout with 4 rows and 1 column
        Font buttonFontInGreeting = new Font("Arial", Font.BOLD, 16);

        JButton addGoodHabitButton = new JButton("Add Good Habit");
        addGoodHabitButton.setFont(buttonFontInGreeting);
        buttonPanel.add(addGoodHabitButton);

        JButton markButton = new JButton("Mark Track");
        markButton.setFont(buttonFontInGreeting);
        buttonPanel.add(markButton);

        JButton progressButton = new JButton("Habit Progress");
        progressButton.setFont(buttonFontInGreeting);
        buttonPanel.add(progressButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setFont(buttonFontInGreeting);
        buttonPanel.add(exitButton);

        greetingPanel.add(greetingLabel, BorderLayout.NORTH);
        greetingPanel.add(buttonPanel, BorderLayout.CENTER);
        greetingFrame.add(greetingPanel);

        JFrame habitsFrame = new JFrame("Habit Tracker");
        habitsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        habitsFrame.setSize(500, 500); // Set the frame size
        habitsFrame.getContentPane().setBackground(new Color(230, 230, 230)); // Set the background color
        // **** Lifted ****
        habits = new ArrayList<>();
        progressMap = new HashMap<>();
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        habitListModel = new DefaultListModel<>();
        habitList = new JList<>(habitListModel);
        mainPanel.add(new JScrollPane(habitList), BorderLayout.CENTER);
        habitListModel1 = new DefaultListModel<>();
        habitList1 = new JList<>(habitListModel1);
        habitListModel2 = new DefaultListModel<>();
        habitList2 = new JList<>(habitListModel1);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        JTextField habitTextField = new JTextField(20);
        JButton addHabitButton = new JButton("Add Habit");
        addHabitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newHabit = habitTextField.getText();
                if (!newHabit.isEmpty()) {
                    habits.add(newHabit);
                    habitListModel.addElement(newHabit);
                    habitListModel1.addElement(newHabit);
                    habitListModel2.addElement(newHabit);
                    habitTextField.setText("");
                    progressMap.put(newHabit, new ArrayList<>());
                }
            }
        });

        JButton removeHabitButton = new JButton("Remove Habit");
        removeHabitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = habitList.getSelectedIndex();
                if (selectedIndex >= 0) {
                    String habit = habits.get(selectedIndex);
                    habits.remove(selectedIndex);
                    habitListModel.remove(selectedIndex);
                    habitListModel1.remove(selectedIndex);
                    habitListModel2.remove(selectedIndex);
                    progressMap.remove(habit);
                }
            }
        });

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                habitsFrame.dispose();
                greetingFrame.setVisible(true);

            }
        });
        controlPanel.add(backButton);
        controlPanel.add(habitTextField);
        controlPanel.add(addHabitButton);
        controlPanel.add(removeHabitButton);
        mainPanel.add(controlPanel, BorderLayout.SOUTH);
        habitsFrame.add(mainPanel);


        // Display the habits frame in the center of the screen
        habitsFrame.setLocationRelativeTo(null);


        // Mark Track Frame
        JFrame markTrackFrame = new JFrame("Habit Tracker");
        markTrackFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        markTrackFrame.setSize(500, 500); // Set the frame size
        markTrackFrame.getContentPane().setBackground(new Color(230, 230, 230));// Set the background color
        JPanel mainPanel1 = new JPanel();
        mainPanel1.setLayout(new BorderLayout());
        mainPanel1.add(new JScrollPane(habitList1), BorderLayout.CENTER);
        JPanel controlPanel1 = new JPanel();
        controlPanel1.setLayout(new FlowLayout());

        // Create a button for going back
        JButton markCompleteButton = new JButton("Mark Complete");
        markCompleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = habitList1.getSelectedIndex();
                if (selectedIndex >= 0) {
                    String habit = habits.get(selectedIndex);
                    progressMap.get(habit).add(true);
                    habitListModel1.set(selectedIndex, habit + " (Complete)");
                }
            }
        });

        JButton markIncompleteButton = new JButton("Mark Incomplete");
        markIncompleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = habitList1.getSelectedIndex();
                if (selectedIndex >= 0) {
                    String habit = habits.get(selectedIndex);
                    progressMap.get(habit).add(false);
                    habitListModel1.set(selectedIndex, habit + " (Incomplete)");
                }
            }
        });
        JButton backButton1 = new JButton("Back");
        backButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                markTrackFrame.dispose();
                greetingFrame.setVisible(true);

            }
        });
        controlPanel1.add(backButton1);
        controlPanel1.add(markCompleteButton);
        controlPanel1.add(markIncompleteButton);
        mainPanel1.add(controlPanel1, BorderLayout.SOUTH);
        markTrackFrame.add(mainPanel1);
        markTrackFrame.setLocationRelativeTo(null);

        progressFrame = new JFrame("Habit Tracker");
        progressFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        progressFrame.setSize(500, 500); // Set the frame size
        progressFrame.getContentPane().setBackground(new Color(230, 230, 230));// Set the background color
        JPanel mainPanel2 = new JPanel();
        mainPanel2.setLayout(new BorderLayout());
        mainPanel2.add(new JScrollPane(habitList2), BorderLayout.CENTER);
        JPanel controlPanel2 = new JPanel();
        controlPanel2.setLayout(new FlowLayout());
        JButton viewProgressByDateButton = new JButton("View Progress by Date");
        viewProgressByDateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String selectedHabit = habitList2.getSelectedValue();
                    if (selectedHabit != null) {
                        showProgressData("Daily Progress for " + selectedHabit, viewProgressByDate(selectedHabit));
                    } else {
                        JOptionPane.showMessageDialog(progressFrame, "Please select a habit from the list.");
                    }
                } catch (NullPointerException ex) {
                    JOptionPane.showMessageDialog(progressFrame, "No progress data available for the selected habit.");
                }
            }
        });

        JButton viewProgressByWeekButton = new JButton("View Progress by Week");
        viewProgressByWeekButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String selectedHabit = habitList2.getSelectedValue();
                    if (selectedHabit != null) {
                        showProgressData("Daily Progress for " + selectedHabit, viewProgressByWeek(selectedHabit));
                    } else {
                        JOptionPane.showMessageDialog(progressFrame, "Please select a habit from the list.");
                    }
                } catch (NullPointerException ex) {
                    JOptionPane.showMessageDialog(progressFrame, "No progress data available for the selected habit.");
                }
            }
        });

        JButton viewProgressByMonthButton = new JButton("View Progress by Month");
        viewProgressByMonthButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String selectedHabit = habitList2.getSelectedValue();
                    if (selectedHabit != null) {
                        showProgressData("Daily Progress for " + selectedHabit, viewProgressByMonth(selectedHabit));
                    } else {
                        JOptionPane.showMessageDialog(progressFrame, "Please select a habit from the list.");
                    }
                } catch (NullPointerException ex) {
                    JOptionPane.showMessageDialog(progressFrame, "No progress data available for the selected habit.");
                }
            }
        });

        JButton viewProgressByYearButton = new JButton("View Progress by Year");
        viewProgressByYearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String selectedHabit = habitList2.getSelectedValue();
                    if (selectedHabit != null) {
                        showProgressData("Daily Progress for " + selectedHabit, viewProgressByYear(selectedHabit));
                    } else {
                        JOptionPane.showMessageDialog(progressFrame, "Please select a habit from the list.");
                    }
                } catch (NullPointerException ex) {
                    JOptionPane.showMessageDialog(progressFrame, "No progress data available for the selected habit.");
                }
            }
        });
        JButton backButton2 = new JButton("Back");
        backButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                progressFrame.dispose();
                greetingFrame.setVisible(true);

            }
        });
        controlPanel2.add(backButton2);
        controlPanel2.add(viewProgressByDateButton);
        controlPanel2.add(viewProgressByWeekButton);
        controlPanel2.add(viewProgressByMonthButton);
        controlPanel2.add(viewProgressByYearButton);
        mainPanel2.add(controlPanel2, BorderLayout.SOUTH);
        progressFrame.add(mainPanel2);
        progressFrame.setLocationRelativeTo(null);


        markButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                greetingFrame.dispose();
                markTrackFrame.setVisible(true);
            }
        });
        // Add an action listener for the "Add Good Habit" button
        addGoodHabitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                greetingFrame.dispose(); // Close the greeting frame
                habitsFrame.setVisible(true);// Open the new frame for habits
            }
        });
        progressButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                greetingFrame.dispose();
                progressFrame.setVisible(true);

            }
        });

        // Add an action listener for the "Add Habit" button
        // This button has not been defined yet, and you can add it as needed.

        // Exit button action listener
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Create an action listener for the "Submit" button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String age = ageField.getText();
                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please enter name", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!isNumeric(age)) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid age", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Display the greeting in the greeting frame
                    greetingLabel.setText("Hello, " + name + "!");
                    greetingFrame.setVisible(true); // Show the greeting frame
                    frame.setVisible(false); // Hide the input frame
                }
            }
        });
        // Display the input frame in the center of the screen
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    // Helper function to check if a string contains only numeric characters
    private static boolean isNumeric(String str) {
        return str.matches("\\d+");
    }
}
