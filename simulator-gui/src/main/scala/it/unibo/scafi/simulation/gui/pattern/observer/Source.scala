package it.unibo.scafi.simulation.gui.pattern.observer
//OBSERVER PATTERN
/**
  * define a generic source that could be observe
  * the order of observer are preserved:
  * the first that is attach is the first that is notify
  */
trait Source {
  /**
    * the type of observer
    */
  type O <: Observer
  //TEMPLATE METHOD
  protected def observers: Iterable[O]

  /**
    * a way to add observer
    * look attach
    */
  final def <-- (observer : O): this.type = {
    attach(observer)
    return this
  }

  /**
    * a way to remove observer
    * look detach
    */
  final def <--! (observer : O): this.type =  {
    detach(observer)
    return this
  }

  /**
    * add an observer to the source
    * @param observer want observe this source
    * @return false if the observer currently observe the source true otherwise
    */
  def attach(observer : O): Boolean
  /**
    * remove an observer to the source
    * @param observer want to stop observer this source
    * @return false if the observer currently doesn't observer the source true otherwise
    */
  def detach(observer: O) : Boolean

  /**
    * notify all the observer
    * @param e the event generate
    */
  def !!! (e :Event) =  observers foreach (_ !! e)
}

/**
  * the root interface of all event
  */
trait Event {}

trait Observer {
  private var _events : List[Event] = List[Event]()
  /**
    * store the event received
    * @param event
    *   the event produced by source
    */
  def !! (event: Event) : Unit = _events = event :: _events

  /**
    * @return the sequence of event listened and clear the current queue of event
    */
  def events : Seq[Event] = {
    val res = this._events
    this._events = List[Event]()
    res
  }
}