package fragments;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xiaocaiyidie.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class CollectFragment extends Fragment {
    private ViewPager pager;
    private TabFragment.FragmentAdapter fragmentAdapter;
    private List<TabFragment> fragmentList;
    private TabLayout tabLayout;
    private List<String> mTitles;
    private String [] title={"收藏","历史记录"};

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.collect_fragment, container, false);
        pager = view.findViewById(R.id.collect_pager);
        tabLayout = view.findViewById(R.id.collect_tabs);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        fragmentList = new ArrayList<>();
        mTitles = new ArrayList<>();
        for (int i = 0; i < title.length; i++) {
            mTitles.add(title[i]);
            fragmentList.add(new TabFragment(title[i]));
        }

        fragmentAdapter = new TabFragment.FragmentAdapter(getActivity().getSupportFragmentManager(), fragmentList, mTitles);
        pager.setAdapter(fragmentAdapter);
        tabLayout.setupWithViewPager(pager);
    }
}