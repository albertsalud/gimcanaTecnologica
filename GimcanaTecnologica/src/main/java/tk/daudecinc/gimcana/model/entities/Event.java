package tk.daudecinc.gimcana.model.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Event {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date initDate;
	
	@ManyToMany
	private List<Location> eventLocations;
	
	private boolean allowPlayersRegistration;
	
	private boolean eventStarted;
	
	public String getFullEventName() {
		return "(" + initDate + ") " + name;
	}
}

