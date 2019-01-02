package com.bitsplease.gui;

import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

@SuppressWarnings("serial")
public class InputField extends Panel implements ActionListener {

  private JTextField textField;
  private String currentText = "1";

  public InputField() {
    textField = new JTextField(20);
    textField.addActionListener(this);
    this.add(textField);
  }

  @Override
  public void actionPerformed(ActionEvent arg0) {
    setCurrentText(textField.getText());
  }

  public String getCurrentText() {
    return currentText;
  }

  public void setCurrentText(String text) {
    this.currentText = text;
  }
}
