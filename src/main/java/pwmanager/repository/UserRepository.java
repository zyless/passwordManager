package pwmanager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import pwmanager.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByUserName(String userName);
	
	/*@Modifying
	@Query("UPDATE User u SET u.userName = ?2, u.password = ?3, u.active = ?3, u.roles = ?4 WHERE u.id = ?1")
	int updateUser(int Id, String userName, String password, boolean active, String roles);
	*/
}
