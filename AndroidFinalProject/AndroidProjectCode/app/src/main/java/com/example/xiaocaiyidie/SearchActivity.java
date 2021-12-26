package com.example.xiaocaiyidie;


import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class SearchActivity extends AppCompatActivity {

    ListView listView;
    SQLiteDatabase sqLiteDatabase;
    private ActionBar actionBar;
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
        Intent intent = getIntent();
        listView = findViewById(R.id.list_view);
        //sqLiteDatabase = new NotePadProvider.DatabaseHelper(this).getReadableDatabase();

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
