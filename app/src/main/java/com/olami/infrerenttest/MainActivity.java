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
    private TextView mFreqsText;
    private Button mVollumeUpBtn;
    private Button mVollumeDownBtn ;
    private Button mGetFre;

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
        mVollumeDownBtn = (Button)findViewById(R.id.vollume_down_button);
        mVollumeUpBtn = (Button)findViewById(R.id.vollume_up_button);
        mGetFre = (Button)findViewById(R.id.get_freqs_button);

        mVollumeUpBtn.setOnClickListener(this);
        mVollumeDownBtn.setOnClickListener(this);
        mGetFre.setOnClickListener(this);

        mFreqsText = (TextView) findViewById(R.id.freqs_text);
    }

    /*View.OnClickListener mSendClickListener = new View.OnClickListener() {
        @TargetApi(Build.VERSION_CODES.KITKAT)
        public void onClick(View v) {
            if (!mCIR.hasIrEmitter()) {
                Log.e(TAG, "未找到红外发身器！");
                return;
            }

            // 一种交替的载波序列模式，通过毫秒测量
            int[] pattern = { 8985,4481,578,555,578,555,578,555,578,555,578,555,578,555,579,555,578,555,578,1688,578,1688,578,1688,578,1688,578,1688,578,1688,578,555,578,1688,578,555,579,554,578,1688,578,555,578,555,578,555,578,1688,578,555,578,1688,578,1688,578,555,578,1688,578,1688,578,1688,578,555,578,1688,579,40734,8985,2242,578,96164 };

            // 在38.4KHz条件下进行模式转换
            mCIR.transmit(38400, pattern);
        }
    };

    @SuppressLint("NewApi")
    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            StringBuilder b = new StringBuilder();

            if (!mCIR.hasIrEmitter()) {
                mFreqsText.setText("未找到红外发身器！");
                return;
            }

            // 获得可用的载波频率范围
            ConsumerIrManager.CarrierFrequencyRange[] freqs = mCIR
                    .getCarrierFrequencies();
            b.append("IR Carrier Frequencies:\n");// 红外载波频率
            // 边里获取频率段
            for (ConsumerIrManager.CarrierFrequencyRange range : freqs) {
                b.append(String.format("    %d - %d\n",
                        range.getMinFrequency(), range.getMaxFrequency()));
            }
            mFreqsText.setText(b.toString());// 显示结果
        }
    };*/

    @Override
    public void onClick(View v) {

        if (!mCIR.hasIrEmitter()) {
            Toast.makeText(mContext,"未找到红外发生器",1000).show();
            Log.e(TAG, "未找到红外发身器！");
            return;
        }
        switch(v.getId()){
            // 一种交替的载波序列模式，通过毫秒测量
            case R.id.vollume_up_button:{
                int[] pattern = { 8985,4481,578,555,578,555,578,555,578,555,578,555,578,555,579,555,578,555,578,1688,578,1688,578,1688,578,1688,578,1688,578,1688,578,555,578,1688,578,555,579,554,578,1688,578,555,578,555,578,555,578,1688,578,555,578,1688,578,1688,578,555,578,1688,578,1688,578,1688,578,555,578,1688,579,40734,8985,2242,578,96164 };
                mCIR.transmit(38400, pattern);
                break;
            }
            case R.id.vollume_down_button:{
                int[] pattern = {8985,4481,578,555,578,555,578,555,578,556,578,555,578,555,578,555,578,555,578,1688,578,1688,578,1688,578,1688,578,1688,578,1688,578,555,579,1688,578,1688,578,1688,578,555,578,555,578,555,578,555,578,1688,578,555,578,555,578,555,578,1688,578,1689,578,1688,578,1688,578,555,578,1688,578,40734,8985,2242,578,96163 };
                mCIR.transmit(38400, pattern);
                break;
            }
            case R.id.get_freqs_button:{

                StringBuilder b = new StringBuilder();

                // 获得可用的载波频率范围
                ConsumerIrManager.CarrierFrequencyRange[] freqs = mCIR
                        .getCarrierFrequencies();
                b.append("IR Carrier Frequencies:\n");// 红外载波频率
                // 边里获取频率段
                for (ConsumerIrManager.CarrierFrequencyRange range : freqs) {
                    b.append(String.format("    %d - %d\n",
                            range.getMinFrequency(), range.getMaxFrequency()));
                }
                mFreqsText.setText(b.toString());// 显示结果
                break;
            }

            // 在38.4KHz条件下进行模式转换

        }
    }
}
