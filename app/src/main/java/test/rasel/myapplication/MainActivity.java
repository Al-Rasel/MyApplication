package test.rasel.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.realm.Realm;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private List<Person> personList = new ArrayList<Person>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_listOfProducts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        for (int i = 0; i < 40; i++) {
            personList.add(new Person("name: Rasel", i));
        }
        RecylerViewAdapter recylerViewAdapter = new RecylerViewAdapter(personList);
        recylerViewAdapter.setOnClick(new RecylerViewAdapter.onClick() {
            @Override
            public void onClickFromMyAdapter(int postion, View v) {

                databaseCheck(postion);
                Toast.makeText(getApplicationContext(), String.valueOf(postion), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onClickNameAdapter(int postion, View v) {
                Toast.makeText(v.getContext(), personList.get(postion).getName(), Toast.LENGTH_LONG).show();


            }
        });
        recyclerView.setAdapter(recylerViewAdapter);

        networkCallCheck();
    }

    private void networkCallCheck() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().readTimeout(30, TimeUnit.SECONDS)
                .cache(new Cache(this.getCacheDir(), 10 * 1024 * 1024))
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.bdvoucher.com/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        HomeNetworkCall homeNetworkCall = retrofit.create(HomeNetworkCall.class);
        homeNetworkCall.getAllCatData().enqueue(new Callback<List<CategoryModel>>() {
            @Override
            public void onResponse(Call<List<CategoryModel>> call, Response<List<CategoryModel>> response) {
                Log.e("valuesCheck", "onResponse: " + String.valueOf(response.body()));
            }

            @Override
            public void onFailure(Call<List<CategoryModel>> call, Throwable t) {

            }
        });


    }


    private void databaseCheck(int positon) {

        Dog dog = new Dog();
        dog.setName("Rex");
        dog.setAge(1);
        // Initialize Realm
        Realm.init(this);

// Get a Realm instance for this thread
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        final Dog managedDog = realm.copyToRealm(dog); // Persist unmanaged objects
        PersonModelDatabase personModelDatabase = realm.createObject(PersonModelDatabase.class); // Create managed objects directly

        personModelDatabase.setName("FirstName:  " + String.valueOf(positon));
        personModelDatabase.getDogs().add(managedDog);
        realm.commitTransaction();

    }

}
