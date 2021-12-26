package fragments;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.example.xiaocaiyidie.R;
import com.example.xiaocaiyidie.RecipeAddActivity;
import com.example.xiaocaiyidie.SearchActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class RecipeFragment extends Fragment {
    private ViewPager pager;
    private TabFragment.FragmentAdapter fragmentAdapter;
    private List<TabFragment> fragmentList;
    private TabLayout tabLayout;
    private List<String> mTitles;
    private String [] title={"菜谱","动态"};

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recipe_fragment, container, false);
        pager = view.findViewById(R.id.recipe_pager);
        tabLayout = view.findViewById(R.id.recipt_tabs);
        setHasOptionsMenu(true);
        return view;
    }

    // 添加fragment界面，分页导航
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        fragmentList = new ArrayList<>();
        mTitles = new ArrayList<>();
        for (int i = 0; i< title.length; i++) {
            mTitles.add(title[i]);
            fragmentList.add(new TabFragment(title[i]));
        }

        fragmentAdapter = new TabFragment.FragmentAdapter(getActivity().getSupportFragmentManager(), fragmentList, mTitles);
        pager.setAdapter(fragmentAdapter);
        tabLayout.setupWithViewPager(pager);
    }



    // 当用户点击Menu按键时触发该方法  添加菜谱、动态
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.recipe_add_trends, menu);
        MenuItem menuItem = menu.findItem(R.id.recipe_add);
        MenuItem menuItem1 = menu.findItem(R.id.recipe_add_ricipe);
        MenuItem menuItem2 = menu.findItem(R.id.recipe_add_trends);
        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.recipe_add:
                Intent intent = new Intent(getActivity(), RecipeAddActivity.class);
                startActivity(intent);
                break;
            case R.id.recipe_add_ricipe:
                Intent intent1 = new Intent();
                intent1.setAction("android.media.action.IMAGE_CAPTURE");
                //通过addCategory设置类别为"android.intent.category.DEFAULT"
                intent1.addCategory("android.intent.category.DEFAULT");
                startActivity(intent1);
                break;
            case R.id.recipe_add_trends:
                Toast.makeText(getActivity(), "点击了分享", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }

}