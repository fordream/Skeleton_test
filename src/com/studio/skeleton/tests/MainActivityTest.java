package com.studio.skeleton.tests;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import com.studio.skeleton.MainActivity;
import com.studio.skeleton.R;

public class MainActivityTest extends
        ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity mMainActivity;
    private EditText mMainEditText;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mMainActivity = getActivity();
        mMainEditText = (EditText) mMainActivity
                .findViewById(R.id.edit_message);
    }

    public void testPreConditions() {
        assertNotNull("mMainActivity is null", mMainActivity);
        assertNotNull("mMainEditText is null", mMainEditText);
    }

    public void testMainEditText_hintText() {
        final String expected = mMainActivity.getString(R.string.edit_message);
        final String actual = mMainEditText.getHint().toString();
        assertEquals(expected, actual);
    }

    public void testMainEditText_inputText() {
        final String expected = "";
        final String actual = mMainEditText.getText().toString();
        assertEquals(expected, actual);
    }
}