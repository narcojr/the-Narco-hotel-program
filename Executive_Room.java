
public class Executive_Room extends Economy_Room {

    private boolean hasExecutiveLounge;
    private boolean hasWorkstation;
    private boolean hasConferenceAccess;
    private boolean hascityView;

    public Executive_Room() {
        super();
        setRoomType("Executive Room");
        setPricePerNight(10500.00);
        setMaxOccupancy(2);
        setDescription("Executive room designed for business travelers: ergonomic workstation, executive lounge access, high-speed WiFi, one minors, and conference room access.");
        this.hasExecutiveLounge  = true;
        this.hasWorkstation      = true;
        this.hasConferenceAccess = true;
        this.hascityView         = true;
    }

    public Executive_Room(int roomNumber) {
        this();
        setRoomNumber(roomNumber);
        setFloor(roomNumber / 100);
    }

    // Getters
    public boolean isHasExecutiveLounge()  { return hasExecutiveLounge;  }
    public boolean isHasWorkstation()      { return hasWorkstation;      }
    public boolean isHasConferenceAccess() { return hasConferenceAccess; }
    public boolean isHascityView()         { return hascityView;         }

    // Setters
    public void setHasExecutiveLounge(boolean hasExecutiveLounge)   { this.hasExecutiveLounge  = hasExecutiveLounge;  }
    public void setHasWorkstation(boolean hasWorkstation)           { this.hasWorkstation      = hasWorkstation;      }
    public void setHasConferenceAccess(boolean hasConferenceAccess) { this.hasConferenceAccess = hasConferenceAccess; }
    public void setHascityView(boolean hascityView)                 { this.hascityView         = hascityView;         }

    @Override
    public String toString() {
        return super.toString() +
               String.format(" | Exec. Lounge: %s | Workstation: %s | Conference: %s | City View: %s",
                       hasExecutiveLounge  ? "Yes" : "No",
                       hasWorkstation      ? "Yes" : "No",
                       hasConferenceAccess ? "Yes" : "No",
                       hascityView         ? "Yes" : "No");
    }
}