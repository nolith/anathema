package net.sf.anathema.character.merits.definition;

import net.sf.anathema.character.generic.traits.types.ITraitTypeVisitor;

public class MeritTemplate implements IMeritTemplate {

	private final String name;
	private final String type;
	private final boolean allowMultiple;
	private final boolean[] legalValues;
	private final IMeritConstraint[] constraints;
	
	public MeritTemplate(String name, String type, boolean allowMultiple, boolean[] values,
			IMeritConstraint... constraints) {
		this.name = name;
		this.type = type;
		this.allowMultiple = allowMultiple;
		this.legalValues = values;
		this.constraints = constraints;
	}
	
	@Override
	public String getId() {
		return name;
	}

	@Override
	public String getType() {
		return type;
	}
	
	@Override
	public boolean allowMultiplePurchases() {
		return allowMultiple;
	}

	@Override
	public boolean[] getLegalValues() {
		return legalValues;
	}
	
	@Override
	public final void accept(ITraitTypeVisitor visitor) {
		// TODO
	}

	@Override
	public int getMaxValue() {
		for (int index = legalValues.length; index >= 1; index--) {
			if (legalValues[index - 1] == true) {
				return index;
			}
		}
		return 0;
	}

	@Override
	public IMeritConstraint[] getConstraints() {
		return constraints;
	}

}
