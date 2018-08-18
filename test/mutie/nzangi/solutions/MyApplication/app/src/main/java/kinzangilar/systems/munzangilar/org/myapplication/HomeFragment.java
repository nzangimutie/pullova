package kinzangilar.systems.munzangilar.org.myapplication;


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
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        tv_promoCode = (TextView) v.findViewById(R.id.tv_promoCode);
        Button btn_generatePromoCode = (Button) v.findViewById(R.id.btn_generatePromoCode);

        String promoCode = new PromoCodeGenerator().generatePromoCode();
        tv_promoCode.setText(promoCode);

        btn_generatePromoCode.setOnClickListener(this);

        TextView tv_tryYourCode = (TextView) v.findViewById(R.id.tv_tryYourCode);
        tv_tryYourCode.setTextColor(getResources().getColor(R.color.colorPrimary));
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
                Fragment fragment = new PromotionFragment();
                Bundle bundle = new Bundle();
                bundle.putString("promoCode", tv_promoCode.getText().toString());
                fragment.setArguments(bundle);
                android.support.v4.app.FragmentTransaction ft = (getActivity().getSupportFragmentManager()).beginTransaction();
                ft.replace(R.id.container, fragment,"promo");
                ft.commit();
                break;
        }
    }
}
