package com.development.transportesejecutivos.wizard.models;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.text.TextUtils;

import com.development.transportesejecutivos.fragments.DestinyFragment;
import com.development.transportesejecutivos.models.User;
import com.development.transportesejecutivos.wizard.ui.SingleChoiceFragment;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A page offering the user a number of mutually exclusive choices.
 */
public class SingleFixedChoicePage extends Page {
    protected Context context;
    protected User user;
    protected ArrayList<String> mChoices = new ArrayList<String>();

    public SingleFixedChoicePage(ModelCallbacks callbacks, String title, Context context) {
        super(callbacks, title);
        this.context = context;
    }

    @Override
    public Fragment createFragment() {
        return DestinyFragment.newInstance(context);
        //return SingleChoiceFragment.create(getKey());
    }

    public String getOptionAt(int position) {
        return mChoices.get(position);
    }

    public int getOptionCount() {
        return mChoices.size();
    }

    @Override
    public void getReviewItems(ArrayList<ReviewItem> dest) {
        dest.add(new ReviewItem(getTitle(), mData.getString(SIMPLE_DATA_KEY), getKey()));
    }

    @Override
    public boolean isCompleted() {
        return !TextUtils.isEmpty(mData.getString(SIMPLE_DATA_KEY));
    }

    public SingleFixedChoicePage setChoices(String... choices) {
        mChoices.addAll(Arrays.asList(choices));
        return this;
    }

    public SingleFixedChoicePage setValue(String value) {
        mData.putString(SIMPLE_DATA_KEY, value);
        return this;
    }
}
