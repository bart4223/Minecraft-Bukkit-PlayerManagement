Minecraft-Bukkit-PlayerManagement
=================================

Dieses Plugin liefert diverse Features für die Verwaltung von Spielern.

Vorraussetzung
--------------
Es wird das Mahn42-Framework benötigt.

Statistik
---------
Es werden diverse Statistiken zu einem Player erfasst.

- erster Login auf dem Server
- letzter Login auf dem Server
- Gesamtspielzeit auf dem Server
- letzte Spielzeit auf dem Server
- abgebaute Blöcke auf dem Server
- gesetzte Blöcke auf dem Server
- NPC Kills auf dem Server
- Player Kills auf dem Server

Social Points
-------------
Mittels dem Plugin ist es möglich, jedem Spieler diverse Punkte für soziales Verhalten auszusprechen,
welche dann pro Kategorie kummuliert werden. Die Punkte dienen z.B. dazu um Verwarnungen zu realisieren.

Social Point Types
------------------
Um Eigenschaften für diverse Typen von Social Points zu hinterlegen, muss eine socialpointtypes.yml angelegt werden.
Ein Beispiel für Verwarnung sieht folgendermaßen aus:
    
    Types:
     - Name: warning
       Props:
         - PointsBeforeKick=5
         - PointsBeforeBann=10

Verwarnung
----------
Mittels des Plugins ist es möglich Verwarnungen für einen Spieler auszusprechen. Die Anzahl der Verwarnungen 
werden kummuliert und ziehen in Folge diverse Konzequenzen(z.B. Kick oder Bann) nach sich.

Kommandos
---------
- pm_pstop(alias pmpst) = listet den Spieler mit der meisten Spielzeit auf
- pm_pslist(alias pmpsl) = listet alle Spieler und deren letzten Logins und letzte Spielzeit auf
- pm_pslist(alias pmpsl) [Spielername] = listet vom Spieler den ersten und letzten Login, die Gesamt- und die letzte Spielzeit auf
- pm_pnadd(alias pmpna) [News] = fügt eine News mit "Normal" hinzu
- pm_pnadd(alias pmpna) [0,1,2] [News] = fügt eine News hinzu (0 = Normal, 1 = Important, 2 = Very Important)
- pm_pnlist(alias pmpnl) = listet alle News seit dem letzten Login auf
- pm_splist(alias pmspl) [Spielername|all] = listet alle Arten von Social Points mit deren Punktestand für den jeweiligen Spieler auf
- pm_warning(alias pmw) [Spielername] [+/-Punkte] [Grund] = erhöht oder vermindert den Punktestand der Verwarnungen ("warning")
- pm_warninglist(alias pmwl) [Spielername] = listet die letzten 3 Verwarnungen eines Spielers auf
- pm_warningpoints(alias pmwp) [Spielername|all] = zeigt den Punktestand der Verwarnungen ("warning") an
