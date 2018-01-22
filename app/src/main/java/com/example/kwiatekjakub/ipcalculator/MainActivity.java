package com.example.kwiatekjakub.ipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.et_ip)
    EditText etIp;
    @BindView(R.id.et_mask)
    EditText etMask;
    @BindView(R.id.tv_address_display)
    TextView tvAddress;
    @BindView(R.id.tv_netmask_display)
    TextView tvNetmask;
    @BindView(R.id.tv_network_display)
    TextView tvNetwork;
    @BindView(R.id.tv_broadcast_display)
    TextView tvBroadcast;
    @BindView(R.id.tv_host_min_display)
    TextView tvHostMin;
    @BindView(R.id.tv_host_max_display)
    TextView tvHostMax;
    @BindView(R.id.tv_hosts_display)
    TextView tvHosts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setToolbar();
    }


    @OnFocusChange(R.id.et_ip)
    public void setNullAddress(boolean b){
        if (b) etIp.setText(null);
    }

    @OnFocusChange(R.id.et_mask)
    public void setNullMask(boolean b){
        if (b) etMask.setText(null);
    }

    @OnClick(R.id.btn_start)
    public void startCounting(){
        try{

            IP ip = new IP(etIp.getText().toString(),etMask.getText().toString(),this);

            tvAddress.setText(ip.getIpAddressString());
            tvNetmask.setText(ip.getMask().toString());
            tvNetwork.setText(ip.netAddress().toString());
            tvBroadcast.setText(ip.broadcast().toString());
            tvHostMin.setText(ip.minAddress().toString());
            tvHostMax.setText(ip.maxAddress().toString());
            tvHosts.setText(String.valueOf(ip.numberOfHosts()));

        }catch (StringIndexOutOfBoundsException e){
            Toast.makeText(this, R.string.wrong_data_alert, Toast.LENGTH_LONG).show();}


        closeKeyboard();
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
    }

    private void closeKeyboard() {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }catch (NullPointerException e){}
    }


}
