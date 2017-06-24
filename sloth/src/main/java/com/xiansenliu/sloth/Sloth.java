package com.xiansenliu.sloth;

/**
 * Author       xinliu
 * Date         4/29/17
 * Time         6:01 PM
 */

public final class Sloth {

    public static Request.RequestBuilder request(String... permissions) {
        return new Request.RequestBuilder(permissions);
    }

    private Sloth() {
    }

}
