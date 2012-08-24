package net.sf.anathema.character.model.additional;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.presenter.overview.IOverviewModel;
import net.sf.anathema.character.presenter.overview.IValueModel;

public interface IIntegralAdditionalModel extends IAdditionalModel {
	IOverviewModel[] getCreationOverviewModels();
	
	IValueModel<Integer>[] getExperiencedOverviewModels();
}
