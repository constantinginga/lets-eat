package view;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.letseat.AuthActivity;
import com.example.letseat.R;
import com.google.firebase.auth.FirebaseAuth;

public class LoginFragment extends Fragment {

    private FirebaseAuth mAuth;
    private Intent mainActivityIntent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        AuthActivity activity = ((AuthActivity) getActivity());
        EditText emailInput = view.findViewById(R.id.loginEmailInput);
        EditText passwordInput = view.findViewById(R.id.loginPasswordInput);
        Button loginBtn = view.findViewById(R.id.loginBtn);
        TextView signUpLink = view.findViewById(R.id.signUpLink);

        signUpLink.setPaintFlags(signUpLink.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        signUpLink.setOnClickListener(v -> activity.replaceFragment(false));
        loginBtn.setOnClickListener(v -> activity.login(emailInput.getText().toString(), passwordInput.getText().toString()));
//        FragmentManager fragmentManager = getParentFragmentManager();
//        FragmentTransaction ft = fragmentManager.beginTransaction();
//        // render different fragment based on login state
//        ft.replace(R.id.fragmentContainerView, new SignUpFragment());
//        ft.commit();

        return view;
    }
}