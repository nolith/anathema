package net.sf.anathema.character.equipment.item.view.fx;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import net.sf.anathema.character.equipment.item.view.ToolListView;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.gui.TechnologyAgnosticUIConfiguration;
import net.sf.anathema.platform.tool.FxButtonTool;
import org.tbee.javafx.scene.layout.MigPane;

import java.util.Arrays;
import java.util.List;

public class FxToolListView<T> implements ToolListView<T> {

  private ListView<T> list;
  private GridPane buttonPanel;
  private MigPane content;

  public FxToolListView() {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        list = new ListView<>();
        buttonPanel = new GridPane();
        content = new MigPane();
        content.add(list);
        content.add(buttonPanel);
        list.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
      }
    });
  }

  @Override
  public void setObjects(T[] items) {
    list.setItems(new ObservableListWrapper<>(Arrays.asList(items)));
  }

  @Override
  public void addListSelectionListener(final Runnable listener) {
    waitForList();
    list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<T>() {
      @Override
      public void changed(ObservableValue<? extends T> observableValue, T t, T t2) {
        listener.run();
      }
    });
  }

  private void waitForList() {
    while (list == null) {
      try {
        Thread.sleep(50);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }

  @Override
  public List<T> getSelectedItems() {
    return list.getSelectionModel().getSelectedItems();
  }

  @Override
  public Tool addTool() {
    final FxButtonTool tool = new FxButtonTool();
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        buttonPanel.getChildren().add(tool.getNode());
      }
    });
    return tool;
  }

  public Node getNode() {
    return content;
  }

  public void setUiConfiguration(final TechnologyAgnosticUIConfiguration<T> configuration) {
    waitForList();
    list.setCellFactory(new Callback<ListView<T>, ListCell<T>>() {
      @Override
      public ListCell<T> call(ListView<T> tListView) {
        return new UITableCell<>(configuration);
      }
    });
  }
}