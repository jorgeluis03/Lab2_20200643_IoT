package com.example.lab2_20200643;

import com.google.gson.annotations.SerializedName;

public class UserProfile {
    @SerializedName("name")
    private Name name;

    @SerializedName("email")
    private String email;

    @SerializedName("login")
    private Login login;

    @SerializedName("picture")
    private Picture picture;

    public Picture getPicture() {
        return picture;
    }

    public Name getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Login getLogin() {
        return login;
    }

    //########################### CLASES NECESARIAS #################

    public class Name {
        @SerializedName("first")
        private String nombre;

        @SerializedName("last")
        private String apellido;

        public String getNombre() {
            return nombre;
        }

        public String getApellido() {
            return apellido;
        }
    }

    public class Login {
        @SerializedName("password")
        private String password;

        public String getPassword() {
            return password;
        }
    }

    public class Picture {
        @SerializedName("large")
        private String foto;

        public String getFoto() {
            return foto;
        }
    }
}
