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
* 添加text_border文件，编辑新的背景
```
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android">
    <solid android:color="#ABD5FF">浅蓝色</solid>
    <corners android:radius="15px"/>
</shape>
```
* 在notelist_item的LinearLayout中修改布局
```    
<!--修改后的-->
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:padding="3dp"
    >

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginLeft="10dp"
        android:layout_marginRight="10dp"
        android:layout_marginTop="5dp"
        android:background="@drawable/text_border"
        android:orientation="vertical">
        <!--    笔记的标题-->
        <TextView
            android:id="@android:id/text1"
            android:layout_width="match_parent"
            android:layout_height="?android:attr/listPreferredItemHeight"
            android:gravity="center_vertical"
            android:paddingLeft="5dip"
            android:singleLine="true"
            android:textAppearance="?android:attr/textAppearanceLarge" />
        <!--    笔记的修改时间-->
        <TextView
            android:id="@android:id/text2"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:gravity="center_vertical"
            android:paddingLeft="5dp"
            android:textSize="15dp" />
    </LinearLayout>
</LinearLayout>
```
* 在NoteList中设置去除ListLayout的下划线
```
getListView().setDivider(null);//去除下划线
```
* 界面展示美化

![image](https://user-images.githubusercontent.com/82711644/143720755-6ce63009-ca49-4352-bff6-e2bfb9fdb076.png)
* 图标美化

![image](https://user-images.githubusercontent.com/82711644/143720768-4e77a654-a946-46b8-ab31-9edb25f0916f.png)

![image](https://user-images.githubusercontent.com/82711644/143720779-6645d288-88ac-463e-82af-204e551e4d5f.png)

![image](https://user-images.githubusercontent.com/82711644/143720783-5709b5f0-fd10-4e27-bcf7-2ddd93ad0e02.png)

### 2、全场景中文优化
实现了应用的中文优化，让用户更好上手，方便的实现各个功能。
* 在string文件中进行修改
```
<resources>
    <string name="app_name">NotePad</string>
    <string name="live_folder_name">Notes</string>

    <string name="title_edit_title">笔记标题</string>
    <string name="title_create">新建笔记</string>
    <string name="title_edit">Edit: %1$s</string>
    <string name="title_notes_list">笔记</string>

    <string name="menu_add">新建</string>
    <string name="menu_save">保存</string>
    <string name="menu_delete">删除</string>
    <string name="menu_open">打开</string>
    <string name="menu_revert">还原更改</string>
    <string name="menu_copy">复制</string>
    <string name="menu_paste">粘贴</string>
    <string name="menu_search">搜索</string>
    <string name="menu_setting">设置</string>
    <string name="menu_background">更改主题</string>

    <string name="button_ok">OK</string>
    <string name="text_title">标题：</string>
    <string name="resolve_edit">编辑笔记</string>
    <string name="resolve_title">编辑标题</string>

    <string name="error_title">错误</string>
    <string name="error_message">加载错误</string>
    <string name="nothing_to_save">There is nothing to save</string>
</resources>
```
* 效果展示

![image](https://user-images.githubusercontent.com/82711644/143720837-21099514-ea7e-4f3e-804b-4078699b8207.png)

![image](https://user-images.githubusercontent.com/82711644/143720841-d66f0d60-99db-45e8-bf8e-db06e7c2c332.png)

![image](https://user-images.githubusercontent.com/82711644/143720853-a2f57c07-4323-4971-a21e-cf1ae39d6bf7.png)

![image](https://user-images.githubusercontent.com/82711644/143720856-26f51b83-f36b-4891-a70e-a1a70ded899a.png)

![image](https://user-images.githubusercontent.com/82711644/143720845-a4dade26-5697-4c27-b471-932a9170a458.png)

### 3、设置
可以设置水印的添加以及通知的开启和关闭，还可以在帮助中，通过qq或者微信来进行联系。
* 添加note_setting布局文件
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical" android:layout_width="match_parent"
    android:layout_height="match_parent"
    >
    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textSize="35sp"
        android:text="设置"
        android:textColor="@color/black"
        android:layout_gravity="center_horizontal"/>
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical">
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="常规"
            android:textColor="@color/theme_cambridge_blue"
            android:textSize="20sp"
            android:layout_marginTop="20dp"/>
        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:orientation="vertical"
            android:divider="@color/theme_cambridge_blue"
            android:showDividers="middle">
            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:orientation="horizontal"
                android:padding="20dp">
                <TextView
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_weight="1"
                    android:text="水印"
                    android:textSize="20sp" />
                <Switch
                    android:id="@+id/watermark"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_weight="1"
                    android:checked="false" />
            </LinearLayout>

            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:orientation="horizontal"
                android:padding="20dp">
                <TextView
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:text="通知"
                    android:layout_weight="1"
                    android:textSize="20sp"/>
                <Switch
                    android:id="@+id/inform"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:checked="false"
                    android:layout_weight="1"/>
            </LinearLayout>
        </LinearLayout>
        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="vertical">
            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="帮助"
                android:textColor="@color/theme_cambridge_blue"
                android:textSize="20sp"
                android:layout_marginTop="20dp"/>
            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:orientation="vertical"
                android:divider="@color/theme_cambridge_blue"
                android:showDividers="middle">

                <LinearLayout
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:orientation="horizontal"
                    android:padding="20dp">
                    <TextView
                        android:clickable="true"
                        android:id="@+id/qq"
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:layout_weight="1"
                        android:text="在 QQ 上联系我们"
                        android:textSize="20sp" />
                </LinearLayout>

                <LinearLayout
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:orientation="horizontal"
                    android:padding="20dp">
                    <TextView
                        android:id="@+id/wechat"
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:text="在 微信 上联系我们"
                        android:layout_weight="1"
                        android:textSize="20sp"/>
                </LinearLayout>
            </LinearLayout>
    </LinearLayout>
    </LinearLayout>
</LinearLayout>
```
* 在list_options_menu中添加设置组件
```
<!--    设置-->
    <item android:id="@+id/menu_setting_in"
        android:title="@string/menu_setting"
        android:alphabeticShortcut="se"/>
```
* 在NoteList类中的onOptionsItemSelected方法中添加跳转
```
case R.id.menu_setting_in:
                //设置功能
            Intent intent_setting = new Intent(this, NoteSetting.class);
            this.startActivity(intent_setting);
            return true;
```
* 在NoteSetting中实现设置功能
```
package com.example.android.notepad;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class NoteSetting extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_setting);

        //点击跳转到qq
        TextView textView = findViewById((R.id.qq));
        textView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent();
                intent.setData(Uri.parse("https://im.qq.com/index"));
                intent.setAction(Intent.ACTION_VIEW);
                startActivity(intent);
            }
        });

        //点击跳转到微信
        TextView textView1 = findViewById(R.id.wechat);
        textView1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent();
                intent.setData(Uri.parse("https://weixin.qq.com/"));
                intent.setAction(Intent.ACTION_VIEW);
                startActivity(intent);
            }
        });
    }
}
```
* 效果展示

![image](https://user-images.githubusercontent.com/82711644/143721001-22b58093-6b5a-427e-a0dc-76db98b40c69.png)


## 三、待实现功能
* 系统主题的修改，参考：

![image](https://user-images.githubusercontent.com/82711644/143721094-e7f1bf61-bd42-466c-9dc1-5f7ebf180fac.png)

* 根据不同的规则来进行不同的排列，参考：

![image](https://user-images.githubusercontent.com/82711644/143721139-488962cc-c5ef-4731-8e82-89fb548b8a67.png)

## 四、参考链接
* https://www.runoob.com/w3cnote/android-tutorial-menu.html
* https://todo.microsoft.com/zh-cn/
* https://blog.csdn.net/qq_41868108/article/details/106169825


