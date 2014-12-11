package com.studio.skeleton.tests;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.ViewAsserts;
import android.test.suitebuilder.annotation.MediumTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.studio.skeleton.MainActivity;
import com.studio.skeleton.R;

public class MainActivityTest extends
        ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity mMainActivity;
    private EditText mMainEditText;
    private Button mSendButton;
    private TextView mInfoTextView;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        setActivityInitialTouchMode(true);

        mMainActivity = getActivity();
        mMainEditText = (EditText) mMainActivity
                .findViewById(R.id.edit_message);
        mSendButton = (Button) mMainActivity.findViewById(R.id.button_send);
        mInfoTextView = (TextView) mMainActivity
                .findViewById(R.id.info_message);
    }

    public void testPreConditions() {
        assertNotNull("mMainActivity is null", mMainActivity);
        assertNotNull("mMainEditText is null", mMainEditText);
        assertNotNull("mSendButton is null", mSendButton);
        assertNotNull("mInfoTextView is null", mInfoTextView);
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

//    @MediumTest
//    public void testSendButton_layout() {
//        final View decorView = mMainActivity.getWindow().getDecorView();
//
//        ViewAsserts.assertOnScreen(decorView, mSendButton);
//
//        final ViewGroup.LayoutParams layoutParams = mSendButton
//                .getLayoutParams();
//        assertNotNull(layoutParams);
//        assertEquals(layoutParams.width,
//                WindowManager.LayoutParams.WRAP_CONTENT);
//        assertEquals(layoutParams.height,
//                WindowManager.LayoutParams.WRAP_CONTENT);
//    }

    @MediumTest
    public void testInfoTextView_layout() {
        final View decorView = mMainActivity.getWindow().getDecorView();
        ViewAsserts.assertOnScreen(decorView, mInfoTextView);
        assertTrue(View.GONE == mInfoTextView.getVisibility());
    }

    @MediumTest
    // Improve this on navigation to display message view. Right now this will
    // cause testSendButton_layout failed to run as the view has been navigated
    // display message view.
    public void testSendButton_clickButtonAndExpectInfoText() {
        String expectedInfoText = mMainEditText.getText().toString();
        TouchUtils.clickView(this, mSendButton);
        assertTrue(View.VISIBLE == mInfoTextView.getVisibility());
        assertEquals(expectedInfoText, mInfoTextView.getText());
    }
}