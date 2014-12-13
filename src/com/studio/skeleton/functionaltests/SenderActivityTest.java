package com.studio.skeleton.functionaltests;

import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.suitebuilder.annotation.MediumTest;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.studio.skeleton.DisplayMessageActivity;
import com.studio.skeleton.MainActivity;
import com.studio.skeleton.R;

public class SenderActivityTest extends
        ActivityInstrumentationTestCase2<MainActivity> {

    private static final int TIMEOUT_IN_MS = 5000;
    private static final String TEST_MESSAGE = "Hello Receiver";

    private MainActivity mMainActivity;
    private EditText mMainEditText;
    private Button mSendButton;

    public SenderActivityTest() {
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
    }

    @MediumTest
    public void testSendMessageToReceiverActivity() {
        // set up an ActivityMonitor
        ActivityMonitor receiverActivityMonitor = getInstrumentation()
                .addMonitor(DisplayMessageActivity.class.getName(), null, false);

        // send string input value
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                mMainEditText.requestFocus();
            }
        });
        getInstrumentation().waitForIdleSync();
        getInstrumentation().sendStringSync(TEST_MESSAGE);
        getInstrumentation().waitForIdleSync();

        // validate that DisplayMessageActivity is started
        TouchUtils.clickView(this, mSendButton);
        DisplayMessageActivity receiverActivity = (DisplayMessageActivity) receiverActivityMonitor
                .waitForActivityWithTimeout(TIMEOUT_IN_MS);
        assertNotNull("DisplayMessageActivity is null", receiverActivity);
        assertEquals("Monitor for DisplayMessageActivity has not been called",
                1, receiverActivityMonitor.getHits());
        assertEquals("Activity is of wrong type", DisplayMessageActivity.class,
                receiverActivity.getClass());

        // validate that correct message is received
        TextView displayText = (TextView) receiverActivity
                .findViewById(R.id.display_message);
        assertEquals(TEST_MESSAGE, displayText.getText().toString());
        receiverActivity.finish();

        // remove the ActivityMonitor
        getInstrumentation().removeMonitor(receiverActivityMonitor);
    }
}