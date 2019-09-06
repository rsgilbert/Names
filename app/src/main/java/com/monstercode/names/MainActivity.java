package com.monstercode.names;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton buttonAddTask;
    private RecyclerView recyclerView;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: creating main activity");
        recyclerView = findViewById(R.id.recyclerview_names);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        buttonAddTask = findViewById(R.id.floating_button_add);
        buttonAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddNameActivity.class));
            }
        });

        getNames();
    }

    private void getNames() {
        Log.d(TAG, "getNames: Getting names");
        class GetNames extends AsyncTask<Void, Void, List<Name>> {
            @Override
            protected void onPostExecute(List<Name> names) {
                Log.d(TAG, "onPostExecute: Posting results");
                super.onPostExecute(names);
                NamesAdapter adapter = new NamesAdapter(MainActivity.this, names);
                recyclerView.setAdapter(adapter);

            }

            @Override
            protected List<Name> doInBackground(Void... voids) {
                Log.d(TAG, "doInBackground: Working in background");
                AppDatabase appDatabase = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase();
                NameDao nameDao = appDatabase.nameDao();
                List<Name> nameList = nameDao.getAll();
                Log.d(TAG, "doInBackground: Finished background task");
                return nameList;
            }
        }

        GetNames getNames = new GetNames();
        getNames.execute();
    }
}
