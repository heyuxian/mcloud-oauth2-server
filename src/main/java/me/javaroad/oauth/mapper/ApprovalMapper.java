package me.javaroad.oauth.mapper;

import java.util.List;
import java.util.Set;
import me.javaroad.oauth.dto.request.ApprovalRequest;
import me.javaroad.oauth.dto.response.ApprovalResponse;
import me.javaroad.oauth.entity.Approval;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author heyx
 */
@Mapper(componentModel = "spring")
public interface ApprovalMapper {
    ApprovalMapper INSTANCE = Mappers.getMapper(ApprovalMapper.class);

    Approval mapRequestToEntity(ApprovalRequest approvalRequest);

    ApprovalResponse mapEntityToResponse(Approval approval);

    List<ApprovalResponse> mapEntityToResponse(List<Approval> approvals);

    Set<ApprovalResponse> mapEntityToResponse(Set<Approval> approvals);
}
