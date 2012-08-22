package net.sf.anathema.character.merits;

import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersister;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersisterFactory;
import net.sf.anathema.character.merits.persistence.MeritsModelPersister;
import net.sf.anathema.framework.messaging.IMessaging;

public class MeritsPersisterFactory implements IAdditionalPersisterFactory{
	@Override
	public IAdditionalPersister createPersister(IMessaging messaging) {
	  return new MeritsModelPersister();
	}
}
