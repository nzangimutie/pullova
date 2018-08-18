package kinzangilar.systems.munzangilar.org.pullova_test;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class HomeFragment extends Fragment implements View.OnClickListener {


    private TextView tv_promoCode;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);;
        tv_promoCode = v.findViewById(R.id.tv_promoCode);
        Button btn_generatePromoCode = v.findViewById(R.id.btn_generatePromoCode);

        if (StaticVariables.ACTIVE_PROMOCODE != null) {
            tv_promoCode.setText(StaticVariables.ACTIVE_PROMOCODE);
            StaticVariables.ACTIVE_PROMOCODE = null;
        } else {
            tv_promoCode.setText(new PromoCodeGenerator().generatePromoCode());
        }

        btn_generatePromoCode.setOnClickListener(this);

        TextView tv_tryYourCode = v.findViewById(R.id.tv_tryYourCode);
        tv_tryYourCode.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_generatePromoCode:
                tv_promoCode.setText(new PromoCodeGenerator().generatePromoCode());
                break;
            case R.id.tv_tryYourCode:
                StaticVariables.ACTIVE_PROMOCODE=tv_promoCode.getText().toString();
                Fragment fragment = new PromotionFragment();
                Bundle bundle = new Bundle();
                bundle.putString("promoCode", tv_promoCode.getText().toString());
                fragment.setArguments(bundle);
                (getActivity().getSupportFragmentManager()).beginTransaction().replace(R.id.container, fragment).addToBackStack(null).commit();
                break;
        }
    }
}
