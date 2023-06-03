package _2;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        new Window("Akvin0");
    }
}

class Window extends JFrame {
    public Window(String username) {
        setTitle("App");
        setSize(500, 500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        add(new Panel(username));
    }
    class Panel extends JPanel {
        String username;
        public Panel(String username) {
            this.username = username;
        }
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            drawGrid(g,11, 100, 45, 300, 250);
            drawWords(g, new String[]{"А", "Б", "В", "Г", "Д", "Е", "Ж", "З", "И", "К"}, 104, 25, 0);
            // PS: Чтобы выровнять цифры под значение 10, пришлось использовать drawWords, а не drawNums
//            drawNums(g, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 58, 62);
            drawWords(g, new String[]{" 1", " 2", " 3", " 4", " 5", " 6", " 7", " 8", " 9", "10"}, 58, 63, 1);
            whoIsUser(g, username, new Color(255, 30, 0, 255), 10, 450);
        }
    }
    private void drawGrid(Graphics gr, double step, int x, int y, int x1, int y1) {
        // PS: Мне нужно, чтобы сетка рисовалась от координат x, y до координат x1, y1
        double stepX = (x1 - x) / (step - 1);
        double stepY = (y1 - y) / (step - 1);
        for (int i = 0; i < step; i++) {
            gr.drawLine((int) (stepX * i) + x, y, (int) (stepX * i) + x, y1);
            gr.drawLine(x, (int) (stepY * i) + y, x1, (int) (stepY * i) + y);
        }
    }
//    private void drawNums(Graphics gr, int[] nums, int x, int y) {
//        for (int i = 0; i < nums.length; i++) {
//            gr.drawString("" + nums[i], x, y);
//            y += 20;
//        }
//    }
    private void drawWords(Graphics gr, String[] words, int x, int y, int action) {
        if (action == 0) {
            for (int i = 0; i < words.length; i++) {
                gr.drawString(words[i], x, y);
                x += 20;
            }
        } else {
            for (int i = 0; i < words.length; i++) {
                gr.drawString(words[i], x, y);
                y += 20;
            }
        }
    }
    private void whoIsUser(Graphics gr, String username, Color color, int x, int y) {
        gr.setColor(color);
        gr.drawString(username, x, y);
    }
}
