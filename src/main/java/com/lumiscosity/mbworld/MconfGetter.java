package com.lumiscosity.mbworld;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class MconfGetter {
    // mconf - mindless config
    // ---
    // every line is either:
    // - a key=value pair
    // - # a comment
    // the only type is string
    // in case of multiple = signs, the first one is the separator
    // in case of multiple keys with the same name, the first one is used
    // in case of failure, default to "false"; you may implement error handling with an optional arg

    public static String get(File file, String key) {
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();

            while (line != null) {
                if (!line.startsWith("#")) {
                    String[] split = line.split("=", 2);
                    if (Objects.equals(split[0], key)) {
                        reader.close();
                        return split[1];
                    }
                }
                line = reader.readLine();
            }

            reader.close();

            return "false";
        } catch (IOException e) {
            return "false";
        }
    }

}