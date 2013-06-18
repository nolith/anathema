package net.sf.anathema;

import net.sf.anathema.character.generic.template.HeroTemplate;
import net.sf.anathema.character.main.model.concept.CharacterConcept;
import net.sf.anathema.character.main.model.concept.CharacterConceptFetcher;
import net.sf.anathema.character.main.model.traits.TraitMap;
import net.sf.anathema.character.main.model.traits.TraitModelFetcher;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.model.charm.ICharmConfiguration;

public class CharacterHolder {
  private ICharacter character;

  public void setCharacter(ICharacter character) {
    this.character = character;
  }

  public ICharmConfiguration getCharms() {
    return character.getCharms();
  }

  public HeroTemplate getCharacterTemplate() {
    return character.getTemplate();
  }

  public CharacterConcept getCharacterConcept() {
    return CharacterConceptFetcher.fetch(character);
  }

  public TraitMap getTraitConfiguration() {
    return TraitModelFetcher.fetch(character);
  }

  public ICharacter getCharacter() {
    return character;
  }
}