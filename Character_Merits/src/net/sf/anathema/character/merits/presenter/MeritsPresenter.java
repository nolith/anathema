package net.sf.anathema.character.merits.presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Icon;

import net.sf.anathema.character.library.intvalue.IRemovableTraitView;
import net.sf.anathema.character.library.trait.presenter.TraitPresenter;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;
import net.sf.anathema.character.merits.definition.IMeritTemplate;
import net.sf.anathema.character.merits.model.IMerit;
import net.sf.anathema.character.merits.model.IMeritListener;
import net.sf.anathema.character.merits.model.IMeritsModel;
import net.sf.anathema.character.merits.view.IMeritsView;
import net.sf.anathema.character.merits.view.IMeritsViewProperties;
import net.sf.anathema.character.merits.view.MeritBoxEditor;
import net.sf.anathema.character.merits.view.MeritDisplayer;
import net.sf.anathema.character.merits.view.MeritInternationalizer;
import net.sf.anathema.character.merits.view.MeritListRenderer;
import net.sf.anathema.character.merits.view.MeritTextCompiler;
import net.sf.anathema.character.presenter.util.I18nComparator;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.framework.presenter.view.IButtonControlledComboEditView;
import net.sf.anathema.lib.collection.IdentityMapping;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.lib.resources.IResources;

public class MeritsPresenter implements Presenter {

	private final IMeritsModel model;
	private final IMeritsView configurationView;
	private final IResources resources;
	private final IdentityMapping<IDefaultTrait, IRemovableTraitView<?>> viewsByMerit = new IdentityMapping<IDefaultTrait, IRemovableTraitView<?>>();
	private final Map<String, IMeritTemplate> templatesByDisplayName = new HashMap<String, IMeritTemplate>();
	private final MeritInternationalizer internationalizer;
	private IButtonControlledComboEditView<Object> controlView;
	private String meritDescription = "";
	private MeritDisplayer displayer;

	public MeritsPresenter(IResources resources, IMeritsModel model,
			IMeritsView view) {
		this.resources = resources;
		this.model = model;
		this.configurationView = view;
		this.internationalizer = new MeritInternationalizer(resources);
		this.model.addMeritListener(new IMeritListener() {
			@Override
			public void meritAdded(IMerit merit) {
				addMeritView(merit);
				updateAvailableMeritList();
			}

			@Override
			public void meritRemoved(IMerit merit) {
				removeMeritView(merit);
				updateAvailableMeritList();
			}
		});
		this.displayer = new MeritDisplayer(internationalizer);
	}

	@Override
	public void initPresentation() {
		Icon addIcon = new BasicUi(resources).getAddIcon();
		controlView = configurationView.addMeritsSelectionView(
				resources.getString("MeritsView.SelectionCombo.Label"), //$NON-NLS-1$
				new MeritListRenderer(displayer), new MeritBoxEditor(displayer), addIcon);
		controlView.addEditChangedListener(new ObjectValueListener<String>() {
			@Override
			public void valueChanged(String newMeritDescription) {
				meritDescription = newMeritDescription;
			}
		});
		controlView.addButtonListener(new ObjectValueListener<Object>() {
			@Override
			public void valueChanged(Object newValue) {
				IMeritTemplate meritType = getMeritType(newValue);
				String description = meritDescription.equals("") ? null : meritDescription;
				if (meritType == null) {
					model.addMerit(newValue.toString(), description);
				} else {
					model.addMerit(meritType, description);
				}
				controlView.clear();
			}
		});
		for (IMeritTemplate template : model.getAllAvailableMeritTemplates()) {
			templatesByDisplayName.put(internationalizer.getDisplayName(template), template);
		}
		configurationView.initGui(new IMeritsViewProperties() {
			@Override
			public String getMeritTitle() {
				return resources.getString("AdvantagesView.Merits.Title");
			}
		});
		updateAvailableMeritList();
	}
	
	private void updateAvailableMeritList() {
		controlView.setObjects(getSortedMerits());
	}

	private IMeritTemplate[] getSortedMerits() {
		IMeritTemplate[] meritTemplates = model.getAllAvailableMeritTemplates();
		Arrays.sort(meritTemplates, new I18nComparator(resources, internationalizer.getPrefix()));
		return meritTemplates;
	}
	
	private IMeritTemplate getMeritType(Object anObject) {
	    if (anObject instanceof IMeritTemplate) {
	      return (IMeritTemplate) anObject;
	    }
	    String displayName = anObject.toString();
	    IMeritTemplate template = templatesByDisplayName.get(displayName);
	    if (template != null) {
	      return template;
	    }
	    return model.getTemplateByName(displayName);
	}
	
	public void setAllowRemoveCreationMerits(boolean allowed) {
	    for (IDefaultTrait merit : viewsByMerit.getAllKeys()) {
	      if (merit.getCalculationValue() > 0) {
	        IRemovableTraitView<?> view = viewsByMerit.get(merit);
	        view.setButtonEnabled(allowed && merit.getMinimalValue() == 0);
	      }
	    }
	  }

	private synchronized void addMeritView(final IMerit merit) {
		Icon deleteIcon = new BasicUi(resources).getRemoveIcon();
		String meritString = new MeritTextCompiler(displayer).compileDisplayedText(merit);
		IRemovableTraitView<?> meritView = configurationView.addMeritView(deleteIcon, meritString,
				merit.getCurrentValue(), merit.getMaximalValue());
		new TraitPresenter(merit, meritView).initPresentation();
		meritView.addButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.removeMerit(merit);
			}
		});
		viewsByMerit.put(merit, meritView);
	}

	private synchronized void removeMeritView(IDefaultTrait background) {
		IRemovableTraitView<?> view = viewsByMerit.get(background);
		viewsByMerit.remove(background);
		view.delete();
	}
}
