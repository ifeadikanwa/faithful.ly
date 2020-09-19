package com.example.faithfully;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class SignInActivity extends AppCompatActivity {
    public static final String TAG = SignInActivity.class.getSimpleName();
    static final private int RC_SIGN_IN = 1111;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        //Before invoking the FirebaseUI authentication flow,
        // your app should check whether a user is already signed in from a previous session:
        FirebaseAuth auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            // already signed in

            //if the account is verified, check the shared preference for if religion has been picked
            //TODO: if it hasnt we go to chooseReligion activity
            // TODO: if it has we go straight to users timeline
            String religionType = PreferenceSettings.getReligionType(this);

            if (religionType == null) {
                //religion type hasn't been set
                startActivity(new Intent(SignInActivity.this, ChooseReligion.class));
            } else {
                //it has been set so we go to users timeline
                startActivity(new Intent(SignInActivity.this, HomePageActivity.class));
            }

        } else {
            // not signed in, start firebaseUI authentication flow
            //Choose authentication providers
            List<AuthUI.IdpConfig> providers = Arrays.asList(
                    new AuthUI.IdpConfig.EmailBuilder().build(),
                    new AuthUI.IdpConfig.GoogleBuilder().build());

            // Create and launch sign-in intent
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(providers)
                            .setLogo(R.drawable.ic_placeholder)
                            .build(),
                    RC_SIGN_IN);
        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        if (user != null) {

            //check the shared preference for if religion has been picked
            //TODO: if it hasnt we go to chooseReligion activity
            // TODO: if it has we go straight to users timeline
            String religionType = PreferenceSettings.getReligionType(this);

            if (religionType == null) {
                //religion type hasn't been set
                startActivity(new Intent(SignInActivity.this, ChooseReligion.class));
            } else {
                //it has been set so we go to users timeline
                startActivity(new Intent(SignInActivity.this, HomePageActivity.class));
                finish();
            }
        }
    }


    //When the sign-in flow is complete, you will receive the result in onActivityResult:
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                Log.i("SIGN IN", "user successfully signed in");

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                

                //if the user signs in take them to choose religion activity
                startActivity(new Intent(SignInActivity.this, ChooseReligion.class));

            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...

                if (response == null) {
                    // User pressed back button
                    Toast.makeText(this, "sign in cancelled", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (response.getError().getErrorCode() == ErrorCodes.NO_NETWORK) {
                    Toast.makeText(this, "no internet connection", Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(this, "unknown error", Toast.LENGTH_SHORT).show();
                Log.e("SIGN IN ERROR", "Sign-in error: ", response.getError());

            }
        }

    }
}