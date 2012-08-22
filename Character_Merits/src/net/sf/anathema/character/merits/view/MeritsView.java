package net.sf.anathema.character.merits.view;

import static net.disy.commons.swing.layout.grid.GridDialogLayoutDataFactory.createHorizontalSpanData;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.Icon;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.sf.anathema.character.library.intvalue.IRemovableTraitView;
import net.sf.anathema.character.library.trait.view.RearButtonTraitViewWrapper;
import net.sf.anathema.character.library.trait.view.SimpleTraitView;
import net.sf.anathema.framework.presenter.view.AbstractInitializableContentView;
import net.sf.anathema.framework.presenter.view.ButtonControlledComboEditView;
import net.sf.anathema.framework.presenter.view.IButtonControlledComboEditView;
import net.sf.anathema.framework.presenter.view.ITextFieldComboBoxEditor;
import net.sf.anathema.framework.value.IntegerViewFactory;

public class MeritsView extends AbstractInitializableContentView<IMeritsViewProperties> implements IMeritsView {
	private final JPanel meritSelectionPanel = new JPanel(new GridDialogLayout(3, false));
	private final JPanel meritDisplayPanel = new JPanel(new GridDialogLayout(2, false));
	private final IntegerViewFactory guiConfiguration;

	public MeritsView(IntegerViewFactory intValueDisplayFactory) {
	  this.guiConfiguration = intValueDisplayFactory;
	}

	@Override
	public void createContent(JPanel content, IMeritsViewProperties properties) {
	  content.setLayout(new FlowLayout(FlowLayout.LEFT));
	  JPanel innerPanel = new JPanel(new GridDialogLayout(2, false));
	  content.add(innerPanel);
	  JPanel meritPanel = createMeritPanel(properties.getMeritTitle());
	  innerPanel.add(meritPanel, createHorizontalSpanData(2, GridDialogLayoutData.FILL_HORIZONTAL));
	}

	private JPanel createMeritPanel(String title) {
	  JPanel panel = new JPanel(new BorderLayout());
	  panel.add(meritSelectionPanel, BorderLayout.CENTER);
	  panel.add(meritDisplayPanel, BorderLayout.SOUTH);
	  return panel;
	}

	@Override
	public IButtonControlledComboEditView<Object> addMeritsSelectionView(String labelText,
	                                                                    ListCellRenderer meritRenderer,
	                                                                    ITextFieldComboBoxEditor meritEditor,
	                                                                    Icon addIcon) {
	  ButtonControlledComboEditView<Object> objectSelectionView = new MeritSelectionView<Object>(addIcon, labelText,
	          meritRenderer, meritEditor);
	  meritSelectionPanel.add(objectSelectionView.getComponent());
	  return objectSelectionView;
	}

	@Override
	public IRemovableTraitView<SimpleTraitView> addMeritView(Icon deleteIcon, String labelText, int value,
	                                                              int max) {
	  SimpleTraitView view = new SimpleTraitView(guiConfiguration, labelText, value, max);
	  RearButtonTraitViewWrapper<SimpleTraitView> meritView = new RearButtonTraitViewWrapper<SimpleTraitView>(view,
	          deleteIcon);
	  meritView.addComponents(meritDisplayPanel);
	  return meritView;
	}
}
