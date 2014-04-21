package demo.service.impl;

import demo.service.Transactional;
import demo.service.TransactionalException;

import javax.annotation.Resource;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.transaction.Status;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 * Class manages transactions in annotated methods
 *
 * @author Erofeev Danil
 * @see Transactional
 */
@Transactional
@Interceptor
public class TransactionInterceptor {
    @Resource
    private UserTransaction utx;

    @AroundInvoke
    public Object aroundInvoke(InvocationContext ic) throws TransactionalException {
        Object result = null;
        boolean activeTransaction = false;
        try {
            if (utx.getStatus() == Status.STATUS_ACTIVE) {
                activeTransaction = true;
                result = ic.proceed();
            } else {
                utx.begin();

                result = ic.proceed();
                if (utx.getStatus() == Status.STATUS_ACTIVE) {
                    utx.commit();
                } else {
                    utx.rollback();
                }
            }

        } catch (Throwable e) {
            rollback(activeTransaction);
            throw new TransactionalException(e);
        }
        return result;
    }

    private void rollback(boolean activeTransaction) throws TransactionalException {
        if (!activeTransaction) {
            try {
                if (null != utx && utx.getStatus() == Status.STATUS_ACTIVE) {
                    utx.rollback();
                }
            } catch (IllegalStateException | SecurityException | SystemException e) {
                throw new TransactionalException(e);
            }
        }
    }

}
