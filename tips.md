Android 项目中asset目录和res的区别 
* res中存放可编译的资源文件,这种资源文件系统会在R.java里面自动生成该资源文件的ID,(除了raw,raw不会被编译);
* res下的文件夹都是有特殊含义的,如anim特指动画类,raw指音频类等等。不能顺便创建文件夹;
* res下的资源通过R.id.xx来访问;

* asset目录的打包后会原封不动的保存在apk包中，不会被编译成二进制;
* asset资源访问时使用AssetManager,以流形式操作;
* asset资源可以自由创建子文件夹，并且都有效，可以访问到;

