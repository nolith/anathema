package net.sf.anathema.character.merits.model;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelBonusPointCalculator;

public class MeritsBonusPointCostCalculator implements IAdditionalModelBonusPointCalculator{

	private final IMeritsModel model;
	
	public MeritsBonusPointCostCalculator(IMeritsModel model) {
		this.model = model;
	}
	
	@Override
	public void recalculate() {
		// TODO Auto-generated method stub
		
	}
	
	public int getSpentFreeDots() {
		return 0;
	}

	@Override
	public int getBonusPointCost() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getBonusPointsGranted() {
		return 0;
	}

}
