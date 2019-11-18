package com.itsol.mockup.services.impl;

import com.itsol.mockup.entity.IssueEntity;
import com.itsol.mockup.repository.IssueRepository;
import com.itsol.mockup.repository.IssueRepositoryCustom;
import com.itsol.mockup.services.IssueService;
import com.itsol.mockup.web.dto.BaseDTO;
import com.itsol.mockup.web.dto.issue.IssueDTO;
import com.itsol.mockup.web.dto.request.SearchUsersRequestDTO;
import com.itsol.mockup.web.dto.response.ArrayResultDTO;
import com.itsol.mockup.web.dto.response.BaseResultDTO;
import com.itsol.mockup.web.dto.response.SingleResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class IssueServiceImpl extends BaseService implements IssueService {

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private IssueRepositoryCustom issueRepositoryCustom;


    @Override
    public BaseResultDTO findAllIssue(Integer pageSize, Integer page) {
        logger.info("=== START FIND ALL ISSUE::");
        ArrayResultDTO arrayResultDTO = new ArrayResultDTO<>();
        List<IssueDTO> listIssue = new ArrayList<>();
        try{
            Page<IssueEntity> datas = issueRepository.findAll(PageRequest.of(page -1,pageSize));
            if (datas != null) {
                if(datas.getContent().size() > 0){
                    for (IssueEntity data: datas){
                        IssueDTO issueDTO = modelMapper.map(data,IssueDTO.class);
                        listIssue.add(issueDTO);
                    }
                }
                arrayResultDTO.setSuccess(listIssue,datas.getTotalElements(),datas.getTotalPages());
                logger.info("FIND ALL ISSUE WITH RESULT: "+ arrayResultDTO.getErrorCode());
            }
        }catch (Exception ex){
            arrayResultDTO.setFail(ex.getMessage());
            logger.error(ex.getMessage(), ex);

        }
        return arrayResultDTO;
    }



    @Override
    public BaseResultDTO addIssue(IssueDTO issueDTO) {
        logger.info("START ADD NEW ISSUE");
        SingleResultDTO singleResultDTO = new SingleResultDTO();
        try{
            IssueEntity issueEntity = modelMapper.map(issueDTO, IssueEntity.class);
            issueEntity = issueRepository.save(issueEntity);
            if(issueEntity.getIssueId() != null){
                singleResultDTO.setSuccess(issueEntity);
            }
            logger.info("ADD NEW ISSUE RESPONSE:: "+singleResultDTO.getErrorCode());
        }catch (Exception ex) {
            singleResultDTO.setFail(ex.getMessage());
            logger.error(ex.getMessage(), ex);
        }
        return singleResultDTO;
    }

    @Override
    public BaseResultDTO updateIssue(IssueDTO issueDTO) {
        logger.info("START UPDATE ISSUE");
        SingleResultDTO singleResultDTO = new SingleResultDTO();
        try{
            IssueEntity issueEntity = issueRepository.getIssueEntityByIssueId(issueDTO.getIssueId());
            if(issueEntity.getIssueId() != null){
                issueEntity.setIssueName(issueDTO.getIssueName());
                issueEntity.setCreatedDate(issueDTO.getCreatedDate());
                issueEntity.setModifiedDate(issueDTO.getModifiedDate());
                issueEntity.setStatus(issueDTO.getStatus());
                issueEntity = issueRepository.save(issueEntity);
                singleResultDTO.setSuccess(issueEntity);
            }
            logger.info("UPDATE ISSUE RESPONSE:: "+singleResultDTO.getErrorCode());
        }catch (Exception ex) {
            singleResultDTO.setFail(ex.getMessage());
            logger.error(ex.getMessage(), ex);
        }
        return singleResultDTO;
    }

    @Override
    public BaseResultDTO deleteIssue(Long id) {
        logger.info("START DELETE ISSUE");
        SingleResultDTO singleResultDTO = new SingleResultDTO();
        try{
            if (id != null){
                issueRepository.deleteById(id);
                singleResultDTO.setSuccess();
                logger.info("DELETE ISSUE RESPONSE:: "+ singleResultDTO.getErrorCode());
            }else {
                singleResultDTO.setFail("Fail");
            }
        }catch (Exception e){
            singleResultDTO.setFail(e.getMessage());
            logger.info(e.getMessage(),e);
        }
        return singleResultDTO;
    }

    @Override
    public BaseResultDTO getIssueById(Long id) {
        logger.info("START FIND ISSUE BY ID");
        SingleResultDTO singleResultDTO = new SingleResultDTO();
        try{
            if(id != null) {
                IssueEntity issueEntity = issueRepository.getIssueEntityByIssueId(id);
                if (issueEntity != null){
                    IssueDTO issueDTO = modelMapper.map(issueEntity,IssueDTO.class);
                    singleResultDTO.setSuccess(issueDTO);
                }
            }
            logger.info("FIND ISSUE BY ID RESPONE:: "+ singleResultDTO);
        }catch (Exception e){
            singleResultDTO.setFail("FAIL");
            logger.error(e.getMessage());
        }
        return singleResultDTO;
    }

    @Override
    public ArrayResultDTO findIssueByUserId(Integer pageSize, Integer page, Long id) {
        ArrayResultDTO arrayResultDTO = new ArrayResultDTO();
        logger.info("START FIND ISSUE BY USER ID::");
        try{
            Page<IssueDTO> data = issueRepositoryCustom.findIssueByUserId(pageSize,page,id);
            arrayResultDTO.setSuccess(data.getContent(), data.getTotalElements(),data.getTotalPages());
            logger.info("FIND ISSUE BY USER ID RESPONSE: "+arrayResultDTO.getErrorCode());
        }catch (Exception e){
            arrayResultDTO.setFail("FAIL");
            logger.error(e.getMessage());
        }
        return arrayResultDTO;
    }

    @Override
    public BaseResultDTO searchIssue(SearchUsersRequestDTO searchUsersRequestDTO) {
        ArrayResultDTO arrayResultDTO = new ArrayResultDTO();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String issueName = searchUsersRequestDTO.getIssueName().trim().toLowerCase()
                .replace("\\", "\\\\")
                .replaceAll("%", "\\%")
                .replaceAll("_", "\\_");
        List<IssueEntity> list;
        if (searchUsersRequestDTO.getUserName() != null){
            List<IssueEntity> issueEntityList = new ArrayList<>();
            List<IssueDTO> data = issueRepositoryCustom.findByUserId(searchUsersRequestDTO.getUserId());
            for(IssueDTO issueDTO: data){
                IssueEntity issueEntity = modelMapper.map(issueDTO, IssueEntity.class);
                issueEntityList.add(issueEntity);
            }
            list = issueEntityList;

        }else {
            list = issueRepository.findAll();
        }
        List result = new ArrayList();
        for(IssueEntity issueEntity : list){
            IssueDTO issueDTO = modelMapper.map(issueEntity,IssueDTO.class);
            String date = simpleDateFormat.format(issueDTO.getCreatedDate());
            if (searchUsersRequestDTO.getIssueName() != "" &&
                    searchUsersRequestDTO.getCreatedDate() != null &&
                    searchUsersRequestDTO.getProjectId() != 0){
                String createdDate = simpleDateFormat.format(searchUsersRequestDTO.getCreatedDate());
                if (issueDTO.getIssueName().contains(issueName) &&
                        createdDate.equals(date) && issueDTO.getProject().getProjectId().equals(searchUsersRequestDTO.getProjectId())
                ){
                    result.add(issueDTO);
                }
            }
            else if (searchUsersRequestDTO.getIssueName() == "" &&
                    searchUsersRequestDTO.getProjectId() != 0 &&
                    searchUsersRequestDTO.getCreatedDate() != null){
                String createdDate = simpleDateFormat.format(searchUsersRequestDTO.getCreatedDate());
                if (date.equals(createdDate) && issueDTO.getProject().getProjectId().equals(searchUsersRequestDTO.getProjectId()))
                {
                    result.add(issueDTO);
                }
            }
            else if (searchUsersRequestDTO.getIssueName() != "" &&
                    searchUsersRequestDTO.getProjectId() == 0 &&
                    searchUsersRequestDTO.getCreatedDate() != null) {
                String createdDate = simpleDateFormat.format(searchUsersRequestDTO.getCreatedDate());
                if ((date.equals(createdDate)) &&
                        issueDTO.getIssueName().contains(issueName)) {
                    result.add(issueDTO);
                }
            }
            else if (searchUsersRequestDTO.getIssueName() !="" &&
                    searchUsersRequestDTO.getProjectId() == 0 &&
                    searchUsersRequestDTO.getCreatedDate() == null){
                if (issueDTO.getIssueName().contains(issueName)){
                    result.add(issueDTO);
                }
            }
            else if (searchUsersRequestDTO.getIssueName() == "" &&
                    searchUsersRequestDTO.getProjectId() != 0 &&
                    searchUsersRequestDTO.getCreatedDate() == null){
                if (issueDTO.getProject().getProjectId().equals(searchUsersRequestDTO.getProjectId())){
                    result.add(issueDTO);
                }
            }else {
                String createdDate = simpleDateFormat.format(searchUsersRequestDTO.getCreatedDate());
                if (date.equals(createdDate)){
                    result.add(issueDTO);
                }
            }
            arrayResultDTO.setSuccess(result,1L,2);
        }
        return arrayResultDTO;
    }

}
