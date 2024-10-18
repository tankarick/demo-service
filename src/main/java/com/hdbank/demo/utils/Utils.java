package com.hdbank.demo.utils;

import lombok.experimental.UtilityClass;

import java.io.Closeable;

@UtilityClass
public class Utils {

    public void close(Closeable closeable) {
        try {
            closeable.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
