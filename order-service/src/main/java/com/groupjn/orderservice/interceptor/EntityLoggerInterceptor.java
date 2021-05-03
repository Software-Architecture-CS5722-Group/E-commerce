package com.groupjn.orderservice.interceptor;

import com.groupjn.orderservice.entity.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import java.io.Serializable;

public class EntityLoggerInterceptor extends EmptyInterceptor {

    private static final long SerialVersionUID = 1L;

    private static Logger logger = LogManager.getLogger(EntityLoggerInterceptor.class);

    @Override
    public boolean onSave(
            Object entity,
            Serializable id,
            Object[] state,
            String[] propertyNames,
            Type[] types) {
        logger.info("onSave Method is called");
        if(entity instanceof Order){
            Order order = (Order) entity;
            logger.info(order);
        }
        return super.onSave(entity,id,state,propertyNames,types);
    }


    @Override
    public boolean onLoad(
            Object entity,
            Serializable id,
            Object[] state,
            String[] propertyNames,
            Type[] types) {

        logger.info("onLoad Method is called");
        if(entity instanceof Order){
            Order order = (Order) entity;
            logger.info(order);
        }
        return super.onSave(entity,id,state,propertyNames,types);
    }
}
