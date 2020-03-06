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

    JRadioButton jrbOptionOff;
    JRadioButton jrbOptionOn;
  
    JButton jbtnSpeichern;
    JButton jbtnAbbrechen;

    String bezeichnung, firma, ansprechpartner, anrede, strasse, plz, ort, telefon, mobil, email, website, quelle, ergebnis;

    JLabel jlabPruef1, jlabPruef2;
  
    JPanel jp1;
    JPanel jp2;
    JPanel jp3;
    JPanel jp4;
  
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
				"Quelle:", "Ergebnis:" };
    
	String[] TFStrListe = {"Stellenbezeichnung", "Firma", "Ansprechpartner", "Anrede",
			       "Straße", "Plz", "Ort", "Telefon (Festnetz)",
			       "Mobil", "E-Mail", "Website", "Quelle",
			       "steht noch aus"};
    
	for(int i = 0; i < JLabList.length; i++)
	    {
		JLabList[i]  = new JLabel(LabStrListe[i]);
		jp1.add(JLabList[i]);
		JTFList[i] = new JTextField(TFStrListe[i]);
		jp1.add(JTFList[i]);
	    }            
    
	JButton jbtnAbbrechen = new JButton("Abbruch");
	JButton jbtnSpeichern = new JButton("Speichern");
    
	jlabPruef1 = new JLabel();
	jlabPruef2 = new JLabel();
    
	jbtnSpeichern.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent ae) {
		    for(JTextField jtf : JTFList)
			list.add(jtf.getText());

		    Firma firma = new Firma(list);
		    firma.connectDatenbank();
		    firma.erstelleDatenbankTabelle();
		    firma.datensatzEinfuegen();
		}
		
	    });
	
	jbtnAbbrechen.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent ae) {
		    System.exit(0);
		}
	    });
    
	jp2 = new JPanel(new GridLayout(1, 2));
	jp2.setPreferredSize(new Dimension(280, 30));
	jp2.setOpaque(true);    
	jp2.add(jbtnAbbrechen);
	jp2.setPreferredSize(new Dimension(350, 30));
	jp2.add(Box.createVerticalStrut(60));
	jp2.setPreferredSize(new Dimension(350, 30));	
	jp2.add(jbtnSpeichern);

	jp3 = new JPanel(new GridLayout(1, 1));
	jp3.setPreferredSize(new Dimension(280, 60));
	jp3.setOpaque(true);
	jp3.add(jlabPruef1);
    
	jp4 = new JPanel(new GridLayout(1, 1));
	jp4.setPreferredSize(new Dimension(280, 60));
	jp4.setOpaque(true);
	jp4.add(jlabPruef2);

	JTabbedPane jtp1 = new JTabbedPane();
	JTabbedPane jtp2 = new JTabbedPane();

	jtp1.addTab("Eingabe", jp1);
	jfrm.add(jtp1);
	jfrm.add(jp2);
	jfrm.add(jp3);
	jfrm.add(jp4);

	jfrm.setVisible(true);
    }

    /**
     * @param args
     */
    public static void main(String[] args) throws ClassNotFoundException {
	SwingUtilities.invokeLater(new Runnable() {
		public void run() {
		    new Bewerbungen();
		}    
	    });
    }
}
