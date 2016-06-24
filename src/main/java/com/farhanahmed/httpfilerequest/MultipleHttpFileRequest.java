package com.farhanahmed.httpfilerequest;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;

import org.apache.commons.io.FilenameUtils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by farhanahmed on 24/06/16.
 */
public class MultipleHttpFileRequest {
    private String url;
    private File[] files;
    private Listener listener;
    private HttpURLConnection httpURLConnection;
    private String [] names;
    private String attachmentName;
    private Context context;
    private Activity activity;
    private AsyncTask<Void, Exception, Response> fileUploadTask;

    public MultipleHttpFileRequest(Context context, final String url, final File []files, String attachmentName, final Listener listener) {
        this.url = url;
        this.files = files;
        this.listener = listener;
        this.context = context;
        this.activity = (Activity) context;
        this.attachmentName = attachmentName;
        {
            names = new String[files.length];
        }
        fileUploadTask = new AsyncTask<Void, Exception, Response>() {
            @Override
            protected Response doInBackground(Void... params) {
                try {
                    httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setConnectTimeout(10000);
                    httpURLConnection.setReadTimeout(10000);

                    String crlf = "\r\n";
                    String twoHyphens = "--";
                    String boundary = "*****";
                    //httpURLConnection.addRequestProperty("attachmentName", MultipleHttpFileRequest.this.name);
                    httpURLConnection.addRequestProperty("attachmentFileName", MultipleHttpFileRequest.this.attachmentName);
                    httpURLConnection.addRequestProperty("Connection", "keep-alive");
                    httpURLConnection.addRequestProperty("Control-cache", "no-cache");
                    httpURLConnection.addRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);

                    httpURLConnection.setDoOutput(true);

                    DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());

                    for (int i = 0;i<files.length;i++)
                    {
                        names[i] = FilenameUtils.getName(files[i].getAbsolutePath());

                        dataOutputStream.writeBytes(twoHyphens + boundary + crlf);
                        dataOutputStream.writeBytes("Content-Disposition:form-data;name=\"" + MultipleHttpFileRequest.this.attachmentName + "\";filename=\"" + names[i] + "\"" + crlf);
                        dataOutputStream.writeBytes(crlf);

                        FileInputStream fileInputStream = new FileInputStream(files[i]);
                        byte[] data = new byte[(int) files[i].length()];
                        //Log.i("FILR",data.length+"");
                        fileInputStream.read(data);

                        dataOutputStream.write(data);

                        dataOutputStream.writeBytes(crlf);
                        dataOutputStream.writeBytes(twoHyphens + boundary + twoHyphens + crlf);
                    }




                    httpURLConnection.connect();


                    Response response = null;
                    BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                    String line = "";
                    StringBuffer buffer = new StringBuffer();
                    while ((line = reader.readLine()) != null) {
                        buffer.append(line);
                    }

                    reader.close();

                    response = new Response(httpURLConnection.getResponseCode(), httpURLConnection.getResponseMessage(), buffer.toString());
                    return response;
                } catch (final Exception e) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listener.onError(e);
                        }
                    });
                }
                return null;
            }

            @Override
            protected void onPostExecute(Response response) {
                if (response != null)
                    listener.onResponse(response);
            }
        };


    }

    public void exec() {
        fileUploadTask.execute();
    }


    public interface Listener {
        void onResponse(Response response);

        void onError(Exception e);
    }
}
