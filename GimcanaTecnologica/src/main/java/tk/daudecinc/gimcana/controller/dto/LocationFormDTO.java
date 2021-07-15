package tk.daudecinc.gimcana.controller.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LocationFormDTO {
	
	private Long id;
	
	@NotBlank(message = "Code is mandatory")
	private String code;
	
	@NotBlank(message = "Name is mandatory")
	private String name;
	
	@NotBlank(message = "Description is mandatory")
	private String description;
	
	private int zone;
	private boolean available = true;

}
