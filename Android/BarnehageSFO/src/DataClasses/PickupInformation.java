package DataClasses;

import java.util.Date;


public class PickupInformation {
	private Date date;
	private GuardianData pickupContact;
	
	public PickupInformation(Date date, GuardianData pickupContact){
		this.date = date;
		this.pickupContact = pickupContact;
	}
	public PickupInformation(Date date, String guardianName, String guardianPhone){
		this.date = date;
		this.pickupContact = new GuardianData(guardianName, guardianPhone, "");
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public GuardianData getPickupContact() {
		return pickupContact;
	}
	public void setPickupContact(GuardianData pickupContact) {
		this.pickupContact = pickupContact;
	}
	public void setPickupContact(String guardianName, String guardianPhone){
		this.pickupContact = new GuardianData(guardianName, guardianPhone, "");
	}
}
