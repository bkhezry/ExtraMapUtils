package com.github.bkhezry.demoextramaputils.ui.fragment;

import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.bkhezry.demoextramaputils.R;
import com.github.bkhezry.demoextramaputils.utils.AppUtils;


public class AboutFragment extends Fragment {
    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_about,
                container, false);
        String versionName = "";
        try {
            versionName = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            // do nothing
        }
        setTextWithLinks((TextView) view.findViewById(R.id.text_application_info), getString(R.string.application_info_text, versionName));
        setTextWithLinks((TextView) view.findViewById(R.id.text_developer_info), getString(R.string.developer_info_text));
        setTextWithLinks((TextView) view.findViewById(R.id.text_libraries), getString(R.string.libraries_text));
        setTextWithLinks((TextView) view.findViewById(R.id.text_license), getString(R.string.license_text));
        return view;
    }

    private void setTextWithLinks(TextView textView, String htmlText) {
        AppUtils.setTextWithLinks(textView, AppUtils.fromHtml(htmlText));
    }

    public Fragment newInstance() {
        return new AboutFragment();
    }
}
