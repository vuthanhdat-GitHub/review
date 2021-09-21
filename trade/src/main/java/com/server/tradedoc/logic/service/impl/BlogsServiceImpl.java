package com.server.tradedoc.logic.service.impl;

import com.google.gson.Gson;
import com.server.tradedoc.logic.converter.BlogsConverter;
import com.server.tradedoc.logic.dto.BlogsDTO;
import com.server.tradedoc.logic.dto.reponse.CountResponse;
import com.server.tradedoc.logic.dto.reponse.CreatedResponse;
import com.server.tradedoc.logic.dto.reponse.FindAllBlogsResponse;
import com.server.tradedoc.logic.dto.reponse.UpdateResponse;
import com.server.tradedoc.logic.entity.BlogsEntity;
import com.server.tradedoc.logic.entity.FilesBlogEntity;
import com.server.tradedoc.logic.repository.BlogsRepository;
import com.server.tradedoc.logic.repository.FilesBlogRepository;
import com.server.tradedoc.logic.service.BlogsService;
import com.server.tradedoc.utils.CommonUtils;
import com.server.tradedoc.utils.FilesUtils;
import com.server.tradedoc.utils.error.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * BlogsServiceImpl
 *
 * @author DatDV
 */
@Service
public class BlogsServiceImpl implements BlogsService {

    @Autowired
    private BlogsRepository blogsRepository;

    @Autowired
    private BlogsConverter blogsConverter;

    @Autowired
    private FilesUtils filesUtils;

    @Autowired
    private Gson gson;

    @Autowired
    private FilesBlogRepository filesBlogRepository;

    /**
     * create
     *
     * @param data
     * @param avatar
     * @param blogFiles
     * @return CreatedResponse {com.server.tradedoc.logic.dto.reponse}
     */
    @Override
    @Transactional
    public CreatedResponse create(String data, MultipartFile avatar, List<MultipartFile> blogFiles) {
        BlogsDTO blogsDTO = gson.fromJson(data, BlogsDTO.class);
        CreatedResponse response = new CreatedResponse();
        if (avatar == null || avatar.isEmpty()) {
            throw new CustomException("avatar empty", CommonUtils.putError("avatar", "ERR_0034"));
        }
        if (blogsDTO.getTitle().equals("")) {
            throw new CustomException("title empty", CommonUtils.putError("blogsDTO", "ERR_0034"));
        }
        if (blogsDTO.getSubTitle().equals("")) {
            throw new CustomException("sub title empty", CommonUtils.putError("blogsDTO", "ERR_0034"));
        }
        if (blogsDTO.getDescription().equals("")) {
            throw new CustomException("descriptio empty", CommonUtils.putError("blogsDTO", "ERR_0034"));
        }
        BlogsEntity blogsEntity = blogsConverter.toEntity(blogsDTO);
        blogsEntity.setAvatar(filesUtils.save(avatar, "/avatar_blogs/", filesUtils.generateFileName(avatar.getOriginalFilename())));
        blogsEntity.setCreatedDate(new Date(System.currentTimeMillis()));
        blogsEntity.setModifiedDate(new Date(System.currentTimeMillis()));
        BlogsEntity blogAfterInsert = blogsRepository.save(blogsEntity);
        if (!blogFiles.get(0).isEmpty()) {
            for (MultipartFile multipartFile : blogFiles) {
                FilesBlogEntity filesBlogEntity = new FilesBlogEntity();
                String nameFile = filesUtils.generateFileName(multipartFile.getOriginalFilename());
                filesBlogEntity.setName(nameFile);
                filesBlogEntity.setPathFile(filesUtils.save(multipartFile , "/files_blog/" , nameFile));
                filesBlogEntity.setBlogs(blogAfterInsert);
                filesBlogRepository.save(filesBlogEntity);
            }
        }
        response.setIdInserted(blogAfterInsert.getId());
        return response;
    }

