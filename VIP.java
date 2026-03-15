
public class VIP extends Economy_Room {

    private boolean hasLimousineService;
    private boolean hasPersonalBodyguard;
    private boolean hasHelipaddAccess;
    private boolean hasPrivateGym;

    public VIP() {
        super();
        setRoomType("VIP Room");
        setPricePerNight(35000.00);
        setMaxOccupancy(4);
        setDescription("Exclusive VIP experience with limousine service, personal bodyguard, helipad access, private gym, and total privacy & discretion guaranteed.");
        this.hasLimousineService   = true;
        this.hasPersonalBodyguard  = true;
        this.hasHelipaddAccess     = true;
        this.hasPrivateGym         = true;
    }

    public VIP(int roomNumber) {
        this();
        setRoomNumber(roomNumber);
        setFloor(roomNumber / 100);
    }

    // Getters
    public boolean isHasLimousineService()  { return hasLimousineService;  }
    public boolean isHasPersonalBodyguard() { return hasPersonalBodyguard; }
    public boolean isHasHelipaddAccess()    { return hasHelipaddAccess;    }
    public boolean isHasPrivateGym()        { return hasPrivateGym;        }

    // Setters
    public void setHasLimousineService(boolean hasLimousineService)   { this.hasLimousineService  = hasLimousineService;  }
    public void setHasPersonalBodyguard(boolean hasPersonalBodyguard) { this.hasPersonalBodyguard = hasPersonalBodyguard; }
    public void setHasHelipaddAccess(boolean hasHelipaddAccess)       { this.hasHelipaddAccess    = hasHelipaddAccess;    }
    public void setHasPrivateGym(boolean hasPrivateGym)               { this.hasPrivateGym        = hasPrivateGym;        }

    @Override
    public String toString() {
        return super.toString() +
               String.format(" | Limo: %s | Bodyguard: %s | Helipad: %s | Private Gym: %s",
                       hasLimousineService  ? "Yes" : "No",
                       hasPersonalBodyguard ? "Yes" : "No",
                       hasHelipaddAccess    ? "Yes" : "No",
                       hasPrivateGym        ? "Yes" : "No");
    }
}