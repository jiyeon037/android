package com.example.user.a2_texttospeechexam;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.a2_texttospeechexam.helper.FileHelper;

import java.io.File;
import java.util.Locale;

/**
 * 엔진이 무겁기 때문에, 초기화 시간이 오래 걸린다.
 * 초기화가 진행되는 동안 App이 벽돌이 될 수는 없으므로
 * 초기화 작업은 백그라운드로 실행되며, 초기화가 완료되면 이벤트를 발생시킨다.
 */
public class MainActivity extends AppCompatActivity
        implements View.OnClickListener, TextToSpeech.OnInitListener {

    EditText editText;
    Button button, button2;
    TextToSpeech tts;       // TextToSpeech 객체
    boolean init;          // TextToSpeech 초기화 여부 저장

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        init = false;   // 일단 초기화 안된걸로 가정
        // tts 객체 (Context, 이벤트리스너 지정)
        tts = new TextToSpeech(this, this);
        button.setOnClickListener(this);
        button2.setOnClickListener(this);

        permissionCheck();
    }

    private void permissionCheck() { // developer.android.com 에서 가져온 것. 퍼미션체크 안하면 에러도 안나고 동작도 안됨.
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},100);
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (!init) {
            Toast.makeText(this, "아직 초기화 되지 않았습니다.", Toast.LENGTH_SHORT).show();
            return;
        }
        String msg = "";
        switch (v.getId()){
            case R.id.button:
                msg = editText.getText().toString().trim();
                if(msg.equals("")) {
                    Toast.makeText(this, "내용을 입력하세요!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                break;
            case R.id.button2:
                // 저장하거나 읽어올 파일의 경로
                String fileName = "tts.txt";
                File dir = Environment.getExternalStorageDirectory();
                String filePath = dir.getAbsolutePath() + "/" + fileName;
                String encType = "utf-8";

                msg = FileHelper.getInstance().readString(filePath, encType);
                //editText.setText(msg);
                break;
        }

        // 사용할 언어
        Locale loc = Locale.KOREA;
        // 해당 언어가 지원되는지 검사
        int available = tts.isLanguageAvailable(loc);
        if (available < 0) {
            Toast.makeText(this, "지정되지 않은 언어입니다.", Toast.LENGTH_SHORT).show();
            return;
        }
        // 언어 설정
        tts.setLanguage(loc);
        // 읽어라
        tts.speak(msg, TextToSpeech.QUEUE_FLUSH, null);
    }
    // TTS의 초기화가 완료되면 자동으로 호출됨
    @Override
    public void onInit(int status) {
        // 초기화 여부를 전역변수에 설정함
        init = (status == TextToSpeech.SUCCESS);
        String msg = "엔진이 초기화에 실패했습니다.";
        if(init) {
            msg = "엔진이 초기화 되었습니다.";
        }
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * TTS는 App이 종료되어도 메모리에 상주하기 때문에
     * App이 종료될 시점에 직접 메모리에서 해제시켜야 한다.
     * */

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(tts != null) {
            tts.stop();     // 음성 출력 정지
            tts.shutdown(); // TextToSpeech 종료
        }
    }
}
