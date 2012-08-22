package net.sf.anathema.character.merits.builder;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.impl.traits.EssenceTemplate;
import net.sf.anathema.character.merits.definition.IMeritTemplate;
import net.sf.anathema.character.merits.definition.MeritTemplate;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Document;
import org.dom4j.Element;

public class MeritBuilder {

	private final static String TAG_MERIT = "merit";
	private final static String ATTRIB_NAME = "name";
	private final static String ATTRIB_TYPE = "type";
	private final static String ATTRIB_VALUES = "values";
	private final static String ATTRIB_ALLOW_MULTIPLE_PURCHASES = "allowMultiplePurchases";
	
	public IMeritTemplate[] buildMerits(Document document) {
		List<IMeritTemplate> merits = new ArrayList<IMeritTemplate>();
		
		for (Object meritObject : document.getRootElement().elements(TAG_MERIT)) {
			Element meritElement = (Element) meritObject;
			String name = meritElement.attributeValue(ATTRIB_NAME);
			String type = meritElement.attributeValue(ATTRIB_TYPE);
			String valueString = meritElement.attributeValue(ATTRIB_VALUES);
			boolean[] values = parseValues(valueString);
			
			//optional parameters
			boolean allowMultiplePurchases = ElementUtilities.getBooleanAttribute(meritElement, ATTRIB_ALLOW_MULTIPLE_PURCHASES, false);
			
			merits.add(new MeritTemplate(name, type, allowMultiplePurchases, values));
		}
		
		return merits.toArray(new IMeritTemplate[0]);
	}
	
	private boolean[] parseValues(String valueString) {
		boolean[] values = new boolean[EssenceTemplate.SYSTEM_ESSENCE_MAX];
		for (String valueSubstring : valueString.split(",")) {
			try {
				int index = Integer.parseInt(valueSubstring) - 1;
				values[index] = true;
				continue;
			} catch (Exception e) {
				
			}
			String[] rangeStrings = valueSubstring.split("-");
			if (rangeStrings.length == 2) {
				int first = Integer.parseInt(rangeStrings[0]) - 1;
				int last = Integer.parseInt(rangeStrings[1]) - 1;
				for (int i = first; i <= last; i++) {
					values[i] = true;
				}
				continue;
			}
			System.out.println("Unknown merit value: " + valueSubstring);
		}
		return values;
	}

}
