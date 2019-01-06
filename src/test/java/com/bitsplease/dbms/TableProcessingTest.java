package com.bitsplease.dbms;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

public class TableProcessingTest {
  ArrayList<ArrayList<String>> randomList, sortedList;

  @Test
  public void testCodeToNum() {
    Assert.assertEquals("failure - wrong result",
        TableProcessing.codeToNum("A"), 0);
  }

  @Test
  public void testNumToCode() {
    // Num to code must be the inverse function of CodeToNum
    Random rand = new Random();
    int num = rand.nextInt(10000);
    Assert.assertEquals("failure - wrong result",
        TableProcessing.codeToNum(TableProcessing.numToCode(num)), num);
  }

  @Test
  public void testSortNumber() {
    randomList = new ArrayList<ArrayList<String>>();
    sortedList = new ArrayList<ArrayList<String>>();
    ArrayList<String> random = new ArrayList<String>();
    random.add("1");
    random.add("4");
    random.add("3");
    random.add("2");
    randomList.add(random);
    Table randomTable = new Table("random", randomList);
    ArrayList<String> sorted = new ArrayList<String>();
    sorted.add("1");
    sorted.add("2");
    sorted.add("3");
    sorted.add("4");
    sortedList.add(sorted);
    Table sortedTable = new Table("sorted", sortedList);
    TableProcessing.sort(randomTable, 0, 0, randomList.get(0).size() - 1);
    Assert.assertEquals("failure - not sorted", randomTable.getList(),
        sortedTable.getList());

  }

  @Test
  public void testSortString() {
    randomList = new ArrayList<ArrayList<String>>();
    sortedList = new ArrayList<ArrayList<String>>();
    ArrayList<String> random = new ArrayList<String>();
    random.add("dab");
    random.add("a");
    random.add("c");
    random.add("b");
    randomList.add(random);
    ArrayList<String> sorted = new ArrayList<String>();
    sorted.add("a");
    sorted.add("b");
    sorted.add("c");
    sorted.add("dab");
    sortedList.add(sorted);
    Table randomTable = new Table("random", randomList);
    Table sortedTable = new Table("sorted", sortedList);
    TableProcessing.sort(randomTable, 0, 0, randomList.get(0).size() - 1);
    Assert.assertEquals("failure - not sorted", randomTable.getList(),
        sortedTable.getList());
  }

}
