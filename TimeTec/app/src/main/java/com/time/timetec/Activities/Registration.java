package com.time.timetec.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.time.timetec.R;

public class Registration extends AppCompatActivity {

    ImageView top_curve,logo;
    EditText fristnameEditText,lastNameEditText,phoneNumberEditText, email, password;
    TextView frist_name_text,last_name_text,phone_number_text, email_text, password_text;
    LinearLayout already_have_account_layout;
    CardView register_card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        top_curve = findViewById(R.id.top_curve);
        fristnameEditText=findViewById(R.id.fristNameTxtEditText);
        lastNameEditText=findViewById(R.id.lastNameEditText);
        phoneNumberEditText=findViewById(R.id.phoneNumberEditText);
        frist_name_text=findViewById(R.id.fristNameTxt);
        last_name_text=findViewById(R.id.lastNameTxt);
        phone_number_text=findViewById(R.id.phoneNumberTxt);


        email = findViewById(R.id.email);
        email_text = findViewById(R.id.email_text);
        password = findViewById(R.id.password);
        password_text = findViewById(R.id.password_text);
        logo = findViewById(R.id.logo);
        already_have_account_layout = findViewById(R.id.already_have_account_text);
        register_card = findViewById(R.id.register_card);


        Animation top_curve_anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.top_down);
        top_curve.startAnimation(top_curve_anim);

        Animation editText_anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.edittext_anim);
        email.startAnimation(editText_anim);
        password.startAnimation(editText_anim);
        fristnameEditText.startAnimation(editText_anim);
        lastNameEditText.startAnimation(editText_anim);
        phoneNumberEditText.startAnimation(editText_anim);

        Animation field_name_anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.field_name_anim);
        email_text.startAnimation(field_name_anim);
        password_text.startAnimation(field_name_anim);
        logo.startAnimation(field_name_anim);
        frist_name_text.startAnimation(field_name_anim);
        last_name_text.startAnimation(field_name_anim);
        phone_number_text.startAnimation(field_name_anim);


        Animation center_reveal_anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.center_reveal_anim);
        register_card.startAnimation(center_reveal_anim);

        Animation new_user_anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.down_top);
        already_have_account_layout.startAnimation(new_user_anim);

    }

    public void login(View view) {
        finish();
    }
    public void registerButton(View view) {

        if (formValidation()){
            registration();
        }
    }



    private boolean formValidation(){
        if (fristnameEditText.getText().toString().equals("")){
            fristnameEditText.setError("Enter your Frist Name");
            return false;
        }
        if (lastNameEditText.getText().toString().equals("")){
            lastNameEditText.setError("Enter your Last Name");
            return false;
        }
        if (email.getText().toString().length()>0 &&!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
            email.setError("Enter a valid Email");
            return false;
        }
        if (phoneNumberEditText.getText().toString().equals("")){
            phoneNumberEditText.setError("Enter your Phone Number (01-xxxxxxxxx)");
            return false;
        }
        if (phoneNumberEditText.getText().length()<11){
            phoneNumberEditText.setError("Phone Must be 11 digit (01-xxxxxxxxx)");
            return false;
        }

        if (password.getText().toString().equals("")){
            password.setError("Enter your Frist Name");
            return false;
        }

        if (password.getText().length()<6){
            password.setError("Password Must be 6 Digit");
            return false;
        }

        return true;



    }


    private void registration(){





    }
}
