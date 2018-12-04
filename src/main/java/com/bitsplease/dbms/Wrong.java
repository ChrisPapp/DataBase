package com.bitsplease.dbms;
import java.awt.Toolkit;
class Wrong {
  private String soundFilename;
  private  boolean isDefault = false;
  public Wrong() {
    this("default");
    isDefault = true;
  }

  public Wrong(String sound) {
    this.soundFilename = sound;
  }
  public void printWrong(String x) {
    System.out.println(x);
    if (isDefault) {
      Toolkit.getDefaultToolkit().beep();

    } else {
      PlaySound pl = new PlaySound();
      pl.play(this.soundFilename);
    }
  }
  public void setSoundFilename(String sound) {
    this.soundFilename = sound;
  }
  public String getSoundFilename() {
    return this.soundFilename;
  }
}
