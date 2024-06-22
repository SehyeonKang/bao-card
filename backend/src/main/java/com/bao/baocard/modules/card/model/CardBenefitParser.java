package com.bao.baocard.modules.card.model;

import java.util.ArrayList;
import java.util.List;

public class CardBenefitParser {

    public static List<BenefitSummary> parseBenefits(String benefits) {
        List<BenefitSummary> benefitList = new ArrayList<>();
        String[] benefitItems = benefits.split(";");

        for (String benefit : benefitItems) {
            String[] parts = benefit.split(": ");
            if (parts.length == 2) {
                benefitList.add(new BenefitSummary(parts[0].trim(), parts[1].trim()));
            }
        }

        return benefitList;
    }
}
