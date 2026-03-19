public class User_name {

    private String firstName;
    private String lastName;
    private String contactNumber;
    private String email;
    private String idType;
    private String idNumber;
    private int    numberOfGuests;
    private String paymentMethod;
    private String creditCardNumber;

    // ---- Constructor ----
    public User_name() {}

    public User_name(String firstName, String lastName, String contactNumber,
                    String email, String idType, String idNumber, int numberOfGuests,
                    String paymentMethod, String creditCardNumber) {
        this.firstName        = firstName;
        this.lastName         = lastName;
        this.contactNumber    = contactNumber;
        this.email            = email;
        this.idType           = idType;
        this.idNumber         = idNumber;
        this.numberOfGuests   = numberOfGuests;
        this.paymentMethod    = paymentMethod;
        this.creditCardNumber = creditCardNumber;
    }

    // ---- Getters ----
    public String getFirstName()       { return firstName;        }
    public String getLastName()        { return lastName;         }
    public String getContactNumber()   { return contactNumber;    }
    public String getEmail()           { return email;            }
    public String getIdType()          { return idType;           }
    public String getIdNumber()        { return idNumber;         }
    public int    getNumberOfGuests()  { return numberOfGuests;   }
    public String getPaymentMethod()   { return paymentMethod;    }
    public String getCreditCardNumber(){ return creditCardNumber; }

    // ---- Setters ----
    public void setFirstName(String firstName)               { this.firstName        = firstName;        }
    public void setLastName(String lastName)                 { this.lastName         = lastName;         }
    public void setContactNumber(String contactNumber)       { this.contactNumber    = contactNumber;    }
    public void setEmail(String email)                       { this.email            = email;            }
    public void setIdType(String idType)                     { this.idType           = idType;           }
    public void setIdNumber(String idNumber)                 { this.idNumber         = idNumber;         }
    public void setNumberOfGuests(int numberOfGuests)        { this.numberOfGuests   = numberOfGuests;   }
    public void setPaymentMethod(String paymentMethod)       { this.paymentMethod    = paymentMethod;    }
    public void setCreditCardNumber(String creditCardNumber) { this.creditCardNumber = creditCardNumber; }

    // ---- Methods ----
    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return String.format("Guest: %s %s | Contact: %s | Email: %s | ID: %s - %s | Guests: %d | Payment: %s | Card: %s",
                firstName, lastName, contactNumber, email, idType, idNumber, numberOfGuests, paymentMethod, creditCardNumber);
    }
}