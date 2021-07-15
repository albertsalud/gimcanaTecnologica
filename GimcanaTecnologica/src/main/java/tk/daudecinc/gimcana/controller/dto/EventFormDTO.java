package tk.daudecinc.gimcana.controller.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EventFormDTO {
	
	private Long id;
	
	@NotBlank(message = "Event name is mandatory")
	@NotNull
	private String name;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "Event date is mandatory")
	private Date initDate;
	
	private boolean allowPlayersRegistration;
	private boolean eventStarted;

}
