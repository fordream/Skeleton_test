package com.studio.skeleton.unittests;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.test.suitebuilder.annotation.MediumTest;
import android.widget.Button;

import com.studio.skeleton.MainActivity;
import com.studio.skeleton.R;

public class LaunchActivityTest extends ActivityUnitTestCase<MainActivity> {

    private Intent mLaunchIntent;

    public LaunchActivityTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mLaunchIntent = new Intent(getInstrumentation().getTargetContext(),
                MainActivity.class);
    }

    @MediumTest
    public void testPreconditions() {
        startActivity(mLaunchIntent, null, null);
        final Button sendButton = (Button) getActivity().findViewById(
                R.id.button_send);
        assertNotNull("mLaunchActivity is null", getActivity());
        assertNotNull("sendButton is null", sendButton);
    }

    @MediumTest
    public void testNextActivityWasLaunchedWithIntent() {
        startActivity(mLaunchIntent, null, null);
        final Button sendButton = (Button) getActivity().findViewById(
                R.id.button_send);
        sendButton.performClick();

        final Intent launchIntent = getStartedActivityIntent();
        assertNotNull("Intent was null", launchIntent);

        final String payload = launchIntent
                .getStringExtra(MainActivity.EXTRA_MESSAGE);
        assertEquals("Payload is empty", "", payload);
    }
}
