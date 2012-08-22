package net.sf.anathema.character.merits.view;

import net.sf.anathema.character.merits.model.IMerit;

import static java.text.MessageFormat.format;

public class MeritTextCompiler {

  private MeritDisplayer displayer;

  public MeritTextCompiler(MeritDisplayer displayer) {
    this.displayer = displayer;
  }

  public String compileDisplayedText(IMerit merit) {
    Object meritName = displayer.getDisplayObject(merit);
    String description = merit.getDescription() != null ? format(" ({0})", merit.getDescription()) : "";
    return meritName + description;
  }
}
