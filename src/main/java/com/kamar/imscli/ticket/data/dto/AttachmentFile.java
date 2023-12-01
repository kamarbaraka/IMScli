package com.kamar.imscli.ticket.data.dto;

import com.vaadin.flow.component.upload.receivers.FileData;
import com.vaadin.flow.component.upload.receivers.MultiFileBuffer;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * attachment file data.
 * @author kamar baraka.*/

public record AttachmentFile(

        FileData fileData

) implements MultipartFile {
    @Override
    public String getName() {

        return fileData.getFile().getName();
    }

    @Override
    public String getOriginalFilename() {

        return fileData.getFileName();
    }

    @Override
    public String getContentType() {

        return fileData.getMimeType();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public long getSize() {

        return fileData.getFile().length();
    }

    @Override
    public byte[] getBytes() throws IOException {


        return getInputStream().readAllBytes();
    }

    @Override
    public InputStream getInputStream() throws IOException {

        File file = fileData.getFile();
        return new FileInputStream(file);
    }

    @Override
    public Resource getResource() {
        return MultipartFile.super.getResource();
    }

    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {

    }
}
