import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();
    private List<Item> items = new ArrayList<Item>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {
        LocalTime requestTime = getCurrentTime();
        if(requestTime.isAfter(openingTime) && requestTime.isBefore(closingTime))
            return true;
        return false;
    }

    public LocalTime getCurrentTime(){ return  LocalTime.now(); }

    public List<Item> getMenu() {
        return menu;
    }

    private Item findItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }
    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    public String getName() {
        return name;
    }


    ////////////////////////   IMPLEMENTING ORDER VALUE /////////////////////////


    public void addToItemListByName(String itemName) throws itemNotFoundException {

        Item itemToBeAdded = findItemByName(itemName);
        if (itemToBeAdded == null)
            throw new itemNotFoundException(itemName);

        items.add(itemToBeAdded);
    }

    public List<Item> getItems() { return items; }

    public int calculateOrderValue(List<Item> items) {
        int sum = 0;
        for(Item item: items){
            sum = sum + item.getPrice();
        }
        System.out.println("Your order will cost: Rs" + sum);
        return sum;
    }
}
