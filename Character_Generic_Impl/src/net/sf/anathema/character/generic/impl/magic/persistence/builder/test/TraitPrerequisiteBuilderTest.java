package net.sf.anathema.character.generic.impl.magic.persistence.builder.test;

import net.disy.commons.core.testing.ExceptionConvertingBlock;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.prerequisite.TraitPrerequisiteBuilder;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.testing.BasicTestCase;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Element;

public class TraitPrerequisiteBuilderTest extends BasicTestCase {

  private TraitPrerequisiteBuilder builder = new TraitPrerequisiteBuilder();

  public void testTraitPrerequisiteBuilder() throws Exception {
    String xml = "<trait id=\"Larceny\" value=\"3\"/>";//$NON-NLS-1$
    Element rootElement = DocumentUtilities.read(xml).getRootElement();
    IGenericTrait trait = builder.build(rootElement);
    assertEquals(AbilityType.Larceny, trait.getType());
    assertEquals(3, trait.getCurrentValue());
  }

  public void testValueMissing() throws Exception {
    assertThrowsException(PersistenceException.class, new ExceptionConvertingBlock() {
      @Override
      public void executeExceptionThrowing() throws Exception {
        String xml = "<trait id=\"Larceny\" />";//$NON-NLS-1$
        Element rootElement = DocumentUtilities.read(xml).getRootElement();
        builder.build(rootElement);
      }
    });
  }
}