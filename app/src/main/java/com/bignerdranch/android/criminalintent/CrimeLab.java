package com.bignerdranch.android.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Woodinner on 15/12/6.
 * 单例模式的类
 * 定义：一个类有且仅有一个实例，并且自行实例化向整个系统提供。
 */
public class CrimeLab {
    private static CrimeLab sCrimeLab;              //一、单例模式的类定义中含有一个该类的静态私有对象

    private List<Crime> mCrimes;

    public static CrimeLab get(Context context) {   //三、该类提供了一个静态的共有的函数用于创建或获取它本身的静态私有对象
        if(sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    private CrimeLab(Context context) {             //二、该类只提供私有的构造函数
        mCrimes = new ArrayList<>();
    }

    public void addCrime(Crime c) {
        mCrimes.add(c);
    }

    public void removeCrime(Crime c) {
        mCrimes.remove(c);
    }

    public List<Crime> getCrimes() {
        return mCrimes;
    }

    public Crime getCrime(UUID id) {
        for (Crime crime : mCrimes) {
            if (crime.getId().equals(id)) {
                return crime;
            }
        }
        return null;
    }
}
