package com.cheng.multithreadstudy.sunframework.proxy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelMap {
    private Map map;
    private static String LIST = "list";

    public ModelMap() {
        map = new HashMap();
    }

    public <T> void put(GString<T> clazz, Object object) {
        if (object instanceof List) {
            Object object2 = map.get(LIST);
            if (object2 == null) {
                Map listMap = new HashMap<Object, List<T>>();
                map.put(LIST, listMap);
                listMap.put(clazz, object);
            } else {
                Map listMap = (Map) object2;
                listMap.put(clazz, object);
            }
        } else {
            map.put(clazz, object);
        }
    }

    public <T> void put(GInteger<T> clazz, Object object) {
        if (object instanceof List) {
            Object object2 = map.get(LIST);
            if (object2 == null) {
                Map listMap = new HashMap<Object, List<T>>();
                map.put(LIST, listMap);
                listMap.put(clazz, object);
            } else {
                Map listMap = (Map) object2;
                listMap.put(clazz, object);
            }
        } else {
            map.put(clazz, object);
        }
    }

    public <T> T get(GInteger<T> clazz) {
        return (T) map.remove(clazz);
    }

    public <T> List<T> getList(GInteger<T> clazz) {
        Object object = map.get(LIST);
        if (object == null) {
            return null;
        } else {
            Map listMap = (Map) object;
            return (List<T>) listMap.remove(clazz);
        }
    }

    public <T> List<T> getList(GString<T> clazz) {
        Object object = map.get(LIST);
        if (object == null) {
            return null;
        } else {
            Map listMap = (Map) object;
            return (List<T>) listMap.remove(clazz);
        }
    }

    public <T> T get(GString<T> clazz) {
        return (T) map.remove(clazz);
    }


    public <T> void put(String key, Object object) {
        put(new GString<T>(key), object);
    }

    public <T> void put(int key, Object object) {
        put(new GInteger<T>(key), object);
    }

    public <T> List<T> getList(String key) {
        return getList(new GString<T>(key));
    }

    public <T> T get(String key) {
        return get(new GString<T>(key));
    }

    public <T> List<T> getList(int key) {
        return getList(new GInteger<T>(key));
    }

    public <T> T get(int key) {
        return get(new GInteger<T>(key));
    }

    public void clear() {
        map.clear();
    }

    public static class GInteger<E1> {

        private int mInteger;

        public GInteger(Integer integer) {
            this.mInteger = integer;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + mInteger;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            GInteger<E1> other = (GInteger<E1>) obj;
            return mInteger == other.mInteger;
        }

    }

    public static class GString<E2> {
        private String mString;

        public GString(String string) {
            this.mString = string;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result
                    + ((mString == null) ? 0 : mString.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            GString<E2> other = (GString<E2>) obj;
            if (mString == null) {
                if (other.mString != null)
                    return false;
            } else if (!mString.equals(other.mString))
                return false;
            return true;
        }
    }

}
