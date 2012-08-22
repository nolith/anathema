package net.sf.anathema.character.merits.definition;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;

public interface IMeritConstraint {
	boolean acceptCharacter(ICharacterModelContext context);
}
