package me.javaroad.oauth.service;

import java.util.List;
import java.util.Set;
import me.javaroad.oauth.dto.request.ApprovalRequest;
import me.javaroad.oauth.dto.response.ApprovalResponse;
import me.javaroad.oauth.entity.Approval;
import me.javaroad.oauth.mapper.ApprovalMapper;
import me.javaroad.oauth.repository.ApprovalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author heyx
 */
@Service
public class ApprovalService {

    private final ApprovalRepository approvalRepository;
    private final ApprovalMapper mapper;

    @Autowired
    public ApprovalService(ApprovalRepository approvalRepository, ApprovalMapper mapper) {
        this.approvalRepository = approvalRepository;
        this.mapper = mapper;
    }

    public ApprovalResponse createApproval(ApprovalRequest approvalRequest) {
        Approval approval = mapper.mapRequestToEntity(approvalRequest);
        approval = approvalRepository.save(approval);
        return mapper.mapEntityToResponse(approval);
    }

    public Approval getApproval(Long approvalId) {
        return approvalRepository.findOne(approvalId);
    }

    public void deleteApproval(Long approvalId) {
        approvalRepository.delete(approvalId);
    }

    public Set<Approval> getApprovalByIds(Set<Long> autoApproveIds) {
        return approvalRepository.findByIdIn(autoApproveIds);
    }

    public List<ApprovalResponse> getAll() {
        List<Approval> approvals = approvalRepository.findAll();
        return mapper.mapEntityToResponse(approvals);
    }
}
