package com.zk.shortlink.admin.toolkit;

import java.security.SecureRandom;

/**
 * 分组ID生成工具类
 */
public class RandomIdGenerator {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int ID_LENGTH = 6;

    private static final SecureRandom secureRandom = new SecureRandom();

    /**
     * 生成6位字母和数字组成的随机ID
     *
     * @return 生成的随机ID
     */
    public static String generateRandomId() {
        StringBuilder stringBuilder = new StringBuilder(ID_LENGTH);

        for (int i = 0; i < ID_LENGTH; i++) {
            int randomIndex = secureRandom.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            stringBuilder.append(randomChar);
        }

        return stringBuilder.toString();
    }
}
