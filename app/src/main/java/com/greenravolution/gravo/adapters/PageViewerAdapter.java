package com.greenravolution.gravo.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.greenravolution.gravo.R;

import java.util.ArrayList;


public class PageViewerAdapter extends PagerAdapter {

    private ArrayList<String> text;
    private LayoutInflater inflater;
    private Context context;

    public PageViewerAdapter(Context context, ArrayList<String> text) {
        this.context = context;
        this.text = text;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return text.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View v = inflater.inflate(R.layout.welcome_page_about_us, view, false);
        TextView textView = v
                .findViewById(R.id.welcome_texts);
        textView.setText(text.get(position));
        view.addView(v, 0);
        return v;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}
