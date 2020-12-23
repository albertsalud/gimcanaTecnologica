package tk.daudecinc.gimcana.model.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "event_id"})})
public class Player {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank(message = "Player name is mandatory")
	@NotNull
	private String name;
	
	@NotBlank(message = "Player password is mandatory")
	@Size(min = 4, message = "Password must contain 4 characters at least")
	@NotNull
	private String password;
	
	@NotNull
	private String secretWord;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "event_id")
	@NotNull
	private Event event;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "player_id")
	private List<CheckPoint> checkPoints = new ArrayList<>();
}
