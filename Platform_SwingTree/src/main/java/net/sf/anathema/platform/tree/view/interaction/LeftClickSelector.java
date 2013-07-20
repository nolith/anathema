package net.sf.anathema.platform.tree.view.interaction;

import net.sf.anathema.framework.ui.Coordinate;
import net.sf.anathema.platform.tree.display.NodeInteractionListener;
import net.sf.anathema.platform.tree.view.PolygonPanel;
import net.sf.anathema.platform.tree.view.container.Cascade;

import javax.swing.SwingUtilities;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LeftClickSelector extends MouseAdapter {
  private final Cascade cascade;
  private final PolygonPanel polygonPanel;
  private final NodeInteractionListener interactionListener;

  public LeftClickSelector(Cascade cascade, PolygonPanel panel, NodeInteractionListener interactionListener) {
    this.cascade = cascade;
    this.polygonPanel = panel;
    this.interactionListener = interactionListener;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    if (!SwingUtilities.isLeftMouseButton(e) || e.isControlDown()) {
      return;
    }
    Point point = e.getPoint();
    polygonPanel.onElementAtPoint(new Coordinate(point.x, point.y)).perform(new NodeSelectionTrigger(cascade, interactionListener));
    polygonPanel.refresh();
  }
}