import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class task2GUI {

    private JPanel task2GUI;
    public boolean getThreads = false;
    private JTabbedPane tabbedPane1;
    private JTextField searchTextField;
    private JButton SEARCHButton;
    private JButton FILTERButton;
    private JTextArea resultsTextArea;
    private JButton newThreadButton;
    private JButton stopThreadButton;
    private JButton allThreadsButton;


    public task2GUI() {
        SEARCHButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getThreads = false;
                Thread value = Task2.searchByName(searchTextField.getText());

                if (value == null) {
                    resultsTextArea.setText("There are no threads with that name. :-))))");
                } else {
                    resultsTextArea.setText(value.toString());
                }
            }
        });

        FILTERButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getThreads = false;
                List<Thread> value = Task2.filterByThreadGroup(searchTextField.getText());

                if (value == null || value.size() == 0) {
                    resultsTextArea.setText("There are no thread groups with that name.");
                } else {
                    resultsTextArea.setText("Threads within group " + searchTextField.getText() + ":\n" + value.toString());
                }
            }
        });

        newThreadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getThreads = false;
                if (searchTextField.getText().isEmpty()) {
                    resultsTextArea.setText("Please provide a thread name to start one.");
                } else {
                    Task2.startNewThread(searchTextField.getText());
                    resultsTextArea.setText("Thread with name " + searchTextField.getText() + " has started.\n" + Thread.currentThread());
                }
            }
        });


        stopThreadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { ;
                getThreads = false;
                if (searchTextField.getText().isEmpty()) {
                    resultsTextArea.setText("You need to enter a Thread name to stop one.");

                } else  {
                    Boolean stopped = Task2.stopThread(searchTextField.getText());

                    if (stopped) {
                        resultsTextArea.setText("Thread with name " + searchTextField.getText() + " has stopped.\n");
                    } else {
                        resultsTextArea.setText("Thread could not be stopped.");
                    }
                }
            }
        });


        allThreadsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                getThreads = true;
                appendThreads();
                Timer timer = new Timer(5000, new ActionListener() {

                    public void actionPerformed(ActionEvent evt) {

                        if (getThreads) {
                            appendThreads();
                        }
                    }
                }); // Execute task each 5000 millisecond
                timer.setRepeats(getThreads);
                timer.start();

            }
        });


    }

    private void appendThreads() {
        Thread[] threads = Task2.getThreadsGUI();

        int i = 0;

        while (threads[i] != null) {
            resultsTextArea.append("Name of thread: " + threads[i].getName());
            resultsTextArea.append("\n");
            resultsTextArea.append("ID of thread: " + threads[i].getId());
            resultsTextArea.append("\n");
            resultsTextArea.append("Priority of thread: " + threads[i].getPriority());
            resultsTextArea.append("\n");
            resultsTextArea.append("State of thread:" + threads[i].getState());
            resultsTextArea.append("\n");
            resultsTextArea.append("Is Daemon: " + threads[i].isDaemon());
            resultsTextArea.append("\n");
            resultsTextArea.append("----------------------------------------------");
            resultsTextArea.append("\n");
            i++;
        }
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Task2");
        frame.setContentPane(new task2GUI().task2GUI);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


}
