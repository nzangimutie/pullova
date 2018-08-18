package kinzangilar.systems.munzangilar.org.pullova_test;


import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class CodeMisMatchFragment extends DialogFragment {


    public CodeMisMatchFragment() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.fragment_code_mis_match, null, false);
        Dialog d = new AlertDialog.Builder(getContext())
                .setTitle("Code Mismatch:")
                .setView(v)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        android.support.v4.app.Fragment fragment = new HomeFragment();
                        android.support.v4.app.FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.container, fragment, "promo");
                        ft.commit();
                    }
                })
                .setNegativeButton("Re-Enter Code", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getContext(), "Re-Enter Code", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
        return d;
    }

}
