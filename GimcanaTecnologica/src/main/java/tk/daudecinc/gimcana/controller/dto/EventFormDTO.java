package tk.daudecinc.gimcana.controller.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;
import tk.daudecinc.gimcana.model.entities.Location;

@Data
@NoArgsConstructor
public class EventFormDTO {
	
	private Long id;
	
	@NotBlank(message = "Event name is mandatory")
	@NotNull
	private String name;
	
	@NotBlank(message = "Meeting point is mandatory")
	private String meetingPoint;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@NotNull(message = "Event date is mandatory")
	private Date initDate;
	
	private boolean allowPlayersRegistration;
	private boolean eventStarted;
	
	@NotEmpty(message = "Set at least one location for the event")
	private List<Location> eventLocations;
	
	private List<Location> allLocations;

}
