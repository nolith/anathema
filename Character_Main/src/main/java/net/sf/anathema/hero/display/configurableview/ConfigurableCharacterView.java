package net.sf.anathema.hero.display.configurableview;

import net.sf.anathema.framework.value.IntValueView;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public interface ConfigurableCharacterView {

  ITextView addLineView(String labelText);

  ITextView addAreaView(String labelText);

  Tool addEditAction();

  MultiComponentLine addMultiComponentLine();

  <T> ObjectSelectionView<T> addSelectionView(String label, AgnosticUIConfiguration<T> uiConfiguration);

  IntValueView addDotSelector(String label, int maxValue);
}