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
  public void testÍumToCode() {
    // Num to code must be the inverse function of CodeToNum
    Random rand = new Random();
    int num = rand.nextInt(10000);
    Assert.assertEquals("failure - wrong result",
        TableProcessing.codeToNum(TableProcessing.numToCode(num)), num);
  }

  @Test
  public void testSort() {
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
    Assert.assertEquals("failure - not sorted", randomList, sortedList);
    randomList = new ArrayList<ArrayList<String>>();
    sortedList = new ArrayList<ArrayList<String>>();
    random.set(0, "b");
    random.set(0, "c");
    random.set(0, "a");
    random.set(0, "d");
    randomList.add(random);
    sorted.set(0, "a");
    sorted.set(0, "b");
    sorted.set(0, "c");
    sorted.set(0, "d");
    sortedList.add(sorted);
    TableProcessing.sort(randomList, 0, 0, randomList.get(0).size() - 1);
    Assert.assertEquals("failure - not sorted", randomList, sortedList);
    
  }
  
}
