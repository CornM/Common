package com.zswl.baseprojectdemo;

import android.support.constraint.Group;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.zswl.common.api.ApiService;
import com.zswl.common.base.BackActivity;
import com.zswl.common.base.BaseObserver;
import com.zswl.common.base.HttpResult;
import com.zswl.common.util.LogUtil;
import com.zswl.common.util.RxUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.reactivex.observables.GroupedObservable;

public class Main3Activity extends BackActivity {
    @BindView(R.id.group)
    Group group;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main3;
    }

    @OnClick(R.id.tv_login)
    public void login() {
//        if (group.getVisibility() == View.VISIBLE)
//            group.setVisibility(View.INVISIBLE);
//        else
//            group.setVisibility(View.VISIBLE);


    }


    public interface Test {
        void test();
    }


    private class TestImpl implements Test {
        @Override
        public void test() {

        }
    }

    private class Invocation implements InvocationHandler {


        // 目标对象
        private Object targetObject;

        //绑定关系，也就是关联到哪个接口（与具体的实现类绑定）的哪些方法将被调用时，执行invoke方法。
        public Object newProxyInstance(Object targetObject) {
            this.targetObject = targetObject;
            //该方法用于为指定类装载器、一组接口及调用处理器生成动态代理类实例
            //第一个参数指定产生代理对象的类加载器，需要将其指定为和目标对象同一个类加载器
            //第二个参数要实现和目标对象一样的接口，所以只需要拿到目标对象的实现接口
            //第三个参数表明这些被拦截的方法在被拦截时需要执行哪个InvocationHandler的invoke方法
            //根据传入的目标返回一个代理对象
            return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(),
                    targetObject.getClass().getInterfaces(), this);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {


            return method.invoke(targetObject, args);
        }
    }


}
