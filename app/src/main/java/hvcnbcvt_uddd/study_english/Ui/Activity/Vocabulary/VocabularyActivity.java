package hvcnbcvt_uddd.study_english.Ui.Activity.Vocabulary;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import hvcnbcvt_uddd.study_english.Data.Local.WordController;
import hvcnbcvt_uddd.study_english.Data.Model.Lesson;
import hvcnbcvt_uddd.study_english.Data.Model.Word;
import hvcnbcvt_uddd.study_english.R;
import hvcnbcvt_uddd.study_english.Ui.Adapter.LessonAdapter;

public class VocabularyActivity extends AppCompatActivity {

    RecyclerView mRecyclerLesson;
    FloatingActionButton mFbNexxt;

    private ArrayList<Lesson> mLessons;
    private LessonAdapter mLessonAdapter;
    private WordController mWordController;
    private ArrayList<Word> mWords;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vocabulary_activity_lession);
        initData();
        initView();
    }

    private void initView() {
        mFbNexxt = (FloatingActionButton) findViewById(R.id.fb_next);
        mRecyclerLesson = (RecyclerView) findViewById(R.id.recycler_lessons);
        mLessonAdapter = new LessonAdapter(mLessons, this);
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerLesson.setLayoutManager(layoutManager);
        mRecyclerLesson.setAdapter(mLessonAdapter);

        mFbNexxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (Lesson lesson : mLessons) {
                    if (lesson.isStateLesson()){
                        mWords.addAll(mWordController.getWord(lesson.getId()));
                    }
                }
                Intent intentSlide = new Intent(getApplicationContext(), SlideActivity.class);
                intentSlide.putExtra("LIST_WORD", mWords);
                startActivity(intentSlide);
            }
        });
    }

    private void initData() {
        mWordController = new WordController(this);
        mWords = new ArrayList<>();
        mLessons = new ArrayList<>();
        mLessons.add(new Lesson(1, "Bài 1", "Contracts",false));
        mLessons.add(new Lesson(2, "Bài 2", "Marketing",false));
        mLessons.add(new Lesson(3, "Bài 3", "Warranties",false));
        mLessons.add(new Lesson(4, "Bài 4", "Business Planning",false));
        mLessons.add(new Lesson(5, "Bài 5", "Conferences",false));
        mLessons.add(new Lesson(6, "Bài 6", "Computers",false));
        mLessons.add(new Lesson(7, "Bài 7", "Office Technology",false));
        mLessons.add(new Lesson(8, "Bài 8", "Office Procedures",false));
        mLessons.add(new Lesson(9, "Bài 9", "Electronics",false));
        mLessons.add(new Lesson(10, "Bài 10", "Correspondence",false));
        mLessons.add(new Lesson(11, "Bài 11", "Job Advertising and Recruiting",false));
        mLessons.add(new Lesson(12, "Bài 12", "Applying and Interviewing",false));
        mLessons.add(new Lesson(13, "Bài 13", "Hiring and Training",false));
        mLessons.add(new Lesson(14, "Bài 14", "Salaries and Benefits",false));
        mLessons.add(new Lesson(15, "Bài 15", "Promotions, Pensions and Awards",false));
        mLessons.add(new Lesson(16, "Bài 16", "Shopping",false));
        mLessons.add(new Lesson(17, "Bài 17", "Ordering Supplies",false));
        mLessons.add(new Lesson(18, "Bài 18", "Shipping",false));
        mLessons.add(new Lesson(19, "Bài 19", "Invoices",false));
        mLessons.add(new Lesson(20, "Bài 20", "Inventory",false));
    }
}
