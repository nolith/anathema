package net.sf.anathema.character.merits.persistence;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersister;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.trait.persistence.TraitPersister;
import net.sf.anathema.character.merits.model.IMerit;
import net.sf.anathema.character.merits.model.IMeritsModel;
import net.sf.anathema.lib.exception.PersistenceException;

import org.dom4j.Element;

public class MeritsModelPersister implements IAdditionalPersister{

	private static final String TAG_MERITS = "Merits"; //$NON-NLS-1$
	private static final String TAG_MERIT = "Merit"; //$NON-NLS-1$
	private static final String TAG_DESCRIPTION = "Description"; //$NON-NLS-1$
	private final TraitPersister persister = new TraitPersister();
	
	@Override
	public void save(Element parent, IAdditionalModel model) {
		Element meritsElement = parent.addElement(TAG_MERITS);
		IMeritsModel meritsModel = (IMeritsModel)model;
	    for (IMerit merit : meritsModel.getCurrentMerits()) {
	      ITraitType meritTemplate = merit.getType();
	      String meritId = meritTemplate.getId();
	      Element meritElement = persister.saveTrait(meritsElement, TAG_MERIT, merit);
	      meritElement.addCDATA(meritId);
	      
	      if (merit.getDescription() != null) {
	    	  Element descriptionElement = meritElement.addElement(TAG_DESCRIPTION);
	    	  descriptionElement.addCDATA(merit.getDescription());
	      }
	    }
	}

	@Override
	public void load(Element parent, IAdditionalModel model)
			throws PersistenceException {
		Element meritsElement = parent.element(TAG_MERITS);
	    if (meritsElement == null) {
	      return;
	    }
	    IMeritsModel meritsModel = (IMeritsModel)model;
	    for (Object meritObjectElement : meritsElement.elements()) {
	      Element meritElement = (Element) meritObjectElement;
	      loadMerit(meritsModel, meritElement);
	    }
	}
	
	private void loadMerit(IMeritsModel meritsModel, Element element) throws PersistenceException {
	    String meritId = element.getName();
	    if (meritId.equals(TAG_MERIT)) {
	      meritId = element.getText().trim();
	    }
	    Element descriptionElement = element.element(TAG_DESCRIPTION);
	    String description = descriptionElement != null ? descriptionElement.getText() : null;
	    IMerit merit = meritsModel.addMerit(meritId, description);
	    if (merit == null) {
	      throw new PersistenceException("Error reading Merit: " + meritId); //$NON-NLS-1$
	    }
	    persister.restoreTrait(element, merit);
	  }

}
