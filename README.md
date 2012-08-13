Minecraft-Bukkit-PlayerManagement
=================================

Dieses Plugin liefert diverse Features für die Verwaltung von Spielern.

Vorraussetzung
--------------
Es wird das Mahn42-Framework benötigt.

Social Points
-------------
Mittels dem Plugin ist es möglich, jedem Spieler diverse Punkte für soziales Verhalten auszusprechen,
welche dann pro Kategorie kummuliert werden. Die Punkte dienen z.B. dazu um Verwarnungen zu realisieren.

Verwarnung
----------
Mittels dem Plugin ist es möglich Verwarnungen für einen Spieler auszusprechen. Die Anzahl der Verwarnungen 
werden kummuliert und ziehen in Folge diverse Konzequenzen nach sich.

Kommandos
---------
-pm_splist(alias pmspl) <Spielername> listet alle Arten von Social Points mit deren Punktestand für den jeweiligen Spieler auf
-pm_warning(alias pmw) <Spielername> <+/-Punkte> <Grund> erhöht oder vermindert den Punktestand der Verwarnungen ("warning")
-pm_warninglist(alias pmwl) <Spielername> listet die letzten 3 Verwarnungen eines Spielers auf
-pm_warningpoints(alias pmwp) <Spielername> zeigt den Punktestand der Verwarnungen ("warning") an