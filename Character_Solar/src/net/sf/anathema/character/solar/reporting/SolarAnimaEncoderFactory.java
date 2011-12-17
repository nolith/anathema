package net.sf.anathema.character.solar.reporting;

import net.sf.anathema.character.reporting.common.encoder.*;
import net.sf.anathema.character.reporting.sheet.common.anima.AbstractAnimaEncoderFactory;
import net.sf.anathema.character.reporting.sheet.common.anima.AnimaTableEncoder;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.pdf.BaseFont;

public class SolarAnimaEncoderFactory extends AbstractAnimaEncoderFactory {

  public SolarAnimaEncoderFactory(IResources resources, BaseFont basefont, BaseFont symbolBaseFont) {
    super(resources, basefont, symbolBaseFont);
  }

  @Override
  protected IPdfTableEncoder getAnimaTableEncoder() {
    return new AnimaTableEncoder(getResources(), getBaseFont(), getFontSize());
  }
}
