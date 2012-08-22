package net.sf.anathema.character.merits.definition;

import net.sf.anathema.character.generic.traits.ITraitType;

public interface IMeritTemplate extends ITraitType {
	String getType();
	
	boolean allowMultiplePurchases();
	
	boolean[] getLegalValues();
	
	int getMaxValue();
	
	IMeritConstraint[] getConstraints();
}
