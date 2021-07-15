package tk.daudecinc.gimcana.controller.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CheckPointFormDTO {
	
	@NotNull
	private Long eventId;

	@NotNull
	private Long playerId;
	
	@NotBlank(message = "Password is mandatory")
	private String password;
	
	private String locationCode;
}
