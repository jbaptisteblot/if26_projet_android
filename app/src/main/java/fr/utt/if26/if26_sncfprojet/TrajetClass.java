package fr.utt.if26.if26_sncfprojet;

import android.support.annotation.Nullable;

/**
 * Classe permettant de gérer les trajets.
 * Created by Jeanba on 10/12/2017.
 */

public class TrajetClass {
    GareClasse gareDepart;
    GareClasse gareArrive;

    public TrajetClass(@Nullable GareClasse gareDepart, @Nullable GareClasse gareArrive) {
        if (gareDepart != null) {
            this.gareDepart = gareDepart;
        }
        if (gareArrive != null) {
            this.gareArrive = gareArrive;
        }
    }

    public GareClasse getGareDepart() {
        return gareDepart;
    }

    public void setGareDepart(GareClasse gareDepart) {
        this.gareDepart = gareDepart;
    }

    public GareClasse getGareArrive() {
        return gareArrive;
    }

    public void setGareArrive(GareClasse gareArrive) {
        this.gareArrive = gareArrive;
    }
}
