# 实验二 Android界面布局
## 一、线性布局(LinearLayout)
### 1、关键代码展示
* 部分布局代码
```
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"
        >
        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="horizontal"
            >

            <TextView
                android:id="@+id/textView13"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_weight="1"
                android:text="OneOne"
                android:layout_margin="5dp"
                android:background="@drawable/textview_border"
                android:gravity="center_horizontal"
                />

            <TextView
                android:id="@+id/textView14"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_weight="1"
                android:text="OneTwo"
                android:layout_margin="5dp"
                android:background="@drawable/textview_border"
                android:gravity="center_horizontal"/>

            <TextView
                android:id="@+id/textView15"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_weight="1"
                android:text="OneThree"
                android:layout_margin="5dp"
                android:background="@drawable/textview_border"
                android:gravity="center_horizontal"/>

            <TextView
                android:id="@+id/textView16"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_weight="1"
                android:text="OneFour"
                android:layout_margin="5dp"
                android:background="@drawable/textview_border"
                android:gravity="center_horizontal"/>
        </LinearLayout>
        ......
    </LinearLayout>
```

* textview_border.xml代码
```
<?xml version="1.0" encoding="utf-8"?>
<!-- shape定义形状，shape="rectangle"表示形状为长方形 -->
<shape
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle"
    >
    <!-- 设置框内填充颜色 -->
    <solid android:color="#ffffff" />
    <!-- 设置边框宽度和颜色 -->
    <stroke
        android:width="1dip"
        android:color="#000000" />
    <!-- 设置边距 -->
    <padding
        android:bottom="5dp"
        android:left="5dp"
        android:right="5dp"
        android:top="5dp" />
</shape>
```

### 2、运行结果效果图
<img src="https://z3.ax1x.com/2021/10/25/54iPLn.jpg" width=300>

## 二、约束布局(ConstrainLayout)
### 1、关键代码展示
* 部分布局代码
```
    <Button
        android:id="@+id/button"
        style="@style/ButtonStyle"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginStart="10dp"
        android:layout_marginLeft="10dp"
        android:layout_marginBottom="12dp"
        android:text="C"
        android:textColor="#0e58f7"
        app:layout_constraintBottom_toTopOf="@+id/button9"
        app:layout_constraintEnd_toStartOf="@+id/button16"
        app:layout_constraintHorizontal_bias="0.5"
        app:layout_constraintStart_toStartOf="parent"
        />
        ......
    <EditText
        android:id="@+id/editTextTextPersonName"
        android:layout_width="355dp"
        android:layout_height="70dp"
        android:layout_marginBottom="52dp"
        android:ems="10"
        android:inputType="textPersonName"
        android:text=""
        app:layout_constraintBottom_toTopOf="@+id/button20"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        tools:ignore="MissingConstraints" />
```

* value下的style.xml
```
<resources>
    <style name="ButtonStyle" >
        <item name="android:radius">10dp</item>
<!--        <item name="android:textStyle">bold</item>-->
        <item name="android:textSize">40sp</item>
        <item name="android:topLeftRadius">50dp</item>
        <item name="android:layout_marginLeft">10dp</item>
    </style>
</resources>
```

### 2、运行结果效果图
<img src="https://z3.ax1x.com/2021/10/25/54iCss.jpg" width=300>

## 三、表格布局(TableLayout)
### 1、关键代码展示
* 部分布局文件
```
<TableLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:stretchColumns="1">
        <TableRow>
            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="     Open..."
                android:minWidth="100dp"
                style="@style/Table_font">
            </TextView>
            <TextView/>
            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="Ctrl+O"
                android:gravity="end"
                android:minWidth="100dp"
                style="@style/Table_font">
            </TextView>
        </TableRow>
        ......
        <View
            android:layout_height="3dp"
            android:layout_width="match_parent"
            style="@style/Table_font"
            android:background="@color/white"/>
        ......
    </TableLayout>
```

* value下的styles.xml
```
<resources>
    <style name="Table_font">
        <item name="android:textSize">20sp</item>
        <item name="android:layout_marginTop">5dp</item>
        <item name="android:textColor">@color/white</item>
    </style>
</resources>
```

### 2、运行结果效果图
<img src="https://z3.ax1x.com/2021/10/25/54i9Mj.jpg" width=300>

## 四、参考链接
* https://www.twle.cn/l/yufei/android/android-basic-constraintlayout.html
* https://developer.android.google.cn/training/constraint-layout?hl=zh-cn
