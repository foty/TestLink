获取屏幕密度dpi值
```
float xdpi = getResources().getDisplayMetrics().xdpi;
float ydpi = getResources().getDisplayMetrics().ydpi;
```
其中 xdpi与ydpi值几乎是相等的。dpi值表示是一个范围值，如 0-120都是属于ldpi范畴。

dpi值 | 等级 
:---: | :---:
120 |  ldpi
160 |  mdpi (标准dpi，通常以此为标准值。几倍图的说法也是基于标准值)
240 |  hdpi
320 |  xhdpi
480 |  xxhdpi
640 |  xxxhdpi

关于图片：  
适配，内存消耗，优化；   
LruCache和DiskLruCache https://blog.csdn.net/guolin_blog/article/details/34093441

