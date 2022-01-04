package com.example.xiaocaiyidie;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class AddTrendsActivity extends AppCompatActivity {
    private ActionBar actionBar;

    private ImageButton imagebutton;
    private ImageView imageView;
    private Button buttonFabu;

    public String imagePathArray[] = {
            "/storage/emulated/0/Pictures/food1.jpg",
            "/storage/emulated/0/Pictures/food2.jpg",
            "/storage/emulated/0/Pictures/food3.jpg",
            "/storage/emulated/0/Pictures/food4.jpg",
            "/storage/emulated/0/Pictures/food5.jpg",
            "/storage/emulated/0/Pictures/food6.jpg"
    };
    public String imagePath;
    public static int num = 10;
    public static String trends_name[];
    public static String trends_time[];
    public static int trends_images[];
    public static String trendsTime[] = {"2021-12-12", "2021-12-3", "2021-12-1", "2021-11-27", "2021-11-22", "2021-11-20", "2021-8-3", "2021-7-4", "2021-5-1"};
    public static String trendsContent[] = {"花生酪", "巧克力曲奇","水果沙拉","芥香虾球","麻辣虾尾","家庭版佛跳墙","红烧鱼块","青椒白玉菇炒鸡蛋","干锅菜花"};
    public static int trendsImages[] ={R.drawable.num3, R.drawable.num5,R.drawable.food6, R.drawable.num6,R.drawable.num7, R.drawable.num8,R.drawable.num9,R.drawable.num10, R.drawable.num14};
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_addnew_trends);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);  // 设置显示返回箭头
        setStatusBarColor(this, R.color.theme_color);  // 修改状态栏的颜色

        // 获取用户相册图片
        imagebutton = findViewById(R.id.recipe_new_dontai_tianjia_imageButton);
        imageView = findViewById(R.id.recipe_new_dontai_image);
        imagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, null);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, 2);
            }
        });

        EditText editText = findViewById(R.id.trends_fabu_text);

        // 点击发布后，传输数据到菜谱页面
        buttonFabu = findViewById(R.id.recipe_new_dontai_fabu_button);
        buttonFabu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd- HH:mm:ss");// HH:mm:ss
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
                //获取当前时间
                Date date = new Date(System.currentTimeMillis());

                trends_name = new String[num];
                trends_time = new String[num];
                trends_images = new int[num];
                if (imagePathArray[0].equals(imagePath)){
                    trends_name[0] = editText.getText().toString();
                    trends_time[0] = simpleDateFormat.format(date);
                    trends_images[0] = R.drawable.food1;
                    setRecipeDetails();
                } else if (imagePathArray[1].equals(imagePath)){
                    trends_name[0] = editText.getText().toString();
                    trends_time[0] = simpleDateFormat.format(date);
                    trends_images[0] = R.drawable.food2;
                    setRecipeDetails();
                } else if (imagePathArray[2].equals(imagePath)){
                    trends_name[0] = editText.getText().toString();
                    trends_time[0] = simpleDateFormat.format(date);
                    trends_images[0] = R.drawable.food3;
                    setRecipeDetails();
                } else if (imagePathArray[3].equals(imagePath)){
                    trends_name[0] = editText.getText().toString();
                    trends_time[0] = simpleDateFormat.format(date);
                    trends_images[0] = R.drawable.food4;
                    setRecipeDetails();
                } else if (imagePathArray[4].equals(imagePath)){
                    trends_name[0] = editText.getText().toString();
                    trends_time[0] = simpleDateFormat.format(date);
                    trends_images[0] = R.drawable.food5;
                    setRecipeDetails();
                } else if (imagePathArray[5].equals(imagePath)){
                    trends_name[0] = editText.getText().toString();
                    trends_time[0] = simpleDateFormat.format(date);
                    trends_images[0] = R.drawable.food6;
                    setRecipeDetails();
                }
            }
        });

    }

    public void setRecipeDetails(){
        for (int i = 1;i<=trendsContent.length;i++) {
            trends_name[i] = trendsContent[i-1];
            trends_time[i] = trendsTime[i-1];
            trends_images[i] = trendsImages[i-1];
        }
        num++;
        trendsContent = trends_name;
        trendsTime = trends_time;
        trendsImages = trends_images;
        AddTrendsActivity.this.finish();
    }

    // 获取相册图片
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            //从相册返回的数据
            if (data != null) {
                // 得到图片的全路径
                Uri uri = data.getData();
                imageView.setImageURI(uri);
                imagePath = getRealPathFromUriAboveApi19(AddTrendsActivity.this,uri);
                Log.d("imagePath", imagePath);
            }
        }
    }

    private static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        String path = null;

        String[] projection = new String[]{MediaStore.Images.Media.DATA};
        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndexOrThrow(projection[0]);
                path = cursor.getString(columnIndex);
            }
        } catch (Exception e) {
            if (cursor != null) {
                cursor.close();
            }
        }
        return path;
    }

    /**
     * 适配api19及以上,根据uri获取图片的绝对路径
     * @param context 上下文对象
     * @param uri     图片的Uri
     * @return 如果Uri对应的图片存在, 那么返回该图片的绝对路径, 否则返回null
     */
    @SuppressLint("NewApi")
    public static String getRealPathFromUriAboveApi19(Context context, Uri uri) {
        String filePath = null;
        if (DocumentsContract.isDocumentUri(context, uri)) {
            // 如果是document类型的 uri, 则通过document id来进行处理
            String documentId = DocumentsContract.getDocumentId(uri);
            if (isMediaDocument(uri)) { // MediaProvider
                // 使用':'分割
                String id = documentId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=?";
                String[] selectionArgs = {id};
                filePath = getDataColumn(context, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection, selectionArgs);
            } else if (isDownloadsDocument(uri)) { // DownloadsProvider
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(documentId));
                filePath = getDataColumn(context, contentUri, null, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())){
            // 如果是 content 类型的 Uri
            filePath = getDataColumn(context, uri, null, null);
        } else if ("file".equals(uri.getScheme())) {
            // 如果是 file 类型的 Uri,直接获取图片对应的路径
            filePath = uri.getPath();
        }
        return filePath;
    }

    /**
     * @param uri the Uri to check
     * @return Whether the Uri authority is MediaProvider
     */
    private static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri the Uri to check
     * @return Whether the Uri authority is DownloadsProvider
     */
    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
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
