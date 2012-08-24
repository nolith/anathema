package net.sf.anathema.character.merits.overview;

import net.sf.anathema.character.impl.model.advance.models.AbstractIntegerValueModel;
import net.sf.anathema.character.merits.model.MeritsExperiencePointCostCalculator;

public class MeritsExperiencedOverviewModel extends AbstractIntegerValueModel {

	private final MeritsExperiencePointCostCalculator meritCalculator;
	
	public MeritsExperiencedOverviewModel(MeritsExperiencePointCostCalculator calculator) {
		super("Experience", "Merits");
		this.meritCalculator = calculator;
	}

	@Override
	public Integer getValue() {
		return meritCalculator.calculateCost();
	}

}
