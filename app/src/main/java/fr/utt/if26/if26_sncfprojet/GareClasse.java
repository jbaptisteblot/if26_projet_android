package fr.utt.if26.if26_sncfprojet;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Classe permettant de gérer les gares.
 * Created by Jeanba on 08/12/2017.
 */

public class GareClasse implements Parcelable {
    private String name;
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    GareClasse(String name, String id) {

        this.name = name;
        this.id = id;
        System.out.println("new Gare : " + name + ", id :" + id);
    }

    private GareClasse(Parcel in) {
        name = in.readString();
        id = in.readString();
    }

    @Override
    public String toString() {
        return "GareClasse{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeString(this.id);
    }

    public static final Creator<GareClasse> CREATOR = new Creator<GareClasse>() {
        @Override
        public GareClasse createFromParcel(Parcel in) {
            return new GareClasse(in);
        }

        @Override
        public GareClasse[] newArray(int size) {
            return new GareClasse[size];
        }
    };

}
