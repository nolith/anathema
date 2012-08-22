package net.sf.anathema.character.merits.definition;

import net.sf.anathema.character.generic.data.IExtensibleDataSet;

public interface IMeritCache extends IExtensibleDataSet {
	IMeritTemplate[] getAllMerits();
	
	IMeritTemplate getTemplateByName(String name);
}
