package net.sf.anathema.character.merits.overview;

import net.sf.anathema.character.impl.model.advance.models.AbstractSpendingModel;
import net.sf.anathema.character.merits.model.MeritsBonusPointCostCalculator;

public class MeritsCreationOverviewModel extends AbstractSpendingModel {

	private final MeritsBonusPointCostCalculator meritCalculator;
	private final int creationPoints;

	public MeritsCreationOverviewModel(MeritsBonusPointCostCalculator meritsCalculator,
			int creationPoints) {
		super("Advantages", "Merits"); //$NON-NLS-1$ //$NON-NLS-2$
	    this.meritCalculator = meritsCalculator;
	    this.creationPoints = creationPoints;
	}

	@Override
	public Integer getValue() {
	    return meritCalculator.getSpentFreeDots();
	}

	@Override
	public int getSpentBonusPoints() {
	    return meritCalculator.getBonusPointCost();
	}

	@Override
	public int getAlotment() {
		return creationPoints;
	}

}
