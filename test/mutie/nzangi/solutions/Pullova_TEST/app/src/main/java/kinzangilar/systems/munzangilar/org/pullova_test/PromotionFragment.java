package kinzangilar.systems.munzangilar.org.pullova_test;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;


/**
 * A simple {@link Fragment} subclass.
 */
public class PromotionFragment extends Fragment implements View.OnClickListener, Toolbar.OnMenuItemClickListener {


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
        Toolbar toolbar = view.findViewById(R.id.toolbar);

        toolbar.setTitle(R.string.promotionScrnTitle);
        toolbar.inflateMenu(R.menu.menu_items);
        toolbar.setOnMenuItemClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnApply:
                String userText = edt_promoCode.getText().toString();
                if (userText.length() == 6) {
                    if (userText.equals(promoCode)) {
                Fragment fragment = new MapFragment();
                (getActivity().getSupportFragmentManager()).beginTransaction().replace(R.id.container, fragment).addToBackStack(null).commit();
                    } else {
                        new CodeMisMatchFragment().show(getActivity().getSupportFragmentManager(), "one");
                    }
                } else {
                    Toast.makeText(getContext(), R.string.wrongCodePromptText, Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.item_close:
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                for (int y = 0; y < fragmentManager.getBackStackEntryCount(); y++) {
                    fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                }
                break;
        }
        return true;
    }
}
