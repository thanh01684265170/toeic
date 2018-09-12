package hvcnbcvt_uddd.study_english.Data.Model;

public class Lesson {
    private int mId;
    private String mName;
    private String mDescription;
    private boolean mStateLesson;

    public Lesson(int id, String name, String description, boolean stateLesson) {
        mId = id;
        mName = name;
        mDescription = description;
        mStateLesson = stateLesson;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public boolean isStateLesson() {
        return mStateLesson;
    }

    public void setStateLesson(boolean stateLesson) {
        mStateLesson = stateLesson;
    }
}
