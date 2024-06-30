package mr.diagspring.bootcamp2.entites;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "accountusers")
public class AccountUserEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "firstName", nullable = false, unique = true, length = 200)
	private String firstName;

	@Column(name = "lastName", unique = true, length = 200)
	private String lastName;

	@Column(name = "email", unique = true, length = 200)
	private String email;

	@Column(name = "password", nullable = false)
	private String password;

	private boolean state;

	public AccountUserEntity() {

	}

	public AccountUserEntity(long id, String firstName, String lastName, String email, String password, boolean state) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.state = state;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
