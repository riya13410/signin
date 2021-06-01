package com.example.sign_in;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputLayout;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    // defining our own password pattern
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[@#$%^&+=])" +	 // at least 1 special character
                    "(?=\\S+$)" +		 // no white spaces
                    ".{4,}" +			 // at least 4 characters
                    "$");
    private TextInputLayout email;
    private TextInputLayout password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referencing email and password
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
    }

    private boolean validateEmail() {

        // Extract input from EditText
        String emailInput = email.getEditText().getText().toString().trim();

        // if the email input field is empty
        if (emailInput.isEmpty()) {
            email.setError("Field can not be empty");
            return false;
        }

        // Matching the input email to a predefined email pattern
        else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            email.setError("Please enter a valid email address");
            return false;
        } else {
            email.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String passwordInput = password.getEditText().getText().toString().trim();
        // if password field is empty
        // it will display error message "Field can not be empty"
        if (passwordInput.isEmpty()) {
            password.setError("Field can not be empty");
            return false;
        }

        // if password does not matches to the pattern
        // it will display an error message "Password is too weak"
        else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            password.setError("Password is too weak");
            return false;
        } else {
            password.setError(null);
            return true;
        }
    }

    public void confirmInput(View v) {
        if (!validateEmail() | !validatePassword()) {
            return;
        }

        // if the email and password matches, a toast message
        // with email and password is displayed
        String input = "Email: " + email.getEditText().getText().toString();
        input += "\n";
        input += "Password: " + password.getEditText().getText().toString();
        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
    }
}
