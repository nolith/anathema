package net.sf.anathema.character.merits;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.library.intvalue.IntValueDisplayFactoryPrototype;
import net.sf.anathema.character.merits.presenter.MeritsPresenter;
import net.sf.anathema.character.merits.view.IMeritsView;
import net.sf.anathema.character.merits.view.MeritsView;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.resources.IResources;

public class MeritsViewFactory implements IAdditionalViewFactory{
	@Override
	public IView createView(IAdditionalModel model, final IResources resources, ICharacterType type) {
	  IMeritsView view = new MeritsView(IntValueDisplayFactoryPrototype.createWithMarkerForCharacterType(resources, type));
	  MeritsPresenter presenter = new MeritsPresenter(resources,
			  ((MeritsAdditionalModel) model).getModel(),
			  view);
	  presenter.initPresentation();
	  return view;
	}
}
