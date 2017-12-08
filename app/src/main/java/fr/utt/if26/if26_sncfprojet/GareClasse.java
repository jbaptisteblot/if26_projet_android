package fr.utt.if26.if26_sncfprojet;

/**
 * Classe permettant de gérer les gares.
 * Created by Jeanba on 08/12/2017.
 */

public class GareClasse {
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

    @Override
    public String toString() {
        return "GareClasse{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
