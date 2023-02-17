package com.f1soft.bankxp.minio.client.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author b.nox<binod.bhandari@f1soft.com>
 */
public class Util {

    public static byte[] readFully(InputStream stream) throws IOException {
        byte[] buffer = new byte[8192];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        int bytesRead;
        while ((bytesRead = stream.read(buffer)) != -1) {
            baos.write(buffer, 0, bytesRead);
        }
        return baos.toByteArray();
    }

    public static Map<String, String> initializeBucketAndFileName(String fileFullPath, String fileName) {
        String bucketName = fileFullPath;
        String[] paths = fileFullPath.split("\\/");
        if (paths.length > 1) {
            bucketName = paths[0];
            fileName = fileFullPath.substring(fileFullPath.indexOf("/") + 1) + "/" + fileName;
        }
        Map<String, String> map = new HashMap<>();
        map.put("bucketName", bucketName);
        map.put("fileName", fileName);
        return map;
    }
}
