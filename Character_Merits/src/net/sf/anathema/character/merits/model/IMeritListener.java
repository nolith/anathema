package net.sf.anathema.character.merits.model;

public interface IMeritListener {
	public void meritAdded(IMerit merit);
	
	public void meritRemoved(IMerit merit);
}
