package com.bao.baocard.modules.openai.model.service;

import com.bao.baocard.infra.exception.CustomException;
import com.bao.baocard.infra.exception.ResponseCode;
import com.bao.baocard.infra.util.SecurityUtils;
import com.bao.baocard.modules.card.model.BenefitSummary;
import com.bao.baocard.modules.card.model.Card;
import com.bao.baocard.modules.card.model.CardBenefitParser;
import com.bao.baocard.modules.card.model.repository.CardRepository;
import com.bao.baocard.modules.openai.model.ChatGPTRequest;
import com.bao.baocard.modules.openai.model.ChatGPTResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OpenAiService {

    private final RestTemplate restTemplate;
    private final CardRepository cardRepository;

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiUrl;

    /**
     * OpenAi와 채팅하는 메서드
     * @param prompt
     * @return
     */
    public String chat(String prompt) {
        ChatGPTRequest request = new ChatGPTRequest(model, prompt);
        ChatGPTResponse response = restTemplate.postForObject(apiUrl, request, ChatGPTResponse.class);
        return response.getChoices().get(0).getMessage().getContent();
    }

    @Transactional(readOnly = true)
    public String recommendCard(String paymentPlace, int paymentAmount) {
        String prompt = "결제처, 결제금액, 보유 카드들의 혜택 정보를 입력으로 받아서 각 카드별로 결제처에 해당하는 혜택이 있다면 할인 금액을 계산합니다. " +
                "가장 큰 할인을 받을 수 있는 카드의 '카드번호', '카드이름', '혜택 정보', '할인 금액'을 JSON 형식으로 반환합니다. " +
                "결제처에 해당하는 카드의 혜택이 없거나, 결제처가 분야·브랜드명이 아니라면, 모든 value에 0을 넣어 반환합니다. " +
                "'카드번호' 는 해당 카드의 '카드 고유 번호',  '혜택 정보'는 결제처에 해당되는 혜택 정보 텍스트를 의미합니다.";

        List<Card> ownedCards = cardRepository.findCardsByMemberId(SecurityUtils.getCurrentMemberId());

        String userInput = "{\"결제처\": \"" + paymentPlace + "\", \"결제 금액\": " + paymentAmount + ", \"카드들의 혜택 정보\": [";
        for (Card ownedCard : ownedCards) {
            Long id = ownedCard.getId();
            String name = ownedCard.getName();
            List<BenefitSummary> benefits = CardBenefitParser.parseBenefits(ownedCard.getBenefits());
            userInput = userInput +"{\"카드 고유 번호”: \""+ id + "\", " + "\"카드 이름\" : \"" + name + "\", \"혜택 목록\": [ ";
            for(BenefitSummary benefit : benefits) {
                userInput += benefit.toString();
            }
            userInput = userInput.substring(0, userInput.length() - 1);
            userInput += "\" },";
        }

        ChatGPTRequest request = new ChatGPTRequest(model, prompt, userInput);
        ChatGPTResponse response = restTemplate.postForObject(apiUrl, request, ChatGPTResponse.class);

        if (response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
            throw new CustomException(ResponseCode.FAILED_TO_OPENAI);
        }

        String result = response.getChoices().get(0).getMessage().getContent();

        log.info(result);

        return result;
    }

}
