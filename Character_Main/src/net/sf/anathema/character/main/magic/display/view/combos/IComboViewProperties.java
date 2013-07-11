package net.sf.anathema.character.main.magic.display.view.combos;

import net.sf.anathema.hero.magic.display.MagicLearnProperties;
import net.sf.anathema.lib.file.RelativePath;

public interface IComboViewProperties extends MagicLearnProperties {

  RelativePath getFinalizeButtonIcon();

  String getFinalizeButtonToolTip();

  String getAvailableComboCharmsLabel();

  String getComboedCharmsLabel();

  RelativePath getClearButtonIcon();

  String getClearButtonToolTip();

  RelativePath getCancelEditButtonIcon();

  String getFinalizeButtonEditToolTip();

  String getCancelButtonEditToolTip();
}