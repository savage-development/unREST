package com.savadev.unrest.domain.docker.network;

import java.util.Set;

public interface Network {

    Set<String> getLinks();

    Set<String> getAliases();

    String getNetworkId();

    String getEndpointId();

    String getGateway();

    String getIPAddress();

    Integer getIPPrefixLength();

}
