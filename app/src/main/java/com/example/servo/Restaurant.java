package com.example.servo;

public class Restaurant {

        private int photo;
        private String name;

        public Restaurant(int photo, String name) {
            this.photo = photo;
            this.name = name;
        }

        public int getPhoto() {
            return photo;
        }

        public void setPhoto(int photo) {
            this.photo = photo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

}
