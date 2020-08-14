package com.suichen.utils.spring.lock.aop;

import com.suichen.utils.spring.lock.LockManager;
import com.suichen.utils.spring.lock.LockableRunnable;
import net.sf.cglib.proxy.MethodProxy;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.Invocation;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.aop.support.RootClassFilter;
import org.springframework.scheduling.TaskScheduler;

import java.lang.reflect.Method;

class SchedulerProxyScheduledLockAdvisor extends AbstractPointcutAdvisor {
    private final Pointcut pointcut = new TaskSchedulerPointcut();
    private final Advice advice;

    SchedulerProxyScheduledLockAdvisor(LockManager lockManager) {
        this.advice = new LockingInterceptor(lockManager);
    }
    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    private static class LockingInterceptor implements MethodInterceptor {
        private final LockManager lockManager;

        public LockingInterceptor(LockManager lockManager) {
            this.lockManager = lockManager;
        }

        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            Object[] arguments = invocation.getArguments();
            if (arguments.length >= 1 && arguments[0] instanceof Runnable) {
                arguments[0] = new LockableRunnable((Runnable) arguments[0], lockManager);
            } else {
                //logger.warn("Task scheduler first argument should be Runnable");
            }
            return invocation.proceed();
        }
    }
    private static class TaskSchedulerPointcut implements Pointcut {
        @Override
        public ClassFilter getClassFilter() {
            return new RootClassFilter(TaskScheduler.class);
        }

        @Override
        public MethodMatcher getMethodMatcher() {
            NameMatchMethodPointcut nameMatchMethodPointcut = new NameMatchMethodPointcut();
            nameMatchMethodPointcut.setMappedNames("schedule", "scheduleAtFixedRate", "scheduleWithFixedDelay");
            return nameMatchMethodPointcut;
        }
    }
}
