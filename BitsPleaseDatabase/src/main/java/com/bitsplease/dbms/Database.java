package com.bitsplease.dbms;

import java.util.ArrayList;
import java.util.Scanner;

public class Database {
  // Fill in our Database with fields and data
  private String name;
  private ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
  private Scanner scanner = new Scanner(System.in);
  public Database() {
    inputName();
    inputFields();
    inputData();
  }

  private void inputName() {
    System.out.println("Enter the name of this Table");
    setName(scanner.nextLine());
  }

  public void inputFields() {
    String category;
    do {
      System.out.println(
          "PLEASE ENTER THE FIELD #" + (list.size() + 1) + " OF YOUR DATABASE");
      System.out.println("OR IF YOU ARE DONE JUST ENTER EXIT");

      category = scanner.nextLine();
      if (!(DatabaseProcessing.equalsExit(category))) { // If category is not
                                                        // "exit"
        list.add(new ArrayList<String>()); // Add a new List
        list.get(list.size() - 1).add(category); // Add category to the last
                                                 // position
      } else
        break;

    } while (true);
  }

  public void inputData() {
    String data;
    System.out.println("PLEASE ENTER THE DATA IN YOUR DATABASE\n");
    while (true) {
      System.out.println("PRESS ENTER TO ADD A LINE OR ENTER 'EXIT' TO STOP");
      String answer = scanner.nextLine(); // Read user's answer
      if (!(DatabaseProcessing.equalsExit(answer))) {
        for (int field = 0; field < list.size(); field++) {
          System.out.println("ADD DATA TO: " + list.get(field).get(0));
          data = scanner.nextLine();
          System.out.println();
          list.get(field).add(data);
          // The first line has the field names
          System.out.println("Added data to:" + list.get(field).get(0));
          System.out.println(); // Empty Line
        }
      } else {
        System.out.println("DATA INSERTION STOPPED");
        break;
      }
    }
  }

  public ArrayList<ArrayList<String>> getList() {
    return list;
  }

  public void setList(ArrayList<ArrayList<String>> list) {
    this.list = list;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}

