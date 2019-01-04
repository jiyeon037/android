package com.example.user.a1_simpledialogexam;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button1, button2, button3, button4, button5;
    // 선택 상태를 관리하기 위한 전역변수
    int checkedItem = 0;
    boolean[] checkedItems = {false, false, false}; // 멀티초이스에 이용. 선택되지 않은 상태로 보여짐
    // 팝업창에 보여질 목록에 대한 배열
    String[] items = {"선택항목1", "선택항목2", "선택항목3"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button1:
                showAlertDialog();
            break;
            case R.id.button2:
                showConfirmDialog();
                break;
            case R.id.button3:
                showListDialog();
                break;
            case R.id.button4:
                showSingleChoiceDialog();
                break;
            case R.id.button5:
                showMultiChoiceDialog();
                break;
        }
    }

    private void showMultiChoiceDialog() {
        // 이전 선택값을 임시로 백업한다.
        final boolean[] temps = new boolean[checkedItems.length];
        System.arraycopy(checkedItems, 0, temps, 0, checkedItems.length);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("확인");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setCancelable(false);
        // 체크박스를 포함한 리스트의 등록과 이벤트 처리
        builder.setMultiChoiceItems(items, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                // 상태유지를 위한 전역변수에 사용자가 선택한 상태 반영
                checkedItems[which] = isChecked;
            }
        });

        // 긍정의 의미를 갖는 버튼
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String result = "";
                for(int i=0; i<checkedItems.length; i++){
                    if(checkedItems[i]){
                        result += items[i] + " ";
                    }
                }
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
            }
        });
        // 부정의 의미를 갖는 버튼
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 백업한 값을 되돌려 놓는다
                System.arraycopy(temps, 0, checkedItems, 0, checkedItems.length);
            }
        });
        // 설정한 정보로 창을 생성
        AlertDialog alertDialog = builder.create();
        // 창을 화면에 표시
        alertDialog.show();
    }

    /** 라디오 버튼을 포함한 리스트 다이얼로그를 표시한다. */
    private void showSingleChoiceDialog() {
        // 이전 선택값을 임시로 백업한다.
        final int temp = checkedItem;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("확인");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setCancelable(false);
        // 두번째 값인 checkedItem은 초기에 선택되어 있기 위한 위치값이다.
        // 이벤트에 의해서 checkedItem값에 사용자의 선택값이 저장되어 있기 때문에,
        // 최종적으로 사용자가 선택했던 항목값이 Dialog에서 선택되게 된다.
        builder.setSingleChoiceItems(items, checkedItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), items[which], Toast.LENGTH_SHORT).show();
                /** 전역변수에 사용자가 선택한 값을 저장 */
                checkedItem = which;
            }
        });
        // 긍정의 의미를 갖는 버튼
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), items[checkedItem], Toast.LENGTH_SHORT).show();
            }
        });
        // 부정의 의미를 갖는 버튼
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 백업한 값을 되돌려 놓는다
                checkedItem = temp;
            }
        });
        // 설정한 정보로 창을 생성
        AlertDialog alertDialog = builder.create();
        // 창을 화면에 표시
        alertDialog.show();
    }

    // 기본 알림창을 화면에 표시한다.
    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // 제목 설정
        builder.setTitle("알림");
        // 내용 설정
        builder.setMessage("알림 대화상자 입니다.");
        // 아이콘 이미지 설정
        builder.setIcon(android.R.drawable.btn_star_big_on);
        // 하드웨어 BackKey가 눌러졌을 때, 창이 닫히지 않도록 함
        builder.setCancelable(false); // 디폴트(안쓰면)는 트루
        // 확인 버튼의 추가 및 이벤트 정의
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"확인을 눌렀습니다.",Toast.LENGTH_SHORT).show();
            }
        });
        // 설정한 정보로 창을 생성
        AlertDialog alertDialog = builder.create();
        // 창을 화면에 표시
        alertDialog.show();
    }

    // 확인 알림창을 화면에 표시한다.
    private void showConfirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // 제목 설정
        builder.setTitle("알림");
        // 내용 설정
        builder.setMessage("알림 대화상자 입니다.");
        // 아이콘 이미지 설정
        builder.setIcon(R.mipmap.ic_launcher);
        // 하드웨어 BackKey가 눌러졌을 때, 창이 닫히지 않도록 함
        builder.setCancelable(false); // 디폴트(안쓰면)는 트루
        // 확인 버튼의 추가 및 이벤트 정의
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"확인을 눌렀습니다.",Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() { // 이것만 추가
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"취소를 눌렀습니다.",Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNeutralButton("보류", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"보류를 눌렀습니다.",Toast.LENGTH_SHORT).show();
            }
        });
        // 설정한 정보로 창을 생성
        AlertDialog alertDialog = builder.create();
        // 창을 화면에 표시
        alertDialog.show();
    }

    // 목록 선택창을 화면에 표시한다.
    private void showListDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // 제목 설정
        builder.setTitle("알림");
        // 내용 설정
        //ListDialog는 팝업창에 긍정버튼 대신, 리스트를 포함하는 형태.
        // 리스트 자체가 긍정의 의미를 하므로, 별도의 확인버튼은 필요없다.
        // setCancelable(false)를 설정하면, 취소버튼을 포함시켜야 선택항목 없이 창을 닫을 수 있기 때문에,
        // 부정의 의미를 갖는 버튼만 포함한 상태로 다이얼로그를 준비한다.
        // setMessage(String) 메소드는 사용하지 않는다.
        // 사용할 경우, 리스트가 표시되지 않는다.
        //builder.setMessage("알림 대화상자 입니다.");
        // 아이콘 이미지 설정
        builder.setIcon(android.R.drawable.btn_star_big_on);
        // 하드웨어 BackKey가 눌러졌을 때, 창이 닫히지 않도록 함
        builder.setCancelable(false); // 디폴트(안쓰면)는 트루
        /** 배열을 다이얼로그에 포함시키기 */
        // 다이얼로그에 표시할 리스트를 구성하기 위한 배열
        final String[] items = {"축구", "농구", "배구"};
        // setItems() 메소드에 준비한 배열과 이벤트 리스너를 설정한다.
        // 긍정 버튼의 역할
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 두번째 파라미터인 which값이 사용자가 선택한 배열의 인덱스 번호
                Toast.makeText(getApplicationContext(),items[which],Toast.LENGTH_SHORT).show();
            }
        });

        // 확인 버튼의 추가 및 이벤트 정의
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"취소를 눌렀습니다.",Toast.LENGTH_SHORT).show();
            }
        });
        // 설정한 정보로 창을 생성
        AlertDialog alertDialog = builder.create();
        // 창을 화면에 표시
        alertDialog.show();
    }
}
