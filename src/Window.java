import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class Window {

    public static void Window_run() {
        JFrame frame = new JFrame("Flaim92 vk killer");

        JPanel contentPane = new JPanel(new BorderLayout());
        frame.setSize(500, 400);
        JPanel panel = new JPanel();
        frame.add(panel);
        frame.setPreferredSize(new Dimension(500, 400));

        // Панель для фона
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image image = new ImageIcon("image.jpg").getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        contentPane.add(backgroundPanel, BorderLayout.CENTER);

        // Консоль
        JTextArea console = new JTextArea();
        console.setSize(300, 200);
        console.setPreferredSize(new Dimension(300, 200));
        console.setForeground(Color.GREEN);
        console.setEditable(false);
        backgroundPanel.add(console);




        // Кнопка выхода
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                OpenAccounts.setIsStopRequested(true);
                OpenAccounts.closeDriver();
                ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "clean_stop.bat");
                try {
                    pb.start();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }


                // Ждем, пока процесс завершится


                try {
                    ProcessKiller.killProcess("geckodriver.exe");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        backgroundPanel.add(exitButton, BorderLayout.SOUTH);

        frame.setContentPane(contentPane);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Перенаправление вывода консоли в JTextArea
        OutputStream out = new OutputStream() {
            @Override
            public void write(int b) {
                console.append(String.valueOf((char) b));
            }
        };
        System.setOut(new PrintStream(out, true));
        System.setErr(new PrintStream(out, true));

        // Приветственное сообщение
        System.out.println("Test ver.0.3");



    }
}