package net.sf.anathema.character.impl.view.concept;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.TitledBorder;

import net.disy.commons.swing.action.SmartAction;
import net.disy.commons.swing.layout.GridDialogLayoutDataUtilities;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.IDialogComponent;
import net.sf.anathema.character.impl.view.EditButtonDialogPanel;
import net.sf.anathema.character.view.concept.ICharacterConceptAndRulesView;
import net.sf.anathema.character.view.concept.ICharacterConceptAndRulesViewProperties;
import net.sf.anathema.character.view.concept.IWillpowerConditionView;
import net.sf.anathema.framework.presenter.view.AbstractTabView;
import net.sf.anathema.lib.gui.gridlayout.DefaultGridDialogPanel;
import net.sf.anathema.lib.gui.gridlayout.IGridDialogPanel;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LabelTextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LineTextView;

public class CharacterConceptAndRulesView extends AbstractTabView<ICharacterConceptAndRulesViewProperties> implements
    ICharacterConceptAndRulesView {

  private final EditButtonDialogPanel characterConceptPanel = new EditButtonDialogPanel();
  private final IGridDialogPanel rulesPanel = new DefaultGridDialogPanel(false);

  public CharacterConceptAndRulesView() {
    super(false);
  }

  @Override
  protected void createContent(JPanel panel, ICharacterConceptAndRulesViewProperties properties) {
    characterConceptPanel.getContent().setBorder(new TitledBorder(properties.getConceptTitle()));
    rulesPanel.getContent().setBorder(new TitledBorder(properties.getRulesTitle()));
    panel.setLayout(new GridDialogLayout(1, false));
    panel.add(characterConceptPanel.getContent(), GridDialogLayoutDataUtilities.createHorizontalFillNoGrab());
    panel.add(rulesPanel.getContent(), GridDialogLayoutDataUtilities.createHorizontalFillNoGrab());
  }

  public <V> IObjectSelectionView<V> addObjectSelectionView(
      String labelText,
      V[] objects,
      ListCellRenderer renderer,
      boolean editable) {
    ObjectSelectionView<V> selectionView = new ObjectSelectionView<V>(labelText, renderer, editable, objects);
    selectionView.getComboBox().getEditor().getEditorComponent().setEnabled(true);
    selectionView.setDisabledLabelColor(Color.DARK_GRAY);
    selectionView.addComponents(characterConceptPanel, GridDialogLayoutDataUtilities.createHorizontalFillNoGrab());
    return selectionView;
  }

  public ITextView addLabelTextView(String labelText) {
    LineTextView lineTextView = new LineTextView(45);
    lineTextView.getTextComponent().setDisabledTextColor(Color.DARK_GRAY);
    LabelTextView labelView = new LabelTextView(labelText, lineTextView);
    labelView.setDisabledLabelColor(Color.DARK_GRAY);
    labelView.addTo(characterConceptPanel, false);
    return labelView;
  }

  public void addRulesLabel(final String labelText) {
    rulesPanel.add(new IDialogComponent() {
      public int getColumnCount() {
        return 1;
      }

      public void fillInto(JPanel panel, int columnCount) {
        panel.add(new JLabel(labelText));
      }
    });
  }

  public IWillpowerConditionView addWillpowerConditionView(final String headerLabelText) {
    WillpowerConditionView view = new WillpowerConditionView(headerLabelText);
    view.addComponents(characterConceptPanel);
    return view;
  }

  public JButton addAction(SmartAction action, int row) {
    return characterConceptPanel.addEditAction(action, row);
  }
}