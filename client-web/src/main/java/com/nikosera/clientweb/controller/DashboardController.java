package com.nikosera.clientweb.controller;

import com.nikosera.common.constant.ApiConstant;
import com.nikosera.common.dto.GenericResponse;
import com.nikosera.user.service.DashboardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
@Slf4j
@RestController
@RequestMapping(path = ApiConstant.Web.DASHBOARDS)
@AllArgsConstructor
public class DashboardController {

    private final DashboardService service;

    @PreAuthorize("isAuthenticated()")
    @GetMapping(path = ApiConstant.Web.MENUS,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> menus() {
        GenericResponse genericResponse = service.menusAndPermissions();
        return ResponseEntity.ok(genericResponse);
    }

}
