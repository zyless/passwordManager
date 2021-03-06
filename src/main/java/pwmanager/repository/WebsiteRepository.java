package pwmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import pwmanager.model.Website;

public interface WebsiteRepository extends JpaRepository<Website, Integer>{

	
}
