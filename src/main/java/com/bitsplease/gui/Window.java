package com.bitsplease.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Window extends JFrame {

  private ConsolePanel consolePanel;

  public Window() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("Bitsplease Database");
    setResizable(false);
    setSize(new Dimension(1280, 800));
    setLayout(new FlowLayout());
    //consolePanel = new ConsolePanel();
    //add(consolePanel);
    setVisible(true);
    // Move the Window to the center of the screen
    setLocationRelativeTo(null);
  }

  public ConsolePanel getConsolePanel() {
    return consolePanel;
  }

  public void setConsolePanel(ConsolePanel consolePanel) {
    this.consolePanel = consolePanel;
  }

}
