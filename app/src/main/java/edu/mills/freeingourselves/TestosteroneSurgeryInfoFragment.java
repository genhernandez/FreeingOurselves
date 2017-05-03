package edu.mills.freeingourselves;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;


/**
 * A simple {@link Fragment} subclass.
 */
public class TestosteroneSurgeryInfoFragment extends Fragment {
    protected View view;
    protected WebView surgeWebView;

    public TestosteroneSurgeryInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_testosterone_surgery_info, container, false);
        surgeWebView = (WebView) view.findViewById(R.id.surgeWebView);
        surgeWebView.loadUrl("file:///android_asset/testosterone_surgery_en.html");
        surgeWebView.setHorizontalScrollBarEnabled(true);
        surgeWebView.setVerticalScrollBarEnabled(true);
        return view;
    }

}
