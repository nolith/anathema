package net.sf.anathema.character.merits;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersisterFactory;
import net.sf.anathema.character.generic.framework.module.CharacterModule;
import net.sf.anathema.character.generic.framework.module.NullObjectCharacterModuleAdapter;
import net.sf.anathema.character.merits.template.MeritsTemplate;
import net.sf.anathema.lib.registry.IRegistry;

@CharacterModule
public class MeritsModule extends NullObjectCharacterModuleAdapter {

	  @Override
	  public void addAdditionalTemplateData(ICharacterGenerics characterGenerics) {
	    IRegistry<String, IAdditionalModelFactory> additionalModelFactoryRegistry = characterGenerics.getAdditionalModelFactoryRegistry();
	    String templateId = MeritsTemplate.ID;
	    additionalModelFactoryRegistry.register(templateId, new MeritsModelFactory());
	    IRegistry<String, IAdditionalViewFactory> additionalViewFactoryRegistry = characterGenerics.getAdditionalViewFactoryRegistry();
	    additionalViewFactoryRegistry.register(templateId, new MeritsViewFactory());
	    IRegistry<String, IAdditionalPersisterFactory> persisterFactory = characterGenerics.getAdditonalPersisterFactoryRegistry();
	    persisterFactory.register(templateId, new MeritsPersisterFactory());
	    characterGenerics.getGlobalAdditionalTemplateRegistry().add(new MeritsTemplate());
	  }
}
