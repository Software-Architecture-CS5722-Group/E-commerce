package com.groupjn.userservice.kieker.common;

import kieker.common.record.io.IValueSerializer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Reiner Jung
 *
 */
public class ArraySerializer implements IValueSerializer {
    List<Object> objects = new ArrayList<>();

    public ArraySerializer() {

    }

    public void clear() {
        objects.clear();
    }

    public Object[] getObjectArray() {
        return objects.toArray();
    }

    /**
     * Stores a {@code boolean} value in the underlying data store.
     *
     * @param value
     *            The value to store
     * @since 1.13
     */
    public void putBoolean(boolean value) {
        this.objects.add(value);
    }

    /**
     * Stores a {@code byte} value in the underlying data store.
     *
     * @param value
     *            The value to store
     * @since 1.13
     */
    public void putByte(byte value) {
        this.objects.add(value);
    }

    /**
     * Stores a {@code char} value in the underlying data store.
     *
     * @param value
     *            The value to store
     * @since 1.13
     */
    public void putChar(char value) {
        this.objects.add(value);
    }

    /**
     * Stores a {@code short} value in the underlying data store.
     *
     * @param value
     *            The value to store
     * @since 1.13
     */
    public void putShort(short value) { // NOPMD
        this.objects.add(value);
    }

    /**
     * Stores an {@code int} value in the underlying data store.
     *
     * @param value
     *            The value to store
     * @since 1.13
     */
    public void putInt(int value) {
        this.objects.add(value);
    }

    /**
     * Stores a {@code long} value in the underlying data store.
     *
     * @param value
     *            The value to store
     * @since 1.13
     */
    public void putLong(long value) {
        this.objects.add(value);
    }

    /**
     * Stores a {@code float} value in the underlying data store.
     *
     * @param value
     *            The value to store
     * @since 1.13
     */
    public void putFloat(float value) {
        this.objects.add(value);
    }

    /**
     * Stores a {@code double} value in the underlying data store.
     *
     * @param value
     *            The value to store
     * @since 1.13
     */
    public void putDouble(double value) {
        this.objects.add(value);
    }

    /**
     * Stores a {@code Enumeration} value in the underlying data store.
     *
     * @param value
     *            The value to store
     * @since 1.14
     */
    public <T extends Enum<T>> void putEnumeration(T value) {
        this.objects.add(value);
    }

    /**
     * Stores raw data in the underlying data store.
     *
     * @param value
     *            The data to store
     * @since 1.13
     */
    public void putBytes(byte[] value) {
        this.objects.add(value);
    }

    /**
     * Stores a {@code String} value in the underlying data store.
     *
     * @param value
     *            The value to store
     * @since 1.13
     */
    public void putString(String value) {
        this.objects.add(value);
    }
}
