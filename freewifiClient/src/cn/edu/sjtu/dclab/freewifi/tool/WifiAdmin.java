package cn.edu.sjtu.dclab.freewifi.tool;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Yang on 2015/5/22.
 */
public class WifiAdmin {
    private final String WIFITAG = "TEST";
    private WifiManager wifiManager;
    private WifiInfo wifiInfo;
    private List<ScanResult> wifiScanResList;
    private List<WifiConfiguration> wifiCfgList;
    private WifiManager.WifiLock wifiLock;

    public WifiAdmin(Context context) {
        wifiManager = (WifiManager) context.getSystemService(context.WIFI_SERVICE);
        wifiInfo = wifiManager.getConnectionInfo();
    }

    public void openWifi() {
        if (!wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        }
    }

    public void wifiScan() {
        wifiManager.startScan();
        wifiScanResList = wifiManager.getScanResults();
        wifiCfgList = getWifiCfgList();
    }

    public List<ScanResult> getWifiScanResList() {
        return wifiScanResList;
    }

    public List<WifiConfiguration> getWifiCfgList() {
        wifiCfgList = wifiManager.getConfiguredNetworks();
        return wifiCfgList;
    }

    public void closeWifi() {
        if (wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(false);
        }
    }

    public int getWifiState() {
        return wifiManager.getWifiState();
    }

    public void createWifiLock() {
        wifiLock = wifiManager.createWifiLock(WIFITAG);
    }

    public void acquireWifiLock() {
        wifiLock.acquire();
    }

    public void releaseWifiLock() {
        if (wifiLock.isHeld()) {
            wifiLock.release();
        }
    }

    public String getMacAddress() {
        return (wifiInfo == null) ? "NULL" : wifiInfo.getMacAddress();
    }

    public int getIPAddress() {
        return (wifiInfo == null) ? 0 : wifiInfo.getIpAddress();
    }

    public String getBSSID() {
        return (wifiInfo == null) ? "NULL" : wifiInfo.getBSSID();
    }

    public int getNetworkId() {
        return (wifiInfo == null) ? 0 : wifiInfo.getNetworkId();
    }

    // 添加一个wifi网络配置
    public WifiConfiguration createWifiCfg(ScanResult res, String passwd) {
        if (isStored(res.BSSID) != null)
            return isStored(res.BSSID);

        WifiConfiguration wificfg = new WifiConfiguration();
        wificfg.allowedAuthAlgorithms.clear();
        wificfg.allowedGroupCiphers.clear();
        wificfg.allowedKeyManagement.clear();
        wificfg.allowedPairwiseCiphers.clear();
        wificfg.allowedProtocols.clear();

        wificfg.SSID = addQuote(res.SSID);
        wificfg.BSSID = res.BSSID;

        Pattern wpa = Pattern.compile(".WPA.*");
        Pattern wep = Pattern.compile(".WEP.*");

        // 判断加密类型
        if (wpa.matcher(res.capabilities).matches()) {
            Log.d("debug", "WIFICIPHER_WPA");
            //WPA/WPA2 Security
            wificfg.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
            wificfg.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
            wificfg.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
            wificfg.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
            wificfg.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
            wificfg.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
            wificfg.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);
            wificfg.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
            wificfg.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
            wificfg.preSharedKey = addQuote(passwd);
        } else if (wep.matcher(res.capabilities).matches()) {
            Log.d("debug", "WIFICIPHER_WEP");
            // WEP Security
            wificfg.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
            wificfg.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
            wificfg.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
            wificfg.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
            wificfg.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.SHARED);
            wificfg.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
            wificfg.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
            wificfg.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
            wificfg.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);
            if (getHexKey(passwd)) wificfg.wepKeys[0] = passwd;
            else wificfg.wepKeys[0] = addQuote(passwd);
            wificfg.wepTxKeyIndex = 0;
        } else {
            // WIFICIPHER_WPA
            Log.d("debug", "WIFICIPHER_WPA");
            wificfg.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
            wificfg.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
            wificfg.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
            wificfg.allowedAuthAlgorithms.clear();
            wificfg.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
            wificfg.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
            wificfg.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
            wificfg.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);
            wificfg.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
            wificfg.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
        }

        return wificfg;
    }

    public boolean addConnection(WifiConfiguration cfg) {
        WifiConfiguration wifiCfg = isStored(cfg.BSSID);
        if (wifiCfg != null) {
            // TODO
            return false;
        } else {
            int netId = wifiManager.addNetwork(cfg);
            if (netId == -1)
                Log.d("bug", "addNetwork failed");
            return wifiManager.enableNetwork(netId, true);
        }
    }

    public void connectWifi(int index) {
        if (index > wifiCfgList.size()) {
            return;
        }
        wifiManager.enableNetwork(wifiCfgList.get(index).networkId, true);
    }


    public boolean disconnect(int netId) {
        wifiManager.disableNetwork(netId);
        return wifiManager.disconnect();
    }

    // 根据BSSID判断该某个扫描到的结果是否已被保存到CfgList
    private WifiConfiguration isStored(String str) {
        wifiCfgList = getWifiCfgList();
        for (WifiConfiguration cfg : wifiCfgList) {
            if (cfg.BSSID.equals(str))
                return cfg;
        }
        return null;
    }

    public void cleanAll() {
        wifiCfgList = getWifiCfgList();
        for (WifiConfiguration wificfg : wifiCfgList) {
            wifiManager.removeNetwork(wificfg.networkId);
        }
    }

    public StringBuffer lookUpScan() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < wifiScanResList.size(); i++) {
            sb.append("Index_" + new Integer(i + 1).toString() + ":");
            sb.append((wifiScanResList.get(i)).toString()).append("\n");
        }
        return sb;
    }

    /**
     * WEP has two kinds of password, a hex value that specifies the key or
     * a character string used to generate the real hex. This checks what kind of
     * password has been supplied. The checks correspond to WEP40, WEP104 & WEP232
     *
     * @param s
     * @return
     */
    private static boolean getHexKey(String s) {
        if (s == null) {
            return false;
        }

        int len = s.length();
        if (len != 10 && len != 26 && len != 58) {
            return false;
        }

        for (int i = 0; i < len; ++i) {
            char c = s.charAt(i);
            if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F')) {
                continue;
            }
            return false;
        }
        return true;
    }

    /**
     * Please note the quotes. String should contain ssid and password in quotes
     * in WifiConfiguration
     * @param str
     * @return
     */
    private String addQuote(String str){
        String quo = "\"";
        return quo.concat(str).concat(quo);
    }
}
