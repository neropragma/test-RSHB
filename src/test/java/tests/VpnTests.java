package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import base.BaseTest;
import helpers.EmulatorHelper;
import pages.vpn.VpnMainPage;

@Tag("Vpn")
public class VpnTests extends BaseTest {

    @BeforeAll
    static void setApp(){
        System.setProperty("appName", "Vpn");
        //EmulatorHelper.runShScript("src/test/resources/bash/install-xapk.sh");
        //EmulatorHelper.runShScript("src/test/resources/bash/setup-adv-hosts.sh");
    }

    @Test
    public void checkVpn(){
        new VpnMainPage()
                .clickOnConnectButton()
                .clickOnOkAlertButton()
                ;
        Assertions.assertTrue(true);
    }
}
