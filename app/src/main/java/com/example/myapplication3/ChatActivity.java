package com.example.myapplication3;

import android.content.Intent;
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
import android.os.AsyncTask;

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

        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
      //  connection.setRequestProperty("Content-Length", content.length() + "");
        connection.setRequestProperty("Authorization", "EndpointKey " + endPoint);
        connection.setDoOutput(true);
        Log.w(TAG, "connection is ready");

        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        byte[] encoded_content = content.getBytes("UTF-8");
        wr.write(encoded_content, 0, encoded_content.length);
        wr.flush();
        wr.close();
        Log.w(TAG, "sending question is done");

        StringBuilder response = new StringBuilder();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));

        String line;
        while ((line = in.readLine()) != null) {
            response.append(line);
            Log.w("ResponseLog", line);
        }
        in.close();


        //полное убожество
        String answer = response.substring(response.indexOf("answer") + 7, response.indexOf("score"));
        answer = answer.substring(answer.indexOf("answer") + 9);
        answer = answer.split(",")[0];
        answer = answer.substring(0, answer.length() - 1);
        Log.w(TAG, answer);
        return answer;
    }

    public static String GetAnswers (String question) throws Exception {
        URL url = new URL(host + method);
        Log.w(TAG, "Calling " + url.toString());
        Log.w(TAG, question);
        return Post(url, question);
    }


    public void SendQuestion(View view) {
        Log.w(TAG, "sendQuestion clicked");
        TextView answerTextView = findViewById(R.id.chat_answer);
        EditText questionEditText = findViewById(R.id.chat_question);
        String question = "{ 'question' : '" + questionEditText.getText().toString() + "', 'top' : 3 }";

        try {
            new RequestTask().execute(question);
        } catch (Exception e) {
            questionEditText.setText("g"+e.toString());
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), RightLeftActivity.class);
        startActivity(intent);
        finish();
    }
    public class RequestTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {

            Log.w(TAG, "RequestTask started");
            try {
                String ans = GetAnswers(params[0]);
                Log.w(TAG, ans);
                return ans;
            } catch (Exception e) {
                Log.i("MyTag","HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH"+e.toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(String res) {
            TextView answerTextView = findViewById(R.id.chat_answer);
            answerTextView.setText(res);
            EditText questionEditText = findViewById(R.id.chat_question);
            questionEditText.setText("");
            super.onPostExecute(res);

        }
    }
}
