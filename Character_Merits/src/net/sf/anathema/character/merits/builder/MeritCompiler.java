package net.sf.anathema.character.merits.builder;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import net.sf.anathema.character.generic.data.IExtensibleDataSet;
import net.sf.anathema.character.generic.data.IExtensibleDataSetCompiler;
import net.sf.anathema.character.merits.definition.IMeritTemplate;
import net.sf.anathema.initialization.ExtensibleDataSetCompiler;
import net.sf.anathema.lib.resources.ResourceFile;

@ExtensibleDataSetCompiler
public class MeritCompiler implements IExtensibleDataSetCompiler {
	private static final String Merit_File_Recognition_Pattern = "Merits.*\\.xml";
	private final List<Document> meritFileList = new ArrayList<Document>();
	private final MeritBuilder builder = new MeritBuilder();
	private final SAXReader reader = new SAXReader();
	private final MeritCache cache = new MeritCache();
	
	@Override
	public String getName() {
		return "Merits";
	}

	@Override
	public String getRecognitionPattern() {
		return Merit_File_Recognition_Pattern;
	}

	@Override
	public void registerFile(ResourceFile resource) throws Exception {
		try {
		    meritFileList.add(reader.read(resource.getURL()));
		} catch (DocumentException e) {
			throw new MeritException(resource.getURL().toExternalForm(), e);
		}
	}

	@Override
	public IExtensibleDataSet build() {
		for (Document document : meritFileList) {
			IMeritTemplate[] merits = builder.buildMerits(document);
			for (IMeritTemplate merit : merits) {
				cache.addMerit(merit);
			}
		}
		return cache;
	}
}
