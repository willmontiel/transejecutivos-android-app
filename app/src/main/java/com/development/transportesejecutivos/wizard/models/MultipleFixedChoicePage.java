package com.development.transportesejecutivos.wizard.models;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.development.transportesejecutivos.wizard.ui.MultipleChoiceFragment;

import java.util.ArrayList;

/**
 * A page offering the user a number of non-mutually exclusive choices.
 */
public class MultipleFixedChoicePage extends SingleFixedChoicePage {
    protected Context context;
    public MultipleFixedChoicePage(ModelCallbacks callbacks, String title, Context context) {
        super(callbacks, title, context);
        this.context = context;
    }

    @Override
    public Fragment createFragment() {
        return MultipleChoiceFragment.create(getKey());
    }

    @Override
    public void getReviewItems(ArrayList<ReviewItem> dest) {
        StringBuilder sb = new StringBuilder();

        ArrayList<String> selections = mData.getStringArrayList(Page.SIMPLE_DATA_KEY);
        if (selections != null && selections.size() > 0) {
            for (String selection : selections) {
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                sb.append(selection);
            }
        }

        dest.add(new ReviewItem(getTitle(), sb.toString(), getKey()));
    }

    @Override
    public boolean isCompleted() {
        ArrayList<String> selections = mData.getStringArrayList(Page.SIMPLE_DATA_KEY);
        return selections != null && selections.size() > 0;
    }
}