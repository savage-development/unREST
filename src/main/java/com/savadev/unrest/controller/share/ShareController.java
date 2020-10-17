package com.savadev.unrest.controller.share;

import com.savadev.unrest.service.share.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/shares")
public class ShareController {

    private final ShareService shareService;

    @Autowired
    public ShareController(ShareService shareService) {
        this.shareService = shareService;
    }

    @GetMapping
    Mono<GetSharesResponse> getAllShares() {
        return shareService.getShares()
                .collect(Collectors.toSet())
                .map(GetSharesResponse::new);
    }

}
