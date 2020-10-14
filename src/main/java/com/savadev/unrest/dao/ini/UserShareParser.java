package com.savadev.unrest.dao.ini;

import com.savadev.unrest.domain.share.UserShare;
import org.ini4j.Profile;

public class UserShareParser extends ShareParser<UserShare> {

    @Override
    public UserShare parse(Profile.Section section) {
        return UserShare.builder()
                .withName(getName(section))
                .withOriginalName(getOriginalName(section))
                .withComment(getComment(section))
                .withAllocator(getAllocator(section))
                .withSplitLevel(getSplitLevel(section))
                .withFloor(getFloor(section))
                .withInclude(getInclude(section))
                .withExclude(getExclude(section))
                .withUseCache(getUseCache(section))
                .withCopyOnWrite(getCopyOnWrite(section))
                .withFree(getFree(section))
                .withSize(getSize(section))
                .isEncrypted(isEncrypted(section))
                .build();
    }

}
