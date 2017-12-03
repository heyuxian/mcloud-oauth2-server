package me.javaroad.oauth.service;

import java.util.List;
import java.util.Objects;
import me.javaroad.oauth.dto.request.DeveloperInfoRequest;
import me.javaroad.oauth.dto.response.DeveloperInfoResponse;
import me.javaroad.oauth.entity.DeveloperInfo;
import me.javaroad.oauth.mapper.DeveloperInfoMapper;
import me.javaroad.oauth.repository.DeveloperInfoRepository;
import me.javaroad.common.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class DeveloperInfoService {

    private final DeveloperInfoRepository developerInfoRepository;
    private final DeveloperInfoMapper developerInfoMapper;

    @Autowired
    public DeveloperInfoService(DeveloperInfoRepository developerInfoRepository, DeveloperInfoMapper developerInfoMapper) {
        this.developerInfoRepository = developerInfoRepository;
        this.developerInfoMapper = developerInfoMapper;
    }

    public List<DeveloperInfoResponse> getAll() {
        List<DeveloperInfo> categories = developerInfoRepository.findAll();
        return developerInfoMapper.mapEntityToResponse(categories);
    }

    public DeveloperInfoResponse getResponse(String username) {
        return developerInfoMapper.mapEntityToResponse(getEntityByUsername(username));
    }

    private DeveloperInfo getEntityByUsername(String username) {
        return developerInfoRepository.findByUserUsername(username);
    }

    @Transactional
    public DeveloperInfoResponse create(DeveloperInfoRequest developerInfoRequest) {
        DeveloperInfo developerInfo = developerInfoMapper.mapRequestToEntity(developerInfoRequest);
        developerInfo = developerInfoRepository.save(developerInfo);
        return developerInfoMapper.mapEntityToResponse(developerInfo);
    }

    @Transactional
    public DeveloperInfoResponse modify(Long developerInfoId, DeveloperInfoRequest developerInfoRequest) {
        DeveloperInfo developerInfo = getNotNullEntity(developerInfoId);
        developerInfoMapper.updateEntityFromRequest(developerInfoRequest, developerInfo);
        developerInfo = developerInfoRepository.save(developerInfo);
        return developerInfoMapper.mapEntityToResponse(developerInfo);
    }

    @Transactional
    public void delete(Long developerInfoId) {
        DeveloperInfo developerInfo = getNotNullEntity(developerInfoId);
        developerInfoRepository.delete(developerInfo);
    }

    private DeveloperInfo getEntity(Long developerInfoId) {
        return developerInfoRepository.findOne(developerInfoId);
    }

    private DeveloperInfo getNotNullEntity(Long developerInfoId) {
        DeveloperInfo developerInfo = getEntity(developerInfoId);
        if (Objects.isNull(developerInfo)) {
            throw new DataNotFoundException("developerInfo[id=%s] not found", developerInfoId);
        }
        return developerInfo;
    }

}
