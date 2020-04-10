package com.mygdx.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class IpChecker {

    public static String getIp() throws Exception {
        URL amazonUrl = new URL("http://checkip.amazonaws.com");
        URL myexternalIp = new URL("http://myexternalip.com/raw");
        BufferedReader in = null;
        String ip = "IP CANT BE RETRIEVED";

        try{
            in = new BufferedReader(new InputStreamReader(
                    amazonUrl.openStream()));
        }catch (Exception amazonException){
            try {
                in = new BufferedReader(new InputStreamReader(myexternalIp.openStream()));
            }catch (Exception myExternalIpException){
                myExternalIpException.printStackTrace();
            }
            amazonException.printStackTrace();
        }
        return in.readLine();
    }
}
