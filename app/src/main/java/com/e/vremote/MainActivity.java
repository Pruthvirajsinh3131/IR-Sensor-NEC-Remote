package com.e.vremote;

import android.app.Activity;
import android.hardware.ConsumerIrManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends Activity {


    private final static String CMD_Remote_POWER =
            "0000 006C 0022 0002 015B 00AD 0016 0016 0016 0016 0016 0016 0016 0041 0016 0041 0016 0016 0016 0016 0016 0016 0016 0041 0016 0016 0016 0016 0016 0041 0016 0016 0016 0041 0016 0041 0016 0041 0016 0016 0016 0016 0016 0016 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0041 0016 0041 0016 0016 0016 0041 0016 0041 0016 0041 0016 0041 0016 0622 015B 0057 0016 0E6C";
    private final static String CMD_Remote_MENU =
            "0000 006C 0022 0002 015B 00AD 0016 0016 0016 0016 0016 0016 0016 0041 0016 0041 0016 0016 0016 0016 0016 0016 0016 0041 0016 0016 0016 0016 0016 0041 0016 0016 0016 0041 0016 0041 0016 0041 0016 0016 0016 0041 0016 0041 0016 0016 0016 0016 0016 0016 0016 0041 0016 0016 0016 0041 0016 0016 0016 0016 0016 0041 0016 0041 0016 0041 0016 0016 0016 0041 0016 0622 015B 0057 0016 0E6C";
    private final static String CMD_Remote_Off =
            "0000 006C 0022 0002 015B 00AD 0016 0016 0016 0016 0016 0016 0016 0041 0016 0041 0016 0016 0016 0016 0016 0016 0016 0041 0016 0016 0016 0016 0016 0041 0016 0016 0016 0041 0016 0041 0016 0041 0016 0016 0016 0016 0016 0041 0016 0016 0016 0041 0016 0016 0016 0016 0016 0016 0016 0041 0016 0041 0016 0016 0016 0041 0016 0016 0016 0041 0016 0041 0016 0041 0016 0622 015B 0057 0016 0E6C";
    private final static String CMD_Remote_SOURCE =
            "0000 006C 0022 0002 015B 00AD 0016 0016 0016 0016 0016 0016 0016 0041 0016 0041 0016 0016 0016 0016 0016 0016 0016 0041 0016 0016 0016 0016 0016 0041 0016 0016 0016 0041 0016 0041 0016 0041 0016 0016 0016 0041 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0016 0016 0016 0016 0041 0016 0041 0016 0041 0016 0041 0016 0041 0016 0622 015B 0057 0016 0E6C";
    private final static String CMD_Remote_CH_NEXT =
            "0000 006C 0022 0002 015B 00AD 0016 0016 0016 0016 0016 0016 0016 0041 0016 0041 0016 0016 0016 0016 0016 0016 0016 0041 0016 0016 0016 0016 0016 0041 0016 0016 0016 0041 0016 0041 0016 0041 0016 0016 0016 0016 0016 0016 0016 0041 0016 0041 0016 0041 0016 0016 0016 0041 0016 0041 0016 0041 0016 0041 0016 0016 0016 0016 0016 0016 0016 0041 0016 0016 0016 0622 015B 0057 0016 0E6C";
    private final static String CMD_Remote_CH_PREV =
            "0000 006C 0022 0002 015B 00AD 0016 0016 0016 0016 0016 0016 0016 0041 0016 0041 0016 0016 0016 0016 0016 0016 0016 0041 0016 0016 0016 0016 0016 0041 0016 0016 0016 0041 0016 0041 0016 0041 0016 0016 0016 0016 0016 0041 0016 0041 0016 0041 0016 0041 0016 0016 0016 0041 0016 0041 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0016 0016 0622 015B 0057 0016 0E6C";
    private final static String CMD_Remote_BACK =
            "0000 006C 0022 0002 015B 00AD 0016 0016 0016 0016 0016 0016 0016 0041 0016 0041 0016 0016 0016 0016 0016 0016 0016 0041 0016 0016 0016 0016 0016 0041 0016 0016 0016 0041 0016 0041 0016 0041 0016 0041 0016 0016 0016 0041 0016 0016 0016 0016 0016 0041 0016 0016 0016 0016 0016 0016 0016 0041 0016 0016 0016 0041 0016 0041 0016 0016 0016 0041 0016 0041 0016 0622 015B 0057 0016 0E6C";
    private final static String CMD_Remote_LEFT =
            "0000 006C 0022 0002 015B 00AD 0016 0016 0016 0016 0016 0016 0016 0041 0016 0041 0016 0016 0016 0016 0016 0016 0016 0041 0016 0016 0016 0016 0016 0041 0016 0016 0016 0041 0016 0041 0016 0041 0016 0016 0016 0041 0016 0041 0016 0041 0016 0041 0016 0041 0016 0016 0016 0041 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0016 0016 0622 015B 0057 0016 0E6C";
    private final static String CMD_Remote_RIGHT =
            "0000 006C 0022 0002 015B 00AD 0016 0016 0016 0016 0016 0016 0016 0041 0016 0041 0016 0016 0016 0016 0016 0016 0016 0041 0016 0016 0016 0016 0016 0041 0016 0016 0016 0041 0016 0041 0016 0041 0016 0016 0016 0041 0016 0016 0016 0041 0016 0041 0016 0041 0016 0016 0016 0041 0016 0041 0016 0016 0016 0041 0016 0016 0016 0016 0016 0016 0016 0041 0016 0016 0016 0622 015B 0057 0016 0E6C";
    private final static String CMD_Remote_ENTER =
            "0000 006C 0022 0002 015B 00AD 0016 0016 0016 0016 0016 0016 0016 0041 0016 0041 0016 0016 0016 0016 0016 0016 0016 0041 0016 0016 0016 0016 0016 0041 0016 0016 0016 0041 0016 0041 0016 0041 0016 0041 0016 0041 0016 0041 0016 0016 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0016 0016 0041 0016 0041 0016 0041 0016 0622 015B 0057 0016 0E6C";




    private ConsumerIrManager irManager;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        irManager = (ConsumerIrManager) getSystemService(CONSUMER_IR_SERVICE);

        findViewById(R.id.remotepower).setOnClickListener(new ClickListener(hex2ir(CMD_Remote_POWER)));
        findViewById(R.id.remoteoff).setOnClickListener(new ClickListener (hex2ir (CMD_Remote_Off)));
        findViewById(R.id.remotemenu).setOnClickListener(new ClickListener (hex2ir (CMD_Remote_MENU)));
        findViewById(R.id.remotesource).setOnClickListener(new ClickListener(hex2ir(CMD_Remote_SOURCE)));
        findViewById(R.id.remotechnext).setOnClickListener(new ClickListener(hex2ir(CMD_Remote_CH_NEXT)));
        findViewById(R.id.remotechprev).setOnClickListener(new ClickListener(hex2ir(CMD_Remote_CH_PREV)));
        findViewById(R.id.remoteback).setOnClickListener(new ClickListener(hex2ir(CMD_Remote_BACK)));
        findViewById(R.id.remoteleft).setOnClickListener(new ClickListener(hex2ir(CMD_Remote_LEFT)));
        findViewById(R.id.remoteright).setOnClickListener(new ClickListener(hex2ir(CMD_Remote_RIGHT)));
        findViewById(R.id.remoteok).setOnClickListener(new ClickListener(hex2ir(CMD_Remote_ENTER)));

    }


    private IRCommand hex2ir(final String irData) {
        List<String> list = new ArrayList<String>(Arrays.asList(irData.split(" ")));
        list.remove(0); // dummy
        int frequency = Integer.parseInt(list.remove(0), 16); // frequency
        list.remove(0); // seq1
        list.remove(0); // seq2

        frequency = (int) (1000000 / (frequency * 0.241246));
        int pulses = 1000000 / frequency;
        int count;

        int[] pattern = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            count = Integer.parseInt(list.get(i), 16);
            pattern[i] = count * pulses;
        }

        return new IRCommand(frequency, pattern);
    }

    private class ClickListener implements View.OnClickListener {
        private final IRCommand cmd;

        ClickListener(final IRCommand cmd) {
            this.cmd = cmd;
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public void onClick(final View view) {
            android.util.Log.d("Remote", "frequency: " + cmd.freq);
            android.util.Log.d("Remote", "pattern: " + Arrays.toString(cmd.pattern));
            irManager.transmit(cmd.freq, cmd.pattern);
        }
    }

    private class IRCommand {
        private final int freq;
        private final int[] pattern;

        private IRCommand(int freq, int[] pattern) {
            this.freq = freq;
            this.pattern = pattern;
        }
    }

}
