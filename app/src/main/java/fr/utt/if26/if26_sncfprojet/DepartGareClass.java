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

    public long getId_depart() {
        return id_depart;
    }

    public void setId_depart(long id_depart) {
        this.id_depart = id_depart;
    }

    public GareClasse getGare_depart() {
        return gare_depart;
    }

    public void setGare_depart(GareClasse gare_depart) {
        this.gare_depart = gare_depart;
    }

    public GareClasse getGare_arrive() {
        return gare_arrive;
    }

    public void setGare_arrive(GareClasse gare_arrive) {
        this.gare_arrive = gare_arrive;
    }

    public Date getHeure_depart() {
        return heure_depart;
    }

    public void setHeure_depart(Date heure_depart) {
        this.heure_depart = heure_depart;
    }

    public Date getHeure_arrive() {
        return heure_arrive;
    }

    public void setHeure_arrive(Date heure_arrive) {
        this.heure_arrive = heure_arrive;
    }

    public DepartGareClass(GareClasse gare_depart, GareClasse gare_arrive, Date heure_depart, Date heure_arrive) {
        this.id_depart = -1;
        this.gare_depart = gare_depart;
        this.gare_arrive = gare_arrive;
        this.heure_depart = heure_depart;
        this.heure_arrive = heure_arrive;
    }
}
