package fragments;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SearchView;

import com.example.xiaocaiyidie.AttentionActivity;
import com.example.xiaocaiyidie.FansActivity;
import com.example.xiaocaiyidie.R;
import com.example.xiaocaiyidie.SearchActivity;
import com.example.xiaocaiyidie.SettingActivity;
import com.example.xiaocaiyidie.StarActivity;
import com.example.xiaocaiyidie.TrendsActivity;
import com.example.xiaocaiyidie.WelcomeActivity;

public class MyFragment extends Fragment {
    private LinearLayout linearLayout1;
    private LinearLayout linearLayout2;
    private LinearLayout linearLayout3;
    private LinearLayout linearLayout4;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_fragment, container, false);
        setHasOptionsMenu(true);

        return view;
    }

    // 跳转到关注界面
    @Override
    public void onStart() {
        super.onStart();
        linearLayout1 = (LinearLayout) this.getActivity().findViewById(R.id.my_attention);
        linearLayout2 = (LinearLayout) this.getActivity().findViewById(R.id.my_star);
        linearLayout3 = (LinearLayout) this.getActivity().findViewById(R.id.my_fans);
        linearLayout4 = (LinearLayout) this.getActivity().findViewById(R.id.my_trends);
        linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AttentionActivity.class);
                startActivity(intent);
            }
        });
        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), StarActivity.class);
                startActivity(intent);
            }
        });
        linearLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FansActivity.class);
                startActivity(intent);
            }
        });
        linearLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TrendsActivity.class);
                startActivity(intent);
            }
        });

    }

    //    //当用户点击Menu按键时触发该方法
//
//    @Override
//    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
//        inflater.inflate(R.menu.actionbat_units, menu);
//        MenuItem menuItem = menu.findItem(R.id.search);
//        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
//        super.onCreateOptionsMenu(menu, inflater);
//    }
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem menuItem) {
//        System.out.println(menuItem.getItemId());
//        switch (menuItem.getItemId()) {
//            case R.id.search:
//                Intent intent = new Intent(getActivity(), SearchActivity.class);
//                startActivity(intent);
//        }
//        return true;
//    }

    //当用户点击Menu按键时触发该方法
    // 点击跳转到设置
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.setting_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.actionbar_setting);
        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.actionbar_setting:
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
        }
        return true;
    }

}