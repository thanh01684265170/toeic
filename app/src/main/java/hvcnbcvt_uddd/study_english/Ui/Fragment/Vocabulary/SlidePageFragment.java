package hvcnbcvt_uddd.study_english.Ui.Fragment.Vocabulary;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.LongDef;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

import de.greenrobot.event.EventBus;
import hvcnbcvt_uddd.study_english.Data.Model.CheckPage;
import hvcnbcvt_uddd.study_english.Data.Model.Word;
import hvcnbcvt_uddd.study_english.R;
import hvcnbcvt_uddd.study_english.Ui.Activity.Vocabulary.SlideActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class SlidePageFragment extends Fragment {

    public static final String ARG_PAGE = "page";
    public static final String ARG_CHECK_ANSWER = "checkAnswer";
    public static final String ARG_CHECK_SWITCH = "isChecked";

    private int mPageNumber; // vị trí trang hiện tại
    public int checkAnswer; // kiểm tra xem đã nộp bài hay chưa
    public int isChecked; //set switch

    ArrayList<Word> arr_word;
    CheckPage checkPage;

    TextView tv_num, tv_question;
    RadioGroup rad_group;
    RadioButton rad_a, rad_b, rad_c;
    Switch sw_auto_next;


    public SlidePageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.vocabulary_fragment_slide, container, false);
        tv_num = (TextView) rootView.findViewById(R.id.tv_number_vocabulary);
        tv_question = (TextView) rootView.findViewById(R.id.tv_question_vocabulary);
        rad_a = (RadioButton) rootView.findViewById(R.id.rad_a_vocabulary);
        rad_b = (RadioButton) rootView.findViewById(R.id.rad_b_vocabulary);
        rad_c = (RadioButton) rootView.findViewById(R.id.rad_c_vocabulary);
        rad_group = (RadioGroup) rootView.findViewById(R.id.radGroup);
        sw_auto_next = (Switch) rootView.findViewById(R.id.sw_auto_next);
        if(isChecked == 0) {
            sw_auto_next.setChecked(false);
        } else {
            sw_auto_next.setChecked(true);
        }
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        arr_word = new ArrayList<Word>();
        SlideActivity slideActivity = (SlideActivity) getActivity();
        arr_word = slideActivity.getData();
        mPageNumber = getArguments().getInt(ARG_PAGE);
        checkAnswer = getArguments().getInt(ARG_CHECK_ANSWER);
        isChecked = getArguments().getInt(ARG_CHECK_SWITCH);
        Log.d("bug","nhận được:  "+isChecked);
    }

    public static SlidePageFragment create(int pageNumber, int checkAnswer, int swIsChecked) {
        SlidePageFragment slidePageFragment = new SlidePageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE,pageNumber);
        args.putInt(ARG_CHECK_ANSWER,checkAnswer);
        args.putInt(ARG_CHECK_SWITCH,swIsChecked);
        slidePageFragment.setArguments(args);
        return slidePageFragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("bug","setup "+ isChecked);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tv_num.setText("Câu " + (mPageNumber+1));
        tv_question.setText(arr_word.get(mPageNumber).getWordEnglish() + " có nghĩa là?");
        rad_a.setText(getItem(mPageNumber).getQuestionA());
        rad_b.setText(getItem(mPageNumber).getQuestionB());
        rad_c.setText(getItem(mPageNumber).getQuestionC());

        if(checkAnswer != 0){
            rad_a.setClickable(false);
            rad_b.setClickable(false);
            rad_c.setClickable(false);
            getCheckAns(getItem(mPageNumber).getWordVietnamese().toString());
            sw_auto_next.setChecked(false);
        }

        rad_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                getItem(mPageNumber).setmResult(getChoice(i));
                if(!getItem(mPageNumber).getWordVietnamese().equals(getItem(mPageNumber).getmResult())) {
                    Toast.makeText(getActivity(),"Sai rồi suy nghĩ lại đi",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(),"Chuẩn đó", Toast.LENGTH_SHORT).show();
                    if(sw_auto_next.isChecked())
                    {
                         checkPage = new CheckPage(1);
                        Log.d("bug","trả về "+1);
                    }
                    else
                    {
                        checkPage = new CheckPage(0);
                        Log.d("bug","trả về "+0);
                    }
                    EventBus.getDefault().postSticky(checkPage);
                }
            }
        });
    }

    public Word getItem(int position){
        return arr_word.get(position);
    }
    //Lấy kết quả
    private String getChoice(int ID)
    {
        if(ID == R.id.rad_a_vocabulary){
            return getItem(mPageNumber).getQuestionA().toString();
        }else if(ID == R.id.rad_b_vocabulary){
            return getItem(mPageNumber).getQuestionB().toString();
        }else if(ID == R.id.rad_c_vocabulary){
            return getItem(mPageNumber).getQuestionC().toString();
        }else return "";
    }


    //Tô background cho đáp án đúng
    private void getCheckAns(String ans){
        if(ans.equals(rad_a.getText().toString()) == true){
            rad_a.setBackgroundColor(Color.RED);
        }else if(ans.equals(rad_b.getText().toString()) == true){
            rad_b.setBackgroundColor(Color.RED);
        }else if(ans.equals(rad_c.getText().toString()) == true){
            rad_c.setBackgroundColor(Color.RED);
        }
    }
}
