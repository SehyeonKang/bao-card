package com.bao.baocard.modules.openai.controller;

import com.bao.baocard.modules.openai.model.service.OpenAiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/openai")
public class OpenAiController {

    private final OpenAiService openAiService;

    @GetMapping("/chat")
    public String chat(@RequestParam(name = "prompt") String prompt) {
        return openAiService.chat(prompt);
    }

    @GetMapping("/recommend-card")
    public String recommendCard(@RequestParam(name = "place") String place,
                                @RequestParam(name = "amount") int amount) {
        return openAiService.recommendCard(place, amount);
    }
}
