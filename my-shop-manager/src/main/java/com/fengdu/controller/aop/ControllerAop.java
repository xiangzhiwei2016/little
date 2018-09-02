package com.fengdu.controller.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

@Aspect
@Component
public class ControllerAop {
	private static final Logger logger = LoggerFactory
			.getLogger(ControllerAop.class);

	@Pointcut("execution(public * com..controller..*.*(..))")
	public void controllerExecution() {
	}

	@Around("controllerExecution()")
	public Object around(ProceedingJoinPoint jp) throws Throwable {
		logger.info("====================进入controller层start========================");
		Object result = null;
		String className = jp.getSignature().getDeclaringType().getName();
		MethodSignature signature = (MethodSignature) jp.getSignature();
		String methodName = signature.getMethod().getName();
		StringBuilder callPath = new StringBuilder();
		callPath.append(className).append(".").append(methodName);
		long start = System.currentTimeMillis();
		logger.info("进入方法{},start:毫秒数【{}】", callPath.toString(), start);
		result = jp.proceed();
		logger.info("进入方法{},end:毫秒数【{}】，总共耗时：【{}】,返回结果：{}",
				callPath.toString(), System.currentTimeMillis(),
				System.currentTimeMillis() - start, JSONObject.toJSON(result));

		logger.info("====================进入controller层end========================");
		return result;
	}
}
