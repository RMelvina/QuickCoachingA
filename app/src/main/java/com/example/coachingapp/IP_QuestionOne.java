package com.example.coachingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.coachingapp.Models.Issues;

public class IP_QuestionOne extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ImageButton imageIconRight;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;



    EditText editText;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseUser user;

    private boolean isClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ip__question_one);

        nav();


        editText = findViewById(R.id.question_1_edit_text);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        myRef = FirebaseDatabase.getInstance().getReference();
        database = FirebaseDatabase.getInstance();
    }


    private void singOut() {
        FirebaseAuth.getInstance().signOut();
        finish();
        startActivity(new Intent(this, Login.class));
    }






    private void nav() {
        drawerLayout = findViewById(R.id.layout_drawer);
        imageIconRight = findViewById(R.id.open_drawer_icon);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        drawerLayout.setDrawerListener(toggle);
        imageIconRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerOpen(navigationView)) {
                    drawerLayout.closeDrawer(navigationView);
                } else if (!drawerLayout.isDrawerOpen(navigationView)) {
                    drawerLayout.openDrawer(navigationView);
                }
            }
        });
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.nav_aboutus:

                Intent aboutUs = new Intent(getApplicationContext(), AboutUs.class);
                startActivity(aboutUs);
                drawerLayout.closeDrawers();

                break;
            case R.id.nav_singout:
                singOut();
                break;
            case R.id.nav_home:
                Intent home = new Intent(getApplicationContext(), UserDashboard.class);
                startActivity(home);
                break;

        }

        return true;
    }

    public void btn_ip_question_one__next(View view) {

        if (!isClicked) {
            dialogBoxTwo();

        } else {
            addToFirebase();

        }
        isClicked = !isClicked;
    }



    private void addToFirebase() {
        String answer_1 = editText.getText().toString().trim();


        if (user != null) {

            Issues issues = new Issues(answer_1);
            String id = user.getUid();
//            myRef.child("users").child(id).push().setValue(issues);

            myRef.child("users").child(id).child("Issues").child("answers_1").setValue(issues);


            Toast.makeText(this, "Information Saved", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(IP_QuestionOne.this, IP_QuestionTwo.class);
            startActivity(intent);


        }
    }

    private void dialogBoxTwo(){

        final AlertDialog.Builder alert = new AlertDialog.Builder(IP_QuestionOne.this);
        alert.setTitle("Help");
        alert.setCancelable(true);
        View mView = getLayoutInflater().inflate(R.layout.custom_dialog_box_two, null);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });


        alert.setView(mView);


        final AlertDialog alertDialog = alert.create();
        alertDialog.show();

//         TextView msgTxt = (TextView) alertDialog.findViewById(R.id.dialog_box_help_paragraph_2);
//         ImageView image = (ImageView) alertDialog.findViewById(R.id.dialog_box_help_paragraph_2);


//         msgTxt.setMovementMethod(LinkMovementMethod.getInstance());
//         alertDialog.setCanceledOnTouchOutside(false);


    }


}
