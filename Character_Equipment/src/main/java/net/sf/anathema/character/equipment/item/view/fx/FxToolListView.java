package net.sf.anathema.character.equipment.item.view.fx;

import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import net.miginfocom.layout.CC;
import net.sf.anathema.character.equipment.item.view.ToolListView;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.util.Closure;
import net.sf.anathema.platform.fx.ConfigurableListCellFactory;
import net.sf.anathema.platform.tool.FxButtonTool;
import org.tbee.javafx.scene.layout.MigPane;

import java.util.List;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.fillWithoutInsets;

public class FxToolListView<T> implements ToolListView<T> {

  private ListView<T> list = new ListView<>();
  private MigPane buttonPanel = new MigPane();
  private MigPane content = new MigPane(fillWithoutInsets().wrapAfter(1));

  public FxToolListView() {
    list.getStyleClass().add("tool-list");
    buttonPanel.getStyleClass().add("tool-buttons");
    content.add(list, new CC().push().grow().span());
    content.add(buttonPanel);
    list.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
  }

  @Override
  public void setObjects(final List<T> items) {
    list.setItems(FXCollections.observableArrayList(items));
  }

  @Override
  public void addListSelectionListener(final Runnable listener) {
    list.getSelectionModel().selectedItemProperty().addListener((observableValue, t, t2) -> listener.run());
  }

  @Override
  public void addListSelectionListener(final Closure<T> listener) {
    list.getSelectionModel().selectedItemProperty().addListener((observableValue, t, newSelection) -> listener.execute(newSelection));
  }

  @Override
  public List<T> getSelectedItems() {
    return list.getSelectionModel().getSelectedItems();
  }

  @Override
  public Tool addTool() {
    final FxButtonTool tool = FxButtonTool.ForAnyPurpose();
    buttonPanel.getChildren().add(tool.getNode());
    return tool;
  }

  public Node getNode() {
    return content;
  }

  public void setUiConfiguration(final AgnosticUIConfiguration<T> configuration) {
    list.setCellFactory(new ConfigurableListCellFactory<>(configuration));
  }
}