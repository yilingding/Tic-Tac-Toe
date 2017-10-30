package controller;

/** 
 * A simpler version of java's class Observable
 
*/

import java.util.HashSet;
import java.util.Set;

public class OurObservable {
  
  protected Set<OurObserver> observers = new HashSet<OurObserver>();

  public void addObserver(OurObserver observer) {
    observers.add(observer);
  }

  public void notifyObservers() {
    // Out simple Onservable
    for (OurObserver observer : observers)
      observer.update();
  }
}