package net.sf.anathema.character.merits;

import net.sf.anathema.character.generic.additionaltemplate.AdditionalModelType;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelBonusPointCalculator;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelExperienceCalculator;
import net.sf.anathema.character.generic.additionaltemplate.NullAdditionalModelExperienceCalculator;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.merits.definition.IMeritCache;
import net.sf.anathema.character.merits.model.IMeritsModel;
import net.sf.anathema.character.merits.model.MeritsBonusPointCostCalculator;
import net.sf.anathema.character.merits.model.MeritsModel;
import net.sf.anathema.character.merits.overview.MeritsCreationOverviewModel;
import net.sf.anathema.character.merits.template.MeritsTemplate;
import net.sf.anathema.character.model.additional.IIntegralAdditionalModel;
import net.sf.anathema.character.presenter.overview.IOverviewModel;
import net.sf.anathema.lib.control.IChangeListener;

public class MeritsAdditionalModel implements IIntegralAdditionalModel {

	private final IMeritsModel model;
	private final ICharacterModelContext context;
	private final MeritsTemplate meritsTemplate;

	public MeritsAdditionalModel(IAdditionalTemplate additionalTemplate, ICharacterModelContext context,
			IMeritCache cache) {
	  this.meritsTemplate = (MeritsTemplate) additionalTemplate;
	  this.context = context;
	  this.model = new MeritsModel(context, cache);
	}
	  
	@Override
	public String getTemplateId() {
		return meritsTemplate.getId();
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
		return new MeritsBonusPointCostCalculator(model);
	}

	@Override
	public IAdditionalModelExperienceCalculator getExperienceCalculator() {
		return new NullAdditionalModelExperienceCalculator();
	}

	@Override
	public IOverviewModel[] getCreationOverviewModels() {
		return new IOverviewModel[] { new MeritsCreationOverviewModel((MeritsBonusPointCostCalculator) getBonusPointCalculator(),
				meritsTemplate.getFreeMeritDotAlotment(context.getBasicCharacterContext().getTemplateType())) };
	}

	@Override
	public IOverviewModel[] getExperiencedOverviewModels() {
		return new IOverviewModel[0];
	}
}
