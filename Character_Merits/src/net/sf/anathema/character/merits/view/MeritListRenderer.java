package net.sf.anathema.character.merits.view;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JComponent;
import javax.swing.JList;
import java.awt.Component;

public class MeritListRenderer extends DefaultListCellRenderer {
  private static final long serialVersionUID = 1L;
  private final MeritDisplayer displayer;

  public MeritListRenderer(MeritDisplayer displayer) {
    this.displayer = displayer;
  }

  @Override
  public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
    JComponent renderer = (JComponent) super.getListCellRendererComponent(list, displayer.getDisplayObject(value), index, isSelected, cellHasFocus);
    // source information can go here
    return renderer;
  }
}