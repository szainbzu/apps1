package com.example.apps1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.google.android.material.color.utilities.MaterialDynamicColors.error;

public class MainActivity extends AppCompatActivity {
    Button btnSave;
    private TextView txtResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSave = findViewById(R.id.btnSave);
        txtResult = findViewById(R.id.txtResult);
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference().child("books");
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               book b = new book("Firebase book 1", "programming", 120);

                ref.push().setValue(b);            }
        });

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    List<String> books = new ArrayList<>();
                    for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                        // Access the data from the child
                        String key = childSnapshot.getKey();
                        book b = childSnapshot.getValue(book.class);
                        books.add(b.getTitle());

                    }
                    String str = "";
                    for (String s : books) {
                        str += s + "\n";
                    }
                    txtResult.setText(str);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("error999", error.toString());
            }
        });



    }
}