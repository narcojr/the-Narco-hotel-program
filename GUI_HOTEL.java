import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GUI_HOTEL extends JFrame {

    public static void main(String[] args) {
        try { UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName()); } catch (Exception ignored) {}
        SwingUtilities.invokeLater(GUI_HOTEL::new);
    }

    
    //  COLOUR PALETTE
    private static final Color GOLD        = new Color(212, 175,   55);
    private static final Color GOLD_LIGHT  = new Color(218, 180,  30);
    private static final Color GOLD_DARK   = new Color(0, 0,   0);
    private static final Color BG_WHITE    = new Color(250, 250, 250);
    private static final Color BG_LGREY    = new Color(255, 255, 255);  // very light grey (main bg)
    private static final Color BG_GREY     = new Color(255, 255, 255);  // light grey panels
    private static final Color PANEL_GREY  = new Color(250, 250, 250);  // borders / dividers
    private static final Color BG_DARK     = new Color( 0,  0,  139);  // dark blue background
    private static final Color TEXT_DARK   = new Color( 10,  0,  0);
    private static final Color BTN_HOVER   = new Color(250, 255,  255);
    private static final Color SUCCESS     = new Color( 34, 139,  34);
    private static final Color ERROR_RED   = new Color(180,  30,  30);
    private static final Color INSURE_BLUE = new Color( 20,  80, 190);

    private static final SimpleDateFormat SDF = new SimpleDateFormat("MM/dd/yyyy");

    
    //  ROOM INVENTORY  (80 rooms)
    private final List<Economy_Room>         allRooms      = new ArrayList<>();
    private final Map<Integer, Insurance>    roomInsurance = new HashMap<>();
    // Store nights for checkout bill
    private final Map<Integer, Long>         roomNights    = new HashMap<>();

    
    //  TAB 1 COMPONENTS
    private JComboBox<String> cbRoomType, cbRoomNumber;
    private JTextField tfFirstName, tfLastName, tfContact, tfEmail, tfIdNumber;
    private JComboBox<String> cbIdType, cbInsurancePlan;
    private JSpinner spGuests, spCheckIn, spCheckOut;
    private JTextArea taRoomDesc;
    private JTextArea insDescArea;
    private JLabel lblPrice, lblAvailability, lblInsuranceCost;
    private JLabel lblCovAcc, lblCovMed, lblCovProp, lblCovTrip, lblCovNat;
    private JLabel lblNights, lblRoomTotal, lblInsTotal, lblGrandTotal;

    //  TAB 2 COMPONENTS
    private JComboBox<String>  cbFilterType;
    private JTable             availTable;
    private DefaultTableModel  availModel;
    private JLabel             lblAvailCount, lblOccupiedCount;

    //  TAB 3 COMPONENTS
    private JTable            reservTable;
    private DefaultTableModel reservModel;

    //  TAB 4 COMPONENTS
    private JComboBox<String> cbCheckoutRoom;
    private JTextArea         taCheckoutInfo;

    //  CONSTRUCTOR
    public GUI_HOTEL() {
        initRooms();
        buildUI();
        setTitle("the 9/11 Reservation System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1030, 780);
        setMinimumSize(new Dimension(920, 680));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    //  INIT 80 ROOMS
    private void initRooms() {
        for (int i = 1; i <= 20; i++) allRooms.add(new Economy_Room   (100 + i));
        for (int i = 1; i <= 10; i++) allRooms.add(new couple_room    (200 + i));
        for (int i = 1; i <= 10; i++) allRooms.add(new Superior_Room  (210 + i));
        for (int i = 1; i <= 10; i++) allRooms.add(new Deluxe_Room    (300 + i));
        for (int i = 1; i <= 10; i++) allRooms.add(new Executive_Room (310 + i));
        for (int i = 1; i <=  8; i++) allRooms.add(new Junior_Suite   (400 + i));
        for (int i = 1; i <=  6; i++) allRooms.add(new Master_Suite   (500 + i));
        for (int i = 1; i <=  4; i++) allRooms.add(new Penthouse_Suite(600 + i));
        for (int i = 1; i <=  2; i++) allRooms.add(new VIP            (700 + i));
    }

    //  MAIN UI BUILD
    private void buildUI() {
        setLayout(new BorderLayout());
        getContentPane().setBackground(BG_DARK);
        add(buildHeader(),  BorderLayout.NORTH);
        add(buildTabs(),    BorderLayout.CENTER);
        add(buildFooter(),  BorderLayout.SOUTH);
    }

    // HEADER 
    private JPanel buildHeader() {
        JPanel h = new JPanel(new BorderLayout());
        h.setBackground(BG_DARK);
        h.setBorder(new EmptyBorder(16, 24, 12, 24));

        JLabel title = new JLabel("THE NINE ELEVENTH TOWER");
        title.setFont(new Font("Georgia", Font.BOLD, 31));
        title.setForeground(GOLD);

        JLabel sub = new JLabel(" Luxury Room Reservations | WATCH FOR THE PLANES ");
        sub.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        sub.setForeground(new Color(200, 200, 200));

        JPanel tp = new JPanel();
        tp.setOpaque(false);
        tp.setLayout(new BoxLayout(tp, BoxLayout.Y_AXIS));
        tp.add(title); tp.add(sub);

        JLabel cnt = new JLabel("ROOMS: 80 Total  |  Available: " + countAvailable() + "  ");
        cnt.setFont(new Font("Segoe UI", Font.BOLD, 12));
        cnt.setForeground(GOLD_LIGHT);

        h.add(tp,  BorderLayout.WEST);
        h.add(cnt, BorderLayout.EAST);

        JSeparator sep = new JSeparator();
        sep.setForeground(GOLD_DARK);

        JPanel wrap = new JPanel(new BorderLayout());
        wrap.setOpaque(false);
        wrap.add(h,   BorderLayout.CENTER);
        wrap.add(sep, BorderLayout.SOUTH);
        return wrap;
    }

    private JLabel buildFooter() {
        JLabel f = new JLabel(
            "  © THE TRUMP TOWER  |  80 Rooms  |  24/7 Service  ",
            SwingConstants.CENTER);
        f.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        f.setForeground(GOLD);
        f.setBackground(BG_DARK);
        f.setOpaque(true);
        f.setBorder(new EmptyBorder(4, 0, 4, 0));
        return f;
    }

    // TABS
    private JTabbedPane buildTabs() {
        JTabbedPane tp = new JTabbedPane(JTabbedPane.TOP);
        tp.setFont(new Font("Segoe UI", Font.BOLD, 13));
        tp.setBackground(BG_GREY);
        tp.setForeground(GOLD);
        tp.addTab("📋  Make Reservation",   buildReservationTab());
        tp.addTab("🔍  Check Availability", buildAvailabilityTab());
        tp.addTab("📖  All Reservations",   buildReservListTab());
        tp.addTab("🚪  Check Out",          buildCheckoutTab());
        return tp;
    }

    //  TAB 1 — MAKE RESERVATION
    private JPanel buildReservationTab() {
        JPanel main = new JPanel(new BorderLayout(10, 10));
        main.setBackground(BG_LGREY);
        main.setBorder(new EmptyBorder(12, 14, 8, 14));

        // Row 1: Room Selection | Guest Info
        JPanel row1 = new JPanel(new GridLayout(1, 2, 12, 0));
        row1.setOpaque(false);
        row1.add(buildRoomPanel());
        row1.add(buildGuestPanel());

        // Row 2: Date Scroll Picker | Insurance
        JPanel row2 = new JPanel(new GridLayout(1, 2, 12, 0));
        row2.setOpaque(false);
        row2.add(buildDatePanel());
        row2.add(buildInsurancePanel());

        // Row 3: Price Summary
        JPanel row3 = buildPriceSummary();

        JPanel center = new JPanel();
        center.setOpaque(false);
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.add(row1);
        center.add(Box.createVerticalStrut(10));
        center.add(row2);
        center.add(Box.createVerticalStrut(10));
        center.add(row3);

        JButton btnOk    = goldButton("✔  CONFIRM RESERVATION");
        JButton btnClear = greyButton("✖  CLEAR FORM");
        btnOk.addActionListener(e -> processReservation());
        btnClear.addActionListener(e -> clearForm());

        JPanel btnRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 14, 6));
        btnRow.setBackground(BG_LGREY);
        btnRow.add(btnOk); btnRow.add(btnClear);

        main.add(center, BorderLayout.CENTER);
        main.add(btnRow, BorderLayout.SOUTH);
        return main;
    }

    // Room Selection panel 
    private JPanel buildRoomPanel() {
        JPanel p = white("Room Selection");

        String[] types = {"Economy Room","Couple Room","Superior Room","Deluxe Room",
                    "Executive Room","Junior Suite","Master Suite","Penthouse Suite","VIP Room"};
        cbRoomType   = new JComboBox<>(types); styleCombo(cbRoomType);
        cbRoomNumber = new JComboBox<>();       styleCombo(cbRoomNumber);

        taRoomDesc = new JTextArea(3, 22);
        taRoomDesc.setLineWrap(true); taRoomDesc.setWrapStyleWord(true);
        taRoomDesc.setEditable(false);
        taRoomDesc.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        taRoomDesc.setBackground(BG_GREY);

        lblPrice        = goldLabel("PHP 0.00 / night");
        lblAvailability = new JLabel("—");
        lblAvailability.setFont(new Font("Segoe UI", Font.BOLD, 12));

        cbRoomType.addActionListener(e -> { updateRoomNumberCombo(); updateRoomInfo(); recalcPrice(); });
        cbRoomNumber.addActionListener(e -> { updateRoomInfo(); recalcPrice(); });

        p.add(formRow("Room Type:",   cbRoomType));
        p.add(formRow("Room Number:", cbRoomNumber));
        p.add(wrapLabel("Description:"));
        p.add(new JScrollPane(taRoomDesc));
        p.add(formRow("Price/Night:", lblPrice));
        p.add(formRow("Status:",      lblAvailability));
        return p;
    }

    // Guest Info panel 
    private JPanel buildGuestPanel() {
        JPanel p = white("Guest Information");

        tfFirstName = sf(); tfLastName = sf(); tfContact = sf();
        tfEmail = sf(); tfIdNumber = sf();

        String[] ids = {"Passport","Driver's License","SSS ID","PhilHealth ID","Voter's ID","School ID"};
        cbIdType = new JComboBox<>(ids); styleCombo(cbIdType);

        spGuests = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        spGuests.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        p.add(formRow("First Name:",    tfFirstName));
        p.add(formRow("Last Name:",     tfLastName));
        p.add(formRow("Contact No.:",   tfContact));
        p.add(formRow("Email:",         tfEmail));
        p.add(formRow("ID Type:",       cbIdType));
        p.add(formRow("ID Number:",     tfIdNumber));
        p.add(formRow("No. of Guests:", spGuests));
        return p;
    }

    // Scroll Date Picker panel 
    private JPanel buildDatePanel() {
        JPanel p = white("Check-In / Check-Out  ▲▼ Scroll to change date");

        Calendar cal = Calendar.getInstance();
        Date today = cal.getTime();
        cal.add(Calendar.DAY_OF_MONTH, 1);
        Date tomorrow = cal.getTime();

        spCheckIn  = new JSpinner(new SpinnerDateModel(today,    today, null, Calendar.DAY_OF_MONTH));
        spCheckOut = new JSpinner(new SpinnerDateModel(tomorrow, today, null, Calendar.DAY_OF_MONTH));

        spCheckIn.setEditor( new JSpinner.DateEditor(spCheckIn,  "MM/dd/yyyy"));
        spCheckOut.setEditor(new JSpinner.DateEditor(spCheckOut, "MM/dd/yyyy"));
        styleSpinner(spCheckIn);
        styleSpinner(spCheckOut);

        javax.swing.event.ChangeListener cl = e -> {
            Date ci = (Date) spCheckIn.getValue();
            Date co = (Date) spCheckOut.getValue();
            if (!co.after(ci)) {
                Calendar c = Calendar.getInstance();
                c.setTime(ci);
                c.add(Calendar.DAY_OF_MONTH, 1);
                spCheckOut.setValue(c.getTime());
            }
            recalcPrice();
        };
        spCheckIn.addChangeListener(cl);
        spCheckOut.addChangeListener(cl);

        JLabel tip = new JLabel("  Use scroll wheel ↑↓ or arrow buttons to pick dates");
        tip.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        tip.setForeground(new Color(130, 130, 130));

        JLabel tip2 = new JLabel("  Minimum stay: 1 night");
        tip2.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        tip2.setForeground(new Color(130, 130, 130));

        p.add(formRow("Check-In Date: ",  spCheckIn));
        p.add(Box.createVerticalStrut(6));
        p.add(formRow("Check-Out Date:", spCheckOut));
        p.add(Box.createVerticalStrut(8));
        p.add(tip);
        p.add(tip2);
        return p;
    }

    // Insurance panel 
    private JPanel buildInsurancePanel() {
        JPanel p = white("Insurance Options");

        String[] plans = {
            Insurance.PLAN_NONE,
            Insurance.PLAN_BASIC     + "  (3% of room total)",
            Insurance.PLAN_STANDARD  + "  (5% of room total)",
            Insurance.PLAN_PREMIUM   + "  (8% of room total)"
        };
        cbInsurancePlan = new JComboBox<>(plans); styleCombo(cbInsurancePlan);
        cbInsurancePlan.addActionListener(e -> { updateInsuranceUI(); recalcPrice(); });

        lblInsuranceCost = new JLabel("PHP 0.00");
        lblInsuranceCost.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblInsuranceCost.setForeground(INSURE_BLUE);

        insDescArea = new JTextArea(3, 22);
        insDescArea.setLineWrap(true); insDescArea.setWrapStyleWord(true);
        insDescArea.setEditable(false);
        insDescArea.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        insDescArea.setBackground(BG_GREY);
        insDescArea.setText("No insurance selected.");

        lblCovAcc  = covBadge("Accidents");
        lblCovMed  = covBadge("Medical");
        lblCovProp = covBadge("Property");
        lblCovTrip = covBadge("Trip Cancel");
        lblCovNat  = covBadge("Nat. Disaster");

        p.add(formRow("Insurance Plan:", cbInsurancePlan));
        p.add(formRow("Insurance Cost:", lblInsuranceCost));
        p.add(wrapLabel("Plan Description:"));
        p.add(new JScrollPane(insDescArea));
        p.add(wrapLabel("What's Covered:"));

        JPanel badges = new JPanel(new GridLayout(1, 5, 4, 0));
        badges.setOpaque(false);
        badges.setBorder(new EmptyBorder(2, 8, 4, 8));
        badges.add(lblCovAcc); badges.add(lblCovMed); badges.add(lblCovProp);
        badges.add(lblCovTrip); badges.add(lblCovNat);
        p.add(badges);

        updateInsuranceUI();
        return p;
    }

    private JLabel covBadge(String text) {
        JLabel l = new JLabel(text, SwingConstants.CENTER);
        l.setFont(new Font("Segoe UI", Font.BOLD, 9));
        l.setOpaque(true);
        l.setBackground(PANEL_GREY);
        l.setForeground(Color.GRAY);
        l.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                new EmptyBorder(3, 2, 3, 2)));
        return l;
    }

    private void updateInsuranceUI() {
        String plan = getInsurancePlan();
        boolean acc  = !plan.equals(Insurance.PLAN_NONE);
        boolean med  = plan.equals(Insurance.PLAN_STANDARD) || plan.equals(Insurance.PLAN_PREMIUM);
        boolean prop = plan.equals(Insurance.PLAN_STANDARD) || plan.equals(Insurance.PLAN_PREMIUM);
        boolean trip = plan.equals(Insurance.PLAN_PREMIUM);
        boolean nat  = plan.equals(Insurance.PLAN_PREMIUM);

        badge(lblCovAcc,  acc);  badge(lblCovMed,  med);
        badge(lblCovProp, prop); badge(lblCovTrip, trip); badge(lblCovNat, nat);

        String desc;
        switch (plan) {
            case Insurance.PLAN_BASIC:
                desc = "Basic (3%): Accident coverage only. Up to PHP 50,000 limit."; break;
            case Insurance.PLAN_STANDARD:
                desc = "Standard (5%): Covers accidents, medical expenses, and property damage. Up to PHP 150,000 limit."; break;
            case Insurance.PLAN_PREMIUM:
                desc = "Premium (8%): Full coverage — accidents, medical, property damage, trip cancellation, and natural disasters. Up to PHP 500,000 limit."; break;
            default:
                desc = "No insurance selected. You will not be covered during your stay."; break;
        }
        if (insDescArea != null) insDescArea.setText(desc);
    }

    private void badge(JLabel l, boolean on) {
        l.setBackground(on ? new Color(220, 255, 220) : PANEL_GREY);
        l.setForeground(on ? SUCCESS : Color.GRAY);
        l.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(on ? SUCCESS : new Color(200, 200, 200), 1),
                new EmptyBorder(3, 2, 3, 2)));
    }

    //Price Summary bar
    private JPanel buildPriceSummary() {
        JPanel p = new JPanel(new GridLayout(1, 4, 10, 0));
        p.setOpaque(false);
        p.setMaximumSize(new Dimension(Integer.MAX_VALUE, 62));

        lblNights     = sumCard("Nights",       "0 nights",   GOLD_DARK);
        lblRoomTotal  = sumCard("Room Total",   "PHP 0.00",   GOLD);
        lblInsTotal   = sumCard("Insurance",    "PHP 0.00",   INSURE_BLUE);
        lblGrandTotal = sumCard("GRAND TOTAL",  "PHP 0.00",   SUCCESS);

        p.add(lblNights); p.add(lblRoomTotal);
        p.add(lblInsTotal); p.add(lblGrandTotal);
        return p;
    }

    private JLabel sumCard(String header, String val, Color col) {
        JLabel l = new JLabel(fmt(header, val, col), SwingConstants.CENTER);
        l.setOpaque(true);
        l.setBackground(BG_WHITE);
        l.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(col, 2),
                new EmptyBorder(6, 8, 6, 8)));
        l.putClientProperty("header", header);
        l.putClientProperty("col",    col);
        return l;
    }

    private String fmt(String header, String val, Color col) {
        return "<html><center><span style='font-size:10px;color:#888'>" + header +
               "</span><br><b style='font-size:13px;color:#" + hex(col) + "'>" + val + "</b></center></html>";
    }

    private void updateCard(JLabel card, String val) {
        String h = (String) card.getClientProperty("header");
        Color  c = (Color)  card.getClientProperty("col");
        card.setText(fmt(h, val, c));
    }

    private String hex(Color c) {
        return String.format("%02x%02x%02x", c.getRed(), c.getGreen(), c.getBlue());
    }


    //  PRICE CALCULATION
    
    private void recalcPrice() {
        Date ci = (Date) spCheckIn.getValue();
        Date co = (Date) spCheckOut.getValue();
        long nights = Math.max(1, TimeUnit.MILLISECONDS.toDays(co.getTime() - ci.getTime()));

        double pricePer  = getSelectedRoomPrice();
        double roomTotal = pricePer * nights;

        String plan      = getInsurancePlan();
        double insRate   = Insurance.getRateFor(plan);
        double insCost   = roomTotal * (insRate / 100.0);
        double grand     = roomTotal + insCost;

        if (lblInsuranceCost != null) lblInsuranceCost.setText(String.format("PHP %,.2f", insCost));
        updateCard(lblNights,     nights + " night" + (nights == 1 ? "" : "s"));
        updateCard(lblRoomTotal,  String.format("PHP %,.2f", roomTotal));
        updateCard(lblInsTotal,   String.format("PHP %,.2f", insCost));
        updateCard(lblGrandTotal, String.format("PHP %,.2f", grand));
    }

    private double getSelectedRoomPrice() {
        String item = (String) cbRoomNumber.getSelectedItem();
        if (item == null || item.startsWith("No")) return 0;
        try {
            Economy_Room r = findRoom(Integer.parseInt(item.replace("Room ", "").trim()));
            return r != null ? r.getPricePerNight() : 0;
        } catch (Exception e) { return 0; }
    }

    private String getInsurancePlan() {
        if (cbInsurancePlan == null) return Insurance.PLAN_NONE;
        String s = (String) cbInsurancePlan.getSelectedItem();
        if (s == null || s.startsWith(Insurance.PLAN_NONE)) return Insurance.PLAN_NONE;
        if (s.startsWith(Insurance.PLAN_PREMIUM))  return Insurance.PLAN_PREMIUM;
        if (s.startsWith(Insurance.PLAN_STANDARD)) return Insurance.PLAN_STANDARD;
        if (s.startsWith(Insurance.PLAN_BASIC))    return Insurance.PLAN_BASIC;
        return Insurance.PLAN_NONE;
    }


    //  TAB 2 — CHECK AVAILABILITY
    private JPanel buildAvailabilityTab() {
        JPanel main = new JPanel(new BorderLayout(8, 8));
        main.setBackground(BG_LGREY);
        main.setBorder(new EmptyBorder(14, 18, 14, 18));

        JPanel bar = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 4));
        bar.setBackground(BG_WHITE);
        bar.setBorder(createGoldBorder("Filter Rooms"));

        String[] opts = {"All Rooms","Available Only","Occupied Only",
                "Economy Room","Couple Room","Superior Room","Deluxe Room",
                "Executive Room","Junior Suite","Master Suite","Penthouse Suite","VIP Room"};
        cbFilterType = new JComboBox<>(opts); styleCombo(cbFilterType);
        cbFilterType.addActionListener(e -> refreshAvailTable());

        JButton btnR = goldButton("🔄  Refresh");
        btnR.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnR.addActionListener(e -> refreshAvailTable());

        lblAvailCount    = new JLabel(); styleStatLabel(lblAvailCount,    SUCCESS);
        lblOccupiedCount = new JLabel(); styleStatLabel(lblOccupiedCount, ERROR_RED);

        bar.add(new JLabel("  Show: ")); bar.add(cbFilterType); bar.add(btnR);
        bar.add(Box.createHorizontalStrut(16));
        bar.add(lblAvailCount); bar.add(Box.createHorizontalStrut(8)); bar.add(lblOccupiedCount);

        String[] cols = {"Room No.","Floor","Type","Price/Night","Max","Status","Guest","Check-In","Check-Out"};
        availModel = new DefaultTableModel(cols, 0) { public boolean isCellEditable(int r, int c) { return false; } };
        availTable = new JTable(availModel); styleTable(availTable);

        JScrollPane sc = new JScrollPane(availTable);
        sc.setBorder(BorderFactory.createLineBorder(GOLD, 1));

        main.add(bar, BorderLayout.NORTH);
        main.add(sc,  BorderLayout.CENTER);
        refreshAvailTable();
        return main;
    }

   
    //  TAB 3 — ALL RESERVATIONS
    
    private JPanel buildReservListTab() {
        JPanel main = new JPanel(new BorderLayout(8, 8));
        main.setBackground(BG_LGREY);
        main.setBorder(new EmptyBorder(14, 18, 14, 18));

        JPanel bar = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 4));
        bar.setBackground(BG_WHITE);
        bar.setBorder(createGoldBorder("Active Reservations"));
        JButton r = goldButton("🔄  Refresh"); r.addActionListener(e -> refreshReservTable());
        bar.add(r);

        String[] cols = {"Room No.","Type","Price/Night","Nights","Room Total","Insurance","Grand Total","Guest","Check-In","Check-Out"};
        reservModel = new DefaultTableModel(cols, 0) { public boolean isCellEditable(int r2, int c) { return false; } };
        reservTable = new JTable(reservModel); styleTable(reservTable);

        JScrollPane sc = new JScrollPane(reservTable);
        sc.setBorder(BorderFactory.createLineBorder(GOLD, 1));

        main.add(bar, BorderLayout.NORTH);
        main.add(sc,  BorderLayout.CENTER);
        refreshReservTable();
        return main;
    }

    
    //  TAB 4 — CHECK OUT
    private JPanel buildCheckoutTab() {
        JPanel main = new JPanel(new BorderLayout(10, 10));
        main.setBackground(BG_LGREY);
        main.setBorder(new EmptyBorder(20, 60, 20, 60));

        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(BG_WHITE);
        card.setBorder(createGoldBorder("Guest Check-Out"));

        cbCheckoutRoom = new JComboBox<>(); styleCombo(cbCheckoutRoom);
        cbCheckoutRoom.addActionListener(e -> showCheckoutInfo());

        taCheckoutInfo = new JTextArea(10, 36);
        taCheckoutInfo.setEditable(false);
        taCheckoutInfo.setFont(new Font("Courier New", Font.PLAIN, 13));
        taCheckoutInfo.setBackground(BG_GREY);
        taCheckoutInfo.setBorder(new EmptyBorder(8, 10, 8, 10));

        JButton btnCO = goldButton("🚪  PROCESS CHECK-OUT");
        btnCO.addActionListener(e -> processCheckout());

        card.add(formRow("Select Occupied Room:", cbCheckoutRoom));
        card.add(Box.createVerticalStrut(8));
        card.add(wrapLabel("Reservation Details:"));
        card.add(new JScrollPane(taCheckoutInfo));
        card.add(Box.createVerticalStrut(10));
        JPanel bp = new JPanel(new FlowLayout(FlowLayout.CENTER)); bp.setOpaque(false); bp.add(btnCO);
        card.add(bp);

        main.add(card, BorderLayout.CENTER);
        refreshCheckoutCombo();
        return main;
    }

    
    //  BUSINESS LOGIC
    private void processReservation() {
        String item = (String) cbRoomNumber.getSelectedItem();
        if (item == null || item.startsWith("No")) { showMsg("No available room selected.", "Error", JOptionPane.ERROR_MESSAGE); return; }

        String first = tfFirstName.getText().trim(), last = tfLastName.getText().trim();
        String contact = tfContact.getText().trim(), idNum = tfIdNumber.getText().trim();
        if (first.isEmpty() || last.isEmpty() || contact.isEmpty() || idNum.isEmpty()) {
            showMsg("Please fill in all required fields.", "Incomplete Form", JOptionPane.WARNING_MESSAGE); return;
        }

        Date ci = (Date) spCheckIn.getValue();
        Date co = (Date) spCheckOut.getValue();
        if (!co.after(ci)) { showMsg("Check-out must be after check-in.", "Invalid Dates", JOptionPane.WARNING_MESSAGE); return; }

        long nights = Math.max(1, TimeUnit.MILLISECONDS.toDays(co.getTime() - ci.getTime()));
        int rn = Integer.parseInt(item.replace("Room ", "").trim());
        Economy_Room room = findRoom(rn);
        if (room == null) { showMsg("Room not found.", "Error", JOptionPane.ERROR_MESSAGE); return; }

        double roomTotal = room.getPricePerNight() * nights;
        String plan      = getInsurancePlan();
        Insurance ins    = new Insurance(rn);
        ins.applyPlan(plan, roomTotal);

        double insCost   = ins.getInsuranceTotalCost();
        double grand     = roomTotal + insCost;

        User_name guest = new User_name(first, last, contact,
                tfEmail.getText().trim(), (String) cbIdType.getSelectedItem(), idNum, (int) spGuests.getValue(), plan);

        boolean ok = room.reserve(guest.getFullName(), SDF.format(ci), SDF.format(co));
        if (ok) {
            roomInsurance.put(rn, ins);
            roomNights.put(rn, nights);

            String receipt = String.format(
                "╔═══════════════════════════════════════════╗\n" +
                "║    THE NINE ELEVENTH TOWER — RESERVATION  ║\n" +
                "╠═══════════════════════════════════════════╣\n" +
                "  Room Number  : %d  (Floor %d)\n" +
                "  Room Type    : %s\n" +
                "─────────────────────────────────────────────\n" +
                "  Guest        : %s\n" +
                "  Contact      : %s\n" +
                "  ID           : %s — %s\n" +
                "  Guests       : %d person(s)\n" +
                "─────────────────────────────────────────────\n" +
                "  Check-In     : %s\n" +
                "  Check-Out    : %s\n" +
                "  Stay         : %d night(s)\n" +
                "─────────────────────────────────────────────\n" +
                "  Price/Night  : PHP %,.2f\n" +
                "  Room Total   : PHP %,.2f\n" +
                "  Insurance    : %s\n" +
                "  Ins. Cost    : PHP %,.2f  (%.0f%% of room total)\n" +
                "  Policy #     : %s\n" +
                "  Coverage Lim : PHP %,.2f\n" +
                "═════════════════════════════════════════════\n" +
                "  Pay over the counter upon check-in.\n" +
                "  GRAND TOTAL  : PHP %,.2f\n" +
                "╚═══════════════════════════════════════════╝\n" +
                "  Thank you for choosing 9/11 hotel!",
                room.getRoomNumber(), room.getFloor(), room.getRoomType(),
                guest.getFullName(), guest.getContactNumber(),
                cbIdType.getSelectedItem(), idNum, (int) spGuests.getValue(),
                SDF.format(ci), SDF.format(co), nights,
                room.getPricePerNight(), roomTotal,
                plan, insCost, Insurance.getRateFor(plan),
                ins.getPolicyNumber(), Insurance.getLimitFor(plan),
                grand);

            JTextArea ta = new JTextArea(receipt);
            ta.setFont(new Font("Courier New", Font.PLAIN, 12));
            ta.setEditable(false);
            ta.setBackground(BG_LGREY);
            JScrollPane sp = new JScrollPane(ta);
            sp.setPreferredSize(new Dimension(500, 360));
            JOptionPane.showMessageDialog(this, sp, "✔ Reservation Confirmed", JOptionPane.INFORMATION_MESSAGE);

            clearForm();
            refreshAll();
        } else {
            showMsg("Room is no longer available.", "Reservation Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearForm() {
        tfFirstName.setText(""); tfLastName.setText(""); tfContact.setText("");
        tfEmail.setText(""); tfIdNumber.setText("");
        spGuests.setValue(1);
        Calendar cal = Calendar.getInstance();
        spCheckIn.setValue(cal.getTime());
        cal.add(Calendar.DAY_OF_MONTH, 1);
        spCheckOut.setValue(cal.getTime());
        cbRoomType.setSelectedIndex(0);
        cbInsurancePlan.setSelectedIndex(0);
        updateRoomNumberCombo(); updateRoomInfo(); updateInsuranceUI(); recalcPrice();
    }

    private void updateRoomNumberCombo() {
        String type = (String) cbRoomType.getSelectedItem();
        cbRoomNumber.removeAllItems();
        for (Economy_Room r : allRooms)
            if (r.getRoomType().equals(type) && r.isAvailable())
                cbRoomNumber.addItem("Room " + r.getRoomNumber());
        if (cbRoomNumber.getItemCount() == 0) cbRoomNumber.addItem("No rooms available");
    }

    private void updateRoomInfo() {
        String item = (String) cbRoomNumber.getSelectedItem();
        if (item == null || item.startsWith("No")) {
            if (taRoomDesc != null)     taRoomDesc.setText("No available rooms of this type.");
            if (lblPrice != null)       lblPrice.setText("PHP —");
            if (lblAvailability != null){ lblAvailability.setText("✖ No rooms available"); lblAvailability.setForeground(ERROR_RED); }
            return;
        }
        Economy_Room r = findRoom(Integer.parseInt(item.replace("Room ", "").trim()));
        if (r != null) {
            taRoomDesc.setText(r.getDescription());
            lblPrice.setText(String.format("PHP %,.2f / night", r.getPricePerNight()));
            lblAvailability.setText("✔ Available");
            lblAvailability.setForeground(SUCCESS);
        }
    }

    private void refreshAvailTable() {
        availModel.setRowCount(0);
        String filter = (String) cbFilterType.getSelectedItem();
        int av = 0, oc = 0;
        for (Economy_Room r : allRooms) {
            boolean show = filter.equals("All Rooms")
                    || (filter.equals("Available Only") && r.isAvailable())
                    || (filter.equals("Occupied Only")  && !r.isAvailable())
                    || filter.equals(r.getRoomType());
            if (show) availModel.addRow(new Object[]{
                r.getRoomNumber(), r.getFloor(), r.getRoomType(),
                String.format("PHP %,.2f", r.getPricePerNight()), r.getMaxOccupancy(),
                r.isAvailable() ? "✔ Available" : "✖ Occupied",
                r.isAvailable() ? "—" : r.getGuestName(),
                r.isAvailable() ? "—" : r.getCheckInDate(),
                r.isAvailable() ? "—" : r.getCheckOutDate()
            });
            if (r.isAvailable()) av++; else oc++;
        }
        lblAvailCount.setText("  ✔ Available: " + av + "  ");
        lblOccupiedCount.setText("  ✖ Occupied: " + oc + "  ");

        availTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable t, Object v,
                    boolean sel, boolean foc, int row, int col) {
                Component c = super.getTableCellRendererComponent(t, v, sel, foc, row, col);
                String st = (String) t.getModel().getValueAt(row, 5);
                if (!sel) {
                    c.setBackground(st.contains("Available") ? new Color(244,255,244) : new Color(255,244,244));
                    c.setForeground(TEXT_DARK);
                }
                return c;
            }
        });
    }

    private void refreshReservTable() {
        reservModel.setRowCount(0);
        for (Economy_Room r : allRooms) {
            if (!r.isAvailable()) {
                Insurance ins = roomInsurance.get(r.getRoomNumber());
                long nights = roomNights.getOrDefault(r.getRoomNumber(), 1L);
                double roomTotal  = r.getPricePerNight() * nights;
                double insCost    = ins != null ? ins.getInsuranceTotalCost() : 0;
                double grand      = roomTotal + insCost;
                reservModel.addRow(new Object[]{
                    r.getRoomNumber(), r.getRoomType(),
                    String.format("PHP %,.2f", r.getPricePerNight()),
                    nights + " night(s)",
                    String.format("PHP %,.2f", roomTotal),
                    ins != null ? ins.getInsurancePlan() : "None",
                    String.format("PHP %,.2f", grand),
                    r.getGuestName(), r.getCheckInDate(), r.getCheckOutDate()
                });
            }
        }
    }

    private void refreshCheckoutCombo() {
        cbCheckoutRoom.removeAllItems();
        cbCheckoutRoom.addItem("-- Select a Room --");
        for (Economy_Room r : allRooms)
            if (!r.isAvailable())
                cbCheckoutRoom.addItem("Room " + r.getRoomNumber() + " — " + r.getGuestName());
    }

    private void showCheckoutInfo() {
        String sel = (String) cbCheckoutRoom.getSelectedItem();
        if (sel == null || sel.startsWith("--")) { taCheckoutInfo.setText(""); return; }
        int rn = Integer.parseInt(sel.split(" ")[1]);
        Economy_Room r = findRoom(rn);
        if (r == null || r.isAvailable()) return;

        Insurance ins  = roomInsurance.get(rn);
        long nights    = roomNights.getOrDefault(rn, 1L);
        double roomTot = r.getPricePerNight() * nights;
        double insCost = ins != null ? ins.getInsuranceTotalCost() : 0;
        double grand   = roomTot + insCost;

        taCheckoutInfo.setText(String.format(
            "  Room Number  : %d  (%s)\n" +
            "  Guest        : %s\n" +
            "  Check-In     : %s\n" +
            "  Check-Out    : %s\n" +
            "  Nights       : %d night(s)\n" +
            " ─────────────────────────────────────\n" +
            "  Price/Night  : PHP %,.2f\n" +
            "  Room Total   : PHP %,.2f\n" +
            "  Insurance    : %s\n" +
            "  Ins. Cost    : PHP %,.2f\n" +
            " ─────────────────────────────────────\n" +
            "  GRAND TOTAL  : PHP %,.2f\n\n" +
            "  Press 'PROCESS CHECK-OUT' to vacate.",
            r.getRoomNumber(), r.getRoomType(),
            r.getGuestName(), r.getCheckInDate(), r.getCheckOutDate(), nights,
            r.getPricePerNight(), roomTot,
            ins != null ? ins.getInsurancePlan() : "None",
            insCost, grand));
    }

    private void processCheckout() {
        String sel = (String) cbCheckoutRoom.getSelectedItem();
        if (sel == null || sel.startsWith("--")) { showMsg("Please select an occupied room.", "No Room", JOptionPane.WARNING_MESSAGE); return; }
        int rn = Integer.parseInt(sel.split(" ")[1]);
        Economy_Room r = findRoom(rn);
        if (r != null) {
            String g = r.getGuestName();
            if (JOptionPane.showConfirmDialog(this, "Check out \"" + g + "\" from Room " + rn + "?",
                    "Confirm Check-Out", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                r.checkout();
                roomInsurance.remove(rn);
                roomNights.remove(rn);
                showMsg("✔ Check-out complete!\n\nRoom " + rn + " is now available.\nThank you, " + g + "!",
                        "Check-Out Complete", JOptionPane.INFORMATION_MESSAGE);
                refreshAll();
                taCheckoutInfo.setText("");
            }
        }
    }

    // REFRESH ALL
    private void refreshAll() {
        updateRoomNumberCombo(); updateRoomInfo();
        refreshAvailTable(); refreshReservTable(); refreshCheckoutCombo();
    }

    //  UTILITY
    private Economy_Room findRoom(int n) { for (Economy_Room r : allRooms) if (r.getRoomNumber() == n) return r; return null; }
    private int countAvailable()         { int n = 0; for (Economy_Room r : allRooms) if (r.isAvailable()) n++; return n; }
    private void showMsg(String m, String t, int type) { JOptionPane.showMessageDialog(this, m, t, type); }

    //  STYLE HELPERS
    private JPanel white(String title) {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBackground(BG_WHITE);
        p.setBorder(createGoldBorder(title));
        return p;
    }

    private JTextField sf() {
        JTextField tf = new JTextField(18);
        tf.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tf.setBackground(BG_LGREY);
        tf.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(GOLD_LIGHT, 1), new EmptyBorder(3, 6, 3, 6)));
        return tf;
    }

    private void styleCombo(JComboBox<?> cb) {
        cb.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        cb.setBackground(BG_LGREY);
        cb.setForeground(TEXT_DARK);
    }

    private void styleSpinner(JSpinner sp) {
        sp.setBorder(BorderFactory.createLineBorder(GOLD_LIGHT, 1));
        JComponent ed = sp.getEditor();
        if (ed instanceof JSpinner.DateEditor) {
            JFormattedTextField tf = ((JSpinner.DateEditor) ed).getTextField();
            tf.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            tf.setBackground(BG_LGREY);
            tf.setForeground(TEXT_DARK);
            tf.setHorizontalAlignment(SwingConstants.CENTER);
        }
    }

    private void styleTable(JTable t) {
        t.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        t.setRowHeight(22); t.setGridColor(PANEL_GREY);
        t.setBackground(BG_WHITE); t.setForeground(TEXT_DARK);
        t.setSelectionBackground(GOLD_LIGHT); t.setSelectionForeground(BG_DARK);
        JTableHeader th = t.getTableHeader();
        th.setFont(new Font("Segoe UI", Font.BOLD, 12));
        th.setBackground(BG_DARK); th.setForeground(GOLD);
        th.setReorderingAllowed(false);
    }

    private JButton goldButton(String txt) {
        JButton b = new JButton(txt);
        b.setFont(new Font("Segoe UI", Font.BOLD, 13));
        b.setBackground(GOLD); b.setForeground(BG_DARK);
        b.setFocusPainted(false);
        b.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(GOLD_DARK, 1), new EmptyBorder(7, 18, 7, 18)));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { b.setBackground(BTN_HOVER); }
            public void mouseExited(MouseEvent e)  { b.setBackground(GOLD); }
        });
        return b;
    }

    private JButton greyButton(String txt) {
        JButton b = new JButton(txt);
        b.setFont(new Font("Segoe UI", Font.BOLD, 13));
        b.setBackground(PANEL_GREY); b.setForeground(TEXT_DARK);
        b.setFocusPainted(false);
        b.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1), new EmptyBorder(7, 18, 7, 18)));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return b;
    }

    private JLabel goldLabel(String t) {
        JLabel l = new JLabel(t);
        l.setFont(new Font("Segoe UI", Font.BOLD, 13));
        l.setForeground(GOLD);
        return l;
    }

    private JLabel wrapLabel(String t) {
        JLabel l = new JLabel("  " + t);
        l.setFont(new Font("Segoe UI", Font.BOLD, 12));
        l.setForeground(GOLD_DARK);
        l.setBorder(new EmptyBorder(4, 2, 2, 2));
        l.setAlignmentX(Component.LEFT_ALIGNMENT);
        return l;
    }

    private void styleStatLabel(JLabel l, Color c) {
        l.setFont(new Font("Segoe UI", Font.BOLD, 12));
        l.setForeground(c); l.setOpaque(true); l.setBackground(BG_WHITE);
        l.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(c, 1), new EmptyBorder(2, 6, 2, 6)));
    }

    private TitledBorder createGoldBorder(String title) {
        TitledBorder tb = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(GOLD, 2), " " + title + " ");
        tb.setTitleFont(new Font("Segoe UI", Font.BOLD, 12));
        tb.setTitleColor(GOLD);
        return tb;
    }

    private JPanel formRow(String label, JComponent field) {
        JPanel row = new JPanel(new BorderLayout(8, 0));
        row.setOpaque(false);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        row.setBorder(new EmptyBorder(3, 8, 3, 8));
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lbl.setForeground(GOLD_DARK);
        lbl.setPreferredSize(new Dimension(138, 26));
        row.add(lbl, BorderLayout.WEST);
        row.add(field, BorderLayout.CENTER);
        return row;
    }

    
}
