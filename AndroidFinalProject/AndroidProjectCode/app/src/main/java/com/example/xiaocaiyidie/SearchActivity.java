package com.example.xiaocaiyidie;


import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


public class SearchActivity extends AppCompatActivity {

    private String[] mDatas = {"花生酪", "巧克力曲奇","水果沙拉","芥香虾球","麻辣虾尾","家庭版佛跳墙","红烧鱼块","青椒白玉菇炒鸡蛋","干锅菜花"};
    ListView listView;
    private ActionBar actionBar;
    private String info; // 获取输入框的内容
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // 修改状态栏的颜色
        setStatusBarColor(this, R.color.theme_color);

        //获取搜索实例
        SearchView searchView = findViewById(R.id.search_view);
        //Intent intent = getIntent();
        listView = findViewById(R.id.list_view);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mDatas));
        listView.setTextFilterEnabled(true);
        //sqLiteDatabase = new NotePadProvider.DatabaseHelper(this).getReadableDatabase();
        // 设置搜索文本监听
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 当点击搜索按钮时触发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (info.equals("花生酪")) {
                    Intent intent = new Intent(SearchActivity.this, RecipeDeatils.class);
                    intent.putExtra("name", "花生酪");
                    startActivity(intent);
                }
                if (info.equals("巧克力曲奇")) {
                    Intent intent = new Intent(SearchActivity.this, RecipeDeatils.class);
                    intent.putExtra("name", "巧克力曲奇");
                    startActivity(intent);
                }
                if (info.equals("水果沙拉")) {
                    Intent intent = new Intent(SearchActivity.this, RecipeDeatils.class);
                    intent.putExtra("name", "水果沙拉");
                    startActivity(intent);
                }
                if (info.equals("芥香虾球")) {
                    Intent intent = new Intent(SearchActivity.this, RecipeDeatils.class);
                    intent.putExtra("name", "芥香虾球");
                    startActivity(intent);
                }
                if (info.equals("麻辣虾尾")) {
                    Intent intent = new Intent(SearchActivity.this, RecipeDeatils.class);
                    intent.putExtra("name", "麻辣虾尾");
                    startActivity(intent);
                }
                if (info.equals("家庭版佛跳墙")) {
                    Intent intent = new Intent(SearchActivity.this, RecipeDeatils.class);
                    intent.putExtra("name", "家庭版佛跳墙");
                    startActivity(intent);
                }
                if (info.equals("红烧鱼块")) {
                    Intent intent = new Intent(SearchActivity.this, RecipeDeatils.class);
                    intent.putExtra("name", "红烧鱼块");
                    startActivity(intent);
                }
                if (info.equals("青椒白玉菇炒鸡蛋")) {
                    Intent intent = new Intent(SearchActivity.this, RecipeDeatils.class);
                    intent.putExtra("name", "青椒白玉菇炒鸡蛋");
                    startActivity(intent);
                }
                if (info.equals("干锅菜花")) {
                    Intent intent = new Intent(SearchActivity.this, RecipeDeatils.class);
                    intent.putExtra("name", "干锅菜花");
                    startActivity(intent);
                }

                return false;
            }

            // 当搜索内容改变时触发该方法
            @Override
            public boolean onQueryTextChange(String newText) {
                if (!TextUtils.isEmpty(newText)){
                    listView.setFilterText(newText);
                    info = newText;
                }else{
                    listView.clearTextFilter();
                }
                return false;
            }
        });

        //设置该SearchView显示搜索按钮
        searchView.setSubmitButtonEnabled(true);

        //设置该SearchView内默认显示的提示文本
        searchView.setQueryHint("搜索");
        //searchView.setOnQueryTextListener(this);

        //设置输入框颜色
        //searchView.setBackgroundColor(getResources().getColor(R.color.theme_cambridge_blue));

        //修改输入框提示文字颜色
        /*
         * 首先获取原生SearchView布局文件中的id：text_id
         * 然后获取文本中的文字
         * 修改提示词的颜色
         * 修改提示词的大小
         * */
        int text_id = searchView.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        TextView textView = (TextView)searchView.findViewById(text_id);
        textView.setHintTextColor(getResources().getColor(R.color.theme_color));
        textView.setTextSize(20);
    }


    // 点击后返回到上一界面
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:   //返回键的id
                this.finish();
                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    /**
     * 修改状态栏颜色，支持4.4以上版本
     * @param activity
     * @param colorId
     */
    public static void setStatusBarColor(Activity activity, int colorId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(activity.getResources().getColor(colorId));
        }
    }

}
