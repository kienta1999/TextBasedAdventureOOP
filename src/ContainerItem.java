import java.util.ArrayList;

public class ContainerItem extends Item{
//an item that is capable of storing items inside of itself -  chest, toolbox, vault
	ArrayList<Item> container = new ArrayList<>();
	public ContainerItem() {
		super();
	}
	public ContainerItem(String name, String type, String description) {
		super(name, type, description);
	}
	public void addToContainer(Item i) {
		container.add(i);
	}
	public Item removeUsingName(String name) {
		for(int i = 0; i < container.size(); i++) {
			if(container.get(i).getName().equals(name)) {
				Item temp = container.get(i);
				container.remove(temp);
				return temp;
			}
		}
		return null;
	}
	public Item removeUsingIndexContainer(int index) {
		return container.remove(index);
	}
	public int countContainer() {
		return container.size();
	}
	public boolean containsItem(String name) {
		for(Item i: container) {
			if(i.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}
	@Override
	public String getDescription() {
		String s = super.getDescription();
		for(Item i: container) {
			s += "\n" + i.getName();
		}
		return s;
	}
}
