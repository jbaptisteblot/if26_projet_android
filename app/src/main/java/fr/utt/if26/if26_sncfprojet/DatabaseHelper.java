package fr.utt.if26.if26_sncfprojet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLInput;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Jeanba on 08/12/2017.
 * Classe permettant d'accéder à la base de données.
 */

public class DatabaseHelper extends SQLiteOpenHelper{
    //Database Version
    private static final int DATABASE_VERSION = 1;

    //Database Name
    private static final String DATABASE_NAME = "sncfprojet";

    //Table Names
    private static final String TABLE_TRAJET = "trajet";
    private static final String TABLE_GARE = "gare";

    // Trajet Table - nom des colonnes
    private static final String KEY_ID_TRAJET = "trajet_id";
    private static final String KEY_GARE_DEPART = "trajet_gare_depart";
    private static final String KEY_GARE_ARRIVE = "trajet_gare_arrive";


    // Gare Table - nom des colonnes
    private static final String KEY_ID_GARE = "gare_id";
    private static final String KEY_NOM_GARE = "gare_nom";

    private static final String CREATE_TABLE_GARE = "CREATE TABLE " + TABLE_GARE + "(" + KEY_ID_GARE + " TEXT PRIMARY KEY, " + KEY_NOM_GARE + " TEXT)";
    private static final String CREATE_TABLE_TRAJET = "CREATE TABLE " + TABLE_TRAJET + "(" + KEY_ID_TRAJET + " INTEGER PRIMARY KEY, " + KEY_GARE_DEPART + " TEXT, " + KEY_GARE_ARRIVE + " TEXT, FOREIGN KEY (" + KEY_GARE_DEPART + ") REFERENCES " + TABLE_GARE + "(" + KEY_ID_GARE + "), FOREIGN KEY (" + KEY_GARE_ARRIVE + ") REFERENCES " + TABLE_GARE + "(" + KEY_ID_GARE + ")";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_GARE);
        sqLiteDatabase.execSQL(CREATE_TABLE_TRAJET);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_TRAJET);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_GARE);

        onCreate(sqLiteDatabase);
    }

    private void createGare(GareClasse gare) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID_GARE, gare.getId());
        values.put(KEY_NOM_GARE, gare.getName());

        db.insert(TABLE_GARE, null, values);
        System.out.println("Gare cree " + gare.getName());
    }

    private void findOrCreateGare(GareClasse gare) {
        GareClasse foundGare = findGare(gare.getId());
        if(foundGare == null) createGare(gare);
    }

    private GareClasse findGare(String gare_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_GARE + " WHERE " + KEY_ID_GARE + " = ?";
        String[] arguments = {gare_id};
        System.out.println(selectQuery);


        Cursor c = db.rawQuery(selectQuery, arguments);
        if (c != null) {
            c.moveToFirst();
        }
        try {
            assert c != null;
            String id_gare = c.getString(c.getColumnIndex(KEY_ID_GARE));
            String nom_gare = c.getString(c.getColumnIndex(KEY_NOM_GARE));

            return new GareClasse(id_gare, nom_gare);
        } catch (NullPointerException e) {
            System.out.println(e.toString());
        }
        c.close();
        return null;
    }

    public List<GareClasse> getAllGares() {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_GARE;
        List<GareClasse> gares = new ArrayList<>();

        Cursor c = db.rawQuery(selectQuery,null);

        if (c.moveToFirst()) {
            do {
                String id_gare = c.getString(c.getColumnIndex(KEY_ID_GARE));
                String nom_gare = c.getString(c.getColumnIndex(KEY_NOM_GARE));

                 gares.add(new GareClasse(id_gare, nom_gare));
            } while(c.moveToNext());
        }
        c.close();

        return gares;
    }

    public void deleteGare(String gare_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_GARE, KEY_ID_GARE + "=?", new String[] {gare_id});
    }

    private long createTrajet(TrajetClasse trajet) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        if (trajet.getGareDepart() != null) {
            findOrCreateGare(trajet.getGareDepart());
            values.put(KEY_GARE_DEPART, trajet.getGareDepart().getId());
        }
        if (trajet.getGareArrive() != null) {
            findOrCreateGare(trajet.getGareArrive());
            values.put(KEY_GARE_ARRIVE, trajet.getGareArrive().getId());
        }
        System.out.println("Trajet créé "+ trajet);
        return db.insert(TABLE_TRAJET, null, values);
    }

    private TrajetClasse findTrajet(long id_trajet) {
       SQLiteDatabase db = this.getReadableDatabase();
       String selectQuery = "SELECT * FROM " + TABLE_TRAJET + " WHERE " + KEY_ID_TRAJET + "= ?";
       Cursor c = db.rawQuery(selectQuery, new String[]{Objects.toString(id_trajet, null)});
       if (c != null) {
           c.moveToFirst();
       }
       try {
           assert c != null;
           GareClasse gare_depart = findGare(c.getString(c.getColumnIndex(KEY_GARE_DEPART)));
           GareClasse gare_arrive = findGare(c.getString(c.getColumnIndex(KEY_GARE_ARRIVE)));
            return new TrajetClasse(id_trajet, gare_depart, gare_arrive);
       } catch (NullPointerException e) {
           System.out.println(e.toString());
       }
        c.close();
       return null;
    }
    private TrajetClasse findTrajet(GareClasse gare_depart, GareClasse gare_arrive) {
       SQLiteDatabase db = this.getReadableDatabase();
       String selectQuery = "SELECT * FROM " + TABLE_TRAJET + " WHERE " + KEY_GARE_DEPART + "= ? AND " + KEY_GARE_ARRIVE + "= ?";
       Cursor c = db.rawQuery(selectQuery, new String[]{gare_depart.getId(), gare_arrive.getId()});
       if (c != null) {
           c.moveToFirst();
       }
       try {
           assert c != null;
           long id_trajet = c.getLong(c.getColumnIndex(KEY_ID_TRAJET));
            return new TrajetClasse(id_trajet, gare_depart, gare_arrive);
       } catch (NullPointerException e) {
           System.out.println(e.toString());
       }
        c.close();
       return null;
    }

    TrajetClasse findOrCreateTrajet(TrajetClasse trajet) {

        if (trajet.getId_trajet() != null && findTrajet(trajet.getId_trajet()) != null) {
            return findTrajet(trajet.getId_trajet());
        } else if (findTrajet(trajet.getGareDepart(), trajet.getGareArrive()) != null) {
            return findTrajet(trajet.getGareDepart(), trajet.getGareArrive());
        }
        else if (trajet.getId_trajet() == null) {
            return new TrajetClasse(createTrajet(trajet), trajet.getGareDepart(), trajet.getGareArrive());
        }
        else {
            return null;
        }
    }
    public List<TrajetClasse> getAllTrajet() {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_TRAJET;
        List<TrajetClasse> trajets = new ArrayList<>();

        Cursor c = db.rawQuery(selectQuery,null);

        if (c.moveToFirst()) {
            do {
                GareClasse gare_depart = findGare(c.getString(c.getColumnIndex(KEY_GARE_DEPART)));
                GareClasse gare_arrive = findGare(c.getString(c.getColumnIndex(KEY_GARE_ARRIVE)));
                long id_trajet = c.getLong(c.getColumnIndex(KEY_ID_TRAJET));
                trajets.add(new TrajetClasse(id_trajet, gare_depart, gare_arrive));
            } while(c.moveToNext());
        }
        c.close();

        return trajets;
    }

    public void deleteTrajet(long trajet_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TRAJET, KEY_ID_TRAJET + "=?", new String[] {Objects.toString(trajet_id)});
    }


}
