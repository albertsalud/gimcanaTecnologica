package tk.daudecinc.gimcana.controller.exceptions;

public class EventNotStartedException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6318020388940718571L;
	
	public EventNotStartedException() {
		super("Event not yet started");
	}

}
