package pwmanager.exception;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Component
@Getter @Setter @NoArgsConstructor
@ToString
public class ResponseMsg {

	private String message;
	
	
}
