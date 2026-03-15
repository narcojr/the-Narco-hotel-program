
public class Superior_Room extends Economy_Room {

    private boolean hasminors;
    private boolean hasBalcony;

    public Superior_Room() {
        super();
        setRoomType("Superior Room");
        setPricePerNight(2800.00);
        setMaxOccupancy(3);
        setDescription("Superior room with island view, spacious layout, premium bedding, smart TV, 2 minors, and work desk.");
        this.hasminors = true;
        this.hasBalcony  = false;
    }

    public Superior_Room(int roomNumber) {
        this();
        setRoomNumber(roomNumber);
        setFloor(roomNumber / 100);
    }

    // Getters
    public boolean isHasminors() { return hasminors; }
    public boolean isHasBalcony()  { return hasBalcony;  }

    // Setters
    public void setHasminors(boolean hasminors) { this.hasminors = hasminors; }
    public void setHasBalcony(boolean hasBalcony)   { this.hasBalcony  = hasBalcony;  }

    @Override
    public String toString() {
        return super.toString() +
               String.format(" | minors: %s | Balcony: %s",
                       hasminors ? "Yes" : "No",
                       hasBalcony  ? "Yes" : "No");
    }
}