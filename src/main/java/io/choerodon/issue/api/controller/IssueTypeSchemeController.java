package io.choerodon.issue.api.controller;

import io.choerodon.issue.api.dto.IssueTypeSchemeDTO;
import io.choerodon.issue.api.service.IssueTypeSchemeService;
import io.choerodon.issue.infra.utils.ParamUtils;
import io.choerodon.core.base.BaseController;
import io.choerodon.core.domain.Page;
import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.mybatis.pagehelper.annotation.SortDefault;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import io.choerodon.mybatis.pagehelper.domain.Sort;
import io.choerodon.swagger.annotation.CustomPageRequest;
import io.choerodon.swagger.annotation.Permission;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.Map;

/**
 * @author shinan.chen
 * @date 2018/8/10
 */
@RestController
@RequestMapping(value = "/v1/organizations/{organization_id}/issue_type_scheme")
public class IssueTypeSchemeController extends BaseController {

    @Autowired
    private IssueTypeSchemeService issueTypeSchemeService;

    @Permission(level = ResourceLevel.ORGANIZATION)
    @ApiOperation(value = "根据id查询问题类型方案")
    @GetMapping(value = "/{id}")
    public ResponseEntity<IssueTypeSchemeDTO> queryById(@PathVariable("organization_id") Long organizationId, @PathVariable("id") Long issueTypeSchemeId) {
        return new ResponseEntity<>(issueTypeSchemeService.queryById(organizationId, issueTypeSchemeId), HttpStatus.OK);
    }

    @Permission(level = ResourceLevel.ORGANIZATION)
    @ApiOperation(value = "创建问题类型方案")
    @PostMapping
    public ResponseEntity<IssueTypeSchemeDTO> create(@PathVariable("organization_id") Long organizationId, @RequestBody @Valid IssueTypeSchemeDTO issueTypeSchemeDTO) {
        return new ResponseEntity<>(issueTypeSchemeService.create(organizationId, issueTypeSchemeDTO), HttpStatus.OK);
    }

    @Permission(level = ResourceLevel.ORGANIZATION)
    @ApiOperation(value = "修改问题类型方案")
    @PutMapping(value = "/{id}")
    public ResponseEntity<IssueTypeSchemeDTO> update(@PathVariable("organization_id") Long organizationId, @PathVariable("id") Long issueTypeSchemeId,
                                                     @RequestBody @Valid IssueTypeSchemeDTO issueTypeSchemeDTO) {
        issueTypeSchemeDTO.setId(issueTypeSchemeId);
        issueTypeSchemeDTO.setOrganizationId(organizationId);
        return new ResponseEntity<>(issueTypeSchemeService.update(organizationId, issueTypeSchemeDTO), HttpStatus.OK);
    }

    @Permission(level = ResourceLevel.ORGANIZATION)
    @ApiOperation(value = "校验问题类型方案是否可以删除")
    @GetMapping(value = "/check_delete/{id}")
    public ResponseEntity<Map<String, Object>> checkDelete(@PathVariable("organization_id") Long organizationId, @PathVariable("id") Long issueTypeSchemeId) {
        return new ResponseEntity<>(issueTypeSchemeService.checkDelete(organizationId, issueTypeSchemeId), HttpStatus.OK);
    }

    @Permission(level = ResourceLevel.ORGANIZATION)
    @ApiOperation(value = "删除问题类型方案")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("organization_id") Long organizationId, @PathVariable("id") Long issueTypeSchemeId) {
        return new ResponseEntity<>(issueTypeSchemeService.delete(organizationId, issueTypeSchemeId), HttpStatus.OK);
    }

    @Permission(level = ResourceLevel.ORGANIZATION)
    @ApiOperation(value = "分页查询问题类型方案列表")
    @CustomPageRequest
    @GetMapping
    public ResponseEntity<Page<IssueTypeSchemeDTO>> pageQuery(@ApiIgnore
                                                              @SortDefault(value = "id", direction = Sort.Direction.DESC) PageRequest pageRequest,
                                                              @PathVariable("organization_id") Long organizationId,
                                                              @RequestParam(required = false) String name,
                                                              @RequestParam(required = false) String description,
                                                              @RequestParam(required = false) String[] param) {
        IssueTypeSchemeDTO issueTypeSchemeDTO = new IssueTypeSchemeDTO();
        issueTypeSchemeDTO.setOrganizationId(organizationId);
        issueTypeSchemeDTO.setName(name);
        issueTypeSchemeDTO.setDescription(description);
        return new ResponseEntity<>(issueTypeSchemeService.pageQuery(pageRequest, issueTypeSchemeDTO, ParamUtils.arrToStr(param)), HttpStatus.OK);
    }

    @Permission(level = ResourceLevel.ORGANIZATION)
    @ApiOperation(value = "校验问题类型名字是否未被使用")
    @GetMapping(value = "/check_name")
    public ResponseEntity<Boolean> checkName(@PathVariable("organization_id") Long organizationId,
                                             @RequestParam("name") String name,
                                             @RequestParam(value = "id", required = false) Long id) {
        return new ResponseEntity<>(issueTypeSchemeService.checkName(organizationId, name, id), HttpStatus.OK);
    }


}