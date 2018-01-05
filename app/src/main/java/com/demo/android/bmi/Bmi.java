package com.demo.android.bmi;

import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class Bmi extends AppCompatActivity implements View.OnClickListener{

    EditText et_height;
    EditText et_weight;
    Button submit;
    TextView result,suggest;
    LinearLayout linearLayout1;
    //ImageView ivTipDark;
    TextView tvHint2,tvHint3;
    ImageView ivgif;
    ImageView ivvisgif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
        findViews();
        playViewAnimation();
    }

    private void findViews() {
        et_height = (EditText) findViewById(R.id.etHeight);
        et_weight = (EditText) findViewById(R.id.etWeight);
        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(this);
        result = (TextView) findViewById(R.id.result);
        suggest = (TextView) findViewById(R.id.suggest);
        //ivTipDark = (ImageView)findViewById(R.id.ivTipDark);
        linearLayout1 = (LinearLayout)findViewById(R.id.linearLayout1);
        linearLayout1.setOnClickListener(onShowLinearLayout);
        tvHint2 = (TextView) findViewById(R.id.tvHint2);
        tvHint3 = (TextView) findViewById(R.id.tvHint3);
        ivgif = (ImageView)findViewById(R.id.ivgif);
        ivvisgif = (ImageView)findViewById(R.id.ivvisgif);
    }

    @Override
    public void onClick(View v) {
        try {
            String height = et_height.getText().toString();
            String weight = et_weight.getText().toString();
            double h = Double.parseDouble(height) / 100;
            double w = Double.parseDouble(weight);
            double BMI = w / (h * h);
            DecimalFormat nf = new DecimalFormat("0.00");
            result.setText(getString(R.string.bmi_result) + nf.format(BMI));

            if (BMI > 25) {
                Toast.makeText(Bmi.this, R.string.advice_heavy, Toast.LENGTH_SHORT).show();
                suggest.setText(R.string.advice_heavy);
                ivvisgif.setVisibility(View.VISIBLE);
                playViewAnimation();
            } else if (BMI < 20)
                suggest.setText(R.string.advice_light);
            else
                suggest.setText(R.string.advice_average);

        } catch (NumberFormatException e) {
            Toast.makeText(Bmi.this, "請輸入資料", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }

    private void playViewAnimation(){
        /*Animation anim = AnimationUtils.loadAnimation(this,R.anim.anim);
        ivTipDark.setAnimation(anim);*/
        AnimationDrawable andr = (AnimationDrawable)ivgif.getBackground();
        andr.start();
        AnimationDrawable andr1 = (AnimationDrawable)ivvisgif.getBackground();
        andr1.start();
    }

    View.OnClickListener onShowLinearLayout = new View.OnClickListener() {
        int count = 0;
        @Override
        public void onClick(View v) {
            count++;
            switch (count){
                case 1:
                    tvHint2.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    tvHint3.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    linearLayout1.setVisibility(View.GONE);
                    break;
            }
        }
    };
}

