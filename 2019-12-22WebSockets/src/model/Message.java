package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class Message {
	
	 private String text;

	@Override
	public String toString() {
		return "Message [text=" + text + "]";
	}
	 
	 
	 
	 

}
