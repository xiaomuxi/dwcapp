package com.network.library.bean.mine.response;

import com.network.library.bean.BaseEntity;

import java.util.List;

public class CarColorsEntity extends BaseEntity<List<CarColorsEntity.Data>>{

    public static class Data {

        /**
         * Key : 1
         * Value : 黑色
         */

        private String Key;
        private String Value;

        public String getKey() {
            return Key;
        }

        public void setKey(String Key) {
            this.Key = Key;
        }

        public String getValue() {
            return Value;
        }

        public void setValue(String Value) {
            this.Value = Value;
        }
    }
}
