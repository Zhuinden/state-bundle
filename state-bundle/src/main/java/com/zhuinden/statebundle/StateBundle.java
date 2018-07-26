/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zhuinden.statebundle;

import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseArray;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Parcelable representation of Bundle, independent from the Android framework.
 */
public class StateBundle
        implements Parcelable {
    static class TypeElement
            implements Parcelable {
        String key;
        int type;

        TypeElement() {
        }

        TypeElement(String key, int type) {
            this.key = key;
            this.type = type;
        }

        protected TypeElement(Parcel in) {
            key = in.readString();
            type = in.readInt();
        }

        public static final Creator<TypeElement> CREATOR = new Creator<TypeElement>() {
            @Override
            public TypeElement createFromParcel(Parcel in) {
                return new TypeElement(in);
            }

            @Override
            public TypeElement[] newArray(int size) {
                return new TypeElement[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(key);
            dest.writeInt(type);
        }
    }

    static final int type_boolean = 0;
    static final int type_byte = 1;
    static final int type_char = 2;
    static final int type_short = 3;
    static final int type_int = 4;
    static final int type_long = 5;
    static final int type_float = 6;
    static final int type_double = 7;
    static final int type_String = 8;
    static final int type_CharSequence = 9;
    static final int type_Serializable = 10;
    static final int type_IntegerArrayList = 11;
    static final int type_StringArrayList = 12;
    static final int type_CharSequenceArrayList = 13;
    static final int type_BooleanArray = 14;
    static final int type_ByteArray = 15;
    static final int type_ShortArray = 16;
    static final int type_CharArray = 17;
    static final int type_IntArray = 18;
    static final int type_LongArray = 19;
    static final int type_FloatArray = 20;
    static final int type_DoubleArray = 21;

    static final int type_StateBundle = 26;
    static final int type_Parcelable = 27;
    static final int type_ParcelableArrayList = 29;
    static final int type_SparseParcelableArray = 30;

    //static final int type_StringArray = 22;
    //static final int type_CharSequenceArray = 23;
    //static final int type_ParcelableArray = 28;
    //static final int type_Size = 24;
    //static final int type_SizeF = 25;

    Map<String, Object> map = new LinkedHashMap<>();
    Map<String, Integer> typeMap = new LinkedHashMap<>();

    /**
     * Constructs a new, empty Bundle.
     */
    public StateBundle() {
    }

    /**
     * Constructs a Bundle containing a copy of the mappings from the given
     * Bundle.
     *
     * @param bundle a Bundle to be copied, must not be null.
     */
    public StateBundle(@NonNull StateBundle bundle) {
        putAll(bundle);
    }

    /**
     * Constructs a Bundle using an Android Parcel.
     * Used by Parcelable implementation.
     *
     * @param in the Parcel
     */
    public StateBundle(Parcel in) {
        int size = in.readInt();
        for(int i = 0; i < size; i++) {
            TypeElement typeElement = in.readParcelable(TypeElement.class.getClassLoader());
            Object object = in.readValue(getClass().getClassLoader());
            switch(typeElement.type) {
                case type_boolean:
                    putBoolean(typeElement.key, (Boolean) object);
                    break;
                case type_byte:
                    putByte(typeElement.key, (Byte) object);
                    break;
                case type_char:
                    putChar(typeElement.key, (Character) object);
                    break;
                case type_short:
                    putShort(typeElement.key, (Short) object);
                    break;
                case type_int:
                    putInt(typeElement.key, (Integer) object);
                    break;
                case type_long:
                    putLong(typeElement.key, (Long) object);
                    break;
                case type_float:
                    putFloat(typeElement.key, (Float) object);
                    break;
                case type_double:
                    putDouble(typeElement.key, (Double) object);
                    break;
                case type_String:
                    putString(typeElement.key, (String) object);
                    break;
                case type_CharSequence:
                    putCharSequence(typeElement.key, (CharSequence) object);
                    break;
                case type_Serializable:
                    putSerializable(typeElement.key, (Serializable) object);
                    break;
                case type_IntegerArrayList:
                    putIntegerArrayList(typeElement.key, (ArrayList<Integer>) object);
                    break;
                case type_StringArrayList:
                    putStringArrayList(typeElement.key, (ArrayList<String>) object);
                    break;
                case type_CharSequenceArrayList:
                    putCharSequenceArrayList(typeElement.key, (ArrayList<CharSequence>) object);
                    break;
                case type_BooleanArray:
                    putBooleanArray(typeElement.key, (boolean[]) object);
                    break;
                case type_ByteArray:
                    putByteArray(typeElement.key, (byte[]) object);
                    break;
                case type_ShortArray:
                    putShortArray(typeElement.key, (short[]) object);
                    break;
                case type_CharArray:
                    putCharArray(typeElement.key, (char[]) object);
                    break;
                case type_IntArray:
                    putIntArray(typeElement.key, (int[]) object);
                    break;
                case type_LongArray:
                    putLongArray(typeElement.key, (long[]) object);
                    break;
                case type_FloatArray:
                    putFloatArray(typeElement.key, (float[]) object);
                    break;
                case type_DoubleArray:
                    putDoubleArray(typeElement.key, (double[]) object);
                    break;
                case type_StateBundle:
                    putBundle(typeElement.key, (StateBundle) object);
                    break;
                case type_Parcelable:
                    putParcelable(typeElement.key, (Parcelable) object);
                    break;
                case type_ParcelableArrayList:
                    putParcelableArrayList(typeElement.key, (ArrayList<Parcelable>) object);
                    break;
                case type_SparseParcelableArray:
                    putSparseParcelableArray(typeElement.key, (SparseArray<Parcelable>) object);
                    break;
            }
        }
    }

    public static final Creator<StateBundle> CREATOR = new Creator<StateBundle>() {
        @Override
        public StateBundle createFromParcel(Parcel in) {
            return new StateBundle(in);
        }

        @Override
        public StateBundle[] newArray(int size) {
            return new StateBundle[size];
        }
    };

    /**
     * Returns the number of mappings contained in this StateBundle.
     *
     * @return the number of mappings as an int.
     */
    public int size() {
        return map.size();
    }

    /**
     * Returns true if the mapping of this StateBundle is empty, false otherwise.
     */
    public boolean isEmpty() {
        return map.isEmpty();
    }

    /**
     * Removes all elements from the mapping of this StateBundle.
     *
     * @return this StateBundle
     */
    public StateBundle clear() {
        map.clear();
        typeMap.clear();
        return this;
    }

    /**
     * Returns true if the given key is contained in the mapping
     * of this StateBundle.
     *
     * @param key a String key
     * @return true if the key is part of the mapping, false otherwise
     */
    public boolean containsKey(String key) {
        return map.containsKey(key);
    }

    /**
     * Returns the entry with the given key as an object.
     *
     * @param key a String key
     * @return an Object, or null
     */
    @Nullable
    public Object get(String key) {
        return map.get(key);
    }

    /**
     * Removes any entry with the given key from the mapping of this StateBundle.
     *
     * @param key a String key
     * @return this StateBundle
     */
    public StateBundle remove(String key) {
        map.remove(key);
        typeMap.remove(key);
        return this;
    }

    /**
     * Inserts all mappings from the given StateBundle into this StateBundle.
     *
     * @param bundle a {@link StateBundle}
     * @return this StateBundle
     */
    public StateBundle putAll(@NonNull StateBundle bundle) {
        if(bundle == null) {
            throw new IllegalArgumentException("The provided bundle should not be null!");
        }
        if(bundle.map != null) {
            map.putAll(bundle.map);
            typeMap.putAll(bundle.typeMap);
        }
        return this;
    }

    /**
     * Returns a Set containing the Strings used as keys in this StateBundle.
     *
     * @return a Set of String keys
     */
    public Set<String> keySet() {
        return map.keySet();
    }

    /**
     * Inserts a Boolean value into the mapping of this StateBundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value a boolean
     * @return this StateBundle
     */
    public StateBundle putBoolean(@Nullable String key, boolean value) {
        map.put(key, value);
        typeMap.put(key, type_boolean);
        return this;
    }

    /**
     * Inserts a byte value into the mapping of this StateBundle, replacing
     * any existing value for the given key.
     *
     * @param key   a String, or null
     * @param value a byte
     * @return this StateBundle
     */
    public StateBundle putByte(@Nullable String key, byte value) {
        map.put(key, value);
        typeMap.put(key, type_byte);
        return this;
    }

    /**
     * Inserts a char value into the mapping of this StateBundle, replacing
     * any existing value for the given key.
     *
     * @param key   a String, or null
     * @param value a char
     * @return this StateBundle
     */
    public StateBundle putChar(@Nullable String key, char value) {
        map.put(key, value);
        typeMap.put(key, type_char);
        return this;
    }

    /**
     * Inserts a short value into the mapping of this StateBundle, replacing
     * any existing value for the given key.
     *
     * @param key   a String, or null
     * @param value a short
     * @return this StateBundle
     */
    public StateBundle putShort(@Nullable String key, short value) {
        map.put(key, value);
        typeMap.put(key, type_short);
        return this;
    }

    /**
     * Inserts an int value into the mapping of this StateBundle, replacing
     * any existing value for the given key.
     *
     * @param key   a String, or null
     * @param value an int
     * @return this StateBundle
     */
    public StateBundle putInt(@Nullable String key, int value) {
        map.put(key, value);
        typeMap.put(key, type_int);
        return this;
    }

    /**
     * Inserts a long value into the mapping of this StateBundle, replacing
     * any existing value for the given key.
     *
     * @param key   a String, or null
     * @param value a long
     * @return this StateBundle
     */
    public StateBundle putLong(@Nullable String key, long value) {
        map.put(key, value);
        typeMap.put(key, type_long);
        return this;
    }

    /**
     * Inserts a float value into the mapping of this StateBundle, replacing
     * any existing value for the given key.
     *
     * @param key   a String, or null
     * @param value a float
     * @return this StateBundle
     */
    public StateBundle putFloat(@Nullable String key, float value) {
        map.put(key, value);
        typeMap.put(key, type_float);
        return this;
    }

    /**
     * Inserts a double value into the mapping of this StateBundle, replacing
     * any existing value for the given key.
     * @return this StateBundle
     *
     * @param key   a String, or null
     * @param value a double
     */
    public StateBundle putDouble(@Nullable String key, double value) {
        map.put(key, value);
        typeMap.put(key, type_double);
        return this;
    }

    /**
     * Inserts a String value into the mapping of this StateBundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value a String, or null
     * @return this StateBundle
     */
    public StateBundle putString(@Nullable String key, @Nullable String value) {
        map.put(key, value);
        typeMap.put(key, type_String);
        return this;
    }

    /**
     * Inserts a CharSequence value into the mapping of this StateBundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value a CharSequence, or null
     * @return this StateBundle
     */
    public StateBundle putCharSequence(@Nullable String key, @Nullable CharSequence value) {
        map.put(key, value);
        typeMap.put(key, type_CharSequence);
        return this;
    }

    /**
     * Inserts an ArrayList of Integer value into the mapping of this StateBundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value an ArrayList of Integer object, or null
     * @return this StateBundle
     */
    public StateBundle putIntegerArrayList(@Nullable String key, @Nullable ArrayList<Integer> value) {
        map.put(key, value);
        typeMap.put(key, type_IntegerArrayList);
        return this;
    }

    /**
     * Inserts an ArrayList of String value into the mapping of this StateBundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value an ArrayList of String object, or null
     * @return this StateBundle
     */
    public StateBundle putStringArrayList(@Nullable String key, @Nullable ArrayList<String> value) {
        map.put(key, value);
        typeMap.put(key, type_StringArrayList);
        return this;
    }

    /**
     * Inserts an ArrayList of CharSequence value into the mapping of this StateBundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value an ArrayList of CharSequence object, or null
     * @return this StateBundle
     */
    public StateBundle putCharSequenceArrayList(@Nullable String key, @Nullable ArrayList<CharSequence> value) {
        map.put(key, value);
        typeMap.put(key, type_CharSequenceArrayList);
        return this;
    }

    /**
     * Inserts a Serializable value into the mapping of this StateBundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value a Serializable object, or null
     * @return this StateBundle
     */
    public StateBundle putSerializable(@Nullable String key, @Nullable Serializable value) {
        map.put(key, value);
        typeMap.put(key, type_Serializable);
        return this;
    }

    /**
     * Inserts a boolean array value into the mapping of this StateBundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value a boolean array object, or null
     * @return this StateBundle
     */
    public StateBundle putBooleanArray(@Nullable String key, @Nullable boolean[] value) {
        map.put(key, value);
        typeMap.put(key, type_BooleanArray);
        return this;
    }

    /**
     * Inserts a byte array value into the mapping of this StateBundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value a byte array object, or null
     * @return this StateBundle
     */
    public StateBundle putByteArray(@Nullable String key, @Nullable byte[] value) {
        map.put(key, value);
        typeMap.put(key, type_ByteArray);
        return this;
    }

    /**
     * Inserts a short array value into the mapping of this StateBundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value a short array object, or null
     * @return this StateBundle
     */
    public StateBundle putShortArray(@Nullable String key, @Nullable short[] value) {
        map.put(key, value);
        typeMap.put(key, type_ShortArray);
        return this;
    }

    /**
     * Inserts a char array value into the mapping of this StateBundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value a char array object, or null
     * @return this StateBundle
     */
    public StateBundle putCharArray(@Nullable String key, @Nullable char[] value) {
        map.put(key, value);
        typeMap.put(key, type_CharArray);
        return this;
    }

    /**
     * Inserts an int array value into the mapping of this StateBundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value an int array object, or null
     * @return this StateBundle
     */
    public StateBundle putIntArray(@Nullable String key, @Nullable int[] value) {
        map.put(key, value);
        typeMap.put(key, type_IntArray);
        return this;
    }

    /**
     * Inserts a long array value into the mapping of this StateBundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value a long array object, or null
     * @return this StateBundle
     */
    public StateBundle putLongArray(@Nullable String key, @Nullable long[] value) {
        map.put(key, value);
        typeMap.put(key, type_LongArray);
        return this;
    }

    /**
     * Inserts a float array value into the mapping of this StateBundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value a float array object, or null
     * @return this StateBundle
     */
    public StateBundle putFloatArray(@Nullable String key, @Nullable float[] value) {
        map.put(key, value);
        typeMap.put(key, type_FloatArray);
        return this;
    }

    /**
     * Inserts a double array value into the mapping of this StateBundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value a double array object, or null
     * @return this StateBundle
     */
    public StateBundle putDoubleArray(@Nullable String key, @Nullable double[] value) {
        map.put(key, value);
        typeMap.put(key, type_DoubleArray);
        return this;
    }

    /**
     * Inserts a String array value into the mapping of this StateBundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value a String array object, or null
     * @return this StateBundle
     */
//    public StateBundle putStringArray(@Nullable String key, @Nullable String[] value) {
//        map.put(key, value);
//        typeMap.put(key, type_StringArray);
//        return this;
//    }

    /**
     * Inserts a CharSequence array value into the mapping of this StateBundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value a CharSequence array object, or null
     * @return this StateBundle
     */
//    public StateBundle putCharSequenceArray(@Nullable String key, @Nullable CharSequence[] value) {
//        map.put(key, value);
//        typeMap.put(key, type_CharSequenceArray);
//        return this;
//    }


    /**
     * Inserts a Parcelable value into the mapping of this StateBundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value a Parcelable object, or null
     * @return this StateBundle
     */
    public StateBundle putParcelable(@Nullable String key, @Nullable Parcelable value) {
        map.put(key, value);
        typeMap.put(key, type_Parcelable);
        return this;
    }

    /**
     * Inserts a Size value into the mapping of this StateBundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value a Size object, or null
     * @return this StateBundle
     */
//    public StateBundle putSize(@Nullable String key, @Nullable Size value) {
//        map.put(key, value);
//        typeMap.put(key, type_Size);
//        return this;
//    }

    /**
     * Inserts a SizeF value into the mapping of this StateBundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value a SizeF object, or null
     * @return this StateBundle
     */
//    public StateBundle putSizeF(@Nullable String key, @Nullable SizeF value) {
//        map.put(key, value);
//        typeMap.put(key, type_SizeF);
//        return this;
//    }

    /**
     * Inserts an array of Parcelable values into the mapping of this StateBundle,
     * replacing any existing value for the given key.  Either key or value may
     * be null.
     *
     * @param key   a String, or null
     * @param value an array of Parcelable objects, or null
     * @return this StateBundle
     */
//    public StateBundle putParcelableArray(@Nullable String key, @Nullable Parcelable[] value) {
//        map.put(key, value);
//        typeMap.put(key, type_ParcelableArray);
//        return this;
//    }

    /**
     * Inserts a List of Parcelable values into the mapping of this StateBundle,
     * replacing any existing value for the given key.  Either key or value may
     * be null.
     *
     * @param key   a String, or null
     * @param value an ArrayList of Parcelable objects, or null
     * @return this StateBundle
     */
    public StateBundle putParcelableArrayList(@Nullable String key, @Nullable ArrayList<? extends Parcelable> value) {
        map.put(key, value);
        typeMap.put(key, type_ParcelableArrayList);
        return this;
    }

    /**
     * Inserts a SparceArray of Parcelable values into the mapping of this
     * Bundle, replacing any existing value for the given key.  Either key
     * or value may be null.
     *
     * @param key   a String, or null
     * @param value a SparseArray of Parcelable objects, or null
     * @return this StateBundle
     */
    public StateBundle putSparseParcelableArray(@Nullable String key, @Nullable SparseArray<? extends Parcelable> value) {
        map.put(key, value);
        typeMap.put(key, type_SparseParcelableArray);
        return this;
    }

    /**
     * Inserts a StateBundle value into the mapping of this StateBundle, replacing
     * any existing value for the given key.  Either key or value may be null.
     *
     * @param key   a String, or null
     * @param value a StateBundle object, or null
     * @return this StateBundle
     */
    public StateBundle putBundle(@Nullable String key, @Nullable StateBundle value) {
        map.put(key, value);
        typeMap.put(key, type_StateBundle);
        return this;
    }

    /**
     * Returns the value associated with the given key, or false if
     * no mapping of the desired type exists for the given key.
     *
     * @param key a String
     * @return a boolean value
     */
    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    /**
     * Returns the value associated with the given key, or defaultValue if
     * no mapping of the desired type exists for the given key.
     *
     * @param key          a String
     * @param defaultValue Value to return if key does not exist
     * @return a boolean value
     */
    public boolean getBoolean(String key, boolean defaultValue) {
        Object o = map.get(key);
        if(o == null) {
            return defaultValue;
        }
        try {
            return (Boolean) o;
        } catch(ClassCastException e) {
            typeWarning(key, o, "Boolean", defaultValue, e);
            return defaultValue;
        }
    }

    /**
     * Returns the value associated with the given key, or (byte) 0 if
     * no mapping of the desired type exists for the given key.
     *
     * @param key a String
     * @return a byte value
     */
    public byte getByte(String key) {
        return getByte(key, (byte) 0);
    }

    /**
     * Returns the value associated with the given key, or defaultValue if
     * no mapping of the desired type exists for the given key.
     *
     * @param key          a String
     * @param defaultValue Value to return if key does not exist
     * @return a byte value
     */
    public Byte getByte(String key, byte defaultValue) {
        Object o = map.get(key);
        if(o == null) {
            return defaultValue;
        }
        try {
            return (Byte) o;
        } catch(ClassCastException e) {
            typeWarning(key, o, "Byte", defaultValue, e);
            return defaultValue;
        }
    }

    /**
     * Returns the value associated with the given key, or (char) 0 if
     * no mapping of the desired type exists for the given key.
     *
     * @param key a String
     * @return a char value
     */
    public char getChar(String key) {
        return getChar(key, (char) 0);
    }

    /**
     * Returns the value associated with the given key, or defaultValue if
     * no mapping of the desired type exists for the given key.
     *
     * @param key          a String
     * @param defaultValue Value to return if key does not exist
     * @return a char value
     */
    public char getChar(String key, char defaultValue) {
        Object o = map.get(key);
        if(o == null) {
            return defaultValue;
        }
        try {
            return (Character) o;
        } catch(ClassCastException e) {
            typeWarning(key, o, "Character", defaultValue, e);
            return defaultValue;
        }
    }

    /**
     * Returns the value associated with the given key, or (short) 0 if
     * no mapping of the desired type exists for the given key.
     *
     * @param key a String
     * @return a short value
     */
    public short getShort(String key) {
        return getShort(key, (short) 0);
    }

    /**
     * Returns the value associated with the given key, or defaultValue if
     * no mapping of the desired type exists for the given key.
     *
     * @param key          a String
     * @param defaultValue Value to return if key does not exist
     * @return a short value
     */
    public short getShort(String key, short defaultValue) {
        Object o = map.get(key);
        if(o == null) {
            return defaultValue;
        }
        try {
            return (Short) o;
        } catch(ClassCastException e) {
            typeWarning(key, o, "Short", defaultValue, e);
            return defaultValue;
        }
    }

    /**
     * Returns the value associated with the given key, or 0 if
     * no mapping of the desired type exists for the given key.
     *
     * @param key a String
     * @return an int value
     */
    public int getInt(String key) {
        return getInt(key, 0);
    }

    /**
     * Returns the value associated with the given key, or defaultValue if
     * no mapping of the desired type exists for the given key.
     *
     * @param key          a String
     * @param defaultValue Value to return if key does not exist
     * @return an int value
     */
    public int getInt(String key, int defaultValue) {
        Object o = map.get(key);
        if(o == null) {
            return defaultValue;
        }
        try {
            return (Integer) o;
        } catch(ClassCastException e) {
            typeWarning(key, o, "Integer", defaultValue, e);
            return defaultValue;
        }
    }

    /**
     * Returns the value associated with the given key, or 0L if
     * no mapping of the desired type exists for the given key.
     *
     * @param key a String
     * @return a long value
     */
    public long getLong(String key) {
        return getLong(key, 0L);
    }

    /**
     * Returns the value associated with the given key, or defaultValue if
     * no mapping of the desired type exists for the given key.
     *
     * @param key          a String
     * @param defaultValue Value to return if key does not exist
     * @return a long value
     */
    public long getLong(String key, long defaultValue) {
        Object o = map.get(key);
        if(o == null) {
            return defaultValue;
        }
        try {
            return (Long) o;
        } catch(ClassCastException e) {
            typeWarning(key, o, "Long", defaultValue, e);
            return defaultValue;
        }
    }

    /**
     * Returns the value associated with the given key, or 0.0f if
     * no mapping of the desired type exists for the given key.
     *
     * @param key a String
     * @return a float value
     */
    public float getFloat(String key) {
        return getFloat(key, 0.0f);
    }

    /**
     * Returns the value associated with the given key, or defaultValue if
     * no mapping of the desired type exists for the given key.
     *
     * @param key          a String
     * @param defaultValue Value to return if key does not exist
     * @return a float value
     */
    public float getFloat(String key, float defaultValue) {
        Object o = map.get(key);
        if(o == null) {
            return defaultValue;
        }
        try {
            return (Float) o;
        } catch(ClassCastException e) {
            typeWarning(key, o, "Float", defaultValue, e);
            return defaultValue;
        }
    }

    /**
     * Returns the value associated with the given key, or 0.0 if
     * no mapping of the desired type exists for the given key.
     *
     * @param key a String
     * @return a double value
     */
    public double getDouble(String key) {
        return getDouble(key, 0.0);
    }

    /**
     * Returns the value associated with the given key, or defaultValue if
     * no mapping of the desired type exists for the given key.
     *
     * @param key          a String
     * @param defaultValue Value to return if key does not exist
     * @return a double value
     */
    public double getDouble(String key, double defaultValue) {
        Object o = map.get(key);
        if(o == null) {
            return defaultValue;
        }
        try {
            return (Double) o;
        } catch(ClassCastException e) {
            typeWarning(key, o, "Double", defaultValue, e);
            return defaultValue;
        }
    }

    /**
     * Returns the value associated with the given key, or null if
     * no mapping of the desired type exists for the given key or a null
     * value is explicitly associated with the key.
     *
     * @param key a String, or null
     * @return a String value, or null
     */
    @Nullable
    public String getString(@Nullable String key) {
        final Object o = map.get(key);
        try {
            return (String) o;
        } catch(ClassCastException e) {
            typeWarning(key, o, "String", e);
            return null;
        }
    }

    /**
     * Returns the value associated with the given key, or defaultValue if
     * no mapping of the desired type exists for the given key or if a null
     * value is explicitly associated with the given key.
     *
     * @param key          a String, or null
     * @param defaultValue Value to return if key does not exist or if a null
     *                     value is associated with the given key.
     * @return the String value associated with the given key, or defaultValue
     * if no valid String object is currently mapped to that key.
     */
    public String getString(@Nullable String key, String defaultValue) {
        final String s = getString(key);
        return (s == null) ? defaultValue : s;
    }

    /**
     * Returns the value associated with the given key, or null if
     * no mapping of the desired type exists for the given key or a null
     * value is explicitly associated with the key.
     *
     * @param key a String, or null
     * @return a CharSequence value, or null
     */
    @Nullable
    public CharSequence getCharSequence(@Nullable String key) {
        final Object o = map.get(key);
        try {
            return (CharSequence) o;
        } catch(ClassCastException e) {
            typeWarning(key, o, "CharSequence", e);
            return null;
        }
    }

    /**
     * Returns the value associated with the given key, or defaultValue if
     * no mapping of the desired type exists for the given key or if a null
     * value is explicitly associated with the given key.
     *
     * @param key          a String, or null
     * @param defaultValue Value to return if key does not exist or if a null
     *                     value is associated with the given key.
     * @return the CharSequence value associated with the given key, or defaultValue
     * if no valid CharSequence object is currently mapped to that key.
     */
    public CharSequence getCharSequence(@Nullable String key, CharSequence defaultValue) {
        final CharSequence cs = getCharSequence(key);
        return (cs == null) ? defaultValue : cs;
    }

    /**
     * Returns the value associated with the given key, or null if
     * no mapping of the desired type exists for the given key or a null
     * value is explicitly associated with the key.
     *
     * @param key a String, or null
     * @return a Serializable value, or null
     */
    @Nullable
    public Serializable getSerializable(@Nullable String key) {
        Object o = map.get(key);
        if(o == null) {
            return null;
        }
        try {
            return (Serializable) o;
        } catch(ClassCastException e) {
            typeWarning(key, o, "Serializable", e);
            return null;
        }
    }

    /**
     * Returns the value associated with the given key, or null if
     * no mapping of the desired type exists for the given key or a null
     * value is explicitly associated with the key.
     *
     * @param key a String, or null
     * @return an ArrayList of String value, or null
     */
    @Nullable
    public ArrayList<Integer> getIntegerArrayList(@Nullable String key) {
        Object o = map.get(key);
        if(o == null) {
            return null;
        }
        try {
            return (ArrayList<Integer>) o;
        } catch(ClassCastException e) {
            typeWarning(key, o, "ArrayList<Integer>", e);
            return null;
        }
    }

    /**
     * Returns the value associated with the given key, or null if
     * no mapping of the desired type exists for the given key or a null
     * value is explicitly associated with the key.
     *
     * @param key a String, or null
     * @return an ArrayList of String value, or null
     */
    @Nullable
    public ArrayList<String> getStringArrayList(@Nullable String key) {
        Object o = map.get(key);
        if(o == null) {
            return null;
        }
        try {
            return (ArrayList<String>) o;
        } catch(ClassCastException e) {
            typeWarning(key, o, "ArrayList<String>", e);
            return null;
        }
    }

    /**
     * Returns the value associated with the given key, or null if
     * no mapping of the desired type exists for the given key or a null
     * value is explicitly associated with the key.
     *
     * @param key a String, or null
     * @return an ArrayList of CharSequence value, or null
     */
    @Nullable
    public ArrayList<CharSequence> getCharSequenceArrayList(@Nullable String key) {
        Object o = map.get(key);
        if(o == null) {
            return null;
        }
        try {
            return (ArrayList<CharSequence>) o;
        } catch(ClassCastException e) {
            typeWarning(key, o, "ArrayList<CharSequence>", e);
            return null;
        }
    }

    /**
     * Returns the value associated with the given key, or null if
     * no mapping of the desired type exists for the given key or a null
     * value is explicitly associated with the key.
     *
     * @param key a String, or null
     * @return a boolean[] value, or null
     */
    @Nullable
    public boolean[] getBooleanArray(@Nullable String key) {
        Object o = map.get(key);
        if(o == null) {
            return null;
        }
        try {
            return (boolean[]) o;
        } catch(ClassCastException e) {
            typeWarning(key, o, "byte[]", e);
            return null;
        }
    }

    /**
     * Returns the value associated with the given key, or null if
     * no mapping of the desired type exists for the given key or a null
     * value is explicitly associated with the key.
     *
     * @param key a String, or null
     * @return a byte[] value, or null
     */
    @Nullable
    public byte[] getByteArray(@Nullable String key) {
        Object o = map.get(key);
        if(o == null) {
            return null;
        }
        try {
            return (byte[]) o;
        } catch(ClassCastException e) {
            typeWarning(key, o, "byte[]", e);
            return null;
        }
    }

    /**
     * Returns the value associated with the given key, or null if
     * no mapping of the desired type exists for the given key or a null
     * value is explicitly associated with the key.
     *
     * @param key a String, or null
     * @return a short[] value, or null
     */
    @Nullable
    public short[] getShortArray(@Nullable String key) {
        Object o = map.get(key);
        if(o == null) {
            return null;
        }
        try {
            return (short[]) o;
        } catch(ClassCastException e) {
            typeWarning(key, o, "short[]", e);
            return null;
        }
    }

    /**
     * Returns the value associated with the given key, or null if
     * no mapping of the desired type exists for the given key or a null
     * value is explicitly associated with the key.
     *
     * @param key a String, or null
     * @return a char[] value, or null
     */
    @Nullable
    public char[] getCharArray(@Nullable String key) {
        Object o = map.get(key);
        if(o == null) {
            return null;
        }
        try {
            return (char[]) o;
        } catch(ClassCastException e) {
            typeWarning(key, o, "char[]", e);
            return null;
        }
    }

    /**
     * Returns the value associated with the given key, or null if
     * no mapping of the desired type exists for the given key or a null
     * value is explicitly associated with the key.
     *
     * @param key a String, or null
     * @return an int[] value, or null
     */
    @Nullable
    public int[] getIntArray(@Nullable String key) {
        Object o = map.get(key);
        if(o == null) {
            return null;
        }
        try {
            return (int[]) o;
        } catch(ClassCastException e) {
            typeWarning(key, o, "int[]", e);
            return null;
        }
    }

    /**
     * Returns the value associated with the given key, or null if
     * no mapping of the desired type exists for the given key or a null
     * value is explicitly associated with the key.
     *
     * @param key a String, or null
     * @return a long[] value, or null
     */
    @Nullable
    public long[] getLongArray(@Nullable String key) {
        Object o = map.get(key);
        if(o == null) {
            return null;
        }
        try {
            return (long[]) o;
        } catch(ClassCastException e) {
            typeWarning(key, o, "long[]", e);
            return null;
        }
    }

    /**
     * Returns the value associated with the given key, or null if
     * no mapping of the desired type exists for the given key or a null
     * value is explicitly associated with the key.
     *
     * @param key a String, or null
     * @return a float[] value, or null
     */
    @Nullable
    public float[] getFloatArray(@Nullable String key) {
        Object o = map.get(key);
        if(o == null) {
            return null;
        }
        try {
            return (float[]) o;
        } catch(ClassCastException e) {
            typeWarning(key, o, "float[]", e);
            return null;
        }
    }

    /**
     * Returns the value associated with the given key, or null if
     * no mapping of the desired type exists for the given key or a null
     * value is explicitly associated with the key.
     *
     * @param key a String, or null
     * @return a double[] value, or null
     */
    @Nullable
    public double[] getDoubleArray(@Nullable String key) {
        Object o = map.get(key);
        if(o == null) {
            return null;
        }
        try {
            return (double[]) o;
        } catch(ClassCastException e) {
            typeWarning(key, o, "double[]", e);
            return null;
        }
    }

    /**
     * Returns the value associated with the given key, or null if
     * no mapping of the desired type exists for the given key or a null
     * value is explicitly associated with the key.
     *
     * @param key a String, or null
     * @return a String[] value, or null
     */
//    @Nullable
//    public String[] getStringArray(@Nullable String key) {
//        Object o = map.get(key);
//        if(o == null) {
//            return null;
//        }
//        try {
//            return (String[]) o;
//        } catch(ClassCastException e) {
//            typeWarning(key, o, "String[]", e);
//            return null;
//        }
//    }

    /**
     * Returns the value associated with the given key, or null if
     * no mapping of the desired type exists for the given key or a null
     * value is explicitly associated with the key.
     *
     * @param key a String, or null
     * @return a CharSequence[] value, or null
     */
//    @Nullable
//    public CharSequence[] getCharSequenceArray(@Nullable String key) {
//        Object o = map.get(key);
//        if(o == null) {
//            return null;
//        }
//        try {
//            return (CharSequence[]) o;
//        } catch(ClassCastException e) {
//            typeWarning(key, o, "CharSequence[]", e);
//            return null;
//        }
//    }

    /**
     * Returns the value associated with the given key, or null if
     * no mapping of the desired type exists for the given key or a null
     * value is explicitly associated with the key.
     *
     * @param key a String, or null
     * @return a Size value, or null
     */
//    @Nullable
//    public Size getSize(@Nullable String key) {
//        final Object o = map.get(key);
//        try {
//            return (Size) o;
//        } catch(ClassCastException e) {
//            typeWarning(key, o, "Size", e);
//            return null;
//        }
//    }

    /**
     * Returns the value associated with the given key, or null if
     * no mapping of the desired type exists for the given key or a null
     * value is explicitly associated with the key.
     *
     * @param key a String, or null
     * @return a Size value, or null
     */
//    @Nullable
//    public SizeF getSizeF(@Nullable String key) {
//        final Object o = map.get(key);
//        try {
//            return (SizeF) o;
//        } catch(ClassCastException e) {
//            typeWarning(key, o, "SizeF", e);
//            return null;
//        }
//    }

    /**
     * Returns the value associated with the given key, or null if
     * no mapping of the desired type exists for the given key or a null
     * value is explicitly associated with the key.
     *
     * @param key a String, or null
     * @return a Bundle value, or null
     */
    @Nullable
    public StateBundle getBundle(@Nullable String key) {
        Object o = map.get(key);
        if(o == null) {
            return null;
        }
        try {
            return (StateBundle) o;
        } catch(ClassCastException e) {
            typeWarning(key, o, "Bundle", e);
            return null;
        }
    }

    /**
     * Returns the value associated with the given key, or null if
     * no mapping of the desired type exists for the given key or a null
     * value is explicitly associated with the key.
     *
     * @param key a String, or null
     * @return a Parcelable value, or null
     */
    @Nullable
    public <T extends Parcelable> T getParcelable(@Nullable String key) {
        Object o = map.get(key);
        if(o == null) {
            return null;
        }
        try {
            return (T) o;
        } catch(ClassCastException e) {
            typeWarning(key, o, "Parcelable", e);
            return null;
        }
    }

    /**
     * Returns the value associated with the given key, or null if
     * no mapping of the desired type exists for the given key or a null
     * value is explicitly associated with the key.
     *
     * @param key a String, or null
     * @return a Parcelable[] value, or null
     */
//    @Nullable
//    public Parcelable[] getParcelableArray(@Nullable String key) {
//        Object o = map.get(key);
//        if(o == null) {
//            return null;
//        }
//        try {
//            return (Parcelable[]) o;
//        } catch(ClassCastException e) {
//            typeWarning(key, o, "Parcelable[]", e);
//            return null;
//        }
//    }

    /**
     * Returns the value associated with the given key, or null if
     * no mapping of the desired type exists for the given key or a null
     * value is explicitly associated with the key.
     *
     * @param key a String, or null
     * @return an ArrayList of T value, or null
     */
    @Nullable
    public <T extends Parcelable> ArrayList<T> getParcelableArrayList(@Nullable String key) {
        Object o = map.get(key);
        if(o == null) {
            return null;
        }
        try {
            return (ArrayList<T>) o;
        } catch(ClassCastException e) {
            typeWarning(key, o, "ArrayList", e);
            return null;
        }
    }

    /**
     * Returns the value associated with the given key, or null if
     * no mapping of the desired type exists for the given key or a null
     * value is explicitly associated with the key.
     *
     * @param key a String, or null
     * @return a SparseArray of T values, or null
     */
    @Nullable
    public <T extends Parcelable> SparseArray<T> getSparseParcelableArray(@Nullable String key) {
        Object o = map.get(key);
        if(o == null) {
            return null;
        }
        try {
            return (SparseArray<T>) o;
        } catch(ClassCastException e) {
            typeWarning(key, o, "SparseArray", e);
            return null;
        }
    }

    // Log a message if the value was non-null but not of the expected type
    protected void typeWarning(String key, Object value, String className, Object defaultValue, ClassCastException e) {
        StringBuilder sb = new StringBuilder();
        sb.append("Key ");
        sb.append(key);
        sb.append(" expected ");
        sb.append(className);
        sb.append(" but value was a ");
        sb.append(value.getClass().getName());
        sb.append(".  The default value ");
        sb.append(defaultValue);
        sb.append(" was returned.");
        System.out.println(sb.toString());
    }

    void typeWarning(String key, Object value, String className, ClassCastException e) {
        typeWarning(key, value, className, "<null>", e);
    }

    /**
     * Copies the keys and values of this {@link StateBundle} into the provided android.os.Bundle
     */
    public void copyToBundle(Bundle bundle) {
        for(Map.Entry<String, Object> entry : map.entrySet()) {
            int typeValue = typeMap.get(entry.getKey());
            String key = entry.getKey();
            Object object = entry.getValue();
            switch(typeValue) {
                case type_boolean:
                    bundle.putBoolean(key, (Boolean) object);
                    break;
                case type_byte:
                    bundle.putByte(key, (Byte) object);
                    break;
                case type_char:
                    bundle.putChar(key, (Character) object);
                    break;
                case type_short:
                    bundle.putShort(key, (Short) object);
                    break;
                case type_int:
                    bundle.putInt(key, (Integer) object);
                    break;
                case type_long:
                    bundle.putLong(key, (Long) object);
                    break;
                case type_float:
                    bundle.putFloat(key, (Float) object);
                    break;
                case type_double:
                    bundle.putDouble(key, (Double) object);
                    break;
                case type_String:
                    bundle.putString(key, (String) object);
                    break;
                case type_CharSequence:
                    bundle.putCharSequence(key, (CharSequence) object);
                    break;
                case type_Serializable:
                    bundle.putSerializable(key, (Serializable) object);
                    break;
                case type_IntegerArrayList:
                    // noinspection unchecked
                    bundle.putIntegerArrayList(key, (ArrayList<Integer>) object);
                    break;
                case type_StringArrayList:
                    // noinspection unchecked
                    bundle.putStringArrayList(key, (ArrayList<String>) object);
                    break;
                case type_CharSequenceArrayList:
                    if(Build.VERSION.SDK_INT >= 8) {
                        // noinspection unchecked
                        bundle.putCharSequenceArrayList(key, (ArrayList<CharSequence>) object);
                    } else {
                        throw new UnsupportedOperationException("CharSequenceArrayList not supported on API < 8.");
                    }
                    break;
                case type_BooleanArray:
                    bundle.putBooleanArray(key, (boolean[]) object);
                    break;
                case type_ByteArray:
                    bundle.putByteArray(key, (byte[]) object);
                    break;
                case type_ShortArray:
                    bundle.putShortArray(key, (short[]) object);
                    break;
                case type_CharArray:
                    bundle.putCharArray(key, (char[]) object);
                    break;
                case type_IntArray:
                    bundle.putIntArray(key, (int[]) object);
                    break;
                case type_LongArray:
                    bundle.putLongArray(key, (long[]) object);
                    break;
                case type_FloatArray:
                    bundle.putFloatArray(key, (float[]) object);
                    break;
                case type_DoubleArray:
                    bundle.putDoubleArray(key, (double[]) object);
                    break;
                case type_StateBundle:
                    bundle.putParcelable(key, (StateBundle) object);
                    break;
                case type_Parcelable:
                    bundle.putParcelable(key, (Parcelable) object);
                    break;
                case type_ParcelableArrayList:
                    // noinspection unchecked
                    bundle.putParcelableArrayList(key, (ArrayList<Parcelable>) object);
                    break;
                case type_SparseParcelableArray:
                    // noinspection unchecked
                    bundle.putSparseParcelableArray(key, (SparseArray<Parcelable>) object);
                    break;
            }
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(map.keySet().size());
        for(String key : map.keySet()) {
            TypeElement typeElement = new TypeElement(key, typeMap.get(key));
            dest.writeParcelable(typeElement, 0);
            dest.writeValue(get(key));
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        Set<Map.Entry<String, Object>> entrySet = map.entrySet();
        if(entrySet.isEmpty()) {
            stringBuilder.append("[]");
        } else {
            for(Map.Entry<String, Object> entry : entrySet) {
                stringBuilder.append("{[");
                stringBuilder.append(entry.getKey());
                stringBuilder.append("]::[");

                int type = typeMap.get(entry.getKey());
                if(entry.getValue() == null) {
                    stringBuilder.append("<null>");
                } else if(type == type_BooleanArray) {
                    stringBuilder.append(Arrays.toString((boolean[]) entry.getValue()));
                } else if(type == type_ByteArray) {
                    stringBuilder.append(Arrays.toString((byte[]) entry.getValue()));
                } else if(type == type_ShortArray) {
                    stringBuilder.append(Arrays.toString((short[]) entry.getValue()));
                } else if(type == type_CharArray) {
                    stringBuilder.append(Arrays.toString((char[]) entry.getValue()));
                } else if(type == type_IntArray) {
                    stringBuilder.append(Arrays.toString((int[]) entry.getValue()));
                } else if(type == type_LongArray) {
                    stringBuilder.append(Arrays.toString((long[]) entry.getValue()));
                } else if(type == type_FloatArray) {
                    stringBuilder.append(Arrays.toString((float[]) entry.getValue()));
                } else if(type == type_DoubleArray) {
                    stringBuilder.append(Arrays.toString((double[]) entry.getValue()));
                } else {
                    stringBuilder.append(entry.getValue());
                }
                stringBuilder.append("]}");
                ++i;
                if(i < entrySet.size()) {
                    stringBuilder.append(" ");
                }
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public int hashCode() {
        int result = 0;
        Set<Map.Entry<String, Object>> entrySet = map.entrySet();
        for(Map.Entry<String, Object> entry : entrySet) {
            result += 31 * entry.getKey().hashCode();
            int type = typeMap.get(entry.getKey());
            if(entry.getValue() == null) {
                result += 0;
            } else if(type == type_BooleanArray) {
                result += 31 * (Arrays.hashCode((boolean[]) entry.getValue()));
            } else if(type == type_ByteArray) {
                result += 31 * (Arrays.hashCode((byte[]) entry.getValue()));
            } else if(type == type_ShortArray) {
                result += 31 * (Arrays.hashCode((short[]) entry.getValue()));
            } else if(type == type_CharArray) {
                result += 31 * (Arrays.hashCode((char[]) entry.getValue()));
            } else if(type == type_IntArray) {
                result += 31 * (Arrays.hashCode((int[]) entry.getValue()));
            } else if(type == type_LongArray) {
                result += 31 * (Arrays.hashCode((long[]) entry.getValue()));
            } else if(type == type_FloatArray) {
                result += 31 * (Arrays.hashCode((float[]) entry.getValue()));
            } else if(type == type_DoubleArray) {
                result += 31 * (Arrays.hashCode((double[]) entry.getValue()));
            } else {
                result += 31 * entry.getValue().hashCode();
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof StateBundle)) {
            return false;
        }
        StateBundle other = (StateBundle) obj;
        if((this.map.isEmpty() && !((StateBundle) obj).map.isEmpty()) || (!this.map.isEmpty() && ((StateBundle) obj).map.isEmpty())) {
            return false;
        }
        for(Map.Entry<String, ?> stored : map.entrySet()) {
            if(!other.containsKey(stored.getKey())) {
                return false;
            }
            if(!other.typeMap.containsKey(stored.getKey())) {
                return false;
            }
            int type = typeMap.get(stored.getKey());
            if(!other.typeMap.get(stored.getKey()).equals(type)) {
                return false;
            }
            Object thisObj = stored.getValue();
            Object thatObj = other.get(stored.getKey());
            if(thisObj == null && thatObj == null) {
                continue;
            }
            if((thisObj == null && thatObj != null) || (thisObj != null && thatObj == null)) {
                return false;
            }
            if(type == type_BooleanArray) {
                boolean eq = Arrays.equals((boolean[]) thisObj, (boolean[]) thatObj);
                if(!eq) {
                    return false;
                }
            } else if(type == type_ByteArray) {
                boolean eq = Arrays.equals((byte[]) thisObj, (byte[]) thatObj);
                if(!eq) {
                    return false;
                }
            } else if(type == type_ShortArray) {
                boolean eq = Arrays.equals((short[]) thisObj, (short[]) thatObj);
                if(!eq) {
                    return false;
                }
            } else if(type == type_CharArray) {
                boolean eq = Arrays.equals((char[]) thisObj, (char[]) thatObj);
                if(!eq) {
                    return false;
                }
            } else if(type == type_IntArray) {
                boolean eq = Arrays.equals((int[]) thisObj, (int[]) thatObj);
                if(!eq) {
                    return false;
                }
            } else if(type == type_LongArray) {
                boolean eq = Arrays.equals((long[]) thisObj, (long[]) thatObj);
                if(!eq) {
                    return false;
                }
            } else if(type == type_FloatArray) {
                boolean eq = Arrays.equals((float[]) thisObj, (float[]) thatObj);
                if(!eq) {
                    return false;
                }
            } else if(type == type_DoubleArray) {
                boolean eq = Arrays.equals((double[]) thisObj, (double[]) thatObj);
                if(!eq) {
                    return false;
                }
            } else {
                if(!thisObj.equals(thatObj)) {
                    return false;
                }
            }
        }
        return true;
    }
}