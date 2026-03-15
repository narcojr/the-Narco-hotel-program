
public class Superior_Room extends Economy_Room {

    private boolean hasCityView;
    private boolean hasBalcony;

    public Superior_Room() {
        super();
        setRoomType("Superior Room");
        setPricePerNight(2800.00);
        setMaxOccupancy(3);
        setDescription("Superior room with city view, spacious layout, premium bedding, smart TV, and work desk.");
        this.hasCityView = true;
        this.hasBalcony  = false;
    }

    public Superior_Room(int roomNumber) {
        this();
        setRoomNumber(roomNumber);
        setFloor(roomNumber / 100);
    }

    // Getters
    public boolean isHasCityView() { return hasCityView; }
    public boolean isHasBalcony()  { return hasBalcony;  }

    // Setters
    public void setHasCityView(boolean hasCityView) { this.hasCityView = hasCityView; }
    public void setHasBalcony(boolean hasBalcony)   { this.hasBalcony  = hasBalcony;  }

    @Override
    public String toString() {
        return super.toString() +
               String.format(" | City View: %s | Balcony: %s",
                       hasCityView ? "Yes" : "No",
                       hasBalcony  ? "Yes" : "No");
    }
}