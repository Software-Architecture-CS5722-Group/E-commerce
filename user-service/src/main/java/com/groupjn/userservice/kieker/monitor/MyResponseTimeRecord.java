package com.groupjn.userservice.kieker.monitor;

import kieker.common.record.AbstractMonitoringRecord;
import kieker.common.record.io.IValueDeserializer;
import kieker.common.record.io.IValueSerializer;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;

public class MyResponseTimeRecord extends AbstractMonitoringRecord {

    public static final int SIZE = (2 * TYPE_SIZE_STRING) + TYPE_SIZE_LONG;
    public static final Class<?>[] TYPES = {
            String.class,
            String.class,
            long.class,
    };

    private static final long serialVersionUID = 7837873751833770201L;
    private final String className;
    private final String methodName;
    private final long responseTimeNanos;

    public MyResponseTimeRecord(final String clazz, final String method, final
    long rtNano) {
        this.className = clazz;
        this.methodName = method;
        this.responseTimeNanos = rtNano;
    }

    public MyResponseTimeRecord(final Object[] values) { AbstractMonitoringRecord.checkArray(values, MyResponseTimeRecord.TYPES);
        this.className = (String) values[0];
        this.methodName = (String) values[1];
        this.responseTimeNanos = (Long) values[2];
    }

    public MyResponseTimeRecord(final IValueDeserializer deserializer) throws
            BufferUnderflowException {
        this.className = deserializer.getString();
        this.methodName = deserializer.getString();
        this.responseTimeNanos = deserializer.getLong();
    }

    @Override
    public void serialize(IValueSerializer iValueSerializer) throws BufferOverflowException {
        iValueSerializer.putString(this.getClassName());
        iValueSerializer.putString(this.getMethodName());
        iValueSerializer.putLong(this.getResponseTimeNanos());
    }

    public String getMethodName() {
        return this.methodName;
    }

    public final String getClassName() {
        return this.className;
    }

    public final long getResponseTimeNanos() {
        return this.responseTimeNanos;
    }

    @Override
    public Class<?>[] getValueTypes() {
        return MyResponseTimeRecord.TYPES;
    }

    @Override
    public String[] getValueNames() {
        return new String[] { "className", "methodName", "responseTimeNanos" };
    }

    @Override
    public int getSize() {
        return SIZE;
    }
}
