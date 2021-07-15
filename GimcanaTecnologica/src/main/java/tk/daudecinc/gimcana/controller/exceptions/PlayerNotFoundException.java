package tk.daudecinc.gimcana.controller.exceptions;

public class PlayerNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3150787468147273538L;
	
	public PlayerNotFoundException() {
		super("User not found");
	}
	

}
