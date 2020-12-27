package tk.daudecinc.gimcana.controller.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;
import tk.daudecinc.gimcana.controller.dto.validation.PasswordDoubleCheck;
import tk.daudecinc.gimcana.model.entities.Event;

@Data
@NoArgsConstructor
@PasswordDoubleCheck(password = "password", repeatedPassword = "repeatedPassword")
public class PlayerRegistrationDTO {
	
	@NotNull
	private Event event;
	
	@NotBlank(message = "Player name is mandatory")
	private String name;
	
	@NotBlank(message = "Player password is mandatory")
	@Size(min = 4, message = "Password must contain 4 characters at least")
	@NotNull
	private String password;
	
	@NotBlank(message = "Repeated password is mandatory")
	private String repeatedPassword;

}
