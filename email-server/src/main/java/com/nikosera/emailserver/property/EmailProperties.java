package com.nikosera.emailserver.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author Sauravi Thapa ON 2/15/21
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "mail")
public class EmailProperties implements Serializable {

    private String host;

    private String username;

    private String password;

    private int port;

    private String protocol;

    private Character enabled;
}
