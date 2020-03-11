import java.sql.*;
import java.util.stream.*;
import java.util.*;
import java.io.*;


class Firma {
    private String bezeichnung;
    private String firma;
    private String ansprechpartner;
    private String anrede;
    private String strasse;
    private String plz;
    private String ort;
    private String telefon;
    private String mobil;
    private String email;
    private String website;
    private String quelle;
    private String ergebnis;
    private String datenbank;
    private final Scanner scan = new Scanner(System.in);
    private Connection con = null;
    private ResultSet rs = null;
    private final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    private String USER = null;
    private String PASS = null;
    private String DB = null;

    private static final String create = "CREATE TABLE IF NOT EXISTS bewerbungen(id MEDIUMINT PRIMARY KEY AUTO_INCREMENT, bezeichnung VARCHAR(255), firma VARCHAR(255), ansprechpartner VARCHAR(255), anrede VARCHAR(255), strasse VARCHAR(255), plz VARCHAR(255), "
            + "ort VARCHAR(255), telefon VARCHAR(255), mobil VARCHAR(255), email VARCHAR(255), website VARCHAR(255), quelle VARCHAR(255), ergebnis VARCHAR(255), zeit TIMESTAMP, CONSTRAINT unID UNIQUE(bezeichnung, firma, ansprechpartner))";

    private static final String insert = "INSERT INTO bewerbungen (bezeichnung, firma, ansprechpartner, anrede, strasse, plz, ort, telefon, mobil, email, website, quelle, ergebnis) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    Statement stmt;
    PreparedStatement pStmt;


    Firma() {
    }

    Firma(List<String> list) {
        System.out.println(list);
        System.out.println("CONSTRUCTOR");

        this.bezeichnung = list.get(0);
        this.firma = list.get(1);
        this.ansprechpartner = list.get(2);
        this.anrede = list.get(3);
        this.strasse = list.get(4);
        this.plz = list.get(5);
        this.ort = list.get(6);
        this.telefon = list.get(7);
        this.mobil = list.get(8);
        this.email = list.get(9);
        this.website = list.get(10);
        this.quelle = list.get(11);
        this.ergebnis = list.get(12);

//        List<String> connect = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(new File("connect.txt")))) {
            List<String> connect = br.lines().collect(Collectors.toList());

            USER = connect.get(0);
            PASS = connect.get(1);
            DB = connect.get(2);

            System.out.println(connect);
        } catch (FileNotFoundException fnfe) {
            System.out.println("********************************************************");
            System.out.println("Datei connect.txt m. Zugangsdaten für mariadb fehlt!");
            System.out.println("Bitte im Verzeichnis der Datei Main.java oder Main.class\neine zweizeilige Textdatei mit\nBenutzername (Zeile 1)\nund Passwort (Zeile 2)\nanlegen.");
            System.out.println("********************************************************");
            System.exit(-1);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }


    public Connection connectDatenbank() {

        String url = "jdbc:mariadb://localhost:3306/" + DB;

        System.out.println(url);

        try {
            con = DriverManager.getConnection(url, USER, PASS);
            System.out.println("Verbindung zu MariaDB hergestellt.");
            stmt = con.createStatement();
            stmt.execute(create);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(getLineNumber());
        } catch (NullPointerException e) {
            System.out.println(getLineNumber());
        }

        return con;
    }


    public void erstelleDatenbankTabelle() {
        try {
            con = connectDatenbank();
            con.setAutoCommit(true);
            stmt = con.createStatement();
            stmt.execute(create);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(getLineNumber());
        } catch (NullPointerException e) {
            System.out.println(getLineNumber());
        }
    }

