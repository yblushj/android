package fragments;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.fonts.Font;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.xiaocaiyidie.AddRecipeActiviey;
import com.example.xiaocaiyidie.AddTrendsActivity;
import com.example.xiaocaiyidie.MainActivity;
import com.example.xiaocaiyidie.R;
import com.example.xiaocaiyidie.SearchActivity;
import com.google.android.material.tabs.TabLayout;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private ViewPager pager;
    private TabFragment.FragmentAdapter fragmentAdapter;
    private List<TabFragment> fragmentList;
    private TabLayout tabLayout;
    private List<String> mTitles;
    private String [] title={"推荐","直播","分类"};

    // 设置菜单内容
    private static final int FONT_10 = 0x111;
    private static final int PLAIN_ITEM = 0x11b;
    private static final int FONT_RED = 0x116;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        pager=view.findViewById(R.id.container);
        tabLayout=view.findViewById(R.id.tabs);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        fragmentList=new ArrayList<>();
        mTitles=new ArrayList<>();
        for(int i=0;i<title.length;i++){
            mTitles.add(title[i]);
            fragmentList.add(new TabFragment(title[i]));
        }
        fragmentAdapter=new TabFragment.FragmentAdapter(getActivity().getSupportFragmentManager(),fragmentList,mTitles);
        pager.setAdapter(fragmentAdapter);
        tabLayout.setupWithViewPager(pager);//与ViewPage建立关系
    }


    // 当用户点击Menu按键时触发该方法
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.actionbar_units, menu);
        MenuItem menuItem = menu.findItem(R.id.actionbar_search);
        MenuItem menuItem1 = menu.findItem(R.id.actionbar_photo);
        MenuItem menuItem2 = menu.findItem(R.id.actionbar_share);
        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actionbar_search:
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.actionbar_photo:
                Intent intent1 = new Intent();
                intent1.setAction("android.media.action.IMAGE_CAPTURE");
                //通过addCategory设置类别为"android.intent.category.DEFAULT"
                intent1.addCategory("android.intent.category.DEFAULT");
                startActivity(intent1);
                break;
            case R.id.actionbar_share:
                Toast.makeText(getActivity(), "点击了分享", Toast.LENGTH_SHORT).show();
                break;
            case R.id.recipe_add_ricipe:
                Intent intent3 = new Intent(getActivity(), AddRecipeActiviey.class);
                startActivity(intent3);
                break;
            case R.id.recipe_add_trends:
                Intent intent4 = new Intent(getActivity(), AddTrendsActivity.class);
                startActivity(intent4);
                break;
            default:
                break;
        }
        return true;
    }


}
