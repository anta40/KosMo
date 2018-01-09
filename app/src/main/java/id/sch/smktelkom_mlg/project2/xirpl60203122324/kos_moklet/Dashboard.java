package id.sch.smktelkom_mlg.project2.xirpl60203122324.kos_moklet;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.sch.smktelkom_mlg.project2.xirpl60203122324.kos_moklet.adapter.ImageAdapter;
import me.relex.circleindicator.CircleIndicator;

public class Dashboard extends Fragment {

    public Dashboard() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        ViewPager vp = (ViewPager) view.findViewById(R.id.view_pager);
        CircleIndicator indicator = (CircleIndicator) view.findViewById(R.id.indicator);
        ImageAdapter adapter = new ImageAdapter(getActivity());
        vp.setAdapter(adapter);
        indicator.setViewPager(vp);
        return view;
    }



}
