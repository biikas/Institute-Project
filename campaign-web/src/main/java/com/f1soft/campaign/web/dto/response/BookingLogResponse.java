package com.f1soft.campaign.web.dto.response;

import com.f1soft.campaign.common.dto.ModelBase;
import com.f1soft.campaign.web.dto.BookingLogDTO;
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
