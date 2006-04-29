package net.sf.anathema.character.generic.impl.magic.persistence.builder.test;

import net.sf.anathema.character.generic.impl.magic.persistence.builder.prerequisite.GenericTraitPrerequisiteBuilder;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.lib.testing.BasicTestCase;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Element;

public class GenericTraitPrerequisiteBuilderTest extends BasicTestCase {
  private GenericTraitPrerequisiteBuilder builder = new GenericTraitPrerequisiteBuilder();

  public void testTraitPrerequisiteBuilder() throws Exception {
    String xml = "<trait  value=\"3\"/>";//$NON-NLS-1$
    Element rootElement = DocumentUtilities.read(xml).getRootElement();
    IGenericTrait trait = builder.build(rootElement, AbilityType.Larceny);
    assertEquals(AbilityType.Larceny, trait.getType());
    assertEquals(3, trait.getCurrentValue());
  }
}