    /**
     * update
     *
     * @param data
     * @param avatar
     * @param blogFiles
     * @return UpdateResponse {com.server.tradedoc.logic.dto.reponse}
     */
    @Override
    @Transactional
    public UpdateResponse update(String data, MultipartFile avatar, List<MultipartFile> blogFiles) {
        BlogsDTO blogsDTO = gson.fromJson(data, BlogsDTO.class);
        UpdateResponse response = new UpdateResponse();
        BlogsEntity blogsEntity = blogsRepository.findById(blogsDTO.getId()).get();
        blogsEntity.setModifiedDate(new Date(System.currentTimeMillis()));
        blogsEntity.setTitle(blogsDTO.getTitle());
        blogsEntity.setSubTitle(blogsDTO.getSubTitle());
        blogsEntity.setDescription(blogsDTO.getDescription());
        if (avatar != null && !avatar.isEmpty()) {
            blogsEntity.setAvatar(filesUtils.save(avatar , "/avatar_blogs/" , filesUtils.generateFileName(avatar.getOriginalFilename())));
        }
        blogsEntity = blogsRepository.save(blogsEntity);
        if (!blogFiles.isEmpty() && !blogFiles.get(0).isEmpty()) {
            filesBlogRepository.deleteByBlogs(blogsEntity);
            for (MultipartFile multipartFile : blogFiles) {
                FilesBlogEntity filesBlogEntity = new FilesBlogEntity();
                String nameFile = filesUtils.generateFileName(multipartFile.getOriginalFilename());
                filesBlogEntity.setName(nameFile);
                filesBlogEntity.setPathFile(filesUtils.save(multipartFile , "/files_blog/" , nameFile));
                filesBlogEntity.setBlogs(blogsEntity);
                filesBlogRepository.save(filesBlogEntity);
            }
        }

        response.setIdUpdated(blogsEntity.getId());
        return response;
    }

    /**
     * delete : delete
     *
     * @param ids
     * @return List Long : list id after deleted
     */
    @Override
    @Transactional
    public List<Long> delete(List<Long> ids) {
        if (!ids.isEmpty()) {
            for (Long id : ids) {
                BlogsEntity blogsEntity = blogsRepository.findById(id).get();
                filesBlogRepository.deleteByBlogs(blogsEntity);
                blogsRepository.deleteById(id);
            }
        }
        return ids;
    }

    /**
     * findAll
     *
     * @param pageable
     * @return FindAllBlogsResponse {com.server.tradedoc.logic.dto.reponse}
     * @throws URISyntaxException
     */
    @Override
    public FindAllBlogsResponse findAll(Pageable pageable) throws URISyntaxException {
        FindAllBlogsResponse response = new FindAllBlogsResponse();
        List<BlogsEntity> entities = blogsRepository.findAll(pageable).getContent();
        List<BlogsDTO> result = new ArrayList<>();
        for (BlogsEntity blogsEntity : entities) {
            blogsEntity.setAvatar(filesUtils.genFilePath(blogsEntity.getAvatar()));
            result.add(blogsConverter.toDto(blogsEntity));
        }
        response.setBlogs(result);
        response.setCountItem(Long.parseLong(String.valueOf(blogsRepository.findAll().size())));
        return response;
    }

    /**
     * count
     *
     * @return CountResponse {com.server.tradedoc.logic.dto.reponse}
     */
    @Override
    public CountResponse count() {
        CountResponse response = new CountResponse();
        response.setCountItem(Long.parseLong(String.valueOf(blogsRepository.findAll().size())));
        return response;
    }

    /**
     * findOne
     *
     * @param id
     * @return
     */
    @Override
    public BlogsDTO findOne(Long id) throws URISyntaxException {
        BlogsDTO result = blogsConverter.toDto(blogsRepository.findById(id).get());
        result.setAvatar(filesUtils.genFilePath(result.getAvatar()));
        return result;
    }

    /**
     * getFileUsingIdFileBlog
     *
     * @param idFileBlog
     * @return File {File.io}
     */
    @Override
    public File getFileUsingIdFileBlog(Long idFileBlog) {
        String pathFileInDB = filesBlogRepository.findById(idFileBlog).get().getPathFile();
        return filesUtils.getFileFormDir(pathFileInDB);
    }
}
