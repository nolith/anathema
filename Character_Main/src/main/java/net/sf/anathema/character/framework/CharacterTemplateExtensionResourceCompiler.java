package net.sf.anathema.character.framework;

import net.sf.anathema.framework.environment.ObjectFactory;
import net.sf.anathema.framework.environment.resources.ResourceFile;
import net.sf.anathema.hero.framework.data.ExtensibleDataSet;
import net.sf.anathema.hero.framework.data.IExtensibleDataSetCompiler;
import net.sf.anathema.hero.framework.data.IExtensibleDataSetProvider;
import net.sf.anathema.initialization.ExtensibleDataSetCompiler;

import java.util.ArrayList;
import java.util.List;

@ExtensibleDataSetCompiler
public class CharacterTemplateExtensionResourceCompiler implements IExtensibleDataSetCompiler {

  private static final String TEMPLATE_FILE_RECOGNITION_PATTERN = "(.+?)\\.template";
  private final List<ResourceFile> templateResources = new ArrayList<>();

  @SuppressWarnings("UnusedParameters")
  public CharacterTemplateExtensionResourceCompiler(ObjectFactory objectFactory, IExtensibleDataSetProvider provider) {
    //nothing to do
  }

  @Override
  public String getName() {
    return "Character template extensions";
  }

  @Override
  public String getRecognitionPattern() {
    return TEMPLATE_FILE_RECOGNITION_PATTERN;
  }

  @Override
  public void registerFile(ResourceFile resource) throws Exception {
    templateResources.add(resource);
  }

  @Override
  public ExtensibleDataSet build() {
    return new CharacterTemplateExtensionResourceCache(templateResources);
  }
}
