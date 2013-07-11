package net.sf.anathema.character.main.framework;

import net.sf.anathema.character.main.framework.data.IExtensibleDataSet;
import net.sf.anathema.character.main.template.ITemplateRegistry;
import net.sf.anathema.character.main.template.magic.ICharmProvider;
import net.sf.anathema.character.main.type.CharacterTypes;
import net.sf.anathema.initialization.ObjectFactory;
import net.sf.anathema.initialization.repository.DataFileProvider;

public interface HeroEnvironment {

  ITemplateRegistry getTemplateRegistry();

  ICharacterTemplateRegistryCollection getCharacterTemplateRegistries();

  ICharmProvider getCharmProvider();

  DataFileProvider getDataFileProvider();

  <T extends IExtensibleDataSet> T getDataSet(Class<T> set);

  ObjectFactory getObjectFactory();

  CharacterTypes getCharacterTypes();
}