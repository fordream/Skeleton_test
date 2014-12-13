package com.studio.skeleton.tests;

import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.test.suitebuilder.annotation.MediumTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.studio.skeleton.MainActivity;
import com.studio.skeleton.R;

public class MainActivityTest extends
        ActivityInstrumentationTestCase2<MainActivity> {

    private static final String TEST_MESSAGE = "Hello Receiver";

    private MainActivity mMainActivity;
    private EditText mMainEditText;
    private Button mSendButton;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mMainActivity = getActivity();
        mMainEditText = (EditText) mMainActivity
                .findViewById(R.id.edit_message);
        mSendButton = (Button) mMainActivity.findViewById(R.id.button_send);
    }

    public void testPreConditions() {
        assertNotNull("mMainActivity is null", mMainActivity);
        assertNotNull("mMainEditText is null", mMainEditText);
        assertNotNull("mSendButton is null", mSendButton);
    }

    public void testMainEditText_hintText() {
        final String expected = mMainActivity.getString(R.string.edit_message);
        final String actual = mMainEditText.getHint().toString();
        assertEquals(expected, actual);
    }

    public void testMainEditText_inputText() {
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                mMainEditText.requestFocus();
            }
        });
        getInstrumentation().waitForIdleSync();
        getInstrumentation().sendStringSync(TEST_MESSAGE);
        getInstrumentation().waitForIdleSync();

        final String expected = TEST_MESSAGE;
        final String actual = mMainEditText.getText().toString();
        assertEquals(expected, actual);
    }

    @MediumTest
    public void testSendButton_layout() {
        final View decorView = mMainActivity.getWindow().getDecorView();

        ViewAsserts.assertOnScreen(decorView, mSendButton);

        final ViewGroup.LayoutParams layoutParams = mSendButton
                .getLayoutParams();
        assertNotNull(layoutParams);
        assertEquals(layoutParams.width,
                WindowManager.LayoutParams.WRAP_CONTENT);
        assertEquals(layoutParams.height,
                WindowManager.LayoutParams.WRAP_CONTENT);
    }
}