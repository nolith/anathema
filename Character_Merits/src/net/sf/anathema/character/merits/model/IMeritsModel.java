package net.sf.anathema.character.merits.model;

import net.sf.anathema.character.merits.definition.IMeritTemplate;
import net.sf.anathema.lib.control.IChangeListener;

public interface IMeritsModel {
	
	IMerit addMerit(IMeritTemplate template, String description);
	
	IMerit addMerit(String templateName, String description);
	
	void removeMerit(IMerit merit);
	
	IMeritTemplate[] getAllAvailableMeritTemplates();
	
	void addChangeListener(IChangeListener listener);

	void addMeritListener(IMeritListener IMeritListener);

	IMerit[] getCurrentMerits();

	IMeritTemplate getTemplateByName(String templateName);
}
