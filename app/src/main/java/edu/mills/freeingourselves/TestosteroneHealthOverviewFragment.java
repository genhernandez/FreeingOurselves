package edu.mills.freeingourselves;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;


/**
 * Displays health overview of hormone treatment.
 */
public class TestosteroneHealthOverviewFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_testosterone_health_overview, container, false);
        WebView healthTestosteroneView = (WebView) view.findViewById(R.id.tWebView);
        healthTestosteroneView.loadUrl("file:///android_asset/testosterone_detail_en.html");
        healthTestosteroneView.setVerticalScrollBarEnabled(true);
        healthTestosteroneView.setHorizontalScrollBarEnabled(true);

        Button holisticCareButton = (Button) view.findViewById(R.id.holisticCareButton);
        holisticCareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment holisticCareFragment = new TestosteroneHolisticCareFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, holisticCareFragment);
                ft.addToBackStack(null);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }
        });

        return view;
    }

}
