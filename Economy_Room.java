
public class Economy_Room {

    // ---- Fields ----
    private int roomNumber;
    private String roomType;
    private double pricePerNight;
    private boolean available;
    private String guestName;
    private String checkInDate;
    private String checkOutDate;
    private int maxOccupancy;
    private String description;
    private int floor;

    // ---- Constructors ----
    public Economy_Room() {
        this.roomType      = "Economy Room";
        this.pricePerNight = 1500.00;
        this.available     = true;
        this.maxOccupancy  = 2;
        this.description   = "Comfortable economy room with essential amenities: AC, TV, WiFi, and private bathroom.";
        this.floor         = 1;
    }

    public Economy_Room(int roomNumber) {
        this();
        this.roomNumber = roomNumber;
        this.floor      = roomNumber / 100;
    }

    // ---- Getters ----
    public int    getRoomNumber()     { return roomNumber;     }
    public String getRoomType()       { return roomType;       }
    public double getPricePerNight()  { return pricePerNight;  }
    public boolean isAvailable()      { return available;      }
    public String getGuestName()      { return guestName;      }
    public String getCheckInDate()    { return checkInDate;    }
    public String getCheckOutDate()   { return checkOutDate;   }
    public int    getMaxOccupancy()   { return maxOccupancy;   }
    public String getDescription()    { return description;    }
    public int    getFloor()          { return floor;          }

    // ---- Setters ----
    public void setRoomNumber(int roomNumber)          { this.roomNumber      = roomNumber;     }
    public void setRoomType(String roomType)           { this.roomType        = roomType;       }
    public void setPricePerNight(double price)         { this.pricePerNight   = price;          }
    public void setAvailable(boolean available)        { this.available       = available;      }
    public void setGuestName(String guestName)         { this.guestName       = guestName;      }
    public void setCheckInDate(String checkInDate)     { this.checkInDate     = checkInDate;    }
    public void setCheckOutDate(String checkOutDate)   { this.checkOutDate    = checkOutDate;   }
    public void setMaxOccupancy(int maxOccupancy)      { this.maxOccupancy    = maxOccupancy;   }
    public void setDescription(String description)     { this.description     = description;    }
    public void setFloor(int floor)                    { this.floor           = floor;          }

    // ---- Methods ----
    public boolean reserve(String guestName, String checkIn, String checkOut) {
        if (available) {
            this.guestName    = guestName;
            this.checkInDate  = checkIn;
            this.checkOutDate = checkOut;
            this.available    = false;
            return true;
        }
        return false;
    }

    public void checkout() {
        this.guestName    = null;
        this.checkInDate  = null;
        this.checkOutDate = null;
        this.available    = true;
    }

    public String getStatus() {
        if (available) return "Available";
        return "Occupied — Guest: " + guestName + " | " + checkInDate + " → " + checkOutDate;
    }

    public String getRoomLabel() {
        return String.format("Room %d | %s | PHP %.2f/night", roomNumber, roomType, pricePerNight);
    }

    @Override
    public String toString() {
        return String.format("Room %-4d | %-20s | PHP %,8.2f/night | Max: %d pax | %s",
                roomNumber, roomType, pricePerNight, maxOccupancy, getStatus());
    }
}