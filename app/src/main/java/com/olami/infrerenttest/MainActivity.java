package com.olami.infrerenttest;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.hardware.ConsumerIrManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener{
    private static final String TAG = "ConsumerIrTest";
    private Button mPower;
    private Button mMute;
    private Button mVollumeUpBtn;
    private Button mVollumeDownBtn ;

    // Android4.4之后 红外遥控ConsumerIrManager，可以被小米4调用
    private ConsumerIrManager mCIR;
    private Context mContext;

    @SuppressLint("InlinedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consumer_ir);
        mContext = this.getBaseContext();
        // 获取系统的红外遥控服务
        mCIR = (ConsumerIrManager) getSystemService(Context.CONSUMER_IR_SERVICE);
        initViewsAndEvents();
    }

    private void initViewsAndEvents() {
        mPower = (Button)findViewById(R.id.power_button);
        mMute = (Button)findViewById(R.id.vollume_mute_button);
        mVollumeDownBtn = (Button)findViewById(R.id.vollume_down_button);
        mVollumeUpBtn = (Button)findViewById(R.id.vollume_up_button);

        mPower.setOnClickListener(this);
        mMute.setOnClickListener(this);
        mVollumeUpBtn.setOnClickListener(this);
        mVollumeDownBtn.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        if (!mCIR.hasIrEmitter()) {
            Toast.makeText(mContext,"未找到红外发生器",1000).show();
            Log.e(TAG, "未找到红外发身器！");
            return;
        }
        switch(v.getId()){
            // 一种交替的载波序列模式，通过毫秒测量

            case R.id.power_button:{
                int[] pattern = { 8985,4481,578,555,578,555,578,556,578,555,578,555,578,555,578,555,578,555,578,1688,579,1687,578,1688,578,1688,578,1688,578,1688,578,555,578,1688,578,555,578,1688,578,555,578,555,578,556,578,555,578,555,578,555,578,1688,578,555,578,1688,578,1688,578,1688,578,1688,578,1688,578,1688,578,40735,8985,2242,578,96163 };
                mCIR.transmit(37950, pattern);
                break;
            }
            case R.id.vollume_mute_button:{
                //int[] pattern = { 8985,4481,578,555,578,555,578,555,578,555,578,555,579,555,578,555,578,555,578,1688,578,1688,578,1688,578,1688,578,1688,578,1688,578,555,578,1688,578,555,578,1688,578,1688,579,1687,578,555,578,555,578,555,578,556,578,1688,578,555,578,555,578,555,578,1688,579,1687,578,1688,578,1689,578,40734,8985,2242,578,96163 };
                int[] pattern = { 8986,4481,578,555,578,555,578,555,578,555,578,555,578,555,578,555,578,555,578,1688,578,1688,578,1688,578,1688,578,1688,578,1688,578,555,578,1688,578,555,578,555,578,555,578,1688,578,555,578,556,577,555,578,556,578,1688,578,1688,578,1688,578,555,578,1688,578,1688,578,1688,578,1688,578,40703};
                mCIR.transmit(37950, pattern);
                break;
            }
            case R.id.vollume_up_button:{
                int[] pattern = { 8985,4481,578,555,578,555,578,555,578,555,578,555,578,555,578,555,578,555,578,1688,578,1688,578,1688,578,1688,578,1688,578,1688,578,555,578,1688,578,555,578,555,578,555,579,1688,578,1688,578,555,578,555,578,555,578,1688,578,1688,578,1688,579,554,578,555,578,1688,578,1688,578,1688,578,40704 };
                mCIR.transmit(37950, pattern);
                break;
            }
            case R.id.vollume_down_button:{
                int[] pattern = {8985,4481,578,555,578,555,578,555,578,555,578,555,578,555,578,555,578,555,578,1688,578,1688,578,1688,578,1688,578,1688,578,1688,578,555,578,1688,578,1688,578,555,578,555,579,1688,578,1688,578,555,578,555,578,555,578,555,578,1688,578,1688,578,555,579,555,578,1688,578,1688,578,1688,578,40703};
                mCIR.transmit(37950, pattern);
                break;
            }

        }
    }
}
