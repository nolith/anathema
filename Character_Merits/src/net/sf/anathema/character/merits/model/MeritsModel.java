package net.sf.anathema.character.merits.model;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.impl.traits.SimpleTraitTemplate;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.library.trait.rules.TraitRules;
import net.sf.anathema.character.merits.definition.CustomMeritTemplate;
import net.sf.anathema.character.merits.definition.IMeritCache;
import net.sf.anathema.character.merits.definition.IMeritConstraint;
import net.sf.anathema.character.merits.definition.IMeritTemplate;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.util.ObjectUtilities;

import org.jmock.example.announcer.Announcer;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

public class MeritsModel implements IMeritsModel {

	private final Announcer<IChangeListener> changeListeners = Announcer.to(IChangeListener.class);
	private final Announcer<IMeritListener> meritListeners = Announcer.to(IMeritListener.class);
	private final ICharacterModelContext context;
	private final IMeritCache cache;
	private final List<IMerit> merits = new ArrayList<IMerit>();
	
	public MeritsModel(ICharacterModelContext context, IMeritCache cache) {
		this.context = context;
		this.cache = cache;
	}

	@Override
	public void addChangeListener(IChangeListener listener) {
		changeListeners.addListener(listener);
	}
	
	@Override
	public void addMeritListener(IMeritListener listener) {
		meritListeners.addListener(listener);
	}
	
	@Override
	public IMerit[] getCurrentMerits() {
		return merits.toArray(new IMerit[0]);
	}

	@Override
	public IMerit addMerit(final IMeritTemplate meritTemplate, final String description) {
		Preconditions.checkNotNull(meritTemplate);
		IMerit existingMerit = getExistingMeritWithTemplate(meritTemplate, description); 
		if (existingMerit != null) {
			return null;
		}
		ITraitTemplate traitTemplate = SimpleTraitTemplate.createStaticLimitedTemplate( //$NON-NLS-1$
                0, meritTemplate.getMaxValue());
		TraitRules rules = new TraitRules(meritTemplate, traitTemplate, context.getTraitContext().getLimitationContext());
		IMerit merit = new Merit(description, rules, context.getTraitContext(), meritTemplate.getLegalValues());
		merits.add(merit);
		fireMeritAddedEvent(merit);
		return merit;
	}
	
	@Override
	public IMerit addMerit(String templateName, String description) {
		for (IMeritTemplate merit : cache.getAllMerits()) {
			if (merit.getId().equals(templateName)) {
				return addMerit(merit, description);
			}
		}
		return null;
	}

	@Override
	public void removeMerit(IMerit merit) {
		if (merits.contains(merit)) {
			merits.remove(merit);
			fireMeritRemovedEvent(merit);
		}
	}
	
	private void fireMeritAddedEvent(IMerit merit) {
		changeListeners.announce().changeOccurred();
		meritListeners.announce().meritAdded(merit);
	}

	private void fireMeritRemovedEvent(IMerit merit) {
		changeListeners.announce().changeOccurred();
		meritListeners.announce().meritRemoved(merit);
	}
	
	private IMerit getExistingMeritWithTemplate(final IMeritTemplate meritTemplate, final String description) {
		return Iterables.find(merits,new Predicate<IMerit>() {
			@Override
			public boolean apply(IMerit listMerit) {
				if (ObjectUtilities.equals(meritTemplate, listMerit.getType())) {
					return !meritTemplate.allowMultiplePurchases() ||
							(description == null && listMerit.getDescription() == null) ||
							(description != null && description.equals(listMerit.getDescription()));
				}
				return false;
			}
		},null);
	}

	@Override
	public IMeritTemplate[] getAllAvailableMeritTemplates() {
		List<IMeritTemplate> meritList = new ArrayList<IMeritTemplate>();
		for (IMeritTemplate template : cache.getAllMerits()) {
			if (!template.allowMultiplePurchases() &&
					getExistingMeritWithTemplate(template, null) != null) {
				continue;
			}
			boolean passesConstraints = true;
			for (IMeritConstraint constraint : template.getConstraints()) {
				if (!constraint.acceptCharacter(context)) {
					passesConstraints = false;
					break;
				}
			}
			if (passesConstraints) {
				meritList.add(template);
			}
		}
		return meritList.toArray(new IMeritTemplate[0]);
	}

	@Override
	public IMeritTemplate getTemplateByName(String templateName) {
		IMeritTemplate template = cache.getTemplateByName(templateName);
		if (template == null) {
			return new CustomMeritTemplate(templateName);
		}
		return template;
	}
}
