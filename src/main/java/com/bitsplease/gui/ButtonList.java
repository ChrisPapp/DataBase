package com.bitsplease.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.bitsplease.menus.AbstractMenu;

@SuppressWarnings("serial")
public class ButtonList extends JPanel {

  private TreeMap<Integer, JButton> buttons;
  private AbstractMenu menu;
  private int i;

  public ButtonList(String[] options, final AbstractMenu menu) {
    this.menu = menu;
    buttons = new TreeMap<Integer, JButton>();
    for (i = 0; i < options.length; i++) {
      buttons.put(i + 1, new JButton(options[i]));
    }
    Set<Entry<Integer, JButton>> set = buttons.entrySet();
    Iterator<Entry<Integer, JButton>> iterator = set.iterator();
    while (iterator.hasNext()) {
      final Map.Entry<Integer, JButton> button = (Entry<Integer, JButton>) iterator
          .next();
      this.add(button.getValue());
      button.getValue().addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent arg0) {
          menu.hideButtons();
          menu.setChoice(button.getKey());
          menu.performAction();

        }

      });
    }
  }

}
