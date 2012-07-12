package net.sf.anathema.character.generic.template.points;

public interface IFavorableTraitCreationPoints
{
  boolean hasCasteTraitPicks();
	
  int getCasteTraitCount();
	
  int getFavoredDotCount();

  int getFavorableTraitCount();
  
  int getExtraFavoredDotCount();
  
  int getExtraGenericDotCount();

  int getDefaultDotCount();
}