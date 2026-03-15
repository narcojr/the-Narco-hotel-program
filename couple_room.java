
public class couple_room extends Economy_Room {

    private boolean hasKingBed;
    private boolean hasJacuzzi;

    public couple_room() {
        super();
        setRoomType("Couple Room");
        setPricePerNight(2200.00);
        setMaxOccupancy(2);
        setDescription("Romantic couple room with king-sized bed, dim mood lighting, mini-bar, and optional jacuzzi.");
        this.hasKingBed = true;
        this.hasJacuzzi = false;
    }

    public couple_room(int roomNumber) {
        this();
        setRoomNumber(roomNumber);
        setFloor(roomNumber / 100);
    }

    // Getters
    public boolean isHasKingBed()  { return hasKingBed;  }
    public boolean isHasJacuzzi()  { return hasJacuzzi;  }

    // Setters
    public void setHasKingBed(boolean hasKingBed)  { this.hasKingBed  = hasKingBed;  }
    public void setHasJacuzzi(boolean hasJacuzzi)  { this.hasJacuzzi  = hasJacuzzi;  }

    @Override
    public String toString() {
        return super.toString() +
               String.format(" | King Bed: %s | Jacuzzi: %s",
                       hasKingBed ? "Yes" : "No",
                       hasJacuzzi ? "Yes" : "No");
    }
}