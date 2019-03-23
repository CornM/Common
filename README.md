# Common
android 基础项目框架
##本项目是根据自己经常使用的框架，开发模式总结出来的，稍加封装之后总结出来的，可能更适合外包项目，快速集成。本着开源的精神，分享出来，共同进步。
1.基础的包base包下，有BaseActivity,BaseFragment，还有单图选择照片BasePhotoActivity,多图选择BasePhotoListActivity等。
2.api包下关于网络请求，使用Rxjava Retrofit封装网络请求，添加有日志拦截器，使用HttpResult封装网络返回结果，使用BaseObserver统一处理请求结果。
例如：当请求成功时，在BaseObserver的receiveResult()中处理后续逻辑，失败时，重写onError(ExceptionHandle.ResponeThrowable e)
即可，其中e.message,e.code均为后台返回。注意,后台返回的json都应含有msg，code字段，当msg为1时，应该包含data字段，当不为1时。可以不包含data字段。具体可以参考base包下的HttpResult类。
3.uti包下，都是常用的一些工具
4.widget包下封装一些常用的控件，例如：选择性别控件，相册，拍照选择控件，还有MyActionBar等。
