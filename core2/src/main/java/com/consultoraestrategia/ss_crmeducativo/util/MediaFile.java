package com.consultoraestrategia.ss_crmeducativo.util;

import android.net.Uri;

/**
 * Created by @stevecampos on 19/09/2017.
 */

public class MediaFile {
    
    public Uri localUri;
    public String downloadUri;
    public String contentType;
    public int height;
    public int width;
    public int rotate;
    public int orientation;
    public String name;
    public long sizeBytes;

    public MediaFile(Uri localUri, String downloadUri, String contentType, int height, int width, int rotate, int orientation, String name, long sizeBytes) {
        this.localUri = localUri;
        this.downloadUri = downloadUri;
        this.contentType = contentType;
        this.height = height;
        this.width = width;
        this.rotate = rotate;
        this.orientation = orientation;
        this.name = name;
        this.sizeBytes = sizeBytes;
    }

    public Uri getLocalUri() {
        return localUri;
    }

    public void setLocalUri(Uri localUri) {
        this.localUri = localUri;
    }

    public String getDownloadUri() {
        return downloadUri;
    }

    public void setDownloadUri(String downloadUri) {
        this.downloadUri = downloadUri;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getRotate() {
        return rotate;
    }

    public void setRotate(int rotate) {
        this.rotate = rotate;
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSizeBytes() {
        return sizeBytes;
    }

    public void setSizeBytes(long sizeBytes) {
        this.sizeBytes = sizeBytes;
    }
}
