package com.nikosera.common.constant;


import com.nikosera.common.dto.ImageConfig;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
public interface MinioBucketMap {

    enum Selector {
        USER("user");

        private String text;

        Selector(String text) {
            this.text = text;
        }

        public String getText() {
            return this.text;
        }

        public static Selector fromString(String text) {
            for (Selector b : Selector.values()) {
                if (b.text.equalsIgnoreCase(text)) {
                    return b;
                }
            }
            return null;
        }
    }

    Map<Selector, ImageConfig> BUCKET = Collections.unmodifiableMap(
            Stream.of(
                    new AbstractMap.SimpleEntry<>(Selector.USER, new ImageConfig("callgram/user", 7200))
            ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
}
