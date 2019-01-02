package com.bitsplease.gui;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.bitsplease.dbms.Table;
import com.bitsplease.dbms.TableProcessing;

@SuppressWarnings("serial")
public class GuiTable extends JPanel {
  private JTable jtable;

  public GuiTable(Table table) {
    TableProcessing.rotate(table.getList());
    // columnNames are on the first list of the table now
    String[] columnNames = new String[table.getList().get(0).size()];
    for (int i = 0; i < columnNames.length; i++) {
      columnNames[i] = table.getList().get(0).get(i);
    }
    // Data is stored at the tables second list and after
    String data[][] = new String[table.getList().size()
        - 1][columnNames.length];
    for (int i = 0; i < table.getList().size() - 1; i++) {
      for (int j = 0; j < columnNames.length; j++) {
        data[i][j] = table.getList().get(i + 1).get(j);
      }
    }
    // Rotate table back to normal
    TableProcessing.rotate(table.getList());
    jtable = new JTable(data, columnNames);
    JScrollPane scrollPane = new JScrollPane(jtable);
    jtable.setPreferredScrollableViewportSize(new Dimension(500, 70));
    jtable.setFillsViewportHeight(true);
    add(scrollPane);
  // this.add(jtable);
  }

}
