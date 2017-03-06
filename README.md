# State Bundle

A non-Android (albeit Parcelable) replacement for `android.os.Bundle`.

The following types are NOT supported:

- `String[]`
- `CharSequence[]`
- `Parcelable[]`
- `Size`
- `SizeF`
- `IBinder`

Please note that `ArrayList<T>` works instead of arrays, so `new ArrayList<>(Arrays.asList(array))` works.

All other `Bundle` types work. But as this is a non-Android Parcelable class, it can be used without mocking.

## Using State Bundle

In order to use State Bundle, you need to add jitpack to your project root gradle:

```groovy
allprojects {
    repositories {
        // ...
        maven { url "https://jitpack.io" }
    }
    // ...
}
```

and add the compile dependency to your module level gradle.
```groovy
compile 'com.github.Zhuinden:state-bundle:1.0.0'
```

## License

    Copyright 2017 Gabor Varadi

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
