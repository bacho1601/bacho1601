import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TimerApp {
    private static final int START_TIME = 0;  // Timer start time in milliseconds
    private int timeInSeconds = START_TIME;
    private int timeInMilliseconds = 0;  // Millisecond counter
    private boolean isRunning = false;
    private Timer timer;  // Swing Timer
    private ArrayList<String> lapTimes;  // Store lap times

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TimerApp().createAndShowGUI();
        });
    }

    public TimerApp() {
        lapTimes = new ArrayList<>();
    }

    public void createAndShowGUI() {
        // Create the JFrame for the application
        JFrame frame = new JFrame("Timer with Lap");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 400);
        frame.setLayout(new BorderLayout());

        // Create the timer label to display the time
        JLabel timerLabel = new JLabel("00:00:00", SwingConstants.CENTER);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 50));
        timerLabel.setForeground(Color.BLACK);
        frame.add(timerLabel, BorderLayout.NORTH);

        // Create buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton startButton = new JButton("Start");
        JButton stopButton = new JButton("Stop");
        JButton resetButton = new JButton("Reset");
        JButton lapButton = new JButton("Lap");

        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(lapButton);

        frame.add(buttonPanel, BorderLayout.CENTER);

        // Create a JTextArea to display the lap times
        JTextArea lapArea = new JTextArea(10, 30);
        lapArea.setEditable(false);
        lapArea.setFont(new Font("Arial", Font.PLAIN, 14));
        lapArea.setText("Lap Times:\n");
        JScrollPane scrollPane = new JScrollPane(lapArea);
        frame.add(scrollPane, BorderLayout.SOUTH);

        // Timer logic to update the time
        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isRunning) {
                    timeInMilliseconds++;
                    if (timeInMilliseconds >= 100) { // When milliseconds reach 100, increment seconds
                        timeInMilliseconds = 0;
                        timeInSeconds++;
                    }
                    updateTimerLabel(timerLabel);
                }
            }
        });

        // Start button action listener
        startButton.addActionListener(e -> {
            if (!isRunning) {
                isRunning = true;
                timer.start();
            }
        });

        // Stop button action listener
        stopButton.addActionListener(e -> {
            isRunning = false;
        });

        // Reset button action listener
        resetButton.addActionListener(e -> {
            isRunning = false;
            timeInSeconds = START_TIME;
            timeInMilliseconds = 0;
            lapTimes.clear(); // Clear lap times on reset
            lapArea.setText("Lap Times:\n"); // Reset lap times display
            updateTimerLabel(timerLabel);
        });

        // Lap button action listener
        lapButton.addActionListener(e -> {
            if (isRunning) {
                String lapTime = formatTime(timeInSeconds, timeInMilliseconds);
                lapTimes.add(lapTime);
                lapArea.append("Lap " + lapTimes.size() + ": " + lapTime + "\n");
            }
        });

        // Show the GUI
        frame.setVisible(true);
    }

    // Update the timer label with the formatted time (mm:ss:SS)
    private void updateTimerLabel(JLabel label) {
        int minutes = timeInSeconds / 60;
        int seconds = timeInSeconds % 60;
        label.setText(String.format("%02d:%02d:%02d", minutes, seconds, timeInMilliseconds));
    }

    // Helper method to format the time as mm:ss:SS
    private String formatTime(int seconds, int milliseconds) {
        int minutes = seconds / 60;
        seconds = seconds % 60;
        return String.format("%02d:%02d:%02d", minutes, seconds, milliseconds);
    }
}
