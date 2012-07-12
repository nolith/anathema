package net.sf.anathema.character.impl.model.traits.creation;

import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.abilities.IGroupedTraitType;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.trait.favorable.FriendlyIncrementChecker;
import net.sf.anathema.character.library.trait.favorable.IFavorableTrait;
import net.sf.anathema.character.library.trait.favorable.IIncrementChecker;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;

import java.util.ArrayList;
import java.util.List;

public class CasteIncrementChecker implements IIncrementChecker {

  private final int maxCasteCount;
  private final ICoreTraitConfiguration traitConfiguration;
  private final ITraitType[] traitTypes;

  public static IIncrementChecker createCasteAbilityIncrementChecker(
      ICharacterTemplate template,
      ICoreTraitConfiguration traitConfiguration) {
    if (template.getCreationPoints().getAbilityCreationPoints().hasCasteTraitPicks()) {
		int maxCasteAbilityCount = template.getCreationPoints().getAbilityCreationPoints().getCasteTraitCount();
	    List<ITraitType> abilityTypes = new ArrayList<ITraitType>();
	    for (IGroupedTraitType traitType : template.getAbilityGroups()) {
	      abilityTypes.add(traitType.getTraitType());
	    }
	    return new CasteIncrementChecker(
	        maxCasteAbilityCount,
	        abilityTypes.toArray(new ITraitType[abilityTypes.size()]),
	        traitConfiguration);
    } else {
    	return new FriendlyIncrementChecker();
    }
  }

  public CasteIncrementChecker(
      int maxCasteCount,
      ITraitType[] traitTypes,
      ICoreTraitConfiguration traitConfiguration) {
    this.maxCasteCount = maxCasteCount;
    this.traitTypes = traitTypes;
    this.traitConfiguration = traitConfiguration;
  }

  @Override
  public boolean isValidIncrement(int increment) {
    int count = 0;
    for (IFavorableTrait trait : getAllTraits()) {
      if (trait.getFavorization().isCasteOption() && trait.getFavorization().isCaste()) {
        count++;
      }
    }
    return count + increment <= maxCasteCount;
  }

  private IFavorableTrait[] getAllTraits() {
    return traitConfiguration.getFavorableTraits(traitTypes);
  }
}