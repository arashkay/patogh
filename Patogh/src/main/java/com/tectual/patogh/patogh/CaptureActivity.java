package com.tectual.patogh.patogh;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.abhi.barcode.frag.libv2.BarcodeFragment;
import com.abhi.barcode.frag.libv2.IScanResultHandler;
import com.abhi.barcode.frag.libv2.ScanResult;
import com.google.zxing.BarcodeFormat;

import java.util.EnumSet;

/**
 * Created by arash on 30/04/2014.
 */
public class CaptureActivity extends FragmentActivity implements IScanResultHandler {

    BarcodeFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scanner);
        fragment = (BarcodeFragment)getSupportFragmentManager().findFragmentById(R.id.qr_code_capture);
        fragment.setScanResultHandler(this);
        // Support for adding decoding type
        fragment.setDecodeFor(EnumSet.of(BarcodeFormat.QR_CODE));
    }

    @Override
    public void scanResult(ScanResult result) {
        PatoghApplication app = ((PatoghApplication) this.getApplication());
        Intent mainIntent = new Intent(this, MainActivity.class);
        mainIntent.putExtra("targetTab", 0);
        mainIntent.putExtra("loyaltyCardId", 1);
        startActivity(mainIntent);
    }

}
