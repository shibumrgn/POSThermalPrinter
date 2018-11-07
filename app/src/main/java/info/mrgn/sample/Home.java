package info.mrgn.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import info.mrgn.thermalprinter.Response;
import info.mrgn.thermalprinter.SendPrint;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        new SendPrint("192.168.1.100", "Testing content", new Response() {
            @Override
            public void success(int status_code, String message) {

            }

            @Override
            public void failed(int status_code, String message) {

            }
        });
    }
}
