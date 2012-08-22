package net.sf.anathema.character.merits.view;

import net.sf.anathema.character.library.util.ProxyComboBoxEditor;

public class MeritBoxEditor extends ProxyComboBoxEditor {
  private MeritDisplayer displayer;

  public MeritBoxEditor(MeritDisplayer displayer) {
    this.displayer = displayer;
  }

  @Override
  public void setItem(Object anObject) {
    super.setItem(displayer.getDisplayObject(anObject));
  }
}