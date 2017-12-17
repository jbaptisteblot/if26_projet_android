package fr.utt.if26.if26_sncfprojet;

import java.util.Date;

/**
 * NextDepartureClass
 * Created by Jeanba on 12/12/2017.
 */

public class NextDepartureClass {
    private long depart_id = -1;
    private TrajetClasse trajet;
    private Date arrival_date_time;
    private Date departure_date_time;
    private int duration;

    NextDepartureClass(TrajetClasse trajet, Date departure_date_time, Date arrival_date_time, int duration) {
        this.trajet = trajet;
        this.arrival_date_time = arrival_date_time;
        this.departure_date_time = departure_date_time;
        this.duration = duration;
    }
    NextDepartureClass(long depart_id,TrajetClasse trajet, Date departure_date_time, Date arrival_date_time, int duration) {
        this.depart_id = depart_id;
        this.trajet = trajet;
        this.arrival_date_time = arrival_date_time;
        this.departure_date_time = departure_date_time;
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "NextDepartureClass{" +
                "depart_id=" + depart_id
                +", trajet=" + trajet +
                ", arrival_date_time=" + arrival_date_time +
                ", departure_date_time=" + departure_date_time +
                ", duration=" + duration +
                '}';
    }
    String durationString() {
        int heures = this.duration/3600;
        int minutes = (this.duration - 3600*heures)/60;
        return (String.valueOf(heures) + "h" + String.valueOf(minutes));
    }
    long getDepart_id() {
        return depart_id;
    }

     void setDepart_id(long depart_id) {
        this.depart_id = depart_id;
    }

     TrajetClasse getTrajet() {
        return trajet;
    }

     void setTrajet(TrajetClasse trajet) {
        this.trajet = trajet;
    }

     Date getArrival_date_time() {
        return arrival_date_time;
    }

     void setArrival_date_time(Date arrival_date_time) {
        this.arrival_date_time = arrival_date_time;
    }

     Date getDeparture_date_time() {
        return departure_date_time;
    }

     void setDeparture_date_time(Date departure_date_time) {
        this.departure_date_time = departure_date_time;
    }

     int getDuration() {
        return duration;
    }

     void setDuration(int duration) {
        this.duration = duration;
    }
}
