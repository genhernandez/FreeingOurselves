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
 * Displays introductory information regarding testosterone.
 */
public class TestosteroneFragment extends Fragment {
    protected View view;
    protected WebView tWebView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_testosterone, container, false);
        tWebView = (WebView) view.findViewById(R.id.tWebView);
        tWebView.loadUrl("file:///android_asset/testosterone_intro_en.html");

        Button learnMoreButton = (Button) view.findViewById(R.id.learnMoreButton);
        learnMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment testosteroneFragment = new TestosteroneHealthOverviewFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, testosteroneFragment);
                ft.addToBackStack(null);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();

            }
        });

        return view;
    }
}
