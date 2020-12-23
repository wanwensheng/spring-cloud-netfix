package com.wan.profiledemo;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2227324689
 * http://www.gupaoedu.com
 **/
public class ProfileService {

    private String profile;

    public ProfileService(String profile) {
        this.profile = profile;
    }

    @Override
    public String toString() {
        return "ProfileService{" +
                "profile='" + profile + '\'' +
                '}';
    }
}
