package net.sf.anathema.character.merits;

import net.sf.anathema.character.generic.additionaltemplate.AdditionalModelType;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelBonusPointCalculator;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelExperienceCalculator;
import net.sf.anathema.character.generic.additionaltemplate.NullAdditionalModelBonusPointCalculator;
import net.sf.anathema.character.generic.additionaltemplate.NullAdditionalModelExperienceCalculator;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.merits.definition.IMeritCache;
import net.sf.anathema.character.merits.model.IMeritsModel;
import net.sf.anathema.character.merits.model.MeritsModel;
import net.sf.anathema.lib.control.IChangeListener;

public class MeritsAdditionalModel implements IAdditionalModel {

	private final IMeritsModel model;
	private final IAdditionalTemplate additionalTemplate;

	public MeritsAdditionalModel(IAdditionalTemplate additionalTemplate, ICharacterModelContext context,
			IMeritCache cache) {
	  this.additionalTemplate = additionalTemplate;
	  this.model = new MeritsModel(context, cache);
	}
	  
	@Override
	public String getTemplateId() {
		return additionalTemplate.getId();
	}
	
	public IMeritsModel getModel() {
		return model;
	}

	@Override
	public AdditionalModelType getAdditionalModelType() {
		return AdditionalModelType.Miscellaneous;
	}

	@Override
	public void addChangeListener(IChangeListener listener) {
		model.addChangeListener(listener);
	}
	
	@Override
	public IAdditionalModelBonusPointCalculator getBonusPointCalculator() {
		return new NullAdditionalModelBonusPointCalculator();
	}

	@Override
	public IAdditionalModelExperienceCalculator getExperienceCalculator() {
		return new NullAdditionalModelExperienceCalculator();
	}
}
