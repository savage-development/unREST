package com.savadev.unrest.controller.share;

import com.savadev.unrest.domain.share.Share;

import java.util.Set;

public class GetSharesResponse {

    private final Set<Share> shares;

    public GetSharesResponse(Set<Share> shares) {
        this.shares = shares;
    }

    public Set<Share> getShares() {
        return shares;
    }
}
