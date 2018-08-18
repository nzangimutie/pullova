package kinzangilar.systems.munzangilar.org.myapplication;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class PromotionFragment extends Fragment implements View.OnClickListener {


    private String promoCode = "";
    private EditText edt_promoCode;

    public PromotionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_promotion, container, false);
        edt_promoCode = view.findViewById(R.id.edt_promoCode);
        Button btnApply = view.findViewById(R.id.btnApply);
        btnApply.setOnClickListener(this);
        promoCode = getArguments().getString("promoCode");

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnApply:
                String userText = edt_promoCode.getText().toString();
                if (userText.length() == 6) {
                    if (userText.equals(promoCode)) {
                    } else {
                    }
                } else {
                    Toast.makeText(getContext(), "Please enter a valid code not: " + userText, Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
