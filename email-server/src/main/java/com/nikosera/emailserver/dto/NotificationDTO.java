package com.nikosera.emailserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Sauravi Thapa ON 2/17/21
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDTO implements Serializable {

    private Long id;

    private String type;

    private String status;

    private Date recordedDate;

    private String notifiedTo;

    private String subject;

    private Date sentDate;

    private String templateName;

    private Character isPublished;
}
