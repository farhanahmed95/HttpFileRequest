# HttpFileRequest
Android File uploading library
<br/>
## add these lines to build.gradle
```
repositories {
    maven {
        url 'https://dl.bintray.com/farhanahmed95/maven/'
    }
}
dependencies {
    compile('com.github.farhanahmed95:httpfilerequest:0.1.8@aar') {
        transitive = true;
    }
}

```
## add permissions to AndroidManifest.xml
```
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
```
## Making request
### Creating FileAttachment instance or array of FileAttachment 
```
File imageFile = ...;
FileAttachment imageAttachment  = new FileAttachment("image",imageFile);
```
### Setting up request
```
HttpFileRequest fileRequest = new HttpFileRequest(this,"< URL >",new HttpFileRequest.Listener() {
            @Override
            public void onResponse(final Response response) {
                
            }

            @Override
            public void onErrorResponse(Response response) {

            }

            @Override
            public void onError(Exception e) {
            }

            @Override
            public void onProgress(int progress) {

            }
        },imageAttachment ) {

            @Override
            public Map<String, String> getParams() {
                HashMap<String, String> params = new HashMap<>();
                params.put("...","...")
                ...
                ...
                return params;
            }

            @Override
            protected TimeoutPolicy getTimeoutPolicy() {
                return new TimeoutPolicy() {
                    @Override
                    public int connectTimeout() {
                        return 20000;
                    }

                    @Override
                    public int readTimeout() {
                        return 20000;
                    }
                };
            }
        };
```
### Executing request
```
fileRequest.exec();

```
