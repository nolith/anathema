package net.sf.anathema.character.merits;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.data.IExtensibleDataSetProvider;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.merits.definition.IMeritCache;

public class MeritsModelFactory implements IAdditionalModelFactory{
	
	@Override
	public IAdditionalModel createModel(IAdditionalTemplate additionalTemplate, ICharacterModelContext context, IExtensibleDataSetProvider data) {
	  return new MeritsAdditionalModel(additionalTemplate, context, data.getDataSet(IMeritCache.class));
	}
}
