package com.sinhvien.finalproject.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.app.ActivityOptions;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.util.Pair;
import android.view.View;

import com.ramotion.paperonboarding.PaperOnboardingFragment;
import com.ramotion.paperonboarding.PaperOnboardingPage;
import com.sinhvien.finalproject.R;

import java.util.ArrayList;
import java.util.Locale;


public class WelcomeActivity extends AppCompatActivity{

    private FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_layout);

        fragmentManager = getSupportFragmentManager();
        
        final PaperOnboardingFragment paperOnboardingFragment = PaperOnboardingFragment.newInstance(getDataForOnBoarding());

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_ob, paperOnboardingFragment);
        fragmentTransaction.commit();
    }



    private ArrayList<PaperOnboardingPage> getDataForOnBoarding() {
        PaperOnboardingPage src1 = new PaperOnboardingPage("Quản lý bán hàng", "Quản lý bán hàng tại cửa hàng thông qua App, tinh tiền, in hóa đơn nhanh chóng, quản lý đơn hàng, theo dõi báo cáo bán hàng mọi lúc.", Color.parseColor("#ffffff"),
                R.drawable.caffeslide,R.drawable.circle_drawable);
        PaperOnboardingPage src2 = new PaperOnboardingPage("Đơn giản & Dễ sử dụng", "Giao diện đơn giản, thân thiện và thông minh. Nhân viên chỉ mất vài phút làm quen để bán hàng.", Color.parseColor("#ffffff"),
                R.drawable.caffeslide1,R.drawable.circle_drawable);
        PaperOnboardingPage src3 = new PaperOnboardingPage("Tiết kiệm chi phí", "Miễn phí cài đặt, triển khai, nâng cấp và hỗ trợ. Rẻ hơn một ly trà đá mỗi ngày.", Color.parseColor("#ffffff"),
                R.drawable.caffeslide2,R.drawable.circle_drawable);
        PaperOnboardingPage src4 = new PaperOnboardingPage("", "", Color.parseColor("#ffffff"),
                R.drawable.logocoffe1,R.drawable.circle_drawable);


        ArrayList<PaperOnboardingPage> element = new ArrayList<>();
        element.add(src1);
        element.add(src2);
        element.add(src3);
        element.add(src4);
        return element;


    }

    ;



    //chuyển sang trang đăng nhập
    public void callLoginFromWel(View view)
    {
        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);

        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(findViewById(R.id.btn_login),"transition_login");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(WelcomeActivity.this,pairs);
            startActivity(intent,options.toBundle());
        }
        else {
            startActivity(intent);
        }
    }
    // chuyển sang trang đăng ký
    public void callSignUpFromWel(View view)
    {
        Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(findViewById(R.id.btn_signup),"transition_signup");
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(WelcomeActivity.this,pairs);
            startActivity(intent,options.toBundle());
        }
        else {
            startActivity(intent);
        }
    }

}