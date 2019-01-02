package com.bitsplease.gui;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ConsolePanel extends JPanel{
  public ConsolePanel() {
    JTextArea textArea = new JTextArea(1, 50);
    textArea.setEditable(false);
    PrintStream printStream = new PrintStream(new CustomOutputStream(textArea));
    System.setOut(printStream);
    System.setErr(printStream);
    add(textArea);
  }
  private class CustomOutputStream extends OutputStream {
    private JTextArea textArea;
     
    public CustomOutputStream(JTextArea textArea) {
        this.textArea = textArea;
    }
     
    @Override
    public void write(int b) throws IOException {
        // redirects data to the text area
        textArea.append(String.valueOf((char)b));
        // scrolls the text area to the end of data
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }
    
    
}
}