    public void datensatzEinfuegen() {
        try {
            System.out.println(insert);
            pStmt = con.prepareStatement(insert);

            String[] datensatz = new String[]{bezeichnung, firma, ansprechpartner, anrede,
                    strasse, plz, ort, telefon, mobil,
                    email, website, quelle, ergebnis};

            for (int i = 1, j = 0; i <= datensatz.length; i++, j++) {

                pStmt.setString(i, datensatz[j]);
            }

            pStmt.executeUpdate();
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
            System.out.println(getLineNumber());
        } finally {
            try {
                stmt.close();
                con.close();
                System.out.println("Verbindung geschlossen");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public void testAbfrage() {
        try {
            String select = "SELECT * FROM bewerbungen";
            Statement stmt = con.createStatement();

            rs = stmt.executeQuery(select);
            con.commit();
        } catch (NullPointerException e) {
            System.out.println(getLineNumber());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(getLineNumber());
        }

        try {
            while (rs.next()) {
                System.out.println("Bezeichnung: " + rs.getString("bezeichnung"));
                System.out.println("Firma: " + rs.getString("firma"));
                System.out.println("Ansprechpartner: " + rs.getString("ansprechpartner"));
                System.out.println("Anrede: " + rs.getString("anrede"));
                System.out.println("Straße: " + rs.getString("strasse"));
                System.out.println("PLZ: " + rs.getString("plz"));
                System.out.println("Ort: " + rs.getString("ort"));
                System.out.println("Telefon: " + rs.getString("telefon"));
                System.out.println("Mobiltelefon: " + rs.getString("mobil"));
                System.out.println("E-Mail: " + rs.getString("email"));
                System.out.println("Website: " + rs.getString("website"));
                System.out.println("Quelle: " + rs.getString("quelle"));
                System.out.println("Ergebnis: " + rs.getString("ergebnis"));
                System.out.println("Datum und Zeit: " + rs.getDate("zeit") + " " + rs.getTime("zeit"));
            }
        } catch (NullPointerException | SQLException e) {
            System.out.println(getLineNumber());
        } finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                e.getStackTrace();
            }
        }

    }


    public String toString() {
        return "\nFüge Datensatz ein:\n\n"
                + "Bezeichnung: " + bezeichnung + "\n"
                + "Firma: " + firma + "\n"
                + "Ansprechpartner: " + ansprechpartner + "\n"
                + "Anrede: " + anrede + "\n"
                + "Straße: " + strasse + "\n"
                + "PLZ: " + plz + "\n"
                + "Ort: " + ort + "\n"
                + "Tel.: " + telefon + "\n"
                + "Mobil: " + mobil + "\n"
                + "E-Mail: " + email + "\n"
                + "Website: " + website + "\n"
                + "Quelle: " + quelle + "\n"
                + "Ergebnis: " + ergebnis + "\n";
    }


    public void eingabe() {
        System.out.print("Bezeichnung: ");
        bezeichnung = scan.nextLine();
        System.out.print("Firma: ");
        firma = scan.nextLine();
        System.out.print("Ansprechpartner: ");
        ansprechpartner = scan.nextLine();
        System.out.print("Anrede: ");
        anrede = scan.nextLine();
        System.out.print("Straße: ");
        strasse = scan.nextLine();
        System.out.print("PLZ: ");
        plz = scan.nextLine();
        System.out.print("Ort: ");
        ort = scan.nextLine();
        System.out.print("Telefon/Festnetz: ");
        telefon = scan.nextLine();
        System.out.print("Telefon/mobil: ");
        mobil = scan.nextLine();
        System.out.print("E-Mail: ");
        email = scan.nextLine();
        System.out.print("Website: ");
        website = scan.nextLine();
        System.out.print("Quelle: ");
        quelle = scan.nextLine();
        System.out.print("Ergebnis: ");
        ergebnis = scan.nextLine();
    }


    public static int getLineNumber() {
        return Thread.currentThread().getStackTrace()[2].getLineNumber();
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public String getAnsprechpartner() {
        return ansprechpartner;
    }

    public void setAnsprechpartner(String ansprechpartner) {
        this.ansprechpartner = ansprechpartner;
    }

    public String getAnrede() {
        return anrede;
    }

    public void setAnrede(String anrede) {
        this.anrede = anrede;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getMobil() {
        return mobil;
    }

    public void setMobil(String mobil) {
        this.mobil = mobil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getQuelle() {
        return quelle;
    }

    public void setQuelle(String quelle) {
        this.quelle = quelle;
    }

    public String getErgebnis() {
        return ergebnis;
    }

    public void setErgebnis(String ergebnis) {
        this.ergebnis = ergebnis;
    }
}
