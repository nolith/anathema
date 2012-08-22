package net.sf.anathema.character.merits.model;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitContext;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.trait.DefaultTrait;
import net.sf.anathema.character.library.trait.IValueChangeChecker;
import net.sf.anathema.character.library.trait.rules.ITraitRules;
import net.sf.anathema.character.merits.definition.CustomMeritTemplate;
import net.sf.anathema.character.merits.view.MeritInternationalizer;
import net.sf.anathema.lib.resources.IResources;

public class Merit extends DefaultTrait implements IMerit  {
	private final String description;

	public Merit(String description,
			ITraitRules traitRules,
			ITraitContext traitContext, 
			boolean[] legalValues) {
		super(traitRules, traitContext, getValueChecker(legalValues));
		this.description = description;
	}
	
	private static IValueChangeChecker getValueChecker(final boolean[] legalValues) {
		return new IValueChangeChecker() {

			@Override
			public boolean isValidNewValue(int value) {
				if (value == 0) {
					return true;
				}
				return legalValues[value - 1];
			}
			
		};
	}

	@Override
    public String getDescription() {
		return description;
	}
	
	@Override
    public String getName(IResources resources) {
		ITraitType type = getType();
		String backgroundId = type.getId();
	    if (type instanceof CustomMeritTemplate) {
	      return backgroundId;
	    }
	    return resources.getString(MeritInternationalizer.MERIT_TYPE_RESOURCE_KEY_PREFIX +
	    		type.getId());
	}
}
