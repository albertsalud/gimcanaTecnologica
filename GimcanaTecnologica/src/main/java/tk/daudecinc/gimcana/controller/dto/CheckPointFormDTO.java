package tk.daudecinc.gimcana.controller.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CheckPointFormDTO {

	@NotBlank
	private String playerName;
	
	@NotBlank
	private String password;
	
	private String locationCode;
}
