package com.razzaaq.fragmentsexample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

public class ViewPagerFragment extends Fragment {
    public static final String KEY_RECIPE_INDEX="recipe_index";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        assert getArguments() != null;
        int position=getArguments().getInt(KEY_RECIPE_INDEX);
        Objects.requireNonNull(getActivity()).setTitle(recipes.names[position]);
        View view= LayoutInflater.from(getContext()).inflate(R.layout.fragment_view_pager,container,false);
        ViewPager viewPager=view.findViewById(R.id.viewPager);
        final IngredientsFragment ingredientsFragment=new IngredientsFragment();
        Bundle bundle=new Bundle();
        bundle.putInt(KEY_RECIPE_INDEX,position);
        ingredientsFragment.setArguments(bundle);
        final DirectionsFragments directionsFragments=new DirectionsFragments();
        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
               return position==0 ? ingredientsFragment:directionsFragments;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return position==0?"Ingredients":"Directions";
            }

            @Override
            public int getCount() {
                return 2;
            }
        });
        TabLayout tabLayout=view.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }
    @Override
    public void onStop() {
        super.onStop();
        Objects.requireNonNull(getActivity()).setTitle(getResources().getString(R.string.app_name));
    }
}
