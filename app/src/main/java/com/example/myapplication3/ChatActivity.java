package com.example.myapplication3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class ChatActivity extends AppCompatActivity {

    private static String TAG = "ChatLog";

    private static String host = "https://samsung-marina.azurewebsites.net";

    private static String endPoint = "0b5123b6-9deb-42d2-83df-ca41e46b6b55";

    private static String kb = "34b1fd2c-b996-40d9-83f9-045e3d23cfbd";

    private static String method = "/qnamaker/knowledgebases/" + kb + "/generateAnswer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
    }

    public static String Post (URL url, String content) throws Exception {
        Log.w(TAG, "Post method started");

        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Content-Length", content.length() + "");
        connection.setRequestProperty("Authorization", "EndpointKey " + endPoint);
        connection.setDoOutput(true);

        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        byte[] encoded_content = content.getBytes("UTF-8");
        wr.write(encoded_content, 0, encoded_content.length);
        wr.flush();
        wr.close();

        StringBuilder response = new StringBuilder ();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));

        String line;
        while ((line = in.readLine()) != null) {
            response.append(line);
        }
        in.close();

        Log.w(TAG, response.toString());
        return response.toString();
    }

    public static String GetAnswers (String question) throws Exception {
        URL url = new URL(host + method);
        Log.w(TAG, "Calling " + url.toString() + ".");
        return Post(url, question);
    }


    public void SendQuestion(View view) {
        Log.w(TAG, "sendQuestion clicked");
        TextView answerTextView = findViewById(R.id.chat_answer);
        EditText questionEditText = findViewById(R.id.chat_question);

        try {
            answerTextView.setText(GetAnswers(questionEditText.getText().toString()));
        } catch (Exception e) {
        }
    }

}
