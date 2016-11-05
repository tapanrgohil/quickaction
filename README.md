# Quick Action [![Build Status](https://travis-ci.org/piruin/QuickAction.svg?branch=master)](https://travis-ci.org/piruin/QuickAction) [ ![Download](https://api.bintray.com/packages/blazei/maven/QuickAction/images/download.svg) ](https://bintray.com/blazei/maven/QuickAction/_latestVersion)

Quick Action is a small android library for easy create Tooltips with some action or
just as decoration. folk from [NewQuickAction3D](https://github.com/lorensiuswlt/NewQuickAction3D)
by [Lorensius W. L. T ](http://www.londatiga.net/).

> Not just a Tooltips, A Tooltips with Action!.

Because *NewQuickAction3D* is design of Android 2.x. So, I change it's style to fit with Material Design
but still compatible with old java source code interface, Refactor, Transform to Gradle project
and publish to JCenter.

## Demo

![Quick Action demo][demo]

## Download

NOTE! This project publish only on **JCenter** not on **Maven Central**.

```groovy
repositories {
    jcenter()
}
```

Add dependencies on app module

```groovy
dependencies {
    ...
    compile 'me.piruin:quickaction:+'
    ...
}
```
 See latest version at download badge above 

## How to Use

QuickAction use same old NewQuickAction3D interface. So, [SampleActivity](https://github.com/piruin/QuickAction/blob/master/quickaction-sample/src/main/java/me/piruin/quickaction/sample/SampleActivity.java)
made by Lorensius W. L. T still work and cover main part of it.
You get more information at his blog http://www.londatiga.net/it/how-to-create-quickaction-dialog-in-android/

## New Feature

My QuickAction have some additional feature more than original

### Set Popup's Color & Text Color

```java
  //Popup color
  quickAction.setColorRes(R.color.pink) //set by Color Resource
  quickAction.setColor(Color.WHITE) // or by Color class

  //Text color
  quickAction.setTextColorRes(R.color.red)
  quickAction.setTextColor(Color.Black)
```

NOTE! setTextColor apply only ActionItem that added afterward.

### Quick Intent Action

To lazy create list of Activity or Service that match with your Intent

```java
  Intent intent = new Intent(Action.VIEW) // intent your want to start
  QuickAction quickIntent = new QuickIntentAction(this)
    .setActivityIntent(intent)
    .create();
```

## Developer By

- [Piruin Panichphol](https://piruin.me)

- [Lorensius W. L. T](http://www.londatiga.net/) - Original Developer

## Contributors
- Kevin Peck - <kevinwpeck@gmail.com>

## Changes


See [CHANGELOG](https://github.com/lorensiuswlt/NewQuickAction3D/blob/master/CHANGELOG.md) for details

## License

This project under [Apache 2.0](http://www.apache.org/licenses/LICENSE-2.0) license

    Copyright 2016 Piruin Panichphol
    Copyright 2011 Lorensius W. L. T

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


[demo]: https://github.com/piruin/QuickAction/blob/master/asset/demo.gif "Demo gif"
