package fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.xiaocaiyidie.AddRecipeActiviey;
import com.example.xiaocaiyidie.AddTrendsActivity;
import com.example.xiaocaiyidie.R;
import com.example.xiaocaiyidie.RadiuImageView;
import com.example.xiaocaiyidie.RecipeDeatils;
import com.example.xiaocaiyidie.SearchActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TabFragment extends Fragment {

    private View addNew_view;  // 在后面添加一个空白view “没有更多内容了”
    // 存储的是菜谱列表
    ListView listView;
    SimpleAdapter simpleAdapter;

    // 存储的是动态列表
    ListView listViewTrends;
    SimpleAdapter simpleAdapterTrends;

    // 存储的是收藏列表
    ListView listViewCollect;
    SimpleAdapter simpleAdapterCollect;

    // 存储的是历史记录列表
    ListView listViewHistory;
    SimpleAdapter simpleAdapterHistory;

    // 存储的是首页推荐列表
    ListView listViewCommend;
    SimpleAdapter simpleAdapterCommend;

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
            case "直播":
                view = inflater.inflate(R.layout.home_fragment_attention, container, false);
                View viewshow = inflater.inflate(R.layout.home_fragment_attention,null);
                break;
            case "推荐":
                view = inflater.inflate(R.layout.home_fragment_commend, container, false);
                listViewCommend =view.findViewById(R.id.home_commend_list);
                simpleAdapterCommend = new SimpleAdapter(
                        getActivity(),
                        getCommendData(),
                        R.layout.home_commend_list_item,
                        new String[]{"commend_text", "tuijian_top_image", "tuijian_top_text", "commendimage","commendimagename", "commendimage2","commendimagename2"},
                        new int[]{
                                R.id.commend_text,
                                R.id.tuijian_top_image,
                                R.id.tuijian_top_text,
                                R.id.home_commend_image,
                                R.id.home_commend_image_name,
                                R.id.home_commend_image2,
                                R.id.home_commend_image_name2
                        });
                listViewCommend.setAdapter(simpleAdapterCommend);
                addNew_view = getLayoutInflater().inflate(R.layout.addnew_view,null);
                //将layout的内容加入到listView当中去
                listViewCommend.addFooterView(addNew_view);
                listViewCommend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getActivity(), RecipeDeatils.class);
                        intent.putExtra("name", "0");
                        startActivity(intent);
                    }
                });
                break;
            case "分类":
                view = inflater.inflate(R.layout.home_fragment_sort, container, false);
                System.out.println("分类：" + view);
                break;
            case "菜谱":
                view = inflater.inflate(R.layout.recipe_fragment_recipe, container, false);
                listView =view.findViewById(R.id.recipe_recipe_list);
                simpleAdapter = new SimpleAdapter(
                        getActivity(),
                        getData(),
                        R.layout.recipe_recipe_list_item,
                        new String[]{"image","name", "miaoshu", "time"},
                        new int[]{
                                R.id.recipe_recipe_list_image,
                                R.id.recipe_recipe_list_name,
                                R.id.recipe_recipe_list_miaoshu,
                                R.id.recipe_recipe_list_time
                        });
                listView.setAdapter(simpleAdapter);
                addNew_view = getLayoutInflater().inflate(R.layout.addnew_view,null);
                //将layout的内容加入到listView当中去
                listView.addFooterView(addNew_view);
                View view1 = inflater.inflate(R.layout.recipe_trends_list_item,null);
                TextView textView = view1.findViewById(R.id.recipe_recipe_list_name);
                RadiuImageView radiuImageView = view1.findViewById(R.id.recipe_recipe_list_image);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getActivity(), RecipeDeatils.class);
                        intent.putExtra("name", "0");
                        startActivity(intent);
                    }
                });
                break;
            case "动态":
                view = inflater.inflate(R.layout.recipe_fragment_trends, container, false);
                listViewTrends =view.findViewById(R.id.recipe_trends_list);
                simpleAdapterTrends = new SimpleAdapter(
                        getActivity(),
                        getTrendsData(),
                        R.layout.recipe_trends_list_item,
                        new String[]{"userheadimage","username", "creatTime", "pushcontent", "pushphoto"},
                        new int[]{
                                R.id.recipe_trends_userheadimage,
                                R.id.recipe_trends_username,
                                R.id.recipe_trends_creat_time,
                                R.id.recipe_trends_user_pushcontent,
                                R.id.recipe_trends_user_pushphoto
                        });
                listViewTrends.setAdapter(simpleAdapterTrends);
                addNew_view = getLayoutInflater().inflate(R.layout.addnew_view,null);
                //将layout的内容加入到listView当中去
                listViewTrends.addFooterView(addNew_view);
                break;
            case "收藏":
                view = inflater.inflate(R.layout.collect_fragment_collections, container, false);
                listViewCollect =view.findViewById(R.id.collect_collect_list);
                simpleAdapterCollect = new SimpleAdapter(
                        getActivity(),
                        getCollectData(),
                        R.layout.collect_listview_item,
                        new String[]{"recipeimage1","recipeimage2", "recipeimage3", "recipename1", "recipename2", "recipename3"},
                        new int[]{
                                R.id.collect_listview_recipeimage1,
                                R.id.collect_listview_recipeimage2,
                                R.id.collect_listview_recipeimage3,
                                R.id.collect_listview_recipename1,
                                R.id.collect_listview_recipename2,
                                R.id.collect_listview_recipename3
                        });
                listViewCollect.setAdapter(simpleAdapterCollect);
                addNew_view = getLayoutInflater().inflate(R.layout.addnew_view,null);
                //将layout的内容加入到listView当中去
                listViewCollect.addFooterView(addNew_view);

                listViewCollect.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getActivity(), RecipeDeatils.class);
                        startActivity(intent);
                    }
                });
                break;
            case "历史记录":
                view = inflater.inflate(R.layout.collection_fragment_history, container, false);
                listViewHistory =view.findViewById(R.id.collect_history_list);
                simpleAdapterHistory = new SimpleAdapter(
                        getActivity(),
                        getHistoryData(),
                        R.layout.collect_listview_item,
                        new String[]{"recipeimage1","recipeimage2", "recipeimage3", "recipename1", "recipename2", "recipename3"},
                        new int[]{
                                R.id.collect_listview_recipeimage1,
                                R.id.collect_listview_recipeimage2,
                                R.id.collect_listview_recipeimage3,
                                R.id.collect_listview_recipename1,
                                R.id.collect_listview_recipename2,
                                R.id.collect_listview_recipename3
                        });
                listViewHistory.setAdapter(simpleAdapterHistory);
                addNew_view = getLayoutInflater().inflate(R.layout.addnew_view,null);
                //将layout的内容加入到listView当中去
                listViewHistory.addFooterView(addNew_view);
                listViewHistory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getActivity(), RecipeDeatils.class);
                        startActivity(intent);
                    }
                });
                break;
        }
        System.out.println("Tab Fragment返回的view：" + view);
        return view;
    }

    // 切换到各个导航栏
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

    // 设置菜谱列表数据
    private List<Map<String,Object>> getData() {
        String [] name= AddRecipeActiviey.recipeName;
        String [] miaoshu={""};
        String [] time= AddRecipeActiviey.recipeTime;
        int [] images= AddRecipeActiviey.recipeImages;
        List<Map<String,Object>> list= new ArrayList<>();
        for(int i=0;i<name.length;i++){
            Map  map = new HashMap();
            map.put("image",images[i]);
            map.put("name",name[i]);
            map.put("miaoshu",miaoshu[0]);
            map.put("time",time[i]);
            list.add(map);
        }
        return list;
    }

    // 设置动态列表数据
    private List<Map<String,Object>> getTrendsData() {
        int [] userheadimage = {R.drawable.head};
        String [] username = {"吃萝卜的小奶虎"};
        String [] creatTime = AddTrendsActivity.trendsTime;
        String [] pushcontent = AddTrendsActivity.trendsContent;
        int [] pushphoto = AddTrendsActivity.trendsImages;
        List<Map<String,Object>> list= new ArrayList<>();
        for(int i=0;i<pushcontent.length;i++){
            Map  map = new HashMap();
            map.put("userheadimage", userheadimage[0]);
            map.put("username", username[0]);
            map.put("creatTime", creatTime[i]);
            map.put("pushcontent", pushcontent[i]);
            map.put("pushphoto", pushphoto[i]);
            list.add(map);
        }
        return list;
    }

    // 设置收藏列表数据
    private List<Map<String,Object>> getCollectData() {
        int [] recipeimage1 = {R.drawable.image_2};
        int [] recipeimage2 = {R.drawable.image_2};
        int [] recipeimage3 = {R.drawable.image_2};
        String [] recipename1 = {"佛跳墙"};
        String [] recipename2 = {"佛跳墙"};
        String [] recipename3 = {"佛跳墙"};
        List<Map<String,Object>> list= new ArrayList<>();
        for(int i=0;i<12;i++){
            Map  map = new HashMap();
            map.put("recipeimage1", recipeimage1[0]);
            map.put("recipeimage2", recipeimage2[0]);
            map.put("recipeimage3", recipeimage3[0]);
            map.put("recipename1", recipename1[0]);
            map.put("recipename2", recipename2[0]);
            map.put("recipename3", recipename3[0]);
            list.add(map);
        }
        return list;
    }

    // 设置历史记录列表数据
    private List<Map<String,Object>> getHistoryData() {
        int [] recipeimage1 = {R.drawable.image_2};
        int [] recipeimage2 = {R.drawable.image_2};
        int [] recipeimage3 = {R.drawable.image_2};
        String [] recipename1 = {"佛跳墙"};
        String [] recipename2 = {"佛跳墙"};
        String [] recipename3 = {"佛跳墙"};
        List<Map<String,Object>> list= new ArrayList<>();
        for(int i=0;i<12;i++){
            Map  map = new HashMap();
            map.put("recipeimage1", recipeimage1[0]);
            map.put("recipeimage2", recipeimage2[0]);
            map.put("recipeimage3", recipeimage3[0]);
            map.put("recipename1", recipename1[0]);
            map.put("recipename2", recipename2[0]);
            map.put("recipename3", recipename3[0]);
            list.add(map);
        }
        return list;
    }

    // 设置推荐列表数据
    private List<Map<String,Object>> getCommendData() {
        String [] commend_text = {"今日推荐", "午后小点", "人气之选", "温馨甜点", "精品推荐", "健康生活"};
        String [] tuijian_top_text = {"草莓蛋糕", "午后咖啡", "火锅", "元气早餐", "牛排", "水果沙拉"};
        int [] tuijian_top_image = {R.drawable.food1, R.drawable.food2, R.drawable.food3, R.drawable.food4, R.drawable.food5, R.drawable.food6};

        int [] commendimage={R.drawable.num1, R.drawable.num3, R.drawable.num5, R.drawable.num7, R.drawable.num9, R.drawable.num11};
        String [] commendimagename={"马卡龙小蛋糕", "花生酪", "巧克力曲奇", "麻辣虾尾", "红烧鱼块", "土豆鸡蛋饼"};

        int [] commendimage2={R.drawable.num2, R.drawable.num4, R.drawable.num6, R.drawable.num8, R.drawable.num10, R.drawable.num12};
        String [] commendimagename2={"冰鲜柠檬", "蔓越莓蛋糕", "芥香虾球", "家庭版佛跳墙", "青椒白玉菇炒鸡蛋", "南瓜小米粥"};

        List<Map<String,Object>> list= new ArrayList<>();
        for(int i=0;i<6;i++){
            Map  map = new HashMap();
            map.put("commend_text", commend_text[i]);
            map.put("tuijian_top_image", tuijian_top_image[i]);
            map.put("tuijian_top_text", tuijian_top_text[i]);
            map.put("commendimage",commendimage[i]);
            map.put("commendimagename",commendimagename[i]);
            map.put("commendimage2",commendimage2[i]);
            map.put("commendimagename2",commendimagename2[i]);
            list.add(map);
        }
        return list;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }




}
