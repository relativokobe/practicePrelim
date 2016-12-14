package com.example.asus.lrucachepracticewithdatabase;

/**
 * Created by asus on 12/12/2016.
 */

import android.support.v4.util.LruCache;

public class Cache {
    private static Cache instance;
    private LruCache<Object,Object> lru;
    public int size;


    private Cache(){
        lru = new LruCache<Object, Object>(8388608);
    }
    public static Cache getInstance(){
        if(instance == null){
            instance = new Cache();
        }
        return instance;
    }
    public LruCache<Object, Object> getLru(){
        return lru;
    }
}
