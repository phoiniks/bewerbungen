# bewerbungen

Installieren Sie MariaDB auf Ihrem Gerät und richten Sie eine Datenbank ein!

1.  Nachdem Sie das Repository geklont haben, begeben Sie sich per cd in das Verzeichnis bewerbungen.
2.  Dort führen Sie den Befehl 'mvn package' aus (Hierzu muss Maven installiert und konfiguriert sein!)
3.  Erstellen Sie eine einfache Textdatei "connect.txt" entweder im Verzeichnis bewerbungen/src/main/java
    oder bewerbungen/target/classes mit drei direkt untereinander folgenden Zeilen (ohne Leerzeile
    dazwischen, beginnend in der ersten Zeile der Datei!):

    benutzer  (Ihr Benutzername: z.B. Franz)
    
    passwort  (Ihr Passwort:     z.B. Holleradudödldu)
    
    datenbank (Ihre Datenbank:   z.B. zickezackehuehnerkacke)

4.  Anschließend können Sie das Hauptprogramm starten, indem Sie im Verzeichnis bewerbungen/target/classes
    das Bash-Skript run.sh mit ./run.sh starten (Diese Prozedur wird demnächst vereinfacht!)
5.  Auf der sich nun öffnenden Oberfläche tragen Sie die gewünschten Daten ein und speichern sie mit
    dem SPEICHERN-Knopf in Ihrer Datenbank ab.
    
Das Python3-Skript dient dann der Erstellung eines PDFs, das sowohl Ihr Anschreiben als auch Ihren
Lebenslauf und Zeugnisse enthält. Allerdings müssen Sie hierzu LaTeX und die entsprechenden Bibliotheken installieren!

Außerdem benötigen Sie LaTeX-Templates, damit das Python-Skript die gewünschen PDF-Dokumente erstellt.
Sie brauchen darüber hinaus sowohl einen Lebenslauf als auch Zeugnisse, da diese für ein ordnungsgemäßes Funktionieren
des Skripts ebensfalls erforderlich sind.

Wenn Sie unter Linux arbeiten, bin ich Ihnen gern behilflich!

Wenn Sie keine grafische Oberfläche verwenden wollen, starten Sie einfach die Main.class in : java -cp ".:*.jar" Main