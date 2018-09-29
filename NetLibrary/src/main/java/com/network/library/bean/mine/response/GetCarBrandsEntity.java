package com.network.library.bean.mine.response;

import com.network.library.bean.BaseEntity;

import java.util.List;

public class GetCarBrandsEntity extends BaseEntity<List<GetCarBrandsEntity.Data>> {

    public static class Data {

        /**
         * Key : 0
         * Value : 全部
         * Logo :
         * Models : [{"Key":"CX00013","Value":"A6L"},{"Key":"CX00014","Value":"A5敞篷"},{"Key":"CX00018","Value":"Q5"},{"Key":"CX00019","Value":"Q7"},{"Key":"CX00020","Value":"TT"},{"Key":"CX00021","Value":"S5"},{"Key":"CX00025","Value":"E级"},{"Key":"CX00026","Value":"SLK敞篷"},{"Key":"T002","Value":"A8L"},{"Key":"T007","Value":"S级"}]
         */

        private int Key;
        private String Value;
        private String Logo;
        private List<ModelsBean> Models;

        public int getKey() {
            return Key;
        }

        public void setKey(int Key) {
            this.Key = Key;
        }

        public String getValue() {
            return Value;
        }

        public void setValue(String Value) {
            this.Value = Value;
        }

        public String getLogo() {
            return Logo;
        }

        public void setLogo(String Logo) {
            this.Logo = Logo;
        }

        public List<ModelsBean> getModels() {
            return Models;
        }

        public void setModels(List<ModelsBean> Models) {
            this.Models = Models;
        }

        public static class ModelsBean {
            /**
             * Key : CX00013
             * Value : A6L
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

            @Override
            public String toString() {
                return "ModelsBean{" +
                        "Key='" + Key + '\'' +
                        ", Value='" + Value + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "Data{" +
                    "Key=" + Key +
                    ", Value='" + Value + '\'' +
                    ", Logo='" + Logo + '\'' +
                    ", Models=" + Models +
                    '}';
        }
    }
}
