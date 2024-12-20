package Model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class StateMaster {
	private int stateId;
	private String stateName;
	public StateMaster(){}
	public StateMaster(int stateId,String stateName){
	this.stateId=stateId;
	this.stateName=stateName;
	}
	public String toString() {
		return "["+stateId+","+stateName+"]";
	}
}
