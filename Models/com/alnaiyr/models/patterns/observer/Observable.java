package com.alnaiyr.models.patterns.observer;

/** Observable ability from observer pattern (OOP)
 * 
 * @author IgoЯ
 * @version 1.0
 */
public interface Observable {

    /** Updates the observers when needed */
    public void updateObservers();
    
    /** Adds an observer watching the object implementing
     * 
     * @param observer
     * @return 
     */
    public void addObserver(Observer observer);
    
    /** Removes an observer watching
     * 
     * @param observer
     */
    public void removeObserver(Observer observer);
}
