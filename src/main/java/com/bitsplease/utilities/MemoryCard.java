package com.bitsplease.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.bitsplease.dbms.Table;

public class MemoryCard {
  public static void save(Table table) {
    String filepath = ".\\src\\main\\resources\\SavedTables";
    FileOutputStream fos;
    ObjectOutputStream oos;
    try {
      fos = new FileOutputStream(new File(filepath, table.getName()));
      oos = new ObjectOutputStream(fos);
      oos.writeObject(table);
      oos.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static Object load(String name) {
    String filepath = ".\\src\\main\\resources\\SavedTables";
    FileInputStream fis;
    ObjectInputStream ois;
    try {
      fis = new FileInputStream(new File(filepath, name));
      ois = new ObjectInputStream(fis);
      Object obj = ois.readObject();
      ois.close();
      return obj;
    } catch (FileNotFoundException e) {
      System.out.println("Error 404: Not found");
    } catch (IOException e) {
      System.out.println("IO Exception");
    } catch (ClassNotFoundException e) {
      System.out.println("Class not found");
    }
    return null;
  }
}
