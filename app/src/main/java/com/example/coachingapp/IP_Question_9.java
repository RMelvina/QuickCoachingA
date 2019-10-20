package com.example.coachingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coachingapp.Models.Issues_12;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class IP_Question_9 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ImageButton imageIconRight;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    Button yesbtn,nobtn;
    TextView textView;
    private int buttonState = 1;

    EditText editText;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ip__question_9);

        nav();

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        myRef = FirebaseDatabase.getInstance().getReference();
        database = FirebaseDatabase.getInstance();

        textView = (TextView) findViewById(R.id.number);

        yesbtn = (Button) findViewById(R.id.btnYes);
        nobtn = (Button) findViewById(R.id.btnNo);

    }

    private void nav(){
        drawerLayout = findViewById(R.id.layout_drawer);
        imageIconRight = findViewById(R.id.open_drawer_icon);

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        drawerLayout.setDrawerListener(toggle);
        imageIconRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(drawerLayout.isDrawerOpen(navigationView)){
                    drawerLayout.closeDrawer(navigationView);
                }else if(!drawerLayout.isDrawerOpen(navigationView)){
                    drawerLayout.openDrawer(navigationView);
                }
            }
        });
    }

    private void singOut(){
        FirebaseAuth.getInstance().signOut();
        finish();
        startActivity(new Intent(this, Login.class));
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){
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

    public void btn_no(View view) {
        if(buttonState % 2 == 0){
            yesbtn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }
        else{
            yesbtn.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            nobtn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            textView.setText("No");
            Toast.makeText(getBaseContext(), "Your are chosing No", Toast.LENGTH_SHORT).show();
        }
        buttonState++;

    }

    public void btn_yes(View view) {

        if(buttonState % 2 == 0){
            yesbtn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }
        else{
            yesbtn.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            nobtn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            textView.setText("Yes");
            Toast.makeText(getBaseContext(), "Your are chosing YES", Toast.LENGTH_SHORT).show();
        }
        buttonState++;
    }

    private void addToFirebase() {

        String answer_12 = textView.getText().toString();

        if (user != null) {
            Issues_12 issues_12 = new Issues_12(answer_12);
            String id = user.getUid();
            myRef.child("users").child(id).child("Issues").child("answer_12").setValue(issues_12);
            Toast.makeText(this, "Information Saved", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(IP_Question_9.this, IP_Question_10.class);
            startActivity(intent);
        }
    }

    public void btn_next(View view) {
        addToFirebase();
    }
}
