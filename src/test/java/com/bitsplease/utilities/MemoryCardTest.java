package com.bitsplease.utilities;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import com.bitsplease.dbms.Table;


public class MemoryCardTest {

  @Test
  public void testSaveLoad() {
    String name = "LoadTest";
    ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
    ArrayList<String> column = new ArrayList<String>();
    column.add("a");
    column.add("b");
    column.add("c");
    column.add("dab");
    list.add(column);
    Table table = new Table(name, list);
    MemoryCard.save(table);
    Table loadedTable = (Table) MemoryCard.load(name);
    Assert.assertEquals("Load failed", table, loadedTable);   
  }

}
