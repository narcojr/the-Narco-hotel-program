
public class Penthouse_Suite extends Economy_Room {

    private boolean hasPrivatePool;
    private boolean hasRooftopTerrace;
    private boolean hasConcierge;
    private boolean hasHomeTheater;
    private boolean hasChefService;
    private boolean hasminors;

    public Penthouse_Suite() {
        super();
        setRoomType("Penthouse Suite");
        setPricePerNight(25000.00);
        setMaxOccupancy(6);
        setDescription("The pinnacle of luxury — Penthouse Suite with private rooftop pool, terrace, 9 years old and below, home theater, personal chef, and 24/7 concierge service.");
        this.hasPrivatePool    = true;
        this.hasRooftopTerrace = true;
        this.hasConcierge      = true;
        this.hasHomeTheater    = true;
        this.hasChefService    = true;
        this.hasminors         = true;
    }

    public Penthouse_Suite(int roomNumber) {
        this();
        setRoomNumber(roomNumber);
        setFloor(roomNumber / 100);
    }

    // Getters
    public boolean isHasPrivatePool()    { return hasPrivatePool;    }
    public boolean isHasRooftopTerrace() { return hasRooftopTerrace; }
    public boolean isHasConcierge()      { return hasConcierge;      }
    public boolean isHasHomeTheater()    { return hasHomeTheater;    }
    public boolean isHasChefService()    { return hasChefService;    }
    public boolean isHasminors()         { return hasminors;         }

    // Setters
    public void setHasPrivatePool(boolean hasPrivatePool)       { this.hasPrivatePool    = hasPrivatePool;    }
    public void setHasRooftopTerrace(boolean hasRooftopTerrace) { this.hasRooftopTerrace = hasRooftopTerrace; }
    public void setHasConcierge(boolean hasConcierge)           { this.hasConcierge      = hasConcierge;      }
    public void setHasHomeTheater(boolean hasHomeTheater)       { this.hasHomeTheater    = hasHomeTheater;    }
    public void setHasChefService(boolean hasChefService)       { this.hasChefService    = hasChefService;    }
    public void setHasminors(boolean hasminors)                 {this.hasminors          = hasminors;         }

    @Override
    public String toString() {
        return super.toString() +
               String.format(" | Private Pool: %s | Rooftop: %s | Concierge: %s | Theater: %s | Chef: %s | minors: %s",
                       hasPrivatePool    ? "Yes" : "No",
                       hasRooftopTerrace ? "Yes" : "No",
                       hasConcierge      ? "Yes" : "No",
                       hasHomeTheater    ? "Yes" : "No",
                       hasChefService    ? "Yes" : "No";
                       hasminors         ? "Yes" : "No");
    }
}