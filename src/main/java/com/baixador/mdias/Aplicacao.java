package com.baixador.mdias;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;

public class Aplicacao extends JFrame {
    private JTextArea logArea;
    private JButton startButton;

    private WebDriver driver;

    public Aplicacao() {
        setTitle("Automação com Selenium");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setBackground(Color.BLACK);
        logArea.setForeground(Color.GREEN);
        logArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(logArea);

        PrintStream printStream = new PrintStream(new CustomOutputStream(logArea));
        System.setOut(printStream);
        System.setErr(printStream);

        startButton = new JButton("Iniciar");
        startButton.setBackground(Color.DARK_GRAY);
        startButton.setForeground(Color.WHITE);
        startButton.setFocusPainted(false);
        startButton.setFont(new Font("Arial", Font.BOLD, 14));
        startButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        startButton.addActionListener(new StartButtonListener());

        add(scrollPane, BorderLayout.CENTER);
        add(startButton, BorderLayout.SOUTH);
    }

    private class StartButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    executeAutomation();
                }
            }).start();
        }
    }

    private void executeAutomation() {
        try {
            System.out.println("Iniciando baixador...");

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");

            driver = new ChromeDriver(options);

            System.out.println("Navegando para Youtube...");
            driver.get("https://www.youtube.com/");

        } catch (Exception ex) {
            System.err.println("Erro durante a automação: " + ex.getMessage());
            ex.printStackTrace(System.err);
        } finally {
            if (driver != null) {
                driver.quit();
            }
            System.out.println("\nBaixador finalizado.");
        }
    }
}

