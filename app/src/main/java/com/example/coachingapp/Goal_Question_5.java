package com.example.coachingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.InputType;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.view.DragEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
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

public class Goal_Question_5 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    ImageButton imageIconRight;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseUser user;


    EditText editText;
    LinearLayout mLayout;

    ImageView i1, i2, i3, i4;

//    List<String> etText = new ArrayList<String>();
    List<EditText> myLists = new ArrayList<EditText>();

    List<String> g ;
    int len;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal__question_5);
        nav();

       // etText = new ArrayList<>();
        mLayout = findViewById(R.id.steps_layouts);
        editText = findViewById(R.id.display_goal_step_txt_box);

        g = new ArrayList<>();
        g = (List<String>) getIntent().getSerializableExtra("Goals");
        len = g.size();

//        SharedPreferences data = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
//
//        String d = data.getString("Goals", "THIS IS DEFAULT VALUE");
//        len = d.size()




        i1 = (ImageView) findViewById(R.id.i1);
        i2 = (ImageView) findViewById(R.id.i2) ;
        i3 = (ImageView) findViewById(R.id.i3);
        i4 = (ImageView) findViewById(R.id.i4) ;

        i1.setBackgroundResource(R.drawable.hazardicon);

        i1.setOnLongClickListener(longClickListener);








        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        myRef = FirebaseDatabase.getInstance().getReference();
        database = FirebaseDatabase.getInstance();


        displayText(g);


        Query query = myRef.child("users").child(user.getUid());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String day = dataSnapshot.child("Goals").child("input_1").child("user_input_1").getValue().toString();
                editText.setText(day);





            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



//        i1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                inText(i1.getDrawable());
//            }
//        });
//
//
//        i2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                inText(i2.getDrawable());
//            }
//        });
//
//
//        img3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                inText(img3.getDrawable());
//            }
//        });
//
//        img4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                inText(img4.getDrawable());
//            }
//        });




    }

    View.OnLongClickListener longClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {

            ClipData clipData = ClipData.newPlainText("","");
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
            v.startDragAndDrop(clipData, shadowBuilder, v, 0);

            return true;
        }
    };


    View.OnDragListener dragListener  = new View.OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {

            int dragEvent = event.getAction();

            switch (dragEvent){
                case DragEvent.ACTION_DRAG_ENTERED:

                    final View view = (View)event.getLocalState();

                    if(view.getId() == R.id.i1){

//                        EditText ed = new EditText(Goal_Question_5.this);
//                        ed.setText("created");

                    }else if (view.getId() == R.id.i2){



                    }

                    break;

                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                   break;

            }

            return false;
        }
    };






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
        Intent intent = new Intent(Goal_Question_5.this, Goal_13.class);
        startActivity(intent);
    }

    private void displayText(List<String> txt){


        int EditTextCursor;

        EditText[] et = new EditText[len];
        for(int i = 0; i <len; i++) {
            EditText ed = new EditText(Goal_Question_5.this);
            ed.setText(txt.get(i).toString());
                    //g.get(i).toString());

            //SpannableStringBuilder spannableStringBuilder ;



            ed.animate()
                    .x(ed.getX())
                    .y(ed.getY())
                    .setDuration(700)
                    .start();

            myLists.add(ed);
            ed.setOnDragListener(dragListener);
            TableLayout.LayoutParams layoutParams = new TableLayout.LayoutParams();
            mLayout.addView(ed);
            ed.setBackground(getDrawable(R.drawable.text_box));
            layoutParams.setMargins(0, 30, 0, 10);
            ed.setLayoutParams(layoutParams);
            et[i] = ed;

        }


    }


//
//           private void inText(Drawable drawableHolder){
//            int EditTextCursor;
//            SpannableStringBuilder spannableStringBuilder ;
//           // drawableHolder.setBounds(0, 0, drawableHolder.getIntrinsicWidth(), drawableHolder.getIntrinsicHeight());
//
//          //  EditTextCursor =  ed.getSelectionStart();
//
//           // ed.setInputType(InputType.TYPE_NULL);
//           // ed.setTextIsSelectable(true);
//
//          //  ed.getText().insert(EditTextCursor, ".");
//           // EditTextCursor = ed.getSelectionStart();
//
//              for (EditText ed: myLists){
//                  EditTextCursor =  ed.getSelectionStart();
//                  ed.getText().insert(EditTextCursor, ".");
//                  EditTextCursor = ed.getSelectionStart();
//                  spannableStringBuilder = new SpannableStringBuilder(ed.getText());
//
//                  spannableStringBuilder.setSpan(new ImageSpan(drawableHolder),
//                          EditTextCursor - ".".length(),
//                          EditTextCursor,
//                          Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
//                  );
//                  ed.setText(spannableStringBuilder);
//                  ed.setSelection(EditTextCursor);
//
//
//
//
//              }
////            spannableStringBuilder = new SpannableStringBuilder(ed.getText());
////
////
////            spannableStringBuilder.setSpan(new ImageSpan(drawableHolder),
////                    EditTextCursor - ".".length(),
////                    EditTextCursor,
////                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
////            );
//
////            ed.setText(spannableStringBuilder);
////            ed.setSelection(EditTextCursor);
//
//    }
////
//    public void click_image_1(View view) {
//         inText(i1.getDrawable());
//    }
//
//    public void click_image_2(View view) {
//        inText(i2.getDrawable());
//    }
//
//    public void click_image_3(View view) {
//        inText(i3.getDrawable());
//    }
//
//
//
//    public void click_image_4(View view) {
//        inText(i4.getDrawable());
//    }
}
