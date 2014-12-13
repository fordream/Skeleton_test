package com.studio.skeleton.unittests;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.test.suitebuilder.annotation.MediumTest;
import android.widget.Button;

import com.studio.skeleton.MainActivity;
import com.studio.skeleton.R;

public class LaunchActivityTest extends ActivityUnitTestCase<MainActivity> {

    private Intent mLaunchIntent;
    private Button mSendButton;

    public LaunchActivityTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mLaunchIntent = new Intent(getInstrumentation().getTargetContext(),
                MainActivity.class);
        startActivity(mLaunchIntent, null, null);
        mSendButton = (Button) getActivity().findViewById(R.id.button_send);
    }

    @MediumTest
    public void testPreconditions() {
        assertNotNull("mLaunchActivity is null", getActivity());
        assertNotNull("sendButton is null", mSendButton);
    }

    @MediumTest
    public void testNextActivityWasLaunchedWithIntent() {
        mSendButton.performClick();

        final Intent launchIntent = getStartedActivityIntent();
        assertNotNull("Intent was null", launchIntent);

        final String payload = launchIntent
                .getStringExtra(MainActivity.EXTRA_MESSAGE);
        assertEquals("Payload is empty", "", payload);
    }
}
