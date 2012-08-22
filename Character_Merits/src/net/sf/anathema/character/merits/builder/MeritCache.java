package net.sf.anathema.character.merits.builder;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.merits.definition.IMeritCache;
import net.sf.anathema.character.merits.definition.IMeritTemplate;


public class MeritCache implements IMeritCache {

	private List<IMeritTemplate> merits = new ArrayList<IMeritTemplate>();
	
	public void addMerit(IMeritTemplate merit) {
		merits.add(merit);
	}
	
	@Override
	public IMeritTemplate[] getAllMerits() {
		return merits.toArray(new IMeritTemplate[0]);
	}

	@Override
	public IMeritTemplate getTemplateByName(String name) {
		for (IMeritTemplate merit : merits) {
			if (merit.getId().equals(name)) {
				return merit;
			}
		}
		return null;
	}

}
