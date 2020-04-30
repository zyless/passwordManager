package pwmanager.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@Getter @Setter @ToString
@JsonIgnoreProperties
public class User {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String userName;
	
	private String password;
	
	private boolean active;
	
	private String roles;
	
	
	@OneToMany
	@JoinColumn(name = "userId")
	private List<Website> websites = new ArrayList<>();
	
	
	public int getId() {
		return id;
	}

	
	
	
}
