package net.sf.anathema.character.merits.view;

import net.sf.anathema.character.merits.definition.CustomMeritTemplate;
import net.sf.anathema.character.merits.definition.IMeritTemplate;
import net.sf.anathema.lib.resources.IResources;

public class MeritInternationalizer {
  public static final String MERIT_TYPE_RESOURCE_KEY_PREFIX = "MeritType.Name."; //$NON-NLS-1$
  private final IResources resources;

  public MeritInternationalizer(IResources resources) {
    this.resources = resources;
  }

  public String getDisplayName(IMeritTemplate template) {
    if (template instanceof CustomMeritTemplate) {
      return template.getId();
    }
    return resources.getString(MERIT_TYPE_RESOURCE_KEY_PREFIX + template.getId());
  }

  public String getPrefix() {
    return MERIT_TYPE_RESOURCE_KEY_PREFIX;
  }
}