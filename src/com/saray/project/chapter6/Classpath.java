package com.saray.project.chapter6;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;

public class Classpath {
//    ShowProperties
    public static void main(String[] args) throws IOException {
        File currentClass = new File(URLDecoder.decode(Classpath.class
                .getProtectionDomain()
                .getCodeSource()
                .getLocation()
                .getPath(), "UTF-8"));
        String jarDirectory = currentClass.getParent();
        System.out.println(jarDirectory);
    }
}
