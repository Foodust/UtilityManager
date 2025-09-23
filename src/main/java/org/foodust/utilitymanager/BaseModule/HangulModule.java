package org.foodust.utilitymanager.BaseModule;

/**
 * 한글 관련 유틸리티 모듈
 * 한국어 조사(이/가, 을/를, 은/는, 으로/로) 처리 및 한글 검증 기능을 제공합니다.
 */
public class HangulModule {
    /**
     * 한국어 조사 타입 열거형
     */
    public enum Josa{
        /** 이/가 조사 */
        EI_GA,
        /** 을/를 조사 */
        EL_LL,
        /** 은/는 조사 */
        EN_NN,
        /** 으로/로 조사 */
        EU_RO
    }
    /**
     * 문자열에 적절한 조사를 붙여서 반환합니다.
     * 마지막 글자의 받침 유무에 따라 조사를 자동으로 선택합니다.
     * 
     * @param str 조사를 붙일 문자열
     * @param josa 사용할 조사 타입
     * @return 조사가 붙은 문자열
     */
    public String getJosa(String str, Josa josa) {
        String one = "";
        String two = "";
        switch (josa){
            case EI_GA -> {
                one = "이";
                two="가";
            }
            case EL_LL -> {
                one = "을";
                two="를";
            }
            case EN_NN -> {
                one = "은";
                two="는";
            }
            case EU_RO -> {
                one = "으로";
                two="로";
            }
        }
            try {
            return getJosa(str, one, two);
        } catch (Exception ignored) {
        }
        return String.valueOf(str);
    }

    /**
     * 문자열에 받침 유무에 따라 다른 조사를 붙여서 반환합니다.
     * 
     * @param str 조사를 붙일 문자열
     * @param firstVal 받침이 있을 때 사용할 조사
     * @param secondVal 받침이 없을 때 사용할 조사
     * @return 조사가 붙은 문자열
     */
    public String getJosa(String str, String firstVal, String secondVal) {
        try {
            char lastStr = str.charAt(str.length() - 1);
            // 한글 범위가 아닌 경우 원본 문자열 반환
            if (lastStr < 0xAC00 || lastStr > 0xD7A3) {
                return str;
            }
            int lastCharIndex = (lastStr - 0xAC00) % 28;
            // 종성 인덱스가 0보다 크면 받침이 있는 경우
            if (lastCharIndex > 0) {
                // 받침이 있는경우
                // '으로/로' 조사의 경우 'ㄹ' 받침일 때는 '로', 나머지는 '으로'
                if (firstVal.equals("으로") && lastCharIndex == 8) {
                    str += secondVal;
                } else {
                    str += firstVal;
                }
            } else {
                // 받침이 없는 경우
                str += secondVal;
            }
        } catch (Exception e) {
            // 예외 발생 시 원본 문자열 반환
        }
        return str;
    }

    /**
     * 문자가 한글인지 확인합니다.
     * 
     * @param c 확인할 문자
     * @return 한글이면 true, 아니면 false
     */
    public boolean isHangul(char c) {
        return c >= 0xAC00 && c <= 0xD7A3;
    }

    /**
     * 문자열이 모두 한글로만 구성되어 있는지 확인합니다.
     * 
     * @param str 확인할 문자열
     * @return 모두 한글이면 true, 아니면 false
     */
    public boolean isHangul(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (!isHangul(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 문자열의 마지막 글자가 받침을 가지고 있는지 확인합니다.
     * 
     * @param str 확인할 문자열
     * @return 받침이 있으면 true, 아니면 false
     */
    public boolean hasLastConsonant(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        char lastChar = str.charAt(str.length() - 1);
        if (!isHangul(lastChar)) {
            return false;
        }
        int lastCharIndex = (lastChar - 0xAC00) % 28;
        return lastCharIndex > 0;
    }

    /**
     * 문자열 끝에 붙은 조사를 제거합니다.
     * 일반적인 한국어 조사들을 자동으로 인식하여 제거합니다.
     * 
     * @param str 조사를 제거할 문자열
     * @return 조사가 제거된 문자열
     */
    public String removeJosa(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        
        // 일반적인 한국어 조사 목록 (길이가 긴 것부터 확인)
        String[] josaList = {"으로", "에서", "에게", "부터", "까지", "이", "가", "을", "를", "은", "는", "로", "와", "과", "도", "만", "에"};
        
        for (String josa : josaList) {
            if (str.endsWith(josa)) {
                return str.substring(0, str.length() - josa.length());
            }
        }
        return str;
    }

    /**
     * 문자열에 필요한 경우에만 조사를 추가합니다.
     * 이미 조사가 붙어있다면 추가하지 않습니다.
     * 
     * @param str 조사를 추가할 문자열
     * @param josa 추가할 조사 타입
     * @return 조사가 추가된 문자열 (이미 있으면 원본 반환)
     */
    public String addJosaIfNeeded(String str, Josa josa) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        
        String result = getJosa(str, josa);
        
        return result.equals(str) ? str : result;
    }
}
