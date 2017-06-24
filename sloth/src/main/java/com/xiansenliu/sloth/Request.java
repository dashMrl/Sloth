package com.xiansenliu.sloth;

import android.app.Activity;

/**
 * Author       xinliu
 * Date         6/24/17
 * Time         7:44 PM
 */

public class Request {
    private String[] mPermissions;
    private OnGrantedCallback mGrantedCallback;
    private OnDeniedCallback mDeniedCallback;
    private RationaleCallback mRationalCallback;
    private int mRequestCode;

    private Request(String[] permissions, OnGrantedCallback grantedCallback, OnDeniedCallback deniedCallback, RationaleCallback rationalCallback, int requestCode) {
        mPermissions = permissions;
        mGrantedCallback = grantedCallback;
        mDeniedCallback = deniedCallback;
        mRationalCallback = rationalCallback;
        this.mRequestCode = requestCode;
    }

    public void commit(Activity activity) {
        PuppetFragment fragment = Utils.getPuppetFragment(activity);
        fragment.request(this);
    }

    String[] getPermissions() {
        return mPermissions;
    }

    OnGrantedCallback getGrantedCallback() {
        return mGrantedCallback;
    }


    OnDeniedCallback getDeniedCallback() {
        return mDeniedCallback;
    }

    RationaleCallback getRationalCallback() {
        return mRationalCallback;
    }

    int getRequestCode() {
        return mRequestCode;
    }

    public static class RequestBuilder {
        private boolean isUsed = false;
        private String[] mPermissions;
        private OnGrantedCallback mGrantedCallback;
        private OnDeniedCallback mDeniedCallback;
        private RationaleCallback mRationaleCallback;
        private int mRequestCode;

        RequestBuilder(String[] permissions) {
            this.mPermissions = permissions;
        }

        public RequestBuilder onRational(RationaleCallback rationalCallback) {
            checkUsed(isUsed);
            mRationaleCallback = rationalCallback;
            return this;
        }

        public RequestBuilder afterGranted(OnGrantedCallback grantedCallback) {
            checkUsed(isUsed);
            this.mGrantedCallback = grantedCallback;
            return this;
        }

        public RequestBuilder requestCode(int requestCode) {
            checkUsed(isUsed);
            this.mRequestCode = requestCode;
            return this;
        }

        public Request afterDenied(OnDeniedCallback deniedCallback) {
            this.mDeniedCallback = deniedCallback;
            isUsed = true;
            return new Request(mPermissions, mGrantedCallback, mDeniedCallback, mRationaleCallback, mRequestCode);
        }

        private static void checkUsed(boolean isUsed) {
            if (isUsed) {
                try {
                    throw new Exception("request has already been executed");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
