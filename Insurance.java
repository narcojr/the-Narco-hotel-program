
public class Insurance extends Economy_Room {

    // ---- Insurance Plan Types ----
    public static final String PLAN_BASIC     = "Basic Protection";
    public static final String PLAN_STANDARD  = "Standard Protection";
    public static final String PLAN_PREMIUM   = "Premium Protection";
    public static final String PLAN_NONE      = "No Insurance";

    // ---- Fields ----
    private String  insurancePlan;
    private double  insuranceRatePercent;   // % of room total
    private boolean coversAccident;
    private boolean coversMedical;
    private boolean coversPropertyDamage;
    private boolean coversTripCancellation;
    private boolean coversNaturalDisaster;
    private String  policyNumber;
    private double  insuranceTotalCost;
    private double  coverageLimit;

    // ---- Constructor — No Insurance (default) ----
    public Insurance() {
        super();
        setRoomType("Insurance Add-on");
        this.insurancePlan         = PLAN_NONE;
        this.insuranceRatePercent  = 0.0;
        this.coversAccident        = false;
        this.coversMedical         = false;
        this.coversPropertyDamage  = false;
        this.coversTripCancellation= false;
        this.coversNaturalDisaster = false;
        this.policyNumber          = "N/A";
        this.insuranceTotalCost    = 0.0;
        this.coverageLimit         = 0.0;
    }

    public Insurance(int roomNumber) {
        this();
        setRoomNumber(roomNumber);
    }

    // ---- Factory: Apply a plan based on selected type ----
    public void applyPlan(String plan, double roomTotalCost) {
        this.insurancePlan = plan;
        switch (plan) {
            case PLAN_BASIC:
                insuranceRatePercent   = 3.0;
                coversAccident         = true;
                coversMedical          = false;
                coversPropertyDamage   = false;
                coversTripCancellation = false;
                coversNaturalDisaster  = false;
                coverageLimit          = 50_000.0;
                setDescription("Basic Protection: Covers accidents only. Up to PHP 50,000 limit.");
                break;

            case PLAN_STANDARD:
                insuranceRatePercent   = 5.0;
                coversAccident         = true;
                coversMedical          = true;
                coversPropertyDamage   = true;
                coversTripCancellation = false;
                coversNaturalDisaster  = false;
                coverageLimit          = 150_000.0;
                setDescription("Standard Protection: Covers accidents, medical, and property damage. Up to PHP 150,000 limit.");
                break;

            case PLAN_PREMIUM:
                insuranceRatePercent   = 8.0;
                coversAccident         = true;
                coversMedical          = true;
                coversPropertyDamage   = true;
                coversTripCancellation = true;
                coversNaturalDisaster  = true;
                coverageLimit          = 500_000.0;
                setDescription("Premium Protection: Full coverage — accidents, medical, property, trip cancellation, and natural disasters. Up to PHP 500,000 limit.");
                break;

            default: // PLAN_NONE
                insuranceRatePercent   = 0.0;
                coversAccident         = false;
                coversMedical          = false;
                coversPropertyDamage   = false;
                coversTripCancellation = false;
                coversNaturalDisaster  = false;
                coverageLimit          = 0.0;
                setDescription("No insurance selected.");
                break;
        }
        this.insuranceTotalCost = roomTotalCost * (insuranceRatePercent / 100.0);
        if (!plan.equals(PLAN_NONE)) {
            this.policyNumber = "TNH-" + System.currentTimeMillis() % 1_000_000;
        } else {
            this.policyNumber = "N/A";
        }
    }

    // ---- Static: Get rate for a plan ----
    public static double getRateFor(String plan) {
        switch (plan) {
            case PLAN_BASIC:    return 3.0;
            case PLAN_STANDARD: return 5.0;
            case PLAN_PREMIUM:  return 8.0;
            default:            return 0.0;
        }
    }

    // ---- Static: Get coverage limit ----
    public static double getLimitFor(String plan) {
        switch (plan) {
            case PLAN_BASIC:    return 50_000.0;
            case PLAN_STANDARD: return 150_000.0;
            case PLAN_PREMIUM:  return 500_000.0;
            default:            return 0.0;
        }
    }

    // ---- Getters ----
    public String  getInsurancePlan()          { return insurancePlan;          }
    public double  getInsuranceRatePercent()   { return insuranceRatePercent;   }
    public boolean isCoversAccident()          { return coversAccident;         }
    public boolean isCoversMedical()           { return coversMedical;          }
    public boolean isCoversPropertyDamage()    { return coversPropertyDamage;   }
    public boolean isCoversTripCancellation()  { return coversTripCancellation; }
    public boolean isCoversNaturalDisaster()   { return coversNaturalDisaster;  }
    public String  getPolicyNumber()           { return policyNumber;           }
    public double  getInsuranceTotalCost()     { return insuranceTotalCost;     }
    public double  getCoverageLimit()          { return coverageLimit;          }

    // ---- Setters ----
    public void setInsurancePlan(String insurancePlan)                  { this.insurancePlan          = insurancePlan;          }
    public void setInsuranceRatePercent(double insuranceRatePercent)    { this.insuranceRatePercent   = insuranceRatePercent;   }
    public void setCoversAccident(boolean coversAccident)               { this.coversAccident         = coversAccident;         }
    public void setCoversMedical(boolean coversMedical)                 { this.coversMedical          = coversMedical;          }
    public void setCoversPropertyDamage(boolean coversPropertyDamage)   { this.coversPropertyDamage   = coversPropertyDamage;   }
    public void setCoversTripCancellation(boolean coversTripCancellation){ this.coversTripCancellation = coversTripCancellation; }
    public void setCoversNaturalDisaster(boolean coversNaturalDisaster) { this.coversNaturalDisaster  = coversNaturalDisaster;  }
    public void setPolicyNumber(String policyNumber)                    { this.policyNumber           = policyNumber;           }
    public void setInsuranceTotalCost(double insuranceTotalCost)        { this.insuranceTotalCost     = insuranceTotalCost;     }
    public void setCoverageLimit(double coverageLimit)                  { this.coverageLimit          = coverageLimit;          }

    // ---- Summary ----
    public String getSummary() {
        if (insurancePlan.equals(PLAN_NONE)) return "  No Insurance";
        return String.format(
            "  Plan      : %s\n" +
            "  Rate      : %.0f%% of room total\n" +
            "  Cost      : PHP %,.2f\n" +
            "  Coverage  : PHP %,.2f limit\n" +
            "  Policy #  : %s\n" +
            "  Covers    : %s%s%s%s%s",
            insurancePlan, insuranceRatePercent, insuranceTotalCost, coverageLimit, policyNumber,
            coversAccident         ? "Accidents  "    : "",
            coversMedical          ? "Medical  "      : "",
            coversPropertyDamage   ? "Property  "     : "",
            coversTripCancellation ? "Cancellation  " : "",
            coversNaturalDisaster  ? "Natural Disaster" : ""
        );
    }

    @Override
    public String toString() {
        return String.format("Insurance: %s | Rate: %.0f%% | Cost: PHP %,.2f | Policy: %s",
                insurancePlan, insuranceRatePercent, insuranceTotalCost, policyNumber);
    }
}