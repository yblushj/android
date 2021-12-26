package com.example.xiaocaiyidie;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class RecipeAddActivity extends AppCompatActivity {
    private ActionBar actionBar;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_add_layout);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);  // 设置显示返回箭头



        // 点击按钮跳转到新建菜谱、动态页面
        ImageButton imageButton = findViewById(R.id.recipe_add_recipe_button);
        ImageButton imageButton1 = findViewById(R.id.recipe_add_trends_button);
        imageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecipeAddActivity.this, AddRecipeActiviey.class);
                startActivity(intent);
            }
        });
        imageButton1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecipeAddActivity.this, AddTrendsActivity.class);
                startActivity(intent);
            }
        });
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
}
