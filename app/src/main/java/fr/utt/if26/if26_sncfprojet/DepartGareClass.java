package fr.utt.if26.if26_sncfprojet;

import java.util.Date;

/**
 * Classe permettant de gérer les différents départs
 * Created by Jeanba on 23/12/2017.
 */

public class DepartGareClass {
    long id_depart;
    GareClasse gare_depart;
    GareClasse gare_arrive;
    Date heure_depart;
    Date heure_arrive;

    public DepartGareClass(long id_depart, GareClasse gare_depart, GareClasse gare_arrive, Date heure_depart, Date heure_arrive) {
        this.id_depart = id_depart;
        this.gare_depart = gare_depart;
        this.gare_arrive = gare_arrive;
        this.heure_depart = heure_depart;
        this.heure_arrive = heure_arrive;
    }
}
