package com.fengdu.constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum ClientTypeEnum {
	//用户类型（0.推广员，1.会员，2潜在用户）
	SPREAD ("0","推广员"),
	MEMBER ("1","会员"),
	POTENTIAL("2","潜在用户");

    private String    code;

    private String text;

    private ClientTypeEnum(String code, String text) {
        this.code = code;
        this.text = text;
    }

    public static ClientTypeEnum getEnumByCode(int code) {
        for (ClientTypeEnum an : ClientTypeEnum.values()) {
            if (an.getCode().equals(code))
                return an;
        }
        return null;
    }

    public static ClientTypeEnum getEnumByText(String typeName) {
        for (ClientTypeEnum an : ClientTypeEnum.values()) {
            if (an.getText().equals(typeName)) {
                return an;
            }
        }
        return null;
    }

    public String getCode() {
        return this.code;
    }

    public String getText() {
        return this.text;
    }

    public static List<Map<String, Object>> getListEnum() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> dto = null;
        for (ClientTypeEnum an : ClientTypeEnum.values()) {
            dto = new HashMap<String, Object>();
            dto.put("text", an.getText());
            dto.put("value", an.getCode());
            list.add(dto);
        }
        return list;
    }


    
}
