package com.monstercode.names;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddNameActivity extends AppCompatActivity {
    private EditText editFirstName, editLastName;
    private Button saveButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_name);

        editFirstName = findViewById(R.id.firstName);
        editLastName = findViewById(R.id.lastName);
        saveButton = findViewById(R.id.saveButton);


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveName();
            }
        });
    }

    private void saveName() {
        final  String sFirstName = editFirstName.getText().toString().trim();
        final String sLastName = editLastName.getText().toString().trim();


        // performing db operation in main thread will crash our app
        class SaveTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                Name name = new Name();
                name.setFirstName(sFirstName);
                name.setLastName(sLastName);

                // add to db
                AppDatabase appDatabase = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase();
                NameDao nameDao = appDatabase.nameDao();
                nameDao.insert(name);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                Toast.makeText(AddNameActivity.this, "Saved Name ", Toast.LENGTH_SHORT).show();
                Log.d("PostExecute", "Saved name");
            }
        }

        SaveTask saveTask = new SaveTask();
        saveTask.execute();
    }
}
