import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.*;
import java.util.*;
import java.io.*;

/* phoiniks@grellopolis.de, 2020 */

public class Bewerbungen{

    JLabel jlabBezeichnung;
    JLabel jlabFirma;
    JLabel jlabAnsprechpartner;
    JLabel jlabAnrede;    
    JLabel jlabStrasse;
    JLabel jlabPlz;
    JLabel jlabOrt;
    JLabel jlabTelefon;
    JLabel jlabMobil;
    JLabel jlabEmail;
    JLabel jlabWebsite;
    JLabel jlabQuelle;
    JLabel jlabErgebnis;
  
    JLabel[] JLabList = { jlabBezeichnung, jlabFirma,  jlabAnsprechpartner,  jlabAnrede,
			  jlabStrasse,  jlabPlz,  jlabOrt,  jlabTelefon,
			  jlabMobil,  jlabEmail, jlabWebsite, jlabQuelle, jlabErgebnis };

    JTextField jtfBezeichnung;
    JTextField jtfFirma;
    JTextField jtfAnsprechpartner;
    JTextField jtfAnrede;    
    JTextField jtfStrasse;
    JTextField jtfPlz;
    JTextField jtfOrt;
    JTextField jtfTelefon;
    JTextField jtfMobil;
    JTextField jtfEmail;
    JTextField jtfWebsite;
    JTextField jtfQuelle;
    JTextField jtfErgebnis;
  
    JTextField[] JTFList = { jtfBezeichnung, jtfFirma,  jtfAnsprechpartner,
			     jtfAnrede, jtfStrasse,  jtfPlz,  jtfOrt,
			     jtfTelefon,  jtfMobil,  jtfEmail, jtfWebsite,
			     jtfQuelle, jtfErgebnis };

    JButton jbtnSpeichern;
    JButton jbtnAbbrechen;

    String bezeichnung, firma, ansprechpartner, anrede, strasse, plz, ort, telefon, mobil, email, website, quelle, ergebnis;

    JPanel jp1;
    JPanel jp2;
  
    public Bewerbungen() {
		java.util.List<String> list = new java.util.ArrayList<>(13);

		JFrame jfrm = new JFrame("Stellenangebote und Bewerbungen");

		jfrm.getContentPane().setLayout(new FlowLayout());

		jfrm.setSize(460, 400);

		jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		jp1 = new JPanel(new GridLayout(13, 2));
		jp1.setPreferredSize(new Dimension(450, 280));
		jp1.setOpaque(true);

		String[] LabStrListe = {"Bezeichnung:", "Firma:", "Ansprechpartner:", "Anrede:",
				"Straße:", "Plz:", "Ort:", "Telefon (Festnetz):",
				"Mobil:", "E-Mail:", "Website:",
				"Quelle:", "Ergebnis:"};

		String[] TFStrListe = {"Stellenbezeichnung", "Firma", "Ansprechpartner", "Anrede",
				"Straße", "Plz", "Ort", "Telefon (Festnetz)",
				"Mobil", "E-Mail", "Website", "Quelle",
				"steht noch aus"};

		for (int i = 0; i < JLabList.length; i++) {
			JLabList[i] = new JLabel(LabStrListe[i]);
			jp1.add(JLabList[i]);
			JTFList[i] = new JTextField(TFStrListe[i]);
			jp1.add(JTFList[i]);
		}

		JButton jbtnAbbrechen = new JButton("Abbruch");
		JButton jbtnSpeichern = new JButton("Speichern");

		jbtnSpeichern.addActionListener(ae -> {
			for (JTextField jtf : JTFList)
				list.add(jtf.getText());

			Firma firma = new Firma(list);
			firma.connectDatenbank();
			firma.erstelleDatenbankTabelle();
			firma.datensatzEinfuegen();
		});

		jbtnAbbrechen.addActionListener(ae -> System.exit(0));

		jp2 = new JPanel(new GridLayout(1, 2));
		jp2.setPreferredSize(new Dimension(280, 30));
		jp2.setOpaque(true);
		jp2.add(jbtnAbbrechen);
		jp2.setPreferredSize(new Dimension(350, 30));
		jp2.add(Box.createVerticalStrut(60));
		jp2.setPreferredSize(new Dimension(350, 30));
		jp2.add(jbtnSpeichern);

		JTabbedPane jtp = new JTabbedPane();

		jtp.addTab("Eingabe", jp1);
		jtp.addTab("Vorlagen", new JLabel("Vorlagen"));
		jtp.addTab("Konfiguration", new JLabel("Konfiguration"));

		jfrm.add(jtp);

		jfrm.add(jp2);

		jfrm.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(Bewerbungen::new);
	}
}
