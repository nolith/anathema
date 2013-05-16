package net.sf.anathema.character.impl.persistence.charm;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmConfiguration;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.character.model.charm.ILearningCharmGroup;
import net.sf.anathema.lib.compare.IdentifiedComparator;
import net.sf.anathema.lib.util.Identified;
import org.dom4j.Element;

import java.util.*;

import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.ATTRIB_EXPERIENCE_LEARNED;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.ATTRIB_NAME;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.ATTRIB_TYPE;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_CHARM;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_CHARMGROUP;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_SPECIAL;

public class CharmSaver {
  private ICharmConfiguration charmConfiguration;
  private final ISpecialCharmPersister specialPersister;

  public CharmSaver(ICharmConfiguration charmConfiguration) {
    this.charmConfiguration = charmConfiguration;
    this.specialPersister = createSpecialCharmPersister(charmConfiguration);
  }

  private ISpecialCharmPersister createSpecialCharmPersister(ICharmConfiguration charmConfiguration) {
    return new SpecialCharmPersister(charmConfiguration.getSpecialCharms(), charmConfiguration.getCharmIdMap());
  }

  public void saveCharms(Identified type, Element charmsElement) {
    ILearningCharmGroup[] charmGroups = charmConfiguration.getCharmGroups(type);
    Arrays.sort(charmGroups, new IdentifiedComparator());
    for (ILearningCharmGroup group : charmGroups) {
      if (group.hasLearnedCharms()) {
        saveCharmGroup(charmsElement, group, specialPersister, charmConfiguration);
      }
    }
  }

  private void saveCharmGroup(Element charmsElement, ILearningCharmGroup group, ISpecialCharmPersister specialPersister,
                              ICharmConfiguration charmConfiguration) {
    Element groupElement = charmsElement.addElement(TAG_CHARMGROUP);
    groupElement.addAttribute(ATTRIB_NAME, group.getId());
    groupElement.addAttribute(ATTRIB_TYPE, group.getCharacterType().getId());
    HashMap<String, Boolean> isExperiencedLearned = new HashMap<>();
    List<ICharm> charms = new ArrayList<>();
    for (ICharm charm : group.getCreationLearnedCharms()) {
      isExperiencedLearned.put(charm.getId(), false);
      charms.add(charm);
    }
    for (ICharm charm : group.getExperienceLearnedCharms()) {
      isExperiencedLearned.put(charm.getId(), true);
      charms.add(charm);
    }
    Collections.sort(charms, new IdentifiedComparator());
    for(ICharm charm : charms) {
      saveCharm(charmConfiguration, specialPersister, groupElement, charm, isExperiencedLearned.get(charm.getId()));
    }
  }

  private void saveCharm(ICharmConfiguration charmConfiguration, ISpecialCharmPersister specialPersister,
                         Element groupElement, ICharm charm, boolean experienceLearned) {
    Element charmElement = groupElement.addElement(TAG_CHARM);
    charmElement.addAttribute(ATTRIB_NAME, charm.getId());
    charmElement.addAttribute(ATTRIB_EXPERIENCE_LEARNED, String.valueOf(experienceLearned));
    ISpecialCharmConfiguration specialCharmConfiguration = charmConfiguration.getSpecialCharmConfiguration(charm);
    if (specialCharmConfiguration != null) {
      Element specialElement = charmElement.addElement(TAG_SPECIAL);
      specialPersister.saveConfiguration(specialElement, specialCharmConfiguration);
    }
  }
}