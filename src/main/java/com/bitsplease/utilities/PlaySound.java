
package com.bitsplease.utilities;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class PlaySound {

  private final int BUFFER_SIZE = 128000;
  private AudioInputStream audioStream;
  private AudioFormat audioFormat;
  private SourceDataLine sourceLine;

  public void play(String fileName) {
    InputStream in = new BufferedInputStream(
        ClassLoader.getSystemResourceAsStream(fileName + ".wav"));

    try {

      audioStream = AudioSystem.getAudioInputStream(in);
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(1);
    }

    audioFormat = audioStream.getFormat();

    DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
    try {
      sourceLine = (SourceDataLine) AudioSystem.getLine(info);
      sourceLine.open(audioFormat);
    } catch (LineUnavailableException e) {
      e.printStackTrace();
      System.exit(1);
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(1);
    }

    sourceLine.start();

    int nBytesRead = 0;
    byte[] abData = new byte[BUFFER_SIZE];
    while (nBytesRead != -1) {
      try {
        nBytesRead = audioStream.read(abData, 0, abData.length);
      } catch (IOException e) {
        e.printStackTrace();
      }
      if (nBytesRead >= 0) {
        @SuppressWarnings("unused")
        int nBytesWritten = sourceLine.write(abData, 0, nBytesRead);
      }
    }

    sourceLine.drain();
    sourceLine.close();
  }

}
