package net.sf.anathema.character.main.presenter.overview;

import net.sf.anathema.character.main.view.labelledvalue.ILabelledAlotmentView;
import net.sf.anathema.hero.points.overview.ISpendingModel;

public class AlotmentSubPresenter implements IOverviewSubPresenter {

  private final ILabelledAlotmentView view;
  private final ISpendingModel model;
  private final FontParameterSetter setter;

  public AlotmentSubPresenter(ISpendingModel model, ILabelledAlotmentView view) {
    this.model = model;
    this.view = view;
    this.setter = new FontParameterSetter(model, view);
  }

  @Override
  public void update() {
    view.setValue(model.getValue());
    view.setAlotment(model.getAllotment());
    setter.setFontParameters();
  }
}