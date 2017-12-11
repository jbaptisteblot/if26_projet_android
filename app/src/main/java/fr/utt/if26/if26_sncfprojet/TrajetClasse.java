package fr.utt.if26.if26_sncfprojet;

import android.os.Parcel;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;

/**
 * Classe permettant de gérer les trajets.
 * Created by Jeanba on 10/12/2017.
 */

public class TrajetClasse implements Parcelable {
    private GareClasse gareDepart;
    private GareClasse gareArrive;
    private Long id_trajet = (long) -1;

    // Constructor
    TrajetClasse(@Nullable GareClasse gareDepart, @Nullable GareClasse gareArrive) {
        if (gareDepart != null) {
            this.gareDepart = gareDepart;
        }
        if (gareArrive != null) {
            this.gareArrive = gareArrive;
        }
    }

    private TrajetClasse(Parcel in) {
        id_trajet = in.readLong();
        gareDepart = in.readParcelable(GareClasse.class.getClassLoader());
        gareArrive = in.readParcelable(GareClasse.class.getClassLoader());
    }
    TrajetClasse(long id_trajet, DatabaseHelper db) {
        this.id_trajet = id_trajet;
        if(!SyncToDB(db)) throw new IllegalArgumentException("n'appartient pas à la BDD");
    }

    TrajetClasse(long id_trajet, GareClasse gareDepart, GareClasse gareArrive) {
        this.gareDepart = gareDepart;
        this.gareArrive = gareArrive;
        this.id_trajet = id_trajet;
    }

    public Long getId_trajet() {
        return id_trajet;
    }

    public void setId_trajet(Long id_trajet) {
        this.id_trajet = id_trajet;
    }

    GareClasse getGareDepart() {
        return gareDepart;
    }

    void setGareDepart(GareClasse gareDepart) {
        this.gareDepart = gareDepart;
    }

    GareClasse getGareArrive() {
        return gareArrive;
    }
    // toString
    @Override
    public String toString() {
        return "TrajetClasse{" +
                "idTrajet=" + id_trajet +
                ", gareDepart=" + gareDepart +
                ", gareArrive=" + gareArrive +
                '}';
    }

    void setGareArrive(GareClasse gareArrive) {
        this.gareArrive = gareArrive;
    }
    // SQLite
    private boolean canSearchOnDB() {
        return (this.getId_trajet() != (long)-1 || (this.getGareDepart() != null && this.getGareArrive() != null));
    }
    boolean SyncToDB(DatabaseHelper db) {
        if (!canSearchOnDB()) {
            return false;
        }
        else {
            TrajetClasse trajetSQL = db.findOrCreateTrajet(this);
            if (this.id_trajet == (long) -1) {
                this.id_trajet = trajetSQL.getId_trajet();
            }
            if (this.gareDepart == null) {
                this.gareDepart = trajetSQL.getGareDepart();
            }
            if (this.gareArrive == null) {
                this.gareArrive = trajetSQL.getGareArrive();
            }
            return true;
        }
    }
    //Parcelable
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id_trajet);
        parcel.writeParcelable(gareDepart, i);
        parcel.writeParcelable(gareArrive, i);

    }

    public static final Creator<TrajetClasse> CREATOR = new Creator<TrajetClasse>() {
        @Override
        public TrajetClasse createFromParcel(Parcel in) {
            return new TrajetClasse(in);
        }

        @Override
        public TrajetClasse[] newArray(int size) {
            return new TrajetClasse[size];
        }
    };
}
