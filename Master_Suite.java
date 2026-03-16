
public class Master_Suite extends Economy_Room {

    private boolean hasPrivateDining;
    private boolean hasSpa;
    private boolean hasButlerService;
    private boolean hasPanoramicView;
    private boolean hasmasseuse;

    public Master_Suite() {
        super();
        setRoomType("Master Suite");
        setPricePerNight(19000.00);
        setMaxOccupancy(4);
        setDescription("Luxurious Master Suite with panoramic views, private dining room, in-room spa, masseuse and dedicated butler service.");
        this.hasPrivateDining  = true;
        this.hasSpa            = true;
        this.hasButlerService  = true;
        this.hasPanoramicView  = true;
        this.hasmasseuse       = true;
    }

    public Master_Suite(int roomNumber) {
        this();
        setRoomNumber(roomNumber);
        setFloor(roomNumber / 100);
    }

    // Getters
    public boolean isHasPrivateDining()  { return hasPrivateDining;  }
    public boolean isHasSpa()            { return hasSpa;            }
    public boolean isHasButlerService()  { return hasButlerService;  }
    public boolean isHasPanoramicView()  { return hasPanoramicView;  }
    public boolean isHasmasseuse()       { return hasmasseuse;       }

    // Setters
    public void setHasPrivateDining(boolean hasPrivateDining)   { this.hasPrivateDining  = hasPrivateDining;  }
    public void setHasSpa(boolean hasSpa)                       { this.hasSpa            = hasSpa;            }
    public void setHasButlerService(boolean hasButlerService)   { this.hasButlerService  = hasButlerService;  }
    public void setHasPanoramicView(boolean hasPanoramicView)   { this.hasPanoramicView  = hasPanoramicView;  }
    public void setHasmasseuse(boolean hasmasseuse)             { this.hasmasseuse       = hasmasseuse;       }

    @Override
    public String toString() {
        return super.toString() +
               String.format(" | Private Dining: %s | Spa: %s | Butler: %s | Panoramic View: %s | Masseuse: %s",
                       hasPrivateDining  ? "Yes" : "No",
                       hasSpa            ? "Yes" : "No",
                       hasButlerService  ? "Yes" : "No",
                       hasPanoramicView  ? "Yes" : "No",
                       hasmasseuse       ? "Yes" : "No");
    }
}