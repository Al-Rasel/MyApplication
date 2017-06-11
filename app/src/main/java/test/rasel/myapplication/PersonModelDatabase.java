package test.rasel.myapplication;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Rasel on 6/11/2017.
 */

public class PersonModelDatabase extends RealmObject {

    private String name;
    private RealmList<Dog> dogs;

    public PersonModelDatabase() {
    }





    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RealmList<Dog> getDogs() {
        return dogs;
    }

    public void setDogs(RealmList<Dog> dogs) {
        this.dogs = dogs;
    }


}
