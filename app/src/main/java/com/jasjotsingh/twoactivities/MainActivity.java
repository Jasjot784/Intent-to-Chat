package com.jasjotsingh.twoactivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    public static final String EXTRA_MESSAGE = "com.jasjotsingh.twoactivities";
    private EditText mMessageEditText;
    public static final int TEXT_REQUEST = 1;
    private TextView mReplyHeadTextView;
    private TextView mReplyTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMessageEditText = findViewById(R.id.editText_main);
        mReplyHeadTextView = findViewById(R.id.text_header_reply);
        mReplyTextView = findViewById(R.id.text_message_reply);
        Log.d(TAG, "-------");
        Log.d(TAG, "onCreate");

        if (savedInstanceState != null) {
            boolean isVisible =
                    savedInstanceState.getBoolean("reply_visible");

            if (isVisible) {
                mReplyHeadTextView.setVisibility(View.VISIBLE);
                mReplyTextView.setText(savedInstanceState.getString("reply_text"));
                mReplyTextView.setVisibility(View.VISIBLE);
            }
        }

    }

    public void launchSecondActivity(View view) {
        String message = mMessageEditText.getText().toString();
        Log.d(TAG, "Button Clicked: ");
        Intent intent = new Intent(MainActivity.this,SecondActivity.class);
        intent.putExtra(EXTRA_MESSAGE,message);
        startActivityForResult(intent, TEXT_REQUEST);
    }
    @Override
    public void onActivityResult(int requestCode,
                                 int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {
                String reply =
                        data.getStringExtra(SecondActivity.EXTRA_REPLY);
                mReplyHeadTextView.setVisibility(View.VISIBLE);
                mReplyTextView.setText(reply);
                mReplyTextView.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onCreate");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        if (mReplyHeadTextView.getVisibility() == View.VISIBLE) {
            outState.putBoolean("reply_visible", true);
            outState.putString("reply_text",mReplyTextView.getText().toString());
        }
    }
}