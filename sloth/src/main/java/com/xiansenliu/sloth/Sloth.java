package com.xiansenliu.sloth;

import java.util.List;

/**
 * Author       xinliu
 * Date         4/29/17
 * Time         6:01 PM
 */

public final class Sloth {

    public static Request.RequestBuilder request(String... permissions) {
        return new Request.RequestBuilder(permissions);
    }


    public static Request.RequestBuilder request(List<String> permissions) {
        return request((String[]) permissions.toArray());
    }

    private Sloth() {
    }

}
