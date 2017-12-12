package fr.utt.if26.if26_sncfprojet;

import java.util.Date;

/**
 * NextDepartureClass
 * Created by Jeanba on 12/12/2017.
 */

public class NextDepartureClass {
    private TrajetClasse trajet;
    private Date arrival_date_time;
    private Date departure_date_time;
    private int duration;

    public NextDepartureClass(TrajetClasse trajet, Date departure_date_time, Date arrival_date_time, int duration) {
        this.trajet = trajet;
        this.arrival_date_time = arrival_date_time;
        this.departure_date_time = departure_date_time;
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "NextDepartureClass{" +
                "trajet=" + trajet +
                ", arrival_date_time=" + arrival_date_time +
                ", departure_date_time=" + departure_date_time +
                ", duration=" + duration +
                '}';
    }

    public TrajetClasse getTrajet() {
        return trajet;
    }

    public void setTrajet(TrajetClasse trajet) {
        this.trajet = trajet;
    }

    public Date getArrival_date_time() {
        return arrival_date_time;
    }

    public void setArrival_date_time(Date arrival_date_time) {
        this.arrival_date_time = arrival_date_time;
    }

    public Date getDeparture_date_time() {
        return departure_date_time;
    }

    public void setDeparture_date_time(Date departure_date_time) {
        this.departure_date_time = departure_date_time;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
