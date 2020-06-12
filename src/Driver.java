import java.util.Scanner;
public class Driver {
	public static Location myLocation;
	//Ta Le Kien
	// character’s current Location
	public static ContainerItem myInventory;
	//a backpack that your character carries with him/her as he/she moves
	//throughout your world
	public static void main(String[] args) {
		myInventory = new ContainerItem("MyBag", "Bag", "A backpack that your character carries");
		Location home = new Location("Area51", "Home of the Alien");
		Item i1 = new Item("Cleaver", "Knife", "Rectangular blade");
		Item i2 = new Item("Handgun", "Gun", "Defaut gun");
		Item i3 = new Item("SmallKit", "First Aid Kit", "10 extra blood");
		ContainerItem c1 = new ContainerItem("DefaultChest", "Chest", "Contain Gerenades");
		ContainerItem c2 = new ContainerItem("DefaultVault", "Vault", "Contain Diamond");
		c1.addToContainer(new Item("TearGas", "Grenades", "Chemical Grenades"));
		c2.addToContainer(new Item("White", "Diamond", "White diamond"));
		c2.addToContainer(new Item("Black", "Diamond", "Black diamond"));
		home.addToCollection(i1);
		home.addToCollection(i2);
		home.addToCollection(i3);
		home.addToCollection(c1);
		home.addToCollection(c2);
		myLocation = home;
		
		
		//second location
		Location city = new Location("Los Angeles, CA", "Civilized Area");
		Item first = new Item("iPhone", "Phone", "iPhone 11 Pro Max");
		Item second = new Item("Sushi", "Food", "Edible food");
		Item third = new Item("Suit", "Clothing", "Find some clothes");
		
		city.addToCollection(first);
		city.addToCollection(second);
		city.addToCollection(third);
		
		//third location
		Location forest = new Location("Amazon", "Amazon Rainforest");
		forest.addToCollection(new Item("Jaguar", "Animal", "Fast animal"));
		forest.addToCollection(new Item("Anaconda", "Snake", "Dangerous Animal"));
		forest.addToCollection(new Item("Centipede", "Animal", "Poisonous Animal"));
		
		//forth location
		Location beach = new Location("Miami", "Beach");
		beach.addToCollection(new Item("YellowSand", "Sand", "Sand on the beach"));
		beach.addToCollection(new Item("Goldfish", "Fish", "Fish on the beach"));
		beach.addToCollection(new Item("Beach Umbrella", "Umbrella", "Umbrella on the beach"));
		
		//fifth location
		Location field = new Location("Indiana", "Field");
		
		//connect locations
		home.direction.put("right", city);
		city.direction.put("left", home);
		
		forest.direction.put("down", city);
		city.direction.put("up", forest);
		
		beach.direction.put("up", city);
		city.direction.put("down", beach);
		
		city.direction.put("right", field);
		field.direction.put("left", city);
		
		Scanner scan = new Scanner(System.in);
		String user = "";
		while(true) {
			user = scan.nextLine();
				if(user.equals("quit")) {
					System.exit(0);
			}
				else if(user.equals("look")) {
					//look at items in the location 
					System.out.print("My location: ");
					System.out.println(myLocation.getDescription());
					System.out.println("List of items: ");
					for(Item i: myLocation.collection) {
						System.out.println(i.getName());
					}
				}
				else if(user.split(" ")[0].equals("examine")){
					String s = user.split(" ")[1];
					for(Item i: myLocation.collection) {
						if(i.getName().equals(s)) {
							System.out.println(s + "\n" + i.getDescription());
						}
					}
				}
				else if(user.split(" ").length == 4 && user.split(" ")[0].equals("take") 
						&& user.split(" ")[2].equals("from")) {
					//take White from DefaultVault
					//get the item with given name out of a containerItem inside location
					//+ add to my bag
					String name = user.split(" ")[1];
					String containerName = user.split(" ")[3];
					//get container
					if (myLocation.containsItem(containerName)){
					ContainerItem container = (ContainerItem)myLocation.getItem(containerName);
					//add to inventory
					if(container.containsItem(name))
					myInventory.addToContainer(container.removeUsingName(name));
					else {
						System.out.println("Name provided is not in the container");
					}
					}
					else {
						System.out.println("Name provided is not in the location");
					}
				}
				else if(user.split(" ").length == 2 && user.split(" ")[0].equals("take")) {
					//take Handgun
					//get the  item with given name out of Location + add to my bag
					String name = user.split(" ")[1];
					if (myLocation.containsItem(name)){
					Item pickUpItem = myLocation.removeItem(name);
					myInventory.addToContainer(pickUpItem);
					}
					else {
						System.out.println("Name provided is not in the location");
					}
				}
				else if (user.split(" ")[0].equals("drop")) {
					//drop the Item
					String name = user.split(" ")[1];
					if(myInventory.containsItem(name)) {
					Item removedItem = myInventory.removeUsingName(name);
					myLocation.addToCollection(removedItem);
					}
					else {
						System.out.println("Name provided is not in the bag");
					}
				}
				else if(user.split(" ").length == 4 && user.split(" ")[0].equals("put") 
						&& user.split(" ")[2].equals("in")) {
					//remove it from the character’s inventory and add it to the specified [container]
					String name = user.split(" ")[1];
					String containerName = user.split(" ")[3];
					if(myInventory.containsItem(name)) {
						Item removedItem = myInventory.removeUsingName(name);
						if(myLocation.containsItem(containerName)) {
							ContainerItem container = (ContainerItem)myLocation.getItem(containerName);
							container.addToContainer(removedItem);
						}
						else {
							System.out.println("container is not in this location");
						}
					}
					else {
						System.out.println("Name provided is not in the inventory");
					}
				}
				else if(user.equals("inventory")){
					System.out.println("Items in your inventory are: ");
					for(Item i: myInventory.container) {
						System.out.println("- " + i.getName());
					}
				}
				else if(user.split(" ").length == 2 && user.split(" ")[0].equals("go")) {
					String goTo = user.split(" ")[1];
					if(myLocation.direction.containsKey(goTo)) {
						myLocation = myLocation.direction.get(goTo);
					}
					else {
						System.out.println("The provided direction is not valid");
					}
				}
				else if(user.equals("help")) {
					printCommands();
				}
				else {
					System.out.println("I don’t know how to do that");
					//System.out.println(c2.getDescription());
				}
		}
	}
	public static void printCommands() {
		System.out.println("Please type one of the following: ");
		System.out.println("1. look: Get current location’s description and the names of the items found at the current location.");
		System.out.println("2. examine [NAME]:get the item with the given name from the current location and print the Item’s name and description");
		System.out.println("3. take [NAME]: remove this item from the current location and add it to the character’s inventory");
		System.out.println("4. drop [NAME]: remove the item from the character’s inventory and add it to the current location");
		System.out.println("5. take [NAME] from [CONTAINER]: remove the item from the [container] and add it to the character’s inventory");
		System.out.println("6. put [NAME] in [CONTAINER]:  remove the item from the character’s inventory and add it to the specified [container] at the character’s current location.");
		System.out.println("7. inventory:  List the items in your character’s inventory.");
		System.out.println("8. go [DIRECTION]: go in any direction represented as a String");
		System.out.println("9. quit: exit and the program ends.");
	}
}
