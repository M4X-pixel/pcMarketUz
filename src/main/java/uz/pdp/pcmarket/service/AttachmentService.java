package uz.pdp.pcmarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.pcmarket.entity.Attachment;
import uz.pdp.pcmarket.entity.AttachmentContent;
import uz.pdp.pcmarket.repository.AttachmentContentRepository;
import uz.pdp.pcmarket.repository.AttachmentRepository;
import uz.pdp.pcmarket.result.Result;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;

@Service
public class AttachmentService {
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    //Method for uploading
    public Result uploadImage(MultipartHttpServletRequest multipartHttpServletRequest) throws IOException {
        Iterator<String> fileNames = multipartHttpServletRequest.getFileNames();
        MultipartFile file = multipartHttpServletRequest.getFile(fileNames.next());

        //checking whether file exist or not in database
        boolean checkFile = attachmentRepository.existsByOriginalName(file.getOriginalFilename());
        if (checkFile) return new Result("File already exists", true);


        Attachment attachment = new Attachment();
        if (file == null) return new Result("Error loading picture", false);
        attachment.setOriginalName(file.getOriginalFilename());
        attachment.setContentType(file.getContentType());
        attachment.setSize((int) file.getSize());
        Attachment savedAttachment = attachmentRepository.save(attachment);

        //Working with attachment bytes
        AttachmentContent attachmentContent = new AttachmentContent();
        attachmentContent.setBytes(file.getBytes());
        attachmentContent.setAttachment(savedAttachment);
        attachmentContentRepository.save(attachmentContent);

        return new Result("File successfully uploaded= " + savedAttachment.getId(), true);

    }

    //method for downloading
    public void download(Integer id, HttpServletResponse response) throws IOException {
        Optional<Attachment> attachmentOptional = attachmentRepository.findById(id);
        if (!attachmentOptional.isPresent()) return;
        Attachment attachment = attachmentOptional.get();

        Optional<AttachmentContent> byAttachment_id = attachmentContentRepository.findByAttachment_Id(id);
        if (!byAttachment_id.isPresent()) return;
        AttachmentContent attachmentContent = byAttachment_id.get();
        response.setHeader("Content-Disposition", "attachment; filename=\"" + attachment.getOriginalName() + "\"");
        response.setContentType(attachment.getContentType());

        FileCopyUtils.copy(attachmentContent.getBytes(), response.getOutputStream());


    }


    //Deleting picture
    public Result delete(Integer id) {
        Optional<Attachment> attachmentOptional = attachmentRepository.findById(id);
        if (!attachmentOptional.isPresent()) return new Result("Attachment not found", false);
        Optional<AttachmentContent> byAttachment_id = attachmentContentRepository.findByAttachment_Id(id);
        if (!byAttachment_id.isPresent()) return new Result("Attachment content not found", false);
        AttachmentContent attachmentContent = byAttachment_id.get();
        attachmentContentRepository.deleteById(attachmentContent.getId());
        attachmentRepository.deleteById(id);
        return new Result("Attachment successfully deleted", true);
    }

    public Page<Attachment> getAll(Integer page) {
        Pageable pageable = PageRequest.of(page, 10);
        return attachmentRepository.findAll(pageable);
    }

    public Attachment getOneById(Integer id) {
        Optional<Attachment> attachmentOptional = attachmentRepository.findById(id);
        if (!attachmentOptional.isPresent()) return new Attachment();
        return attachmentOptional.get();
    }


}
