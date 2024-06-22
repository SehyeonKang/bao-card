package com.bao.baocard.modules.card.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class BenefitSummary {

    private String benefitField;
    private String benefitContents;

    @Override
    public String toString() {
        return "\"" + benefitField + ": " + benefitContents + "\",";
    }

}
