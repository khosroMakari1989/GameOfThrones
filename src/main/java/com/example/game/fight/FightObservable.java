package com.example.game.fight;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 *
 * @author khosro.makari@gmail.com
 */
public class FightObservable {

    private PropertyChangeSupport support;
    private FightEvent fightEvent = FightEvent.STARTED;

    public FightObservable() {
        support = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }

    public FightEvent getFightEvent() {
        return fightEvent;
    }

    public void setFightEvent(FightEvent fightEvent) {
        support.firePropertyChange("fightEvent", this.fightEvent, fightEvent);
        this.fightEvent = fightEvent;
    }

}
