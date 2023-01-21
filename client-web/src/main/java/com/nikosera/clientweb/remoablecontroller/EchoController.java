package com.nikosera.clientweb.remoablecontroller;

import com.nikosera.common.constant.ApiConstant;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: Narayan Joshi  <narenzoshi@gmail.com>
 * Date: 4/12/2021
 * Time: 2:51 PM
 */

@RestController
@RequestMapping(path = ApiConstant.ECHO)
public class EchoController {

    @PreAuthorize("permitAll()")
    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String echo() {
        return "<div style=\"color: #18067a; position: absolute; left: 50%; top: 50%;\">\n" +
                "<img src=\"\"/>\n" +
                "  <div>\n" +
                "    <h1>\n" +
                "        Callgram Web Application is Running\n" +
                "    </h1>\n" +
                "  </div>\n" +
                "</div>";
    }
}
