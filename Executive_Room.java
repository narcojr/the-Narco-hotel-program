// =============================================
//  EXECUTIVE ROOM - Subclass of Economy_Room
// =============================================
public class Executive_Room extends Economy_Room {

    private boolean hasExecutiveLounge;
    private boolean hasWorkstation;
    private boolean hasConferenceAccess;

    public Executive_Room() {
        super();
        setRoomType("Executive Room");
        setPricePerNight(4500.00);
        setMaxOccupancy(2);
        setDescription("Executive room designed for business travelers: ergonomic workstation, executive lounge access, high-speed WiFi, and conference room access.");
        this.hasExecutiveLounge  = true;
        this.hasWorkstation      = true;
        this.hasConferenceAccess = true;
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

    // Setters
    public void setHasExecutiveLounge(boolean hasExecutiveLounge)   { this.hasExecutiveLounge  = hasExecutiveLounge;  }
    public void setHasWorkstation(boolean hasWorkstation)           { this.hasWorkstation      = hasWorkstation;      }
    public void setHasConferenceAccess(boolean hasConferenceAccess) { this.hasConferenceAccess = hasConferenceAccess; }

    @Override
    public String toString() {
        return super.toString() +
               String.format(" | Exec. Lounge: %s | Workstation: %s | Conference: %s",
                       hasExecutiveLounge  ? "Yes" : "No",
                       hasWorkstation      ? "Yes" : "No",
                       hasConferenceAccess ? "Yes" : "No");
    }
}