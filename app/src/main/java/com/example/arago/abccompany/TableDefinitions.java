package com.example.arago.abccompany;

public class TableDefinitions {
//    //Ship Table
    public static final String SQL_CREATE_SHIP =
            "CREATE TABLE SHIP (ship_id integer primary key, ship_name text, cabin_amount integer)";
    public static final String SQL_DELETE_SHIP =
            "DROP TABLE IF EXISTS SHIP";
//
//    //Cabin Table
    public static final String SQL_CREATE_CABIN =//use cabin_id as foreign key in customer for room assignment
            "CREATE TABLE CABIN (cabin_id integer primary key, cabin_type text, cabin_roomNum text, cabin_availability text, ship_id integer," +
                    "FOREIGN KEY (ship_id) REFERENCES SHIP(ship_id) on delete cascade)";
    public static final String SQL_DELETE_CABIN =
            "DROP TABLE IF EXISTS CABIN";
//
//    //Cruise Table
    public static final String SQL_CREATE_CRUISE =
            "CREATE TABLE CRUISE(cruise_id integer primary key, ship_id integer, cruise_name text, cruise_price float," +
            "FOREIGN KEY (ship_id) REFERENCES SHIP(ship_id) on delete cascade)";
    public static final String SQL_DELETE_CRUISE =
            "DROP TABLE IF EXISTS CRUISE";

    //Customer Table
    public static final String SQL_CREATE_CUSTOMER =
            "CREATE TABLE CUSTOMER (cust_id integer primary key, firstName text, lastName text, address text, email text, roomNumber text, cabin_id integer," +
                    "FOREIGN KEY (cabin_id) REFERENCES CABIN(cabin_id) on delete cascade)";
    public static final String SQL_DELETE_CUSTOMER =
            "DROP TABLE IF EXISTS CUSTOMER";

//    //Booking Table
    public static final String SQL_CREATE_BOOKING =
            "CREATE TABLE BOOKING (booking_id integer primary key, cust_id integer, oba_id integer, poc_id integer, " +
                    "cabin_id integer, invoice_id integer, booking_date text, "
                    + "FOREIGN KEY (cust_id) REFERENCES CUSTOMER(cust_id)," +
                    "FOREIGN KEY (oba_id) REFERENCES ONBOARD(oba_id)," +
                    "FOREIGN KEY (poc_id) REFERENCES PORTSOFCALL(poc_id)," +
                    "FOREIGN KEY (cabin_id) REFERENCES CABIN(cabin_id)," +
                    "FOREIGN KEY (invoice_id) REFERENCES INVOICE(invoice_id))";
    public static final String SQL_DELETE_BOOKING =
            "DROP TABLE IF EXISTS BOOKING";
//
//    //Invoice Table
    public static final String SQL_CREATE_INVOICE =
            "CREATE TABLE INVOICE(invoice_id integer primary key, cust_id integer, total_price float," +
                    "FOREIGN KEY (cust_id) REFERENCES CUSTOMER(cust_id) on delete cascade)";
    public static final String SQL_DELETE_INVOICE =
            "DROP TABLE IF EXISTS INVOICE";
//
//    //Ports of Call Table
    public static final String SQL_CREATE_PORTSOFCALL =
            "CREATE TABLE PORTSOFCALL(poc_id integer primary key, poc_name text, poc_limit integer, activity_name text, activity_no_members integer," +
                    "activity_price float)"
                    ;
//
    public static final String SQL_DELETE_PORTSOFCALL =
            "DROP TABLE IF EXISTS PORTSOFCALL";

//On Board Activity Table
    public static final String SQL_CREATE_ONBOARD =
            "CREATE TABLE ONBOARD (oba_id integer primary key, oba_name text, oba_limit integer ,oba_day integer ,oba_price float)";
    public static final String SQL_DELETE_ONBOARD =
            "DROP TABLE IF EXISTS ONBOARD";


