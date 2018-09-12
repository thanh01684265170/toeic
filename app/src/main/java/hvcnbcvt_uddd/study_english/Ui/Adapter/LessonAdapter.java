package hvcnbcvt_uddd.study_english.Ui.Adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hvcnbcvt_uddd.study_english.Data.Model.Lesson;
import hvcnbcvt_uddd.study_english.R;

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.ViewHolder> {
    private List<Lesson> mLessons;
    private Context mContext;

    public LessonAdapter(List<Lesson> lessons, Context context) {
        mLessons = lessons;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lesson, parent, false);
        return new ViewHolder(view, mLessons);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Lesson lesson = mLessons.get(position);
        holder.bindData(lesson);
    }

    @Override
    public int getItemCount() {
        return mLessons != null ? mLessons.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder
            implements CompoundButton.OnCheckedChangeListener {
        @BindView(R.id.tv_title_lesson_vocabulary)
        TextView mTitleLesson;
        @BindView(R.id.tv_description_lesson_vocabulary)
        TextView mDescriptionLesson;
        @BindView(R.id.checkbox_lesson)
        CheckBox mCheckBox;

        private List<Lesson> mLessons;

        public ViewHolder(View itemView, List<Lesson> lessons) {
            super(itemView);
            this.mLessons = lessons;
            ButterKnife.bind(this, itemView);
            setOnCheckbox();
        }

        private void setOnCheckbox(){
            mCheckBox.setOnCheckedChangeListener(this);
        }

        public void bindData(Lesson lesson) {
            mTitleLesson.setText(lesson.getName());
            mDescriptionLesson.setText(lesson.getDescription());
        }

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            mLessons.get(getLayoutPosition()).setStateLesson(b);
        }
    }
}
