package com.college.campaign.web.dto.response;

import com.college.campaign.common.dto.ModelBase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author Prajwol Hada
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingLogResponse extends ModelBase {

    private List<BookingLogDTO> log;
}
