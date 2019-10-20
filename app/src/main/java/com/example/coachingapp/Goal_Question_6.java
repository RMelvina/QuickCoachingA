package com.example.coachingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Goal_Question_6 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    ImageButton imageIconRight;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    EditText editText;
    LinearLayout mLayout;

    FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseUser user;

//    List<String> etText = new ArrayList<String>();
//    List<EditText> myLists = new ArrayList<EditText>();
//
//    List<String> g ;
//    int len;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal__question_6);
        nav();
        setTimer();

//        etText = new ArrayList<>();
        mLayout = findViewById(R.id.steps_layouts);
        editText = findViewById(R.id.title_1);

//        g = new ArrayList<>();
//        g = (List<String>) getIntent().getSerializableExtra("Goals");
//        len = g.size();

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        myRef = FirebaseDatabase.getInstance().getReference();
        database = FirebaseDatabase.getInstance();

//        displayText();

        Query query = myRef.child("users").child(user.getUid());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String day = dataSnapshot.child("Goals").child("input_1").getValue().toString();
                editText.setText(day);





            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void setTimer(){
        final Button button = (Button)findViewById(R.id.btn_next_my_rd_map_2);
        final Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                button.setVisibility(View.VISIBLE);
                button.startAnimation(animation);


            }
        },5000);
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

    public void btn_next(View view) {
        Intent intent = new Intent(Goal_Question_6.this, Goal_Question_7.class);
        startActivity(intent);
    }

//    private void displayText(){
//
//
//
//        EditText[] et = new EditText[len];
//        for(int i = 0; i <len; i++) {
//            EditText ed = new EditText(Goal_Question_6.this);
//            ed.setText(g.get(i).toString());
//            TableLayout.LayoutParams layoutParams = new TableLayout.LayoutParams();
//            mLayout.addView(ed);
//            ed.setBackground(getDrawable(R.drawable.text_box));
//            layoutParams.setMargins(0, 30, 0, 10);
//            ed.setLayoutParams(layoutParams);
//            et[i] = ed;
//
//        }
//
//
//
//
//
//

    }

