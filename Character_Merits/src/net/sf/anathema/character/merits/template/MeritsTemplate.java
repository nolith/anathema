package net.sf.anathema.character.merits.template;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.template.additional.IGlobalAdditionalTemplate;
import net.sf.anathema.lib.util.Identificate;

public class MeritsTemplate extends Identificate implements IGlobalAdditionalTemplate{
	public static String ID = "Merits";
	
	private final ICharacterGenerics generics;
	
	public MeritsTemplate(ICharacterGenerics generics) {
	   super(ID);
	   this.generics = generics;
	}
	
	public int getFreeMeritDotAlotment(ITemplateType type) {
		ICharacterTemplate template = generics.getTemplateRegistry().getTemplate(type);
		// TODO: reusing background point value for now, in a full implementation
		// Merits should have their own creation points allocation
		return template.getCreationPoints().getBackgroundPointCount();
	}
}
