package edu.mills.freeingourselves;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class TestosteroneHealthOverviewFragment extends Fragment {
    protected View view;
    protected WebView healthTestosteroneView;

    public TestosteroneHealthOverviewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_testosterone_health_overview, container, false);
        healthTestosteroneView = (WebView)view.findViewById(R.id.tWebView);
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
