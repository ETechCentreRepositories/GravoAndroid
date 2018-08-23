package com.greenravolution.gravo.MainFragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.greenravolution.gravo.R;
import com.greenravolution.gravo.contents.ActivitySettingsItems;
import com.greenravolution.gravo.contents.ActivityWebView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Settings extends Fragment {


    public Settings() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        RelativeLayout tnc = view.findViewById(R.id.tnc);
        RelativeLayout pp = view.findViewById(R.id.pp);
        RelativeLayout ag = view.findViewById(R.id.ag);
        ag.setOnClickListener(v-> startActivity(new Intent(getContext(), ActivityWebView.class).putExtra("link","http://ehostingcentre.com/gravo/content/aboutgravo.php")));
        tnc.setOnClickListener(v-> startActivity(new Intent(getContext(), ActivityWebView.class).putExtra("link","http://ehostingcentre.com/gravo/content/termsandconditions.php")));
        pp.setOnClickListener(v-> startActivity(new Intent(getContext(), ActivityWebView.class).putExtra("link","http://ehostingcentre.com/gravo/content/privacypolicy.php")));
        return view;
    }
}
