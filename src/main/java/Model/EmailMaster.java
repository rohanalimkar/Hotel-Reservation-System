package Model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class EmailMaster {

	private String to, from, subject,text;
	public EmailMaster(){}
	public EmailMaster(String to,String from,String subject,String text){
		this.to=to;
		this.from=from;
		this.subject=subject;
		this.text=text;
		
	}
}
