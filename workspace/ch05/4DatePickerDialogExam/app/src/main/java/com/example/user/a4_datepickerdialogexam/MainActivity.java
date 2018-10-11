package com.example.user.a4_datepickerdialogexam;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.user.a4_datepickerdialogexam.helper.DateTimeHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button button, button2;
    // 상태 유지를 위한 전역변수
    int YEAR=0, MONTH=0, DAY=0;
    int HOUR = 0, MINUTE = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        // Dialog에 출력하기 위한 현재 시스템 날짜를 구하여 전역변수에 미리 셋팅한다.
        int[] date = DateTimeHelper.getInstance().getDate();
        YEAR = date[0];
        MONTH = date[1];
        DAY = date[2];
        // Dialog에 출력하기 위한 현재 시스템 시간을 구하여 전역변수에 미리 셋팅한다.
        int[] time = DateTimeHelper.getInstance().getTime();
        HOUR = time[0];
        MINUTE = time[1];

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                showDatePickerDialog();
                break;
            case R.id.button2:
                showTimePickerDialog();
                break;
        }
    }

    // 시간 선택 팝업을 표시한다.
    private void showTimePickerDialog() {
        // 원복 처리에 사용될 임시값 - 원본데이터를 백업한다.
        final int temp_hh = HOUR;
        final int temp_mi = MINUTE;
        // TimePickerDialog 객체의 생성
        // "Context, 이벤트핸들러, 시, 분, 24시간제 사용여부"를 파라미터로 지정한다.
        // true - 24시간제 사용, false - 24시간제 사용 안함
        TimePickerDialog dialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
            // Dialog에서 사용자가 시간을 설정 후, "확인"버튼을 누르면 동작하는 이벤트
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        // 사용자의 선택값을 전역변수에 설정한다.
                        HOUR = hourOfDay;
                        MINUTE = minute;
                        String result = HOUR + "시 " + MINUTE + "분";
                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                    }
                }, HOUR, MINUTE, false);
        // 최소시의 상태 유지값 원복 처리
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                // 백업해 두었던 값을 원복시킨다.
                HOUR = temp_hh;
                MINUTE = temp_mi;
            }
        });

        dialog.setMessage("약속시간을 선택하세요");
        dialog.show();
    }

    // 날짜 선택 팝업을 표시한다.
    private void showDatePickerDialog() {
        // 원복처리에 사용될 임시값 - 원본데이터를 백업한다.
        final int temp_yy = YEAR;
        final int temp_mm = MONTH;
        final int temp_dd = DAY;
        // DatePickerDialog 객체의 생성
        // "Context, 이벤트 핸들러, 년, 월, 일"을 파라미터로 전달한다.
        DatePickerDialog dialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() { // 익명클래스로 이벤트 처리
                    // Dialog에서 사용자가 날짜를 선택하고 "확인"버튼을 누르면 동작하는 이벤트
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // 사용자의 선택값을 전역변수에 설정한다.
                        YEAR = year;
                        MONTH = month + 1;
                        DAY = dayOfMonth;
                        String result = YEAR + "년" + MONTH + "월" + DAY + "일";
                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                    }
                }, YEAR, MONTH - 1, DAY);
        // 사용자가 Back키나 "CANCEL" 을 눌렀을 때, 동작하는 이벤트 정의
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                // 백업해 두었던 값을 원복시킨다.
                YEAR = temp_yy;
                MONTH = temp_mm;
                DAY = temp_dd;
            }
        });

        dialog.setMessage("생일을 선택하세요");
        // Dialog의 화면 표시
        dialog.show();
    }
}
