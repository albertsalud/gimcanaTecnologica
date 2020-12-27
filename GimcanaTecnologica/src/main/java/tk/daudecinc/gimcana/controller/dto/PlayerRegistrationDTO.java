package tk.daudecinc.gimcana.controller.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;
import tk.daudecinc.gimcana.model.entities.Event;

@Data
@NoArgsConstructor
public class PlayerRegistrationDTO {
	
	@NotNull
	private Event event;
	
	@NotBlank(message = "Name is mandatory")
	private String name;
	
	@NotBlank(message = "Password is mandatory")
	private String password;
	
	@NotBlank(message = "Repeated password is mandatory")
	private String repeatedPassword;

}
