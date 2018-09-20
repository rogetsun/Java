package com.uv.multiaop.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.transaction.CannotCreateTransactionException;

/**
 * @author uvsun 2018/9/12 下午3:20
 */
public class Level2Aop {
    public Object round(ProceedingJoinPoint pjp) throws Throwable {
        Object r = null;
        try {
            System.out.println("Level2Aop.round");
            r = pjp.proceed();
            System.out.println("Level2Aop.round");
        } catch (CannotCreateTransactionException cannotCreateTransactionException) {
            System.out.println("round catch CannotCreateTransactionException:" + cannotCreateTransactionException.getMessage());
//            r = pjp.proceed();
            throw cannotCreateTransactionException;
        } catch (Throwable throwable) {
            System.out.println("round catch Throwable:" + throwable.getMessage());
            while (true) {
//                throwable.getCause().getCause()
                this.isException(throwable , NullPointerException.class);
                break;
            }
            throw throwable;
        }
        return r;
    }

    private boolean isException(Throwable throwable, Class<NullPointerException> nullPointerExceptionClass) {
        System.out.println(throwable.getCause());
        return false;
    }


}
