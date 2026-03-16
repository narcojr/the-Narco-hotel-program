
public class Superior_Room extends Economy_Room {

    private boolean hascityView;
    private boolean hasBalcony;
    private boolean hasmasseuse;

    public Superior_Room() {
        super();
        setRoomType("Superior Room");
        setPricePerNight(2000.00);
        setMaxOccupancy(3);
        setDescription("Superior room with island view, spacious layout, premium bedding, smart TV, city view, masseuse, and work desk.");
        this.hascityView = true;
        this.hasBalcony  = false;
        this.hasmasseuse  = true;
    }

    public Superior_Room(int roomNumber) {
        this();
        setRoomNumber(roomNumber);
        setFloor(roomNumber / 100);
    }

    // Getters
    public boolean isHascityView() { return hascityView; }
    public boolean isHasBalcony()  { return hasBalcony;  }
    public boolean isHasmasseuse()  { return hasmasseuse;  }

    // Setters
    public void setHascityView(boolean hascityView) { this.hascityView = hascityView; }
    public void setHasBalcony(boolean hasBalcony)   { this.hasBalcony  = hasBalcony;  }

    @Override
    public String toString() {
        return super.toString() +
               String.format(" | City View: %s | Balcony: %s | Masseuse: %s",
                       hascityView ? "Yes" : "No",
                       hasBalcony  ? "Yes" : "No",
                       hasmasseuse  ? "Yes" : "No");
    }
}