package edu.mills.freeingourselves;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

/**
 * Displays an about page with information.
 */
public class AboutFragment extends Fragment {

    protected WebView aboutWebView;
    protected View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_about, container, false);
        aboutWebView = (WebView) view.findViewById(R.id.aboutWebView);
        aboutWebView.loadUrl("file:///android_asset/about_en.html");
        aboutWebView.setVerticalScrollBarEnabled(true);
        aboutWebView.setHorizontalScrollBarEnabled(true);

        return view;
    }
}
