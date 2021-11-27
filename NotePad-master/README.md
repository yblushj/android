# Android记事本——NotePad应用学习
## 一、基本功能
### 1、显示时间戳(显示的是修改时间)
* 在noteslist_item中添加一个TextView，用于显示时间戳
```
        <!--    笔记的修改时间-->
        <TextView
            android:id="@android:id/text2"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:gravity="center_vertical"
            android:paddingLeft="5dp"
            android:textSize="15dp" />
```
* 在NotesList中添加“COLUMN_NAME_MODIFICATION_DATE”字段
```
    private static final String[] PROJECTION = new String[] {
    // 可以在这里添加时间戳，SimpleDateFormat，用这个函数来处理时间戳
            NotePad.Notes._ID, // 0
            NotePad.Notes.COLUMN_NAME_TITLE, // 1
            NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE,//修改时间显示
    };
```
* 修改适配器内容
```
// The names of the cursor columns to display in the view, initialized to the title column
        String[] dataColumns = {
                NotePad.Notes.COLUMN_NAME_TITLE,
                NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE//修改配适器内容添加时间
        } ;

        // The view IDs that will display the cursor columns, initialized to the TextView in
        // noteslist_item.xml
        int[] viewIDs = {
                android.R.id.text1,
                android.R.id.text2 //增加一个参数来存放时间
        };
```
* 在NoteEditor的updateNote函数中获取系统时间，并进行格式化
```
//获取系统时间，动态改变
        ContentValues values = new ContentValues();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年/MM月/dd日 HH:mm");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        Long timeNow = Long.valueOf(System.currentTimeMillis());
        String sdFormat = sdf.format(timeNow);      //设置成动态可变的
        values.put(NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE, sdFormat);
```
* 效果展示

![image](https://user-images.githubusercontent.com/82711644/143720349-d677bc1b-bf32-40c4-8eea-4bc53ab0d019.png)

### 2、笔记内容搜索功能
* 在menu下的notelist_item中添加搜索组件
```
<!--    搜索按钮-->
    <item
          android:id="@+id/menu_search"
          android:icon="@drawable/ic_menu_blue_search"
          android:title="@string/menu_search"
          android:showAsAction="always"/>
```
* 新建搜索界面的布局文件
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">
<!--    搜索框-->
    <SearchView
        android:id="@+id/search_view"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:iconifiedByDefault="false"
        />
<!--    搜索列表-->
    <ListView
        android:id="@+id/list_view"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:divider="@color/theme_white"
        />
</LinearLayout>
```
* 在NoteList类中的onOptionsItemSelected方法中添加跳转
```
case R.id.menu_search:
      //查找功能
      Intent intent = new Intent(this, NoteSearch.class);
      this.startActivity(intent);
      return true;
```
* 在NoteSearch中实现搜索功能
```
package com.example.android.notepad;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class NoteSearch extends Activity implements SearchView.OnQueryTextListener
{
    ListView listView;
    SQLiteDatabase sqLiteDatabase;

    private static final String[] PROJECTION = new String[]{
            NotePad.Notes._ID, // 0
            NotePad.Notes.COLUMN_NAME_TITLE, // 1
            NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE//时间
    };

    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_search);

        //获取搜索实例
        SearchView searchView = findViewById(R.id.search_view);
        Intent intent = getIntent();
        if (intent.getData() == null) {
            intent.setData(NotePad.Notes.CONTENT_URI);
        }
        listView = findViewById(R.id.list_view);
        sqLiteDatabase = new NotePadProvider.DatabaseHelper(this).getReadableDatabase();

        //设置该SearchView显示搜索按钮
        searchView.setSubmitButtonEnabled(true);

        //设置该SearchView内默认显示的提示文本
        searchView.setQueryHint("搜索");
        searchView.setOnQueryTextListener(this);

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
        textView.setHintTextColor(getResources().getColor(R.color.theme_cambridge_blue));
        textView.setTextSize(20);


    }
    public boolean onQueryTextChange(String string) {
        String selection1 = NotePad.Notes.COLUMN_NAME_TITLE+" like ? or "+NotePad.Notes.COLUMN_NAME_NOTE+" like ?";
        String[] selection2 = {"%"+string+"%","%"+string+"%"};
        Cursor cursor = sqLiteDatabase.query(
                NotePad.Notes.TABLE_NAME,
                PROJECTION,
                selection1,
                selection2,
                null,
                null,
                NotePad.Notes.DEFAULT_SORT_ORDER
        );
        String[] dataColumns = {
                NotePad.Notes.COLUMN_NAME_TITLE,
                NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE
        } ;

        int[] viewIDs = {android.R.id.text1, android.R.id.text2};
        SimpleCursorAdapter adapter
                = new SimpleCursorAdapter(
                this,
                R.layout.noteslist_item,
                cursor,
                dataColumns,
                viewIDs
        );
        listView.setAdapter(adapter);
        return true;
    }
}
```
* 效果展示

![image](https://user-images.githubusercontent.com/82711644/143720691-bb171a64-f5aa-4e92-9312-2d83deb1e4e6.png)
![image](https://user-images.githubusercontent.com/82711644/143720698-96f1178f-d991-4b8d-a109-6ba7e9b3f5df.png)
![image](https://user-images.githubusercontent.com/82711644/143720708-b04910f9-4aeb-4092-957e-748ec7332454.png)

## 二、拓展功能
### 1、UI美化
### 2、全场景中文优化
### 3、设置
## 三、待实现功能
## 四、参考链接
* https://www.runoob.com/w3cnote/android-tutorial-menu.html


