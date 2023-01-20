package com.nikosera.repository.utils;

import java.io.File;

/**
 * @author Sauravi Thapa ON 2/24/21
 */
public class FileResourceUtils {

    public File convertResourcesFileIntoFile(String fileLocation){
        File file = new File(getClass().getClassLoader().getResource(fileLocation).getFile());
        return file;
    }
}
