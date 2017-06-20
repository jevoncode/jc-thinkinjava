package com.jc.generic.util.tuple;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
 
import static com.jc.generic.util.tuple.Tuple.*;
/**
 * 哇，很屌很强大，以前我用就只代理一个对象，这里是以下代理N个对象。为了比装饰模式实现更屌的混型(Mixins)
 * @author jevoncode
 *
 */
class MixinProxy implements InvocationHandler {
	Map<String, Object> delegatesByMethod;

	public MixinProxy(TwoTuple<Object, Class<?>>... pairs) {
		delegatesByMethod = new HashMap<String, Object>();
		for (TwoTuple<Object, Class<?>> pair : pairs) {
			for (Method method : pair.second.getMethods()) {
				String methodName = method.getName();
				// The first interface in the map
				// implements the method.
				if (!delegatesByMethod.containsKey(methodName))
					delegatesByMethod.put(methodName, pair.first);
			}
		}
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		String methodName = method.getName();
		Object delegate = delegatesByMethod.get(methodName);
		return method.invoke(delegate, args);
	}

	@SuppressWarnings("unchecked")
	public static Object newInstance(TwoTuple... pairs) {
		Class[] interfaces = new Class[pairs.length];
		for (int i = 0; i < pairs.length; i++) {
			interfaces[i] = (Class) pairs[i].second;
		}
		ClassLoader cl = pairs[0].first.getClass().getClassLoader();
		return Proxy.newProxyInstance(cl, interfaces, new MixinProxy(pairs));
	}
}

public class DynamicProxyMixin {
	public static void main(String[] args) {
		Object mixin = MixinProxy.newInstance(tuple(new BasicImp(), Basic.class),
				tuple(new TimeStampedImp(), TimeStamped.class), tuple(new SerialNumberedImp(), SerialNumbered.class));
		Basic b = (Basic) mixin;
		TimeStamped t = (TimeStamped) mixin;
		SerialNumbered s = (SerialNumbered) mixin;
		b.set("Hello");
		System.out.println(b.get());
		System.out.println(t.getStamp());
		System.out.println(s.getSerialNumber());
	}
}