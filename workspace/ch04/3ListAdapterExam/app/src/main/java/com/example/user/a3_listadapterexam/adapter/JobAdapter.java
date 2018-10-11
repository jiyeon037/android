package com.example.user.a3_listadapterexam.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.a3_listadapterexam.R;
import com.example.user.a3_listadapterexam.model.Job;

import java.util.List;

/** ArrayAdapter를 상속받는 사용자 정의 클래스
 * => ListView 클래스와 소통하는 역할
 * => List 클래스 관리
 */


public class JobAdapter extends ArrayAdapter<Job> {
    // 화면을 관리하는 객체에 대한 참조 객체 선언
    Activity activity;
    // 한 줄의 모양을 담당하기 위한 Layout XML파일을 의미하는 리소스 아이디값
    int resource;

    /**
     * MainActivity로부터 전달받는다.
     * @param context - Activity 객체
     * @param resource - list_item.xml 파일의 id
     * @param objects - List 객체
     */

    public JobAdapter(@NonNull Context context, int resource, @NonNull List<Job> objects) {
        super(context, resource, objects);
        activity = (Activity)context;
        this.resource = resource;
    }

    /**
     * ListView에 의해서 호출되는 메소드
     * List안에서 한 줄에 대한 Beans를 추출하여 레이아웃에 매핑한 후,
     * ListView에게 리턴해 준다.
     * @param position - 리스트의 몇번째 항목인지를 의미하는 인덱스 값
     * @param convertView - 앞서 전달받았던 레이아웃 객체. 최초 호출시에는 null이지만,
     *                    이후 호출될 때마다 이전에 리턴받았던 View를 이 파라미터를 통해서 되돌려 준다.
     * @param parent - ListView (사용안함)
     * @return View - 한 줄의 모양을 정의한 레이아웃에 Beans의 데이터를 매핑하여 리턴.
     */

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;
        // 최초 호출시에는 null이므로, 생성자의 파라미터로 전달받은 리소스 아이디값을 사용하여 레이아웃을 생성한다.
        if(itemView == null){
            // 1. 한줄화면 만들기
            // list_item.xml 파일에 설정된 대로 클래스를 만들어서,
            // 전체를 View 클래스로 리턴
            itemView = activity.getLayoutInflater().inflate(resource,null);
        }
        // 2. 전달받은 List에서 데이터를 하나 꺼낸다.
        Job item = getItem(position);
        // 3. 데이터가 존재한다면, itemView 객체에 포함된 컴포넌트들에게 데이터를 출력한다.
        // itemView = 컴포넌트 클래스 + 데이터
        if(item != null){
            ImageView imageView = (ImageView)itemView.findViewById(R.id.imageView);
            TextView textView1 = (TextView)itemView.findViewById(R.id.textView1);
            TextView textView2 = (TextView)itemView.findViewById(R.id.textView2);

            imageView.setImageResource(item.getImage());
            textView1.setText(item.getTitle());
            textView2.setText(item.getDescription());
        }
        // 4. 다시 리스트 뷰에게 되돌려 준다.
        return itemView;
    }
}
