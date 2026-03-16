
public class Junior_Suite extends Economy_Room {

    private boolean hasSeparateLivingArea;
    private boolean hasMiniBar;
    private boolean hasBathTub;

    public Junior_Suite() {
        super();
        setRoomType("Junior Suite");
        setPricePerNight(7500.00);
        setMaxOccupancy(3);
        setDescription("Junior Suite with separate living area, plush king bed, marble bathroom with bathtub, and complimentary mini-bar.");
        this.hasSeparateLivingArea = true;
        this.hasMiniBar            = true;
        this.hasBathTub            = true;
    }

    public Junior_Suite(int roomNumber) {
        this();
        setRoomNumber(roomNumber);
        setFloor(roomNumber / 100);
    }

    // Getters
    public boolean isHasSeparateLivingArea() { return hasSeparateLivingArea; }
    public boolean isHasMiniBar()            { return hasMiniBar;            }
    public boolean isHasBathTub()            { return hasBathTub;            }

    // Setters
    public void setHasSeparateLivingArea(boolean hasSeparateLivingArea) { this.hasSeparateLivingArea = hasSeparateLivingArea; }
    public void setHasMiniBar(boolean hasMiniBar)                       { this.hasMiniBar            = hasMiniBar;            }
    public void setHasBathTub(boolean hasBathTub)                       { this.hasBathTub            = hasBathTub;            }

    @Override
    public String toString() {
        return super.toString() +
               String.format(" | Living Area: %s | Mini-Bar: %s | Bathtub: %s",
                       hasSeparateLivingArea ? "Yes" : "No",
                       hasMiniBar            ? "Yes" : "No",
                       hasBathTub            ? "Yes" : "No");
    }
}