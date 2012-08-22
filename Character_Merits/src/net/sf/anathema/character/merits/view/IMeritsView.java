package net.sf.anathema.character.merits.view;

import javax.swing.Icon;
import javax.swing.ListCellRenderer;

import net.sf.anathema.character.library.intvalue.IRemovableTraitView;
import net.sf.anathema.framework.presenter.view.IButtonControlledComboEditView;
import net.sf.anathema.framework.presenter.view.IInitializableContentView;
import net.sf.anathema.framework.presenter.view.ITextFieldComboBoxEditor;

public interface IMeritsView extends IInitializableContentView<IMeritsViewProperties> {
	  IButtonControlledComboEditView<Object> addMeritsSelectionView(String labelText, ListCellRenderer renderer,
	                                                               ITextFieldComboBoxEditor meritEditor,
	                                                               Icon addIcon);

	  IRemovableTraitView< ? > addMeritView(Icon deleteIcon, String labelText, int creationValue, int max);
	}
