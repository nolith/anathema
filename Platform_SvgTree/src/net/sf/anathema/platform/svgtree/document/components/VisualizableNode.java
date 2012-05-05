package net.sf.anathema.platform.svgtree.document.components;

import net.sf.anathema.graph.nodes.IIdentifiedRegularNode;
import net.sf.anathema.graph.nodes.ISimpleNode;
import net.sf.anathema.lib.collection.MultiEntryMap;

import java.awt.Dimension;
import java.util.Map;

public class VisualizableNode extends AbstractSingleVisualizableNode {

  @Override
  public void accept(IVisualizableNodeVisitor visitor) {
    visitor.visitSingleNode(this);
  }

  public VisualizableNode(IIdentifiedRegularNode contentNode, Map<ISimpleNode, IVisualizableNode> map,
                          Dimension nodeDimension, MultiEntryMap<ISimpleNode, ISimpleNode> leafNodesByAncestors) {
    super(contentNode, map, nodeDimension, leafNodesByAncestors);
  }

  @Override
  protected IIdentifiedRegularNode getContentNode() {
    return (IIdentifiedRegularNode) super.getContentNode();
  }

  public String getId(){
    return getContentNode().getId();
  }

  public String toString() {
    return getId();
  }
}
