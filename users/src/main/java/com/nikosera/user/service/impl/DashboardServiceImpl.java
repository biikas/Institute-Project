package com.nikosera.user.service.impl;

import com.nikosera.common.aop.MethodLogging;
import com.nikosera.common.builder.MsgBuilder;
import com.nikosera.common.builder.ResponseBuilder;
import com.nikosera.common.constant.MsgConstant;
import com.nikosera.common.dto.GenericResponse;
import com.nikosera.common.util.ApplicationUtil;
import com.nikosera.entity.ApplicationUser;
import com.nikosera.user.mapper.MenuDetailMapper;
import com.nikosera.user.response.dto.MenusPermissionsDTO;
import com.nikosera.user.service.DashboardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    public final ApplicationUtil applicationUtil;

    @MethodLogging
    @Override
    public GenericResponse menusAndPermissions() {
        ApplicationUser currentLoggedUser = applicationUtil.loggedInUser();

        MenusPermissionsDTO response = MenuDetailMapper.mapMenusPermissionsDTO(currentLoggedUser);

        return ResponseBuilder
                .buildSuccessMessage(response, MsgBuilder.successSingle(MsgConstant.Model.MENU));
    }
}
