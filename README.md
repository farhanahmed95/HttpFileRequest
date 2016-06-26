# HttpFileRequest
Android File uploading library
# Example
### add these lines to build.gradle
```
repositories {
    maven {
        url 'https://dl.bintray.com/farhanahmed95/maven/'
    }
}
dependencies {
    compile 'com.github.farhanahmed95:httpfilerequest:0.0.4@aar'
}

```
### add permissions to AndroidManifest.xml
```
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
```
#### Basic single file request
<pre>

        File file = new File(Environment.getExternalStorageDirectory()+"/"+Environment.DIRECTORY_DOWNLOADS+"/user-image.png");
        HttpFileRequest request = new HttpFileRequest
                            (this,                  // context
                            "YOUR URL",             // url
                            file,                   // file instance holding path of file.
                            "user-image",   // name attribute like in HTML input name="user-image" 
                            new HttpFileRequest.Listener() {
            @Override
            public void onResponse(Response response) {
                Log.d("FILE REQ",response.body);
            }

            @Override
            public void onError(Exception e) {
                Log.e("FILE REQ",e.getMessage() + " "+e.getClass().getSimpleName());

            }
        }){
            @Override
            public Map<String, String> getParams() {        // you can send params with file request
                HashMap<String,String> map = new HashMap<>(); 
                map.put("desc","my notes");
                return map;
            }
        };

        request.exec();
</pre>
#### Multiple file request
<pre>
        File file [] = new File[2];
        file[0] = new File(Environment.getExternalStorageDirectory()+"/"+Environment.DIRECTORY_DOWNLOADS+"/W04.pdf");
        file[1] = new File(Environment.getExternalStorageDirectory()+"/"+Environment.DIRECTORY_DOWNLOADS+"/W05.pdf");

        MultipleHttpFileRequest request = new MultipleHttpFileRequest(this, "URL", "NAME","ARRAY OF FILE OBJECTS", new MultipleHttpFileRequest.Listener() {
            @Override
            public void onResponse(Response response) {
                Log.d("FILE REQ",response.body);
            }

            @Override
            public void onError(Exception e) {
                Log.e("FILE REQ",e.getMessage() + " "+e.getClass().getSimpleName());

            }
        }){
            @Override
            public Map<String, String> getParams() {        // you can send params with file request
                HashMap<String,String> map = new HashMap<>(); 
                map.put("desc","my notes");
                return map;
            }
        };

        request.exec();
</pre>
