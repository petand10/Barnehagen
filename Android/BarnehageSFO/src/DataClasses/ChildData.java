package DataClasses;

import java.util.List;

public class ChildData {
	
	private String name;
	private List<GuardianData> guardians;
	
	public ChildData(String name, List<GuardianData> guardians){
		this.name = name;
		this.guardians = guardians;
	}
	
	public ChildData() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	/* Guardian stuff */
	public boolean hasGuardian(){
		return !guardians.isEmpty();
	}
	public List<GuardianData> getGuardians() {
		return guardians;
	}
	public GuardianData getGuardian(int id)
	{
		if(guardians.get(id) != null)
			return guardians.get(id);
		return null;
	}
	public String getGuardianName(int id){
		if(guardians.get(id) != null)
			return guardians.get(id).getName();
		if(hasGuardian())
			return guardians.get(0).getName();
		return "Ingen foresatt";
	}
	public void setGuardians(List<GuardianData> guardians) {
		this.guardians = guardians;
	}
	public void addGuardian(GuardianData guardian){
		this.guardians.add(guardian);
	}
	public void removeGuardian(int id){
		this.guardians.remove(id);
	}
	public void removeAllGuardians(){
		this.guardians.clear();
	}
	
}
