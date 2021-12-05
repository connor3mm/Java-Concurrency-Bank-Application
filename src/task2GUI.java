import javax.swing.*;

public class task2GUI {
    private JPanel task2GUI;




    public static void main(String[] args) {
        JFrame frame = new JFrame("Task2");
        frame.setContentPane(new task2GUI().task2GUI);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


}
