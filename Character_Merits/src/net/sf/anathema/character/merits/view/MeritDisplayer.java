package net.sf.anathema.character.merits.view;

import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;
import net.sf.anathema.character.merits.definition.IMeritTemplate;


public class MeritDisplayer {
  private MeritInternationalizer internationalizer;

  public MeritDisplayer(MeritInternationalizer internationalizer) {
    this.internationalizer = internationalizer;
  }

  public Object getDisplayObject(Object anObject) {
    if (anObject instanceof IDefaultTrait) {
      anObject = ((IDefaultTrait) anObject).getType();
    }
    if (anObject instanceof IMeritTemplate) {
      return internationalizer.getDisplayName((IMeritTemplate) anObject);
    }
    return anObject;
  }
}