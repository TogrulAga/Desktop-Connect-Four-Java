package four;

import javax.swing.*;
import java.awt.*;

public class ConnectFour extends JFrame {
    private String currentPlayer = "X";
    private final JButton[][] buttons = new JButton[6][7];
    private boolean gameFinished = false;

    public ConnectFour() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 750);
        setVisible(true);
        setLayout(new BorderLayout());
        setTitle("Connect Four");

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(6, 7));

        for (int i = 6; i > 0; i--) {
            for (int j = 0; j < 7; j++) {
                JButton button = new JButton(" ");
                button.setFocusPainted(false);
                button.setName("Button" + (char) ('A' + j) + "" + (char) ('0' + i));
                button.setBackground(Color.GRAY);
                button.setFont(new Font("Courier", Font.PLAIN, 16));
                button.addActionListener(e -> {
                    if (gameFinished) {
                        return;
                    }

                    JButton src = (JButton) e.getSource();

                    JButton emptyCell = getEmptyCellInColumn(src);

                    if (emptyCell != null) {
                        emptyCell.setText(currentPlayer);
                        checkPlayerWon();
                        currentPlayer = currentPlayer.equals("X") ? "O" : "X";
                    }
                });
                buttons[i - 1][j] = button;
                topPanel.add(button);
            }
        }

        add(topPanel, BorderLayout.CENTER);

        // Create panel for placing reset button
        JPanel bottomPanel = new JPanel();
        JButton resetButton = new JButton("Reset");
        resetButton.setName("ButtonReset");
        resetButton.addActionListener(e -> resetButtonStates());
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(resetButton, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void checkPlayerWon() {
        // Check rows
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length - 3; j++) {
                if (currentPlayer.equals(buttons[i][j].getText()) && currentPlayer.equals(buttons[i][j + 1].getText()) &&
                        currentPlayer.equals(buttons[i][j + 2].getText()) && currentPlayer.equals(buttons[i][j + 3].getText())) {
                    buttons[i][j].setBackground(Color.CYAN);
                    buttons[i][j + 1].setBackground(Color.CYAN);
                    buttons[i][j + 2].setBackground(Color.CYAN);
                    buttons[i][j + 3].setBackground(Color.CYAN);

                    gameFinished = true;
                    return;
                }
            }
        }

        // Check columns
        for (int i = 0; i < buttons.length - 3; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                if (currentPlayer.equals(buttons[i][j].getText()) && currentPlayer.equals(buttons[i + 1][j].getText()) &&
                        currentPlayer.equals(buttons[i + 2][j].getText()) && currentPlayer.equals(buttons[i + 3][j].getText())) {
                    buttons[i][j].setBackground(Color.CYAN);
                    buttons[i + 1][j].setBackground(Color.CYAN);
                    buttons[i + 2][j].setBackground(Color.CYAN);
                    buttons[i + 3][j].setBackground(Color.CYAN);

                    gameFinished = true;
                    return;
                }
            }
        }

        // Check main diagonal
        for (int i = 0; i < buttons.length - 3; i++) {
            for (int j = 0; j < buttons[i].length - 3; j++) {
                if (currentPlayer.equals(buttons[i][j].getText()) && currentPlayer.equals(buttons[i + 1][j + 1].getText()) &&
                        currentPlayer.equals(buttons[i + 2][j + 2].getText()) && currentPlayer.equals(buttons[i + 3][j + 3].getText())) {
                    buttons[i][j].setBackground(Color.CYAN);
                    buttons[i + 1][j + 1].setBackground(Color.CYAN);
                    buttons[i + 2][j + 2].setBackground(Color.CYAN);
                    buttons[i + 3][j + 3].setBackground(Color.CYAN);

                    gameFinished = true;
                    return;
                }
            }
        }

        // Check side diagonal
        for (int i = 0; i < buttons.length - 3; i++) {
            for (int j = buttons[i].length - 1; j >= 3; j--) {
                if (currentPlayer.equals(buttons[i][j].getText()) && currentPlayer.equals(buttons[i + 1][j - 1].getText()) &&
                        currentPlayer.equals(buttons[i + 2][j - 2].getText()) && currentPlayer.equals(buttons[i + 3][j - 3].getText())) {
                    buttons[i][j].setBackground(Color.CYAN);
                    buttons[i + 1][j - 1].setBackground(Color.CYAN);
                    buttons[i + 2][j - 2].setBackground(Color.CYAN);
                    buttons[i + 3][j - 3].setBackground(Color.CYAN);

                    gameFinished = true;
                    return;
                }
            }
        }
    }

    private void resetButtonStates() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                buttons[i][j].setText(" ");
                buttons[i][j].setBackground(Color.GRAY);
                gameFinished = false;
                currentPlayer = "X";
            }
        }
    }

    private JButton getEmptyCellInColumn(JButton src) {
        int column = -1;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (buttons[i][j].equals(src)) {
                    column = j;
                    break;
                }
            }
        }

        for (int i = 0; i < 6; i++) {
            if (" ".equals(buttons[i][column].getText())) {
                return buttons[i][column];
            }
        }

        return null;
    }
}