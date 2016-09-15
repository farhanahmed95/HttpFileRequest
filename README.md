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
<br/>
<br/>
<br/>
Copyright 2010 Farhan Ahmed

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
