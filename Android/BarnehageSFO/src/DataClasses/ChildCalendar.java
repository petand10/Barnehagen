package DataClasses;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ChildCalendar {
	private ArrayList<PickupInformation> pickupDates;
	private GuardianData permanentGuardian;
	
	
	public GuardianData getPermanentGuardian() {
		return permanentGuardian;
	}
	public void setPermanentGuardian(GuardianData permanentGuardian) {
		this.permanentGuardian = permanentGuardian;
	}
	public void setPermanentGuardian(String permanentGuardianName, String permanentGuardianPhone) {
		this.permanentGuardian = new GuardianData(permanentGuardianName, permanentGuardianPhone, "");
	}
	public void setPermanentGuardian(String permanentGuardianName, String permanentGuardianPhone, String permanentGuardianAdress) {
		this.permanentGuardian = new GuardianData(permanentGuardianName, permanentGuardianPhone, permanentGuardianAdress);
	}
	public ArrayList<PickupInformation> getAllDates(){
		return pickupDates;
	}
	public void setNewCalendar(ArrayList<PickupInformation> pickupDates){
		this.pickupDates = pickupDates;
	}
	public void setNewCalendarDatesFixedGuardian(ArrayList<Date> dates, GuardianData guardian){
		ArrayList<PickupInformation> tmp = new ArrayList<PickupInformation>();
		for (Date date : dates) {
			tmp.add(new PickupInformation(date,guardian));
		}
		this.pickupDates = tmp;
	}
	public void sortCalendar(){
		Collections.sort(pickupDates, new CustomComparator());
	}
	
	public class CustomComparator implements Comparator<PickupInformation> {
		@Override
		public int compare(PickupInformation o1, PickupInformation o2) {
	        return o1.getDate().compareTo(o2.getDate());
		}
	}
}
