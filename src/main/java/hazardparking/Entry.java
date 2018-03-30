package hazardparking;

import java.time.*;
/**
 * Entry Object for the dataset, simple getters and setters and protected variables
 * @author Ethan Johnston
 *
 */
public class Entry {
	
	private float x;
	private float y;
	private LocalDateTime date;
	private String day;
	private String violationCode;
	private String violationReason;
	private String street;
	
	public Entry(float x, float y, String day, LocalDateTime date, String violationCode, String violationReason, String street) {
		
		this.x = x;
		this.y = y;
		this.day = day;
		this.date = date;
		this.violationCode = violationCode;
		this.violationReason = violationReason;
		this.street = street;
	}
	
	public float getX(){
        return this.x;
    }

    public float getY(){
        return this.y;
    }
    
    public String getDay() {
    	return this.day;
    }
    public LocalDateTime getDate() {
    	return this.date;
    }

    public String getViolationCode(){
        return this.violationCode;
    }

    public String getViolationReason(){
        return this.violationReason;
    }

    public String getStreet(){
        return this.street;
    }
}
