# HttpFileRequest
Android simple form data uploading library
<br/>
## add these lines to build.gradle
```
repositories {
    maven {
        url 'https://dl.bintray.com/farhanahmed95/maven/'
    }
}
dependencies {
    compile('com.github.farhanahmed95:httpfilerequest:2.0.2@aar') {
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

### Setting up request
```
new HttpFileRequest()
    .url("< URL >")
    .add("file name", fileObject1)
    .add...
    .add...
    .setParams(new HashMap<String, String>())
    .listener(new ListenerAdapter() {
        @Override
        public void onResponse(Response response) {
            Log.d("onResponse", response.body);
        }

        @Override
        public void onProgress(int progress) {
            Log.d("onProgress", progress + "");
        }
    })
    .run();
```
<br/>
<br/>
<br/>
Copyright 2016 Farhan Ahmed

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
