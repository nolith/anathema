package net.sf.anathema.character.impl.model.creation.bonus.ability;

import net.sf.anathema.character.generic.template.creation.ICreationPoints;
import net.sf.anathema.character.impl.model.advance.models.AbstractSpendingModel;
import net.sf.anathema.character.library.trait.IFavorableTraitCostCalculator;

public class CasteAbilityPickModel extends AbstractSpendingModel {

  private final IFavorableTraitCostCalculator abilityCalculator;
  private final ICreationPoints creationPoints;

  public CasteAbilityPickModel(IFavorableTraitCostCalculator abilityCalculator, ICreationPoints creationPoints) {
    super("Abilities", "CastePick"); //$NON-NLS-1$ //$NON-NLS-2$
    this.abilityCalculator = abilityCalculator;
    this.creationPoints = creationPoints;
  }

  @Override
  public int getSpentBonusPoints() {
    return 0;
  }

  @Override
  public Integer getValue() {
    return abilityCalculator.getCastePicksSpent();
  }

  @Override
  public int getAlotment() {
    return creationPoints.getAbilityCreationPoints().getCasteTraitCount();
  }
}