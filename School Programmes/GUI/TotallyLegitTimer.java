import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppTimer {
    private static int timeInSec = 0;
    private static int timeInMillisec = 0;  // variable for milliseconds (which gets displayed)
    private static boolean isRunning = false;
    private static Timer timer;  // timer to do the counting
    private static String lapTimes = "00:00:00"; // variable for storing and getting the lap time
    private static int lapCount = 0;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Timer with Lap");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 400);
        frame.setLayout(new BorderLayout());

        //creation of timer label which displays counted time
        JLabel timerLabel = new JLabel("00:00:00", JLabel.CENTER);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 50));
        timerLabel.setForeground(Color.BLACK);
        frame.add(timerLabel, BorderLayout.NORTH);

        //button creation
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

        frame.add(buttonPanel);

        //creation of JTextArea to display laps
        JTextArea lapArea = new JTextArea(10, 30);
        lapArea.setEditable(false);
        lapArea.setFont(new Font("Arial", Font.PLAIN, 14));
        lapArea.setText("Lap Times:\n");
        JScrollPane scrollPane = new JScrollPane(lapArea);
        frame.add(scrollPane, BorderLayout.SOUTH);

        //timer to do the incrementation of time
        timer = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isRunning) {
                    timeInMillisec+=10;
                    if (timeInMillisec >= 1000) { // When milliseconds reach 100, increment seconds
                        timeInMillisec = 0;
                        timeInSec++;
                    }
                    updateTimerLabel(timerLabel);
                }
            }
        });

        //start button function setting
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isRunning) {
                    isRunning = true;
                    timer.start();
                }
            }
        });

        //stop button function setting
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    isRunning = false;
            }
        });

        //reset button function setting
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isRunning = false;
                timeInSec = 0;
                timeInMillisec = 0;
                lapTimes = "00:00:00"; //variable for getting and counting lap time is reset
                lapCount = 0;
                lapArea.setText("Lap Times:\n"); //clear time display and reset it
                updateTimerLabel(timerLabel);
            }
        });

        //lap button function setting
        lapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isRunning) {
                    String lapTime = formatTime(timeInSec, timeInMillisec/10);
                    lapTimes.equals(lapTime);
                    lapCount++;
                    lapArea.append("Lap " + lapCount + ": " + lapTime + "\n");
                    lapArea.setCaretPosition(lapArea.getDocument().getLength()); //for auto scroll
                }
            }
        });

        frame.setVisible(true);
    }

    //method for updating the label with a properly formatted time (min:sec:millisec)
    private static void updateTimerLabel(JLabel label) {
        int minutes = timeInSec / 60;
        int seconds = timeInSec % 60;
        label.setText(String.format("%02d:%02d:%02d", minutes, seconds, timeInMillisec/10));
    }

    //method for doing the actual formatting of the time in a String
    private static String formatTime(int seconds, int milliseconds) {
        int minutes = seconds / 60;
        seconds = seconds % 60;
        return String.format("%02d:%02d:%02d", minutes, seconds, milliseconds);
    }
}
