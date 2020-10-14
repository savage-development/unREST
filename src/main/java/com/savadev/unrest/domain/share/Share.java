package com.savadev.unrest.domain.share;

public interface Share {

    String getName();

    String getOriginalName();

    String getComment();

    String getAllocator();

    String getSplitLevel();

    String getFloor();

    String getInclude();

    String getExclude();

    String getUseCache();

    String getCopyOnWrite();

    Long getFree();

    Long getSize();

    Boolean isEncrypted();

}
