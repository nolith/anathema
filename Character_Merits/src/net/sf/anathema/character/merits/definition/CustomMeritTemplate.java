package net.sf.anathema.character.merits.definition;

import net.sf.anathema.character.generic.impl.traits.EssenceTemplate;

public class CustomMeritTemplate extends MeritTemplate {

	private static final String CUSTOM_TYPE = "Custom";
	
	public CustomMeritTemplate(String name) {
		super(name, CUSTOM_TYPE, false, getDefaultLegalValues());	
	}
	
	private static boolean[] getDefaultLegalValues() {
		boolean[] values = new boolean[EssenceTemplate.SYSTEM_ESSENCE_MAX];
		for (int i = 0; i != values.length; i++) {
			values[i] = true;
		}
		return values;
	}
}