    //Fill the table
    public static final String SQL_FILL_CABIN =
            "INSERT INTO CABIN (cabin_type, cabin_roomNum, cabin_availability, ship_id)" +
                    "VALUES ('Inside','INS01','TRUE',1)," +
                    "('Inside','INS02','TRUE',1)," +
                    "('Inside','INS03','TRUE',1)," +
                    "('Inside','INS04','TRUE',1)," +
                    "('Inside','INS05','TRUE',1)," +
                    "('Oceanview','OCE01','TRUE',1)," +
                    "('Oceanview','OCE02','TRUE',1)," +
                    "('Oceanview','OCE03','TRUE',1)," +
                    "('Oceanview','OCE04','TRUE',1)," +
                    "('Oceanview','OCE05','TRUE',1)," +
                    "('Concierge','CON01','TRUE',1)," +
                    "('Concierge','CON02','TRUE',1)," +
                    "('Concierge','CON03','TRUE',1)," +
                    "('Concierge','CON04','TRUE',1)," +
                    "('Concierge','CON05','TRUE',1)," +
                    "('Verandah','VER01','TRUE',1)," +
                    "('Verandah','VER02','TRUE',1)," +
                    "('Verandah','VER03','TRUE',1)," +
                    "('Verandah','VER04','TRUE',1)," +
                    "('Verandah','VER05','TRUE',1)," +
                    "('Inside','INS01','TRUE',2)," +
                    "('Inside','INS02','TRUE',2)," +
                    "('Inside','INS03','TRUE',2)," +
                    "('Oceanview','OCE01','TRUE',2)," +
                    "('Oceanview','OCE02','TRUE',2)," +
                    "('Oceanview','OCE03','TRUE',2)," +
                    "('Concierge','CON01','TRUE',2)," +
                    "('Concierge','CON02','TRUE',2)," +
                    "('Concierge','CON03','TRUE',2)," +
                    "('Verandah','VER01','TRUE',2)," +
                    "('Verandah','VER02','TRUE',2)," +
                    "('Verandah','VER03','TRUE',2)," +
                    "('Inside','INS01','TRUE',3)," +
                    "('Inside','INS02','TRUE',3)," +
                    "('Inside','INS03','TRUE',3)," +
                    "('Oceanview','OCE01','TRUE',3)," +
                    "('Oceanview','OCE02','TRUE',3)," +
                    "('Oceanview','OCE03','TRUE',3)," +
                    "('Concierge','CON01','TRUE',3)," +
                    "('Concierge','CON02','TRUE',3)," +
                    "('Concierge','CON03','TRUE',3)," +
                    "('Verandah','VER01','TRUE',3)," +
                    "('Verandah','VER02','TRUE',3)," +
                    "('Verandah','VER03','TRUE',3)," +
                    "('Inside','INS01','TRUE',4)," +
                    "('Inside','INS02','TRUE',4)," +
                    "('Inside','INS03','TRUE',4)," +
                    "('Oceanview','OCE01','TRUE',4)," +
                    "('Oceanview','OCE02','TRUE',4)," +
                    "('Oceanview','INS03','TRUE',4)," +
                    "('Concierge','CON01','TRUE',4)," +
                    "('Concierge','CON02','TRUE',4)," +
                    "('Concierge','CON03','TRUE',4)," +
                    "('Verandah','VER01','TRUE',4)," +
                    "('Verandah','VER02','TRUE',4)," +
                    "('Verandah','VER03','TRUE',4)";


    public static final String SQL_FILL_SHIP =
            "INSERT INTO SHIP (ship_name, cabin_amount)" +
                    "VALUES ('Northern Spirit', 20)," +//ID = 1 and so on
                    "('Star Line', 12)," +
                    "('Cheerio Cruise', 12)," +
                    "('Epic Cabana', 12)";

    public static final String SQL_FILL_CRUISE =
            "INSERT INTO CRUISE (cruise_name, ship_id)" +
                    "VALUES ('7-Night Northern Europe Cruise from Copenhagen',1)," +
                    "('7-Night Mediterranean Cruise from Rome',2)," +
                    "('5-Night British Isles Cruise from Barcelona',3)," +
                    "('5-Night British Isles Cruise from Barcelona',4)";
    //"CREATE TABLE ONBOARD (oba_id integer primary key, oba_name text,oba_limit integer ,oba_day integer ,oba_price float)";

    public static final String SQL_FILL_ONBOARD =
            "INSERT INTO ONBOARD (oba_name, oba_limit, oba_day, oba_price)" +
                    "VALUES ('Spa', 200, 1, 50)," +
                    "('Imax', 200, 3, 50)," +
                    "('GoKart', 200, 5, 100)," +
                    "('Painting', 200, 7, 150)";

    public static final String SQL_FILL_PORTSOFCALL =
            "INSERT INTO PORTSOFCALL (poc_name, activity_name, poc_limit, activity_price)" +
                    "VALUES ('Berlin', 'Brandenburg Gate', 50, 50.00)," +
                    "('Berlin', 'Potsdamer Platz', 50, 75.00)," +
                    "('St. Petersburg', 'Hermitage', 50, 25.00)," +
                    "('St. Petersburg', 'Peterhof Palace', 50, 100.00)," +
                    "('Helsinki', 'Suomenlinna', 50, 50.00)," +
                    "('Helsinki', 'Market Sqaure', 50, 25.00)," +
                    "('Koper', 'Praetorian Palace', 50, 50.00)," +
                    "('Koper', 'Tito Square', 50, 175.00)," +
                    "('Split', 'Trajektna Luka', 50, 25.00)," +
                    "('Split', 'Diocletian Palace', 50, 60.00)," +
                    "('Corfu', 'Sidari', 50, 50.00)," +
                    "('Corfu', 'Achilleion', 50, 45.00)," +
                    "('Edinburg', 'Edinburg Castle', 50, 150.00)," +
                    "('Edinburg', 'Old Town', 50, 85.00)," +
                    "('Dublin', 'Gunnies House', 50, 55.00)," +
                    "('Dublin', 'Dublin Castle', 50, 100.00)," +
                    "('Naples', 'Royal Place', 50, 30.00)," +
                    "('Naples', 'Molo Beverello', 50, 75.00)," +
                    "('Cannes', 'Lerins Islands', 50, 125.00)," +
                    "('Cannes', 'Ile Marguerite', 50, 70.00)";


}
