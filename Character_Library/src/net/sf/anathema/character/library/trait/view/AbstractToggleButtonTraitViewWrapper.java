package net.sf.anathema.character.library.trait.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.GridDialogPanel;
import net.disy.commons.swing.layout.grid.IDialogComponent;
import net.sf.anathema.character.library.intvalue.IIconToggleButtonProperties;
import net.sf.anathema.framework.value.IconToggleButton;
import net.sf.anathema.lib.control.booleanvalue.IBooleanValueChangedListener;

public abstract class AbstractToggleButtonTraitViewWrapper extends AbstractTraitViewWrapper {

  private final IconToggleButton button;
  private final IIconToggleButtonProperties properties;
  private JPanel traitViewPanel;
  private JPanel innerViewPanel;

  public AbstractToggleButtonTraitViewWrapper(ITraitView view, IIconToggleButtonProperties properties, boolean selected) {
    super(view);
    this.properties = properties;
    this.button = new IconToggleButton(properties.createStandardIcon(), properties.createUnselectedIcon());
    setButtonState(selected, true);
    button.setToolTipText(properties.getToolTipText());

  }

  public void setButtonState(boolean selected, boolean enabled) {
    button.setIconSet(properties.createStandardIcon(), properties.createUnselectedIcon());
    button.setEnabled(enabled);
    button.setSelected(selected);
  }

  public void addComponents(JPanel viewPanel) {
    this.traitViewPanel = viewPanel;

  }

  public void delete() {
    getInnerView().delete();
    traitViewPanel.remove(innerViewPanel);
    traitViewPanel.remove(button.getComponent());
    traitViewPanel.revalidate();
  }

  public void addComponents(GridDialogPanel abilityPanel) {
    abilityPanel.add(new IDialogComponent() {
      public int getColumnCount() {
        return 2;
      }

      public void fillInto(JPanel panel, int columnCount) {
        addComponents(panel);
      }
    });
  }

  public void addButtonSelectedListener(final IBooleanValueChangedListener listener) {
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        listener.valueChanged(!button.isSelected());
      }
    });
  }

  protected IconToggleButton getButton() {
    return button;
  }

  protected void addInnerView(JPanel panel) {
    this.innerViewPanel = new JPanel(new GridDialogLayout(2, false));
    getInnerView().addComponents(innerViewPanel);
    panel.add(innerViewPanel, GridDialogLayoutData.FILL_HORIZONTAL);
  }
}