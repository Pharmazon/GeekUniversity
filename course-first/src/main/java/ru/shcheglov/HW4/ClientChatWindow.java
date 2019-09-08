package ru.shcheglov.HW4;

import javax.swing.*;
import java.awt.*;

public class ClientChatWindow extends JFrame {

    private JTextArea textArea;
    private JTextField textField;
    private final String TITLE = "Chat by Alexey Shcheglov";
    private final String BUTTONTEXT = "Send";
    private final String GREETING = "Welcome to chat!";

    public static void main(String[] args) {
        new ClientChatWindow();
    }

    public ClientChatWindow() {
        createGUI();
    }

    private void createGUI() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle(TITLE);
        setBounds(500,200,500,600);

        JButton sendButton = new JButton(BUTTONTEXT);
        sendButton.setPreferredSize(new Dimension(70,30));
        sendButton.addActionListener(e -> sendMessage());

        JLabel label = new JLabel(GREETING);
        add(label, BorderLayout.NORTH);

        textField = new JTextField();
        textField.addActionListener(e -> sendMessage());

        textArea = new JTextArea();
        JScrollPane scroll = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setFont(new Font("Arial", Font.ITALIC, 15));
        textArea.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        add(scroll, BorderLayout.CENTER);

        JPanel footerPanel = new JPanel(new BorderLayout());
        footerPanel.add(textField, BorderLayout.CENTER);
        footerPanel.add(sendButton, BorderLayout.EAST);
        add(footerPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void sendMessage() {
        textArea.append("You: " + textField.getText() + "\n");
        textField.setText("");
        textField.grabFocus();
    }
}
