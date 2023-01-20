package com.nikosera.common.util;

import java.io.File;

/**
 * @author Sauravi Thapa ON 2/16/21
 */
public class FileResourceUtils {
    public File convertResourcesFileIntoFile(String fileLocation){
        File file = new File(getClass().getClassLoader().getResource(fileLocation).getFile());
        return file;
    }
}
