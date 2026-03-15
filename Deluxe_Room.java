
public class Deluxe_Room extends Economy_Room {

    private boolean hasSeaView;
    private boolean hasSofa;
    private boolean hasLounge;

    public Deluxe_Room() {
        super();
        setRoomType("Deluxe Room");
        setPricePerNight(3500.00);
        setMaxOccupancy(3);
        setDescription("Deluxe room featuring sea or garden view, premium furnishings, sofa area, and 24-hour room service.");
        this.hasSeaView = true;
        this.hasSofa    = true;
        this.hasLounge  = false;
    }

    public Deluxe_Room(int roomNumber) {
        this();
        setRoomNumber(roomNumber);
        setFloor(roomNumber / 100);
    }

    // Getters
    public boolean isHasSeaView() { return hasSeaView; }
    public boolean isHasSofa()    { return hasSofa;    }
    public boolean isHasLounge()  { return hasLounge;  }

    // Setters
    public void setHasSeaView(boolean hasSeaView) { this.hasSeaView = hasSeaView; }
    public void setHasSofa(boolean hasSofa)       { this.hasSofa    = hasSofa;    }
    public void setHasLounge(boolean hasLounge)   { this.hasLounge  = hasLounge;  }

    @Override
    public String toString() {
        return super.toString() +
               String.format(" | Sea View: %s | Sofa: %s | Lounge: %s",
                       hasSeaView ? "Yes" : "No",
                       hasSofa    ? "Yes" : "No",
                       hasLounge  ? "Yes" : "No");
    }
}