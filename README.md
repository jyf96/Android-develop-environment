# Android-develop-environment
关于如何搭建android开发环境
## 下载安装idea
搜索idea 下载社区版即可
## 安装Android SDK
新建android 项目，按提示下载sdk
## Installed Build Tools revision 31.0.0 is corrupted. Remove and install again using the SDK Manager.
参考链接https://stackoverflow.com/questions/68387270/android-studio-error-installed-build-tools-revision-31-0-0-is-corrupted/
First of all, I faced this issue in Android Studio 4.2.2 and you do not need to downgrade the SDK build tool from 31 to 30 or change compile SDK version.

The main problem is the two files missing in SDK build tool 31 that are:

dx.bat
dx.jar
The solution is that these files are named d8 in the file location so changing their name to dx will solve the error.

The steps are below.

For Windows
go to the location

 "C:\Users\user\AppData\Local\Android\Sdk\build-tools\31.0.0"
find a file named d8.bat. This is a Windows batch file.

rename d8.bat to dx.bat.

in the folder lib ("C:\Users\user\AppData\Local\Android\Sdk\build-tools\31.0.0\lib")

rename d8.jar to dx.jar

Remember AppData is a hidden folder. Turn on hidden items to see the AppData folder.

For macOS or Linux
change below to your Android SDK path
cd ~/Library/Android/sdk/build-tools/31.0.0 \
  && mv d8 dx \
  && cd lib  \
  && mv d8.jar dx.jar
  
