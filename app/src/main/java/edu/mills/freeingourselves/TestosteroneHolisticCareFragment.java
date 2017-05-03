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
 * Displays information regarding holistic care when on hormone treatment.
 */
public class TestosteroneHolisticCareFragment extends Fragment {
    protected View view;
    protected WebView holisticCareWebView;

    public TestosteroneHolisticCareFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_testosterone_holistic_care, container, false);

        holisticCareWebView = (WebView)view.findViewById(R.id.tWebView);
        holisticCareWebView.loadUrl("file:///android_asset/holistic_care_en.html");
        holisticCareWebView.setHorizontalScrollBarEnabled(true);
        holisticCareWebView.setVerticalScrollBarEnabled(true);

        Button surgeryCareButton = (Button) view.findViewById(R.id.surgeryInfoButton);
        surgeryCareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment surgeryInfoFragment = new TestosteroneSurgeryInfoFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, surgeryInfoFragment);
                ft.addToBackStack(null);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }
        });
        return view;
    }

}
