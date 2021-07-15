package tk.daudecinc.gimcana.controller.exceptions;

public class LocationNotFoundException extends Exception {
	private static final long serialVersionUID = -4659756423222988154L;
	
	
	public LocationNotFoundException() {
		super("Location not found");
	}
}
