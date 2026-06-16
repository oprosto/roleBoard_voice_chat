package com.roleplace.AIAssistant.controller;


import com.roleplace.AIAssistant.service.AudioPipelineService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/audio")
@Tag(name = "Audio Controller", description = "Управление аудио файлами")
public class AudioController {

    private final AudioPipelineService pipelineService;

    public AudioController(AudioPipelineService pipelineService) {
        this.pipelineService = pipelineService;
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadAudio(@RequestParam("file") MultipartFile file) {
        pipelineService.processAudio(file);
        return "Processing started";
    }
}