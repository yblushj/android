package fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.xiaocaiyidie.R;

import java.util.List;

public class TabFragment extends Fragment {
        private String mTitle;

        //这个构造方法是便于各导航同时调用一个fragment
        public TabFragment(String title){
            mTitle=title;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.home_fragment_commend, container, false);
            System.out.println("TabFragment中获取的mTitle" + mTitle);
            switch ( mTitle) {
                case "关注":
                    view = inflater.inflate(R.layout.home_fragment_attention, container, false);
                    System.out.println("关注：" + view);
                    break;
                case "推荐":
                    view = inflater.inflate(R.layout.home_fragment_commend, container, false);
                    System.out.println("推荐：" + view);
                    break;
                case "分类":
                    view = inflater.inflate(R.layout.home_fragment_sort, container, false);
                    System.out.println("分类：" + view);
                    break;
                case "菜谱":
                    view = inflater.inflate(R.layout.recipe_fragment_recipe, container, false);
                    Log.d("菜谱", "guanzhu");
                    break;
                case "动态":
                    view = inflater.inflate(R.layout.recipe_fragment_trends, container, false);
                    Log.d("动态", "guanzhu");
                    break;
                case "收藏":
                    view = inflater.inflate(R.layout.collect_fragment_collections, container, false);
                    Log.d("菜谱", "guanzhu");
                    break;
                case "历史记录":
                    view = inflater.inflate(R.layout.collection_fragment_history, container, false);
                    Log.d("动态", "guanzhu");
                    break;
            }
            System.out.println("Tab Fragment返回的view：" + view);
            return view;
        }

    public static class FragmentAdapter extends FragmentStatePagerAdapter {
        private List<TabFragment> mFragmentList;//各导航的Fragment
        private List<String> mTitle; //导航的标题

        public FragmentAdapter(FragmentManager fragmentManager, List<TabFragment>fragments, List<String>title){
            super(fragmentManager);
            mFragmentList=fragments;
            mTitle=title;
            System.out.println(mFragmentList);
        }
        @Override
        public Fragment getItem(int position) {
            System.out.println("FragmentAdapter中获取position " + mFragmentList.get(position));
            System.out.println("FragmentAdapter中获取mFragment  " + mFragmentList);
            System.out.println(position);
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return mTitle.get(position);
        }
    }
}
