package org.easydarwin.player.simpleplayer;

import android.os.Bundle;
import android.view.TextureView;

import org.easydarwin.video.EasyPlayerClient;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EasyPlayerClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextureView textureView = findViewById(R.id.texture_view);
        /**
         * 参数说明
         * 第一个参数为Context,第二个参数为KEY
         * 第三个参数为的textureView,用来显示视频画面
         * 第四个参数为一个ResultReceiver,用来接收SDK层发上来的事件通知;
         * 第五个参数为I420DataCallback,如果不为空,那底层会把YUV数据回调上来.
         */
        client = new EasyPlayerClient(this,  textureView, null, null);

        client.play("rtsp://192.168.8.196/tech-exam/2b4ffd65c4219b95068205bb216138d7");

//        final EditText et = new EditText(this);
//        et.setHint("请输入RTSP地址");
//        final SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
//        et.setText(sp.getString("url","rtsp://192.168.8.196/tech-exam/2b4ffd65c4219b95068205bb216138d7"));
//
//        new AlertDialog.Builder(this).setView(et).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                String url = et.getText().toString();
//                if (!TextUtils.isEmpty(url)){
//
//                    sp.edit().putString("url", url).apply();
//                }
//            }
//        }).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                finish();
//            }
//        }).show();

    }
}
