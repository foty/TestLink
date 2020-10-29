package com.example.testlink.utils

//import com.tencent.mmkv.MMKV


class MmkvUtils private constructor() {
//    companion object {
//        /**
//         * 保存数据的方法，需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
//         *
//         * @param key       key
//         * @param content    object
//         */
//        fun put(key: String, content: Any?) {
//            val mmkv: MMKV = MMKV.defaultMMKV()
//            when (content) {
//                is String -> mmkv.encode(key, content)
//                is Int -> mmkv.encode(key, content)
//                is Boolean -> mmkv.encode(key, content)
//                is Float -> mmkv.encode(key, content)
//                is Long -> mmkv.encode(key, content)
//                else -> mmkv.encode(key, content.toString())
//            }
//        }
//
//        /**
//         * 得到保存数据的方法，根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
//         *
//         * @param key           key
//         * @param defaultObject defaultObject
//         */
//        @Suppress("UNCHECKED_CAST", "IMPLICIT_CAST_TO_ANY")
//        operator fun <T> get(key: String, defaultObject: T): T {
//            val mmkv: MMKV = MMKV.defaultMMKV()
//            return when (defaultObject) {
//                is String -> mmkv.decodeString(key, defaultObject)
//                is Int -> mmkv.decodeInt(key, defaultObject)
//                is Boolean -> mmkv.decodeBool(key, defaultObject)
//                is Float -> mmkv.decodeFloat(key, defaultObject)
//                is Long -> mmkv.decodeLong(key, defaultObject)
//                else -> defaultObject
//            } as T
//        }
//
//
//        /**
//         * 移除某个key值已经对应的值
//         *
//         * @param key       key
//         */
//        fun remove(key: String) {
//            val mmkv: MMKV = MMKV.defaultMMKV()
//            mmkv.removeValueForKey(key)
//        }
//
//        /**
//         * 清除所有数据
//         *
//         */
//        fun clear() {
//            val mmkv: MMKV = MMKV.defaultMMKV()
//            mmkv.clearAll()
//        }
//
//        /**
//         * 查询某个key是否已经存在
//         *
//         * @param key       key
//         */
//        fun contains(key: String): Boolean {
//            val mmkv: MMKV = MMKV.defaultMMKV()
//            return mmkv.contains(key)
//        }
//    }


}

