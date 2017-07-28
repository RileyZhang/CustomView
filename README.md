# CustomView
这个工程主要用于来学习自定义view的


自定义view的实现主要分为下面几步：
1.在attr里面增加自定义view的属性
使用<declare-styleable></declare-styleable>时 这个里面的属性需要在<resource> 里面先定义好 不然使用的时候会报错

2.在构造函数里面得到这个属性值对应的数据并赋初值

3、重写onMeasure(), 重写最后记得setMeasuredDimension() 把修改后的width和height设置进去

4、重写onLayout(),如果布局需要改变的话

5、重写onDraw(),准备好画笔根据需要画矩形和圆形之类的，动画的实现可以用ValueAnimator 设一个范围然后不停调用postInvalidate() 执行onDraw()的
