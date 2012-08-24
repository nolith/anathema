package net.sf.anathema.character.merits.model;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelExperienceCalculator;

public class MeritsExperiencePointCostCalculator implements IAdditionalModelExperienceCalculator {

	private IMeritsModel model;
	
	public MeritsExperiencePointCostCalculator(IMeritsModel model) {
		this.model = model;
	}
	
	@Override
	public int calculateCost() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int calculateGain() {
		// TODO Auto-generated method stub
		return 0;
	}

}
