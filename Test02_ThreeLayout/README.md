# 实验二 Android界面布局
## 一、线性布局(LinearLayout)
### 1、关键代码展示
* 布局代码
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
        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="horizontal">
            <TextView
                android:id="@+id/textView"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_weight="1"
                android:text="TwoOne"
                android:layout_margin="5dp"
                android:background="@drawable/textview_border"
                android:gravity="center_horizontal"/>

            <TextView
                android:id="@+id/textView3"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_weight="1"
                android:text="TwoTwo"
                android:layout_margin="5dp"
                android:background="@drawable/textview_border"
                android:gravity="center_horizontal"/>

            <TextView
                android:id="@+id/textView4"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_weight="1"
                android:text="TwoThree"
                android:layout_margin="5dp"
                android:background="@drawable/textview_border"
                android:gravity="center_horizontal"/>

            <TextView
                android:id="@+id/textView2"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_weight="1"
                android:text="TwoFour"
                android:layout_margin="5dp"
                android:background="@drawable/textview_border"
                android:gravity="center_horizontal"/>
        </LinearLayout>
        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="horizontal">
            <TextView
                android:id="@+id/textView5"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_weight="1"
                android:text="ThreeOne"
                android:layout_margin="5dp"
                android:background="@drawable/textview_border"
                android:gravity="center_horizontal"/>

            <TextView
                android:id="@+id/textView6"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_weight="1"
                android:text="ThreeTwo"
                android:layout_margin="5dp"
                android:background="@drawable/textview_border"
                android:gravity="center_horizontal"/>

            <TextView
                android:id="@+id/textView7"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_weight="1"
                android:text="ThreeThree"
                android:layout_margin="5dp"
                android:background="@drawable/textview_border"
                android:gravity="center_horizontal"/>

            <TextView
                android:id="@+id/textView8"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_weight="1"
                android:text="ThreeFour"
                android:layout_margin="5dp"
                android:background="@drawable/textview_border"
                android:gravity="center_horizontal"/>
        </LinearLayout>
        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="horizontal">
            <TextView
                android:id="@+id/textView9"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_weight="1"
                android:text="FourOne"
                android:layout_margin="5dp"
                android:background="@drawable/textview_border"
                android:gravity="center_horizontal"/>

            <TextView
                android:id="@+id/textView10"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_weight="1"
                android:text="FourTwo"
                android:layout_margin="5dp"
                android:background="@drawable/textview_border"
                android:gravity="center_horizontal"/>

            <TextView
                android:id="@+id/textView11"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_weight="1"
                android:text="FourThree"
                android:layout_margin="5dp"
                android:background="@drawable/textview_border"
                android:gravity="center_horizontal"/>

            <TextView
                android:id="@+id/textView12"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_weight="1"
                android:text="FourFour"
                android:layout_margin="5dp"
                android:background="@drawable/textview_border"
                android:gravity="center_horizontal"/>
        </LinearLayout>
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
* 布局代码
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

    <Button
        android:id="@+id/button2"
        style="@style/ButtonStyle"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:text="÷"
        app:layout_constraintBaseline_toBaselineOf="@+id/button5"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.5"
        app:layout_constraintStart_toEndOf="@+id/button5"
        android:layout_marginRight="10dp"
        android:textColor="#0e58f7"/>

    <Button
        android:id="@+id/button3"
        style="@style/ButtonStyle"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:text="x"
        app:layout_constraintBaseline_toBaselineOf="@+id/button11"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.5"
        app:layout_constraintStart_toEndOf="@+id/button11"
        android:layout_marginRight="10dp"
        android:textColor="#0e58f7"/>

    <Button
        android:id="@+id/button4"
        style="@style/ButtonStyle"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:text="⬅"
        android:textColor="#0e58f7"
        app:layout_constraintBaseline_toBaselineOf="@+id/button20"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.5"
        app:layout_constraintStart_toEndOf="@+id/button20"
        android:layout_marginRight="10dp"/>

    <Button
        android:id="@+id/button9"
        style="@style/ButtonStyle"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginBottom="12dp"
        android:text="7"
        app:layout_constraintBottom_toTopOf="@+id/button7"
        app:layout_constraintEnd_toStartOf="@+id/button6"
        app:layout_constraintHorizontal_bias="0.5"
        app:layout_constraintStart_toStartOf="parent" />

    <Button
        android:id="@+id/button6"
        style="@style/ButtonStyle"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:text="8"
        app:layout_constraintBaseline_toBaselineOf="@+id/button9"
        app:layout_constraintEnd_toStartOf="@+id/button5"
        app:layout_constraintHorizontal_bias="0.5"
        app:layout_constraintStart_toEndOf="@+id/button9"
        />

    <Button
        android:id="@+id/button5"
        style="@style/ButtonStyle"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:text="9"
        app:layout_constraintBaseline_toBaselineOf="@+id/button6"
        app:layout_constraintEnd_toStartOf="@+id/button2"
        app:layout_constraintHorizontal_bias="0.5"
        app:layout_constraintStart_toEndOf="@+id/button6" />

    <Button
        android:id="@+id/button8"
        style="@style/ButtonStyle"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:text="—"
        app:layout_constraintBaseline_toBaselineOf="@+id/button13"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.5"
        app:layout_constraintStart_toEndOf="@+id/button13"
        android:layout_marginRight="10dp"/>

    <Button
        android:id="@+id/button7"
        style="@style/ButtonStyle"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginStart="10dp"
        android:layout_marginLeft="10dp"
        android:layout_marginBottom="12dp"
        android:text="4"
        app:layout_constraintBottom_toTopOf="@+id/button17"
        app:layout_constraintEnd_toStartOf="@+id/button10"
        app:layout_constraintHorizontal_bias="0.5"
        app:layout_constraintStart_toStartOf="parent" />

    <Button
        android:id="@+id/button10"
        style="@style/ButtonStyle"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:text="5"
        app:layout_constraintBaseline_toBaselineOf="@+id/button7"
        app:layout_constraintEnd_toStartOf="@+id/button11"
        app:layout_constraintHorizontal_bias="0.5"
        app:layout_constraintStart_toEndOf="@+id/button7" />

    <Button
        android:id="@+id/button11"
        style="@style/ButtonStyle"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:text="6"
        app:layout_constraintBaseline_toBaselineOf="@+id/button10"
        app:layout_constraintEnd_toStartOf="@+id/button3"
        app:layout_constraintHorizontal_bias="0.5"
        app:layout_constraintStart_toEndOf="@+id/button10" />

    <Button
        android:id="@+id/button12"
        style="@style/ButtonStyle"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:text="+"
        app:layout_constraintBaseline_toBaselineOf="@+id/button15"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.5"
        app:layout_constraintStart_toEndOf="@+id/button15"
        android:layout_marginRight="10dp"
        android:textColor="#0e58f7"/>

    <Button
        android:id="@+id/button13"
        style="@style/ButtonStyle"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:text="="
        app:layout_constraintBaseline_toBaselineOf="@+id/button19"
        app:layout_constraintEnd_toStartOf="@+id/button8"
        app:layout_constraintHorizontal_bias="0.5"
        app:layout_constraintStart_toEndOf="@+id/button19" />

    <Button
        android:id="@+id/button14"
        style="@style/ButtonStyle"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginStart="10dp"
        android:layout_marginLeft="10dp"
        android:layout_marginBottom="12dp"
        android:text="·"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toStartOf="@+id/button19"
        app:layout_constraintHorizontal_bias="0.5"
        app:layout_constraintStart_toStartOf="parent" />

    <Button
        android:id="@+id/button15"
        style="@style/ButtonStyle"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:text="3"
        app:layout_constraintBaseline_toBaselineOf="@+id/button18"
        app:layout_constraintEnd_toStartOf="@+id/button12"
        app:layout_constraintHorizontal_bias="0.5"
        app:layout_constraintStart_toEndOf="@+id/button18" />

    <Button
        android:id="@+id/button16"
        style="@style/ButtonStyle"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:text="%"
        app:layout_constraintBaseline_toBaselineOf="@+id/button"
        app:layout_constraintEnd_toStartOf="@+id/button20"
        app:layout_constraintHorizontal_bias="0.5"
        app:layout_constraintStart_toEndOf="@+id/button"
        android:textColor="#0e58f7"/>

    <Button
        android:id="@+id/button17"
        style="@style/ButtonStyle"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginStart="10dp"
        android:layout_marginLeft="10dp"
        android:layout_marginBottom="12dp"
        android:text="1"
        app:layout_constraintBottom_toTopOf="@+id/button14"
        app:layout_constraintEnd_toStartOf="@+id/button18"
        app:layout_constraintHorizontal_bias="0.5"
        app:layout_constraintStart_toStartOf="parent" />

    <Button
        android:id="@+id/button18"
        style="@style/ButtonStyle"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:text="2"
        app:layout_constraintBaseline_toBaselineOf="@+id/button17"
        app:layout_constraintEnd_toStartOf="@+id/button15"
        app:layout_constraintHorizontal_bias="0.5"
        app:layout_constraintStart_toEndOf="@+id/button17" />

    <Button
        android:id="@+id/button19"
        style="@style/ButtonStyle"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:text="0"
        app:layout_constraintBaseline_toBaselineOf="@+id/button14"
        app:layout_constraintEnd_toStartOf="@+id/button13"
        app:layout_constraintHorizontal_bias="0.5"
        app:layout_constraintStart_toEndOf="@+id/button14" />

    <Button
        android:id="@+id/button20"
        style="@style/ButtonStyle"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:text="M"
        app:layout_constraintBaseline_toBaselineOf="@+id/button16"
        app:layout_constraintEnd_toStartOf="@+id/button4"
        app:layout_constraintHorizontal_bias="0.5"
        app:layout_constraintStart_toEndOf="@+id/button16"
        android:textColor="#0e58f7"/>

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
* 布局文件
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

        <TableRow>
            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="     Save..."
                style="@style/Table_font">
            </TextView>
            <TextView/>
            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="Ctrl+S"
                android:gravity="end"
                style="@style/Table_font">
            </TextView>
        </TableRow>

        <TableRow>
            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="     Save As..."
                style="@style/Table_font">
            </TextView>
            <TextView/>
            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="Ctrl+Shift+S"
                android:gravity="end"
                style="@style/Table_font">
            </TextView>
        </TableRow>
        <View
            android:layout_height="3dp"
            android:layout_width="match_parent"
            style="@style/Table_font"
            android:background="@color/white"/>
        <TableRow>
            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="X  Import..."
                style="@style/Table_font">
            </TextView>
            <TextView/>
            <TextView/>
        </TableRow>

        <TableRow>
            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="X  Export"
                style="@style/Table_font">
            </TextView>
            <TextView/>
            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="Ctrl+E"
                android:layout_gravity="end"
                style="@style/Table_font">
            </TextView>
        </TableRow>
        <View
            android:layout_height="3dp"
            android:layout_width="match_parent"
            style="@style/Table_font"
            android:background="@color/white"/>
        <TableRow>
            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="     Quit"
                style="@style/Table_font">
            </TextView>
            <TextView/>
            <TextView/>
        </TableRow>
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
