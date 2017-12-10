package fr.utt.if26.if26_sncfprojet;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;

/**
 * Classe permettant de gérer les trajets.
 * Created by Jeanba on 10/12/2017.
 */

public class TrajetClasse implements Parcelable {
    private GareClasse gareDepart;
    private GareClasse gareArrive;

    TrajetClasse(@Nullable GareClasse gareDepart, @Nullable GareClasse gareArrive) {
        if (gareDepart != null) {
            this.gareDepart = gareDepart;
        }
        if (gareArrive != null) {
            this.gareArrive = gareArrive;
        }
    }

    private TrajetClasse(Parcel in) {
        gareDepart = in.readParcelable(GareClasse.class.getClassLoader());
        gareArrive = in.readParcelable(GareClasse.class.getClassLoader());
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

    void setGareArrive(GareClasse gareArrive) {
        this.gareArrive = gareArrive;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
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
