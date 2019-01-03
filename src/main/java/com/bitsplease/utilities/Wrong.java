package com.bitsplease.utilities;

import java.awt.Toolkit;

import javax.swing.JOptionPane;

import com.bitsplease.dbms.StartMain;

public class Wrong {
  private String soundFilename;
  private boolean isDefault = false;

  public Wrong() {
    this("default");
    isDefault = true;
  }

  public Wrong(String sound) {
    this.soundFilename = sound;
  }

  public void printWrong(String x) {
    if (isDefault) {
      Toolkit.getDefaultToolkit().beep();

    } else {
      PlaySound pl = new PlaySound();
      pl.play(this.soundFilename);
    }
    JOptionPane.showMessageDialog(StartMain.getWindow(), x, "ERROR!!!",
        JOptionPane.ERROR_MESSAGE);
  }

  public void setSoundFilename(String sound) {
    this.soundFilename = sound;
  }

  public String getSoundFilename() {
    return this.soundFilename;
  }
}
