/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

/**
 *
 * @author CAU_TU
 */
public interface Acceptable {

    public final String STU_ID_VALID = "^[CcDdHhSsQq][Ee]\\d{6}$";
    public final String NAME_VALID = "^[a-zA-Z\\s]{2,20}$";
    public final String PHONE_VALID = "^0\\d{9}";
    public final String INTEGER_VALID = "\\d+";
    public final String DOUBLE_VALID = "\\d+.?\\d*";
    public final String VNPT_VALID = "^(081|082|083|084|085|088|091|094)\\d{7}$";
    public final String VIETTEL_VALID = "^(032|033|034|035|036|037|038|039|086|096|097|098)\\d{7}$";
    public final String EMAIL_VALID = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9._%+-]+\\.[a-zA-Z]{2,7}$";
            
    /**
     * kiểm tra dữ liệu có trong data có phù hợp với mẫu pattern theo yêu cầu
     * không
     *
     * @param data
     * @param pattern
     * @return
     */
    public static boolean isValid(String data, String pattern) {
        return data.matches(pattern);
    }
}