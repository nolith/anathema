package net.sf.anathema.character.impl.model;

import net.sf.anathema.character.change.ChangeAnnouncer;
import net.sf.anathema.character.change.ChangeFlavor;
import net.sf.anathema.character.model.charm.ICombo;
import net.sf.anathema.character.model.charm.IComboConfigurationListener;

public final class CharacterChangeComboListener implements IComboConfigurationListener {

  private ChangeAnnouncer announcer;

  public CharacterChangeComboListener(ChangeAnnouncer announcer) {
    this.announcer = announcer;
  }

  @Override
  public void editEnded() {
    // Nothing to do
  }

  @Override
  public void editBegun(ICombo combo) {
    // Nothing to do
  }

  @Override
  public void comboDeleted(ICombo combo) {
    announcer.announceChangeOf(ChangeFlavor.UNSPECIFIED);
  }

  @Override
  public void comboChanged(ICombo combo) {
    announcer.announceChangeOf(ChangeFlavor.UNSPECIFIED);
  }

  @Override
  public void comboAdded(ICombo combo) {
    announcer.announceChangeOf(ChangeFlavor.UNSPECIFIED);
  }
}