package com.bitsplease.dbms;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TableProcessingTest {
  ArrayList<ArrayList<String>> randomList, sortedList;

  @Before
  public void setupSort() {
    randomList = new ArrayList<ArrayList<String>>();
    sortedList = new ArrayList<ArrayList<String>>();
    ArrayList<String> random = new ArrayList<String>();
    random.add("1");
    random.add("4");
    random.add("3");
    random.add("2");
    randomList.add(random);
    ArrayList<String> sorted = new ArrayList<String>();
    sorted.add("1");
    sorted.add("2");
    sorted.add("3");
    sorted.add("4");
    sortedList.add(sorted);
    TableProcessing.sort(randomList, 0, 0, randomList.get(0).size() - 1);
  }

  @Test
  public void testCodeToNum() {
    Assert.assertEquals("failure - wrong result",
        TableProcessing.codeToNum("A"), 0);
  }

  @Test
  public void testSort() {
    Assert.assertEquals("failure - not sorted", randomList, sortedList);
  }

}
