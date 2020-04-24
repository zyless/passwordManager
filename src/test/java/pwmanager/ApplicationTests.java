package pwmanager;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import pwmanager.repository.UserRepository;

@SpringBootTest
@EnableJpaRepositories(basePackageClasses= UserRepository.class)
class ApplicationTests {

	@Test
	void contextLoads() {
	}

}
