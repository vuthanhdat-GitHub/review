package com.server.tradedoc.utils;

import com.server.tradedoc.config.exception.FileStorageException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * FilesUtils
 *
 * @author DatDV
 */
@Component
public class FilesUtils {

    @Value("${local.path.uploadfile}")
    private String root;

    @Value("${project.domain}")
    private String url;

    @Value("${directory.client.request.file}")
    private String directoryClientRequest;

    /**
     * generateFileName : generate file name using save to dir
     *
     * @param fileNameClient : name file client request
     * @return String {java.lang.String}
     */
    public String generateFileName(String fileNameClient) {
        String dateTimeNow = DateTimeUtils.getDateTimeNow("yyyyMMddHHmmssSSS");
        String[] splitString = fileNameClient.split("\\.");
        return StringUtils.substringBeforeLast(fileNameClient, ".") + "_" + dateTimeNow + "." + splitString[splitString.length - 1];
    }

    /**
     * genFilePath
     *
     * @param urlFile
     * @return
     * @throws URISyntaxException
     */
    public String genFilePath(String urlFile) throws URISyntaxException {
        return this.getDomainName() + directoryClientRequest + urlFile;
    }

    /**
     * getDomainName
     *
     * @return String {java.lang.String}
     * @throws URISyntaxException
     */
    public String getDomainName() throws URISyntaxException {
        URI uri = new URI(url);
        String domain = uri.toString();
        return domain.startsWith("www.") ? domain.substring(4) : domain;
    }

    /**
     * save : save file to dir
     *
     * @param file     : file use save to dir
     * @param path     : path use save file
     * @param fileName : file name
     * @return String {java.lang.String}
     */
    public String save(MultipartFile file, String path, String fileName) {
        //this.createFile();
        File fileMkdir = new File(root + path);

        if (!fileMkdir.exists()) {
            fileMkdir.mkdir();
        }

        Path rootCustomFileName = Paths.get(root + path);
        try {
            Files.copy(file.getInputStream(), rootCustomFileName.resolve(fileName));
        } catch (Exception e) {
            throw new FileStorageException("Could not store file " + path + ". Please try again!", e);
        }
        return path + fileName;
    }

    /**
     * delete from dir
     *
     * @param path : path file for delete
     * @return Boolean {java.lang.Boolean}
     */
    public Boolean delete(String path) {
        try {
            File file = new File(root + path);
            if (file.delete()) {
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new FileStorageException("Could not store file " + path + ". Please try again!", e);
        }
    }

    /**
     * getFileFormDir : get file form dir
     *
     * @param pathFile : path file use get file
     * @return File {java.io.File}
     */
    public File getFileFormDir(String pathFile) {
        String path = root + pathFile;
        return new File(path);
    }

    /*private void createFile() {
        File fileMkdirUsr = new File("/usr");
        if (!fileMkdirUsr.exists()) {
            fileMkdirUsr.mkdir();
        }
        File fileMkdirVar = new File("/usr/var");
        if (!fileMkdirVar.exists()) {
            fileMkdirVar.mkdir();
        }
    }*/
}