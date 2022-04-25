package view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.letseat.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthFragment extends Fragment {

    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_auth, container, false);
        mAuth = FirebaseAuth.getInstance();
        EditText emailInput = view.findViewById(R.id.emailInput);
        EditText passwordInput = view.findViewById(R.id.passwordInput);
        Button signupBtn = view.findViewById(R.id.signupBtn);

        signupBtn.setOnClickListener(v -> {
//            mAuth.createUserWithEmailAndPassword(emailInput.getText().toString(), passwordInput.getText().toString())
//                    .addOnCompleteListener(getActivity(), task -> {
//                        if (task.isSuccessful()) {
//                            // Sign up successful
//                            Log.d("Auth", "createUserWithEmail:success");
//                            Toast.makeText(getActivity(), "Registration successful", Toast.LENGTH_SHORT).show();
//                            FirebaseUser user = mAuth.getCurrentUser();
//                        } else {
//                            // Sign up failed
//                            Log.w("Auth", "createUserWithEmail:failure", task.getException());
//                            Toast.makeText(getActivity(), "Registration failed", Toast.LENGTH_SHORT).show();
//                        }
//                    });
            mAuth.signInWithEmailAndPassword(emailInput.getText().toString(), passwordInput.getText().toString())
                    .addOnCompleteListener(getActivity(), task -> {
                       if (task.isSuccessful()) {
                           // Login successful
                           FragmentManager fragmentManager = getParentFragmentManager();
                           FragmentTransaction ft = fragmentManager.beginTransaction();
                           // MainPageFragment mainPageFragment = new MainPageFragment();
                           // ft.hide(AuthFragment.this);
                           ft.replace(R.id.frameLayout, new MainPageFragment());
                           ft.commit();
                       }
                       else {
                           // Login failed
                           Log.w("Auth", "signInWithEmailAndPassword:failure", task.getException());
                           Toast.makeText(getActivity(), "Login failed", Toast.LENGTH_SHORT).show();
                       }
                    });
        });
        return view;
    }
}
