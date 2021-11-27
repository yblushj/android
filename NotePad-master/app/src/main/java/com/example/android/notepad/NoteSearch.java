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
                PROJECTION, // The columns to return from the query
                selection1, // The columns for the where clause
                selection2, // The values for the where clause
                null,          // don't group the rows
                null,          // don't filter by row groups
                NotePad.Notes.DEFAULT_SORT_ORDER // The sort order
        );
        // The names of the cursor columns to display in the view, initialized to the title column
        String[] dataColumns = {
                NotePad.Notes.COLUMN_NAME_TITLE,
                NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE
        } ;
        // The view IDs that will display the cursor columns, initialized to the TextView in
        // noteslist_item.xml
        int[] viewIDs = {android.R.id.text1, android.R.id.text2};
        // Creates the backing adapter for the ListView.
        SimpleCursorAdapter adapter
                = new SimpleCursorAdapter(
                this,                             // The Context for the ListView
                R.layout.noteslist_item,         // Points to the XML for a list item
                cursor,                           // The cursor to get items from
                dataColumns,
                viewIDs
        );
        // Sets the ListView's adapter to be the cursor adapter that was just created.
        listView.setAdapter(adapter);
        return true;
    }


}
