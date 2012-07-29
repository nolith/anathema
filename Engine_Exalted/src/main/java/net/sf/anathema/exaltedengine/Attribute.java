package net.sf.anathema.exaltedengine;

import net.sf.anathema.characterengine.quality.Name;
import net.sf.anathema.characterengine.quality.Quality;

public class Attribute implements Quality {
  private final NumericValue value;
  private final Name name;

  public Attribute(NumericValue value, Name name) {
    this.value = value;
    this.name = name;
  }

  public boolean hasValue(NumericValue value) {
    return value.equals(this.value);
  }

  public boolean isGreaterThan(NumericValue value) {
    return this.value.isGreaterThan(value);
  }

  public boolean isCalled(Name name) {
    return this.name.equals(name);
  }
}