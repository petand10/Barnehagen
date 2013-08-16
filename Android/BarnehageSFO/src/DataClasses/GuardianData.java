package DataClasses;

import android.provider.ContactsContract.CommonDataKinds.Phone;

public class GuardianData {
	private String name;
	private String number;
	private String adress;
	
	public GuardianData(String name, String number, String adress){
		this.name = name;
		this.number = number;
		this.adress = adress;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	
}
