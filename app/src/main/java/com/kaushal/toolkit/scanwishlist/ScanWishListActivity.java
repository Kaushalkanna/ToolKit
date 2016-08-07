package com.kaushal.toolkit.scanwishlist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.kaushal.toolkit.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class ScanWishListActivity extends Activity {
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scanwishlist_main);
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);
    }

    public void barcode_scanner(View v) {
        Intent intent;
        intent = new Intent(this, BarCodeScanner.class);
        startActivity(intent);
    }

    public void listView(View v) {
        Intent intent;
        intent = new Intent(this, ListViewAndroid.class);
        startActivity(intent);
    }

}
