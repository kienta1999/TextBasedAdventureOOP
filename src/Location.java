import java.util.ArrayList;
import java.util.HashMap;

public class Location {
	private String name;
	private String description;
	ArrayList<Item> collection = new ArrayList<>();
	HashMap<String, Location> direction = new HashMap<>();
	
	public Location() {}
	public Location(String name, String description) {
		this.name = name;
		this.description = description;
	}
//	@Override
//	public String toString() {
//		return collection.toString();
//	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void addToCollection(Item i) {
		collection.add(i);
	}
	public boolean containsItem(String itemName){
		for(Item i: collection) {
			if(i.getName().equals(itemName))
				return true;
		}
		return false;
	}
	public Item getItem(String itemName) {
		for(Item i: collection) {
			if(i.getName().equals(itemName))
				return i;
		}
		return null;
	}
	public int countItem() {
		return collection.size();
	}	
	public Item removeItem(String itemName) {
		for(Item i: collection) {
			if(i.getName().equals(itemName)) {
				Item temp = i;
				collection.remove(i);
				return temp;
			}
		}
		return null;
	}
}
