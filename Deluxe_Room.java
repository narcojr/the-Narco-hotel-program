
public class Deluxe_Room extends Economy_Room {

    private boolean hasSofa;
    private boolean hasLounge;
    private boolean hascityView;

    public Deluxe_Room() {
        super();
        setRoomType("Deluxe Room");
        setPricePerNight(8500.00);
        setMaxOccupancy(3);
        setDescription("Deluxe room city or garden view, premium furnishings, sofa area, and 24-hour room service.");
        this.hasSofa    = true;
        this.hasLounge  = false;
        this.hascityView  = true;
    }

    public Deluxe_Room(int roomNumber) {
        this();
        setRoomNumber(roomNumber);
        setFloor(roomNumber / 100);
    }

    // Getters
    public boolean isHasSofa()    { return hasSofa;    }
    public boolean isHasLounge()  { return hasLounge;  }
    public boolean isHascityView()  { return hascityView;  }

    // Setters
    public void setHasSofa(boolean hasSofa)       { this.hasSofa    = hasSofa;    }
    public void setHasLounge(boolean hasLounge)   { this.hasLounge  = hasLounge;  }
    public void setHascityView(boolean hascityView)   { this.hascityView  = hascityView;  }

    @Override
    public String toString() {
        return super.toString() +
               String.format(" | Sofa: %s | Lounge: %s | City View: %s",
                       hasSofa    ? "Yes" : "No",
                       hasLounge  ? "Yes" : "No",
                       hascityView  ? "Yes" : "No");
    }
}