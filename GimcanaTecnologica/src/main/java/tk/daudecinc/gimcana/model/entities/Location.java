package tk.daudecinc.gimcana.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Location {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	@NotNull
	@Column(unique = true)
	private String code;
	
	@NotBlank
	@NotNull
	private String name;
	
	@NotBlank
	@NotNull
	private String description;
	
	private int zone;
	
	private boolean available = true;

}
