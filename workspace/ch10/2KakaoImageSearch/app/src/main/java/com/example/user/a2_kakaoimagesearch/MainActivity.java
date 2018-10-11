package com.example.user.a2_kakaoimagesearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.a2_kakaoimagesearch.adapter.ImageAdapter;
import com.example.user.a2_kakaoimagesearch.model.Image;
import com.example.user.a2_kakaoimagesearch.response.ImageResponse;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AbsListView.OnScrollListener {

    List<Image> list;
    ImageAdapter adapter;
    EditText editText;
    Button button;
    ListView listView;
    AsyncHttpClient client;
    ImageResponse response;
    LinearLayout linearLayoutFooter;    // 푸터 영역
    // 페이징 처리
    // 한 페이지에 보여질 목록의 수
    public static final int PAGE_SIZE = 20;     // 20개씩 받아옴
    // 현재 페이지
    public static int PAGE = 1;
    // 화면에 리스트의 마지막 아이템이 보여지는지 체크
    boolean lastItemVisibleFlag = false;    // 끝에 도착 시 판단할 불린변수
    // 다음 페이지 요청시에도 검색어 보전을 위한 문자열
    String keyword = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 푸터뷰
        View footerView = getLayoutInflater().inflate(R.layout.list_footer, null);
        linearLayoutFooter = (LinearLayout) footerView.findViewById(R.id.linearLayoutFooter);
        linearLayoutFooter.setVisibility(View.GONE);

        list = new ArrayList<>();
        adapter = new ImageAdapter(this, R.layout.list_item, list);
        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);
        listView = (ListView) findViewById(R.id.listView);
        listView.addFooterView(footerView);
        listView.setAdapter(adapter);
        client = new AsyncHttpClient();
        response = new ImageResponse(this, adapter, listView, linearLayoutFooter);

        button.setOnClickListener(this);
        listView.setOnScrollListener(this); // 스크롤 할 때 마다 이 함수 호출됨
    }

    private void getKakaoData(String keyword) {
        // 파라미터 정보를 저장할 수 있는 객체
        RequestParams params = new RequestParams();
        params.put("query", keyword);
        params.put("size", String.valueOf(PAGE_SIZE));
        params.put("page", String.valueOf(PAGE));
        // 헤더파일에 api 키 추가
        client.addHeader("Authorization", "KakaoAK 40152091cb4c2db97c65723ece95b283");
        // 네트워크 접속 요청
        client.get("https://dapi.kakao.com/v2/search/image", params, response);
    }

    @Override
    public void onClick(View v) {
        keyword = editText.getText().toString().trim();
        if (keyword.equals("")) {
            Toast.makeText(this, "검색어를 입력하세요", Toast.LENGTH_SHORT).show();
            return;
        }
        // 버튼을 통한 검색은 신규 검색이므로, 페이지수를 초기화하고,
        // 리스트뷰에 보여지고 있는 데이터를 삭제한다.
        PAGE = 1;
        adapter.clear();
        getKakaoData(keyword);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == SCROLL_STATE_IDLE && lastItemVisibleFlag) {   // 멈췄을 때 끝인지 판단
            if (PAGE < Image.pageable_count) {
                PAGE++;
                getKakaoData(keyword);
            }
        }
    }

    /**
     * 리스트 뷰의 스크롤이 이동하는 동안 발생한다.
     * @param view                  - 화면에 보여지는 View 객체
     * @param firstVisibleItem    - 현재 화면에 보이는 첫번째 리스트 아이템의 번호
     * @param visibleItemCount    - 현재 화면에 보이는 리스트 아이템의 개수
     * @param totalItemCount      - 리스트 항목의 전체의 개수
     */
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        lastItemVisibleFlag = (totalItemCount > 0) && (firstVisibleItem + visibleItemCount >= totalItemCount);
        // 20 > 0 && 11 + 9 > == 20
    }
}
