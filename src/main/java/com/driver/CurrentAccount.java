package com.driver;

import java.util.Arrays;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception

        super(name,balance,5000.0);

    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception

        if(!validID(tradeLicenseId)){
            int n = tradeLicenseId.length();
            int[] freq = new int[26];

            for(int i=0;i<n;i++){
                freq[tradeLicenseId.charAt(i)-'A']++;
            }

            char max_char = getMax(freq);

            if(n%2==0){
                if(freq[max_char-'A'] > n/2) throw new Exception("Valid License can not be generated");
            }else {
                if(freq[max_char-'A']>(n/2+1)) throw new Exception("Valid License can not be generated");
            }

            char[] ans = new char[n];

            int index = 0;

            while(freq[max_char-'A']>0){
                ans[index]=max_char;
                index=index+2;
                freq[max_char-'A']--;
            }
            int i = 0 ;
            while(i<n){
                while(freq[i]>0){
                    if(index >= n){
                        index = 1;
                    }
                    ans[index]=(char)('A'+i);
                    index=index+2;
                    freq[i]--;
                }
                i++;
            }

            tradeLicenseId = Arrays.toString(ans);
        }
    }

    public char getMax(int[] freq){
        char max = 'A';
        int m = freq[0];

        for(int i=0;i<freq.length;i++){
            if(freq[i]>m){
                max = (char)('A'+i);
                m = freq[i];
            }
        }
        return max;
    }

    public boolean validID(String tradeLicenseId){
         int l = tradeLicenseId.length();
         for(int i=0;i<l-1;i++){
             if(tradeLicenseId.charAt(i)==tradeLicenseId.charAt(i+1)) return false;
         }
         return true;
    }

    public String getTradeLicenseId() {
        return tradeLicenseId;
    }

    public void setTradeLicenseId(String tradeLicenseId) {
        this.tradeLicenseId = tradeLicenseId;
    }
}